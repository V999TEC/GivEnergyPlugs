package uk.co.myzen.a_z;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import internal.InternalData;
import internal.InternalDataPoint;
import internal.InternalGridData;
import internal.InternalGridDataPoint;
import internal.InternalLogData;
import internal.InternalLogDataEvent;
import v1.V1BatteryData;
import v1.V1ChargeDischarge;
import v1.V1CommunicationDevice;
import v1.V1CommunicationDeviceData;
import v1.V1CommunicationDeviceDatum;
import v1.V1DataBooleanValue;
import v1.V1DataDescriptor;
import v1.V1DataIntegerValue;
import v1.V1DataMeter;
import v1.V1DataSettings;
import v1.V1DataStringValue;
import v1.V1DataSystem;
import v1.V1ImportExport;
import v1.V1Inverter;
import v1.V1Links;
import v1.V1Meters;
import v1.V1Notification;
import v1.V1SmartDevice;
import v1.V1SmartDeviceData;
import v1.V1SmartDevices;
import v1.V1SystemData;
import v1.V1TimeAndPower;

public class Icarus {

	@SuppressWarnings("unused")
	private static final String docsUrl = "https://api.givenergy.cloud/docs/api/v1";

	private static final String context = "\"context\": \"icarus\"";

	private static final String body = "{" + context + "}";

	private static final String baseUrl = "https://api.givenergy.cloud/v1";

	private static final String baseInternalUrl = "https://givenergy.cloud/internal-api";

	// example POST
	// https://givenergy.cloud/internal-api/paginate/inverter-register-record?page=1&itemsPerPage=15

	private static final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36";
	private static final String contentType = "application/json";
	private static final String accept = "application/json";

	private static final String DEFAULT_PROPERTY_FILENAME = "./givenergy.properties";

	private static final String KEY_ZONE_ID = "zone.id";
	private static final String DEFAULT_ZONE_ID_PROPERTY = "Europe/London";

	private static final String DEFAULT_PAGE_SIZE = "100";

	private static final DateTimeFormatter defaultDateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
	private static final DateTimeFormatter formatter24HourClock = DateTimeFormatter.ofPattern("HH:mm");
	private static final DateTimeFormatter formatterYYYYMMDD = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private static Integer pageSize = Integer.parseInt(DEFAULT_PAGE_SIZE);

	private static Properties properties;

	private static ObjectMapper mapper;

	private static String propertyFileName = DEFAULT_PROPERTY_FILENAME;

	private static int exitStatus = 0;

	private static boolean loadProperties(File externalPropertyFile) throws Exception {

		boolean external = false;

		properties = new Properties();

		InputStream is;

		if (null == externalPropertyFile || !externalPropertyFile.exists()) {

			ClassLoader cl = Thread.currentThread().getContextClassLoader();

			cl = ClassLoader.getSystemClassLoader();

			is = cl.getResourceAsStream("givenergy.properties");

		} else {

			is = new FileInputStream(externalPropertyFile);

			external = true;

		}

		properties.load(is);

		return external;
	}

	public static void main(String[] args) {

		mapper = new ObjectMapper();

		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);

		try {

			// Check for existence of a named property file in the current directory
			// if it exists, use it in preference to the built-in resource compiled into the
			// jar

			String alias = null;
			String p2 = null;
			String p3 = null;
			String p4 = null;

			OffsetDateTime from = null;
			OffsetDateTime to = null;

			if (args.length > 0) {

				propertyFileName = args[0].trim();

				// if there are no further parameter, just enumerate all the smart devices with
				// aliases defined in the property file

				if (args.length > 1) {

					// the optional 2nd parameter is the name of a specific alias defined in the
					// properties file

					alias = args[1].trim();

					if (args.length > 2) {

						if (null != alias && 0 != "inverter".compareTo(alias) && 0 != "notfication".compareTo(alias)) {

							// the optional 3rd parameter should be a 'from' date which represents the
							// oldest timestamp we want to filter on
							// if not specified assume the start of epoch (1970)

							p2 = args[2].trim();

							from = OffsetDateTime.parse(p2, defaultDateTimeFormatter);

							// the optional 4th parameter should be a 'to' date which represents the latest
							// timestamp we want to filter on
							// if not specified assume the time 'now'

							if (args.length > 3) {

								p3 = args[3].trim();

								to = OffsetDateTime.parse(p3, defaultDateTimeFormatter);

								// the optional 5th parameter is just to adjust performance
								// it will increase the number of page requests by adjusting pageSize

								if (args.length > 4) {

									p4 = args[4].trim();

									pageSize = Integer.parseInt(p4);
								}
							}
						}
					}

				}
			}

			File externalProperties = new File(propertyFileName);

			loadProperties(externalProperties);

			ZoneId ourZoneId = ZoneId.of(properties.getProperty(KEY_ZONE_ID, DEFAULT_ZONE_ID_PROPERTY).trim());

			if (null == alias) {

				for (V1SmartDevice smartDevice : getListOfSmartDevice()) {

					String rawAlias = smartDevice.getAlias();

					String key = rawAlias.replace(" ", "\\ ");

					System.out.println(key + "=" + smartDevice.getUuid());
				}

			} else {

				if (0 == "notification".compareTo(alias) || 0 == "inverter".compareTo(alias)) {

					if (0 == "notification".compareTo(alias)) {

						OffsetDateTime odt = OffsetDateTime.ofInstant(Instant.now(), ourZoneId);

						postV1Notification("The OffsetDateTime is " + odt.format(defaultDateTimeFormatter));

					} else if (0 == "inverter".compareTo(alias)) {

						inverter(args);
					}

					System.exit(exitStatus);
				}

				if (properties.containsKey(alias)) {

					smartDevice(alias, from, to, ourZoneId);

				} else {

					System.out.println("Unknown smart device " + alias);
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private static void smartDevice(String alias, OffsetDateTime from, OffsetDateTime to, ZoneId ourZoneId)
			throws MalformedURLException, IOException, InterruptedException {
		List<V1TimeAndPower> cacheNewestFirst = null;

		String uuid = properties.getProperty(alias);

		String cacheName = uuid + ".tmp";

		V1SmartDeviceData data = null;

		List<V1TimeAndPower> cacheOldestFirst = new ArrayList<V1TimeAndPower>();

		File fileCache = new File(cacheName);

		if (fileCache.exists()) {

			// if the file exists assume it has data starting at the oldest available
			// chronologically but it won't necessarily have the latest available data

			BufferedReader br = new BufferedReader(new FileReader(fileCache));

			String line = null;

			while (null != (line = br.readLine())) {

				String[] fields = line.split(" ");

				V1TimeAndPower entry = new V1TimeAndPower();

				entry.setTime(fields[0]);

				entry.setPower("null".equals(fields[1]) ? null : Float.valueOf(fields[1]));

				cacheOldestFirst.add(entry);
			}

			br.close();

			// find youngest entry

			V1TimeAndPower timeAndPowerYoungest = cacheOldestFirst.get(cacheOldestFirst.size() - 1);

			OffsetDateTime odtYoungest = OffsetDateTime.parse(timeAndPowerYoungest.getTime(), defaultDateTimeFormatter);

			// now via GE API get recent data page by page adding noting anything that is
			// newer than our existing cacheOldestFirst because that won't be in our disk
			// cache

			List<V1TimeAndPower> recent = new ArrayList<V1TimeAndPower>();

			boolean done = false;

			Integer page = 0;

			do {

				page++;

				V1SmartDeviceData sample = getV1SmartDeviceData(uuid, page, pageSize);

				List<V1TimeAndPower> testList = sample.getTimeAndPower();

				for (V1TimeAndPower test : testList) {

					OffsetDateTime odtTest = OffsetDateTime.parse(test.getTime(), defaultDateTimeFormatter);

					if (odtTest.isAfter(odtYoungest)) {

						recent.add(0, test); // put each successive older item at the start of list so the
						// latest remains in highest element

					} else {

						// we have found enough new entries to add to the cache

						done = true;
						break;
					}
				}

				if (sample.getMeta().getLastPage() == page) {

					done = true;
				}

			} while (!done);

			// update the cache with the recent data

			cacheOldestFirst.addAll(recent);

			// append to fileCache just the recent stuff

			FileWriter fw = new FileWriter(fileCache, true);

			BufferedWriter bw = new BufferedWriter(fw);

			for (V1TimeAndPower entry : recent) {

				bw.append(entry.getTime());
				bw.append(" ");
				bw.append(String.valueOf(entry.getPower()));
				bw.append("\n");
			}

			bw.close();
			fw.close();

			// now reverse the cache to match the ordering returned by the GE API

			cacheNewestFirst = reverse(cacheOldestFirst);

		} else {

			// we don't have a cache file for this uuid
			// get all available datapoints
			// the GE API returns the data newest first/ oldest last

			cacheNewestFirst = processDeviceDataByAlias(alias, null, null, ourZoneId, null, true);

			data = new V1SmartDeviceData();

			data.setTimeAndPower(cacheNewestFirst);

			// we store the datapoint cache on disk with newest last deliberately
			// so that we an update it next time with an append mode

			cacheOldestFirst = reverse(cacheNewestFirst);

			FileWriter fw = new FileWriter(fileCache, true);

			BufferedWriter bw = new BufferedWriter(fw);

			for (V1TimeAndPower entry : cacheOldestFirst) {

				bw.append(entry.getTime());
				bw.append(" ");
				bw.append(String.valueOf(entry.getPower()));
				bw.append("\n");
			}

			bw.close();
			fw.close();
		}

		if (null == from && null == to) {

			// analyse today, yesterday, past 7 days, past 30 days

			OffsetDateTime now = OffsetDateTime.now();

			OffsetDateTime todayBegin = now.withHour(0).withMinute(0).withSecond(0).withNano(0);
			OffsetDateTime todayEnd = now.withHour(23).withMinute(59).withSecond(59).withNano(0);

			System.out.println("\nToday:");
			processDeviceDataByAlias(alias, todayBegin, todayEnd, ourZoneId, cacheNewestFirst, true);

			OffsetDateTime yesterdayBegin = todayBegin.minusDays(1);

			System.out.println("\nYesterday:");
			processDeviceDataByAlias(alias, yesterdayBegin, todayBegin, ourZoneId, cacheNewestFirst, true);

			OffsetDateTime pastSevenDaysBegin = todayBegin.minusDays(7);

			System.out.println("\nPast 7 days:");
			processDeviceDataByAlias(alias, pastSevenDaysBegin, todayBegin, ourZoneId, cacheNewestFirst, true);

			OffsetDateTime pastThirtyDaysBegin = todayBegin.minusDays(30);
			System.out.println("\nPast 30 days:");

			processDeviceDataByAlias(alias, pastThirtyDaysBegin, todayBegin, ourZoneId, cacheNewestFirst, true);

		} else {

			processDeviceDataByAlias(alias, from, to, ourZoneId, cacheNewestFirst, false);
		}

	}

	private static void inverter(String[] args) throws MalformedURLException, IOException, URISyntaxException {

		if (2 == args.length) {

			// default - no further parameters

			V1DataMeter dataMeter = getV1InverterMeter();

			renderInverterValue(dataMeter);

		} else if (args.length > 2) {

			if ("presets".equalsIgnoreCase(args[2])) {

				V1DataDescriptor v1Data = getV1InverterPresets(1, 50);

				renderInverterValue(v1Data);

			} else if ("system".equalsIgnoreCase(args[2])) {

				if ("internal".equalsIgnoreCase(args[3])) {

					InternalData internalDataDaily = null;

					LocalDateTime ldtToday = LocalDateTime.now();

					if (args.length == 4) {

						String dateToday = ldtToday.format(formatterYYYYMMDD);

						internalDataDaily = getInternalInverterDailyData(dateToday);

						renderInverterValue(internalDataDaily);

					} else {

						if (args.length > 4) {

							if ("presets".equalsIgnoreCase(args[4])) {

								Object json = getInternalInverterPresets();

								renderInverterValue(json);

							} else if ("log".equalsIgnoreCase(args[4])) {

								InternalLogData data = getInternalInverterLog(1, 3, 10);

								renderInverterValue(data);

							} else {

								LocalDateTime ldtSpecified = LocalDate.parse(args[4], formatterYYYYMMDD).atStartOfDay();

								if (ldtSpecified.getDayOfYear() > ldtToday.getDayOfYear()) {

									System.err.println("ERROR: Cannot use future date " + args[4]);

									exitStatus = 1;

								} else {

									internalDataDaily = getInternalInverterDailyData(args[4]);

									if (args.length > 5) {

										if ("grid".equalsIgnoreCase(args[5])) {

											InternalGridData internalGridData = filterGridOnly(internalDataDaily);

											if (args.length > 6 && "csv".equalsIgnoreCase(args[6])) {

												// render as csv

												boolean streamToFile = args.length > 7
														&& "file".equalsIgnoreCase(args[7]);

												renderGridDataAsCSV(internalGridData, streamToFile);

											} else { // render as json

												renderInverterValue(internalGridData);
											}

										} else {

											renderInverterValue(internalDataDaily);
										}
									}

									else {

										renderInverterValue(internalDataDaily);
									}
								}
							}

						} else {

							renderInverterValue(internalDataDaily);
						}
					}

				} else {

					V1SystemData dataSystemData = getV1InverterSystem().getData();

					if (args.length > 3) {

						if ("battery".equalsIgnoreCase(args[3])) {

							V1BatteryData batteryData = dataSystemData.getBattery();

							if (args.length > 4) {

								if ("percent".equalsIgnoreCase(args[4])) {

									System.out.println(batteryData.getPercent());

								} else if ("power".equalsIgnoreCase(args[4])) {

									System.out.println(batteryData.getPower());

								} else if ("temperature".equalsIgnoreCase(args[4])) {

									System.out.println(batteryData.getTemperature());
								}

							} else {

								renderInverterValue(batteryData);
							}

						} else if ("inverter".equalsIgnoreCase(args[3])) {

							V1Inverter inverter = dataSystemData.getInverter();

							if (args.length > 4) {

								if ("power".equalsIgnoreCase(args[4])) {

									System.out.println(inverter.getPower());

								} else if ("temperature".equalsIgnoreCase(args[4])) {

									System.out.println(inverter.getTemperature());

								} else if ("eps_power".equalsIgnoreCase(args[4])) {

									System.out.println(inverter.getEpsPower());

								} else if ("output_voltage".equalsIgnoreCase(args[4])) {

									System.out.println(inverter.getOutputVoltage());

								} else if ("output_frequency".equalsIgnoreCase(args[4])) {

									System.out.println(inverter.getOutputFrequency());

								} else {

									renderInverterValue(inverter);
								}

							} else {

								renderInverterValue(inverter);
							}

						} else {

							renderInverterValue(dataSystemData);
						}
					}
				}

			} else if ("meter".equalsIgnoreCase(args[2])) {

				V1DataMeter dataMeter = getV1InverterMeter();

				if (3 == args.length) {

					renderInverterValue(dataMeter);

				} else if (args.length > 3 && "today".equalsIgnoreCase(args[3])) {

					V1Meters today = dataMeter.getData().getToday();

					if (4 == args.length) {

						renderInverterValue(today);

					} else if (args.length > 4) {

						if ("ac_charge".equalsIgnoreCase(args[4])) {

							System.out.println(today.getAcCharge());

						} else if ("consumption".equalsIgnoreCase(args[4])) {

							System.out.println(today.getConsumption());

						} else if ("solar".equalsIgnoreCase(args[4])) {

							System.out.println(today.getSolar());

						} else if ("battery".equalsIgnoreCase(args[4])) {

							V1ChargeDischarge chargeDischarge = today.getBattery();

							if (args.length > 5) {

								if ("charge".equalsIgnoreCase(args[5])) {

									System.out.println(chargeDischarge.getCharge());
									chargeDischarge = null;

								} else if ("discharge".equalsIgnoreCase(args[5])) {

									System.out.println(chargeDischarge.getDischarge());
									chargeDischarge = null;
								}
							}

							if (null != chargeDischarge) {

								renderInverterValue(chargeDischarge);
							}

						} else if ("grid".equalsIgnoreCase(args[4])) {

							V1ImportExport grid = today.getGrid();

							if (args.length > 5) {

								if ("import".equalsIgnoreCase(args[5])) {

									System.out.println(grid.getImp());
									grid = null;

								} else if ("export".equalsIgnoreCase(args[5])) {

									System.out.println(grid.getExport());
									grid = null;
								}
							}

							if (null != grid) {

								renderInverterValue(grid);
							}
						}
					}
				}

			} else if ("settings".equalsIgnoreCase(args[2])) {

				if (args.length < 4) {

					V1DataSettings dataSettings = getV1InverterSettings();

					renderInverterValue(dataSettings);

				} else if ("devices".equalsIgnoreCase(args[3])) {

					V1CommunicationDeviceData data = getV1CommunicationDevices("");

					renderInverterValue(data);
				}

			} else if ("macro".equalsIgnoreCase(args[2])) {

				// inverter macro X [HH:mm HH:mm [ Soc ]]
				// if Soc not specified or it is "" or < 0 then no change
				// if Soc > 100 SoC set to 100

				// if no time(s) specified than assume 00:00 implied

				int soc = -1; // implies don't set value

				if (7 == args.length) { // assume a SoC specified or ""

					if (0 != "".compareTo(args[6])) {

						soc = Integer.parseInt(args[6]);

						if (soc > 100) {

							soc = 100;
						}
					}
				}

				if (args.length > 3) {

					if ("A".equalsIgnoreCase(args[3])) {

						// macro A HH:mm HH:mm 0-100
						// set start time, end time and SoC of timed battery charge

						postV1InverterSettingWriteString(64, args.length < 5 ? "00:00" : args[4]);
						// AC
						// Charge
						// 1
						// Start
						// Time or
						// midnight

						postV1InverterSettingWriteString(65, args.length < 6 ? "00:00" : args[5]);
						// AC
						// Charge
						// 1
						// End
						// Time or
						// midnight

						if (soc > -1) {

							postV1InverterSettingWrite(101, soc); // AC Charge 1 Upper
																	// SOC %
																	// Limit
						}

					} else if ("B".equalsIgnoreCase(args[3])) {

						// macro B HH:mm HH:mm 0-100
						// set start time, end time and SoC of timed battery charge

						// 28 & 29 or 102 & 103

						postV1InverterSettingWriteString(102, args.length < 5 ? "00:00" : args[4]);
						// AC
						// Charge
						// 2
						// Start
						// Time or
						// midnight

						postV1InverterSettingWriteString(103, args.length < 6 ? "00:00" : args[5]);
						// AC
						// Charge
						// 2
						// End
						// Time or
						// midnight

						if (soc > -1) {

							postV1InverterSettingWrite(104, soc); // AC Charge 2 Upper
																	// SOC %
																	// Limit
						}

					} else if ("C".equalsIgnoreCase(args[3])) {

						// macro B HH:mm HH:mm 0-100
						// set start time, end time and SoC of timed battery charge

						postV1InverterSettingWriteString(105, args.length < 5 ? "00:00" : args[4]); // AC
						// Charge
						// 3
						// Start
						// Time or
						// midnight

						postV1InverterSettingWriteString(106, args.length < 6 ? "00:00" : args[5]); // AC
						// Charge
						// 3
						// End
						// Time or
						// midnight

						if (soc > -1) {

							postV1InverterSettingWrite(107, soc); // AC Charge 3 Upper
																	// SOC %
																	// Limit
						}

					} else if ("D".equalsIgnoreCase(args[3])) {

						// macro B HH:mm HH:mm 0-100
						// set start time, end time and SoC of timed battery charge

						// 28 & 29 or 102 & 103

						postV1InverterSettingWriteString(108, args.length < 5 ? "00:00" : args[4]); // AC
						// Charge
						// 4
						// Start
						// Time or
						// midnight

						postV1InverterSettingWriteString(109, args.length < 6 ? "00:00" : args[5]); // AC
						// Charge
						// 4
						// End
						// Time or
						// midnight

						if (soc > -1) {

							postV1InverterSettingWrite(110, soc); // AC Charge 4 Upper
																	// SOC %
																	// Limit
						}

					} else if ("E".equalsIgnoreCase(args[3])) {

						// macro B HH:mm HH:mm 0-100
						// set start time, end time and SoC of timed battery charge

						postV1InverterSettingWriteString(111, args.length < 5 ? "00:00" : args[4]); // AC
						// Charge
						// 5
						// Start
						// Time or
						// midnight

						postV1InverterSettingWriteString(112, args.length < 6 ? "00:00" : args[5]); // AC
						// Charge
						// 5
						// End
						// Time or
						// midnight

						if (soc > -1) {

							postV1InverterSettingWrite(113, soc); // AC Charge 5 Upper
																	// SOC %
																	// Limit
						}
					} else if ("F".equalsIgnoreCase(args[3])) {

						// macro F HH:mm HH:mm 0-100
						// set start time, end time and SoC of timed battery charge

						postV1InverterSettingWriteString(114, args.length < 5 ? "00:00" : args[4]); // AC
						// Charge
						// 6
						// Start
						// Time or
						// midnight

						postV1InverterSettingWriteString(115, args.length < 6 ? "00:00" : args[5]); // AC
						// Charge
						// 6
						// End
						// Time or
						// midnight

						if (soc > -1) {

							postV1InverterSettingWrite(116, soc); // AC Charge 6 Upper
																	// SOC %
																	// Limit
						}
					} else if ("G".equalsIgnoreCase(args[3])) {

						// macro G HH:mm HH:mm 0-100
						// set start time, end time and SoC of timed battery charge

						postV1InverterSettingWriteString(117, args.length < 5 ? "00:00" : args[4]); // AC
						// Charge
						// 7
						// Start
						// Time or
						// midnight

						postV1InverterSettingWriteString(118, args.length < 6 ? "00:00" : args[5]); // AC
						// Charge
						// 7
						// End
						// Time or
						// midnight

						if (soc > -1) {

							postV1InverterSettingWrite(119, soc); // AC Charge 7 Upper
																	// SOC %
																	// Limit
						}
					} else if ("H".equalsIgnoreCase(args[3])) {

						// macro H HH:mm HH:mm 0-100
						// set start time, end time and SoC of timed battery charge

						postV1InverterSettingWriteString(120, args.length < 5 ? "00:00" : args[4]); // AC
						// Charge
						// 8
						// Start
						// Time or
						// midnight

						postV1InverterSettingWriteString(121, args.length < 6 ? "00:00" : args[5]); // AC
						// Charge
						// 8
						// End
						// Time or
						// midnight

						if (soc > -1) {

							postV1InverterSettingWrite(122, soc); // AC Charge 8 Upper
																	// SOC %
																	// Limit
						}
					} else if ("I".equalsIgnoreCase(args[3])) {

						// macro I HH:mm HH:mm 0-100
						// set start time, end time and SoC of timed battery charge

						postV1InverterSettingWriteString(123, args.length < 5 ? "00:00" : args[4]); // AC
						// Charge
						// 9
						// Start
						// Time or
						// midnight

						postV1InverterSettingWriteString(124, args.length < 6 ? "00:00" : args[5]); // AC
						// Charge
						// 9
						// End
						// Time or
						// midnight

						if (soc > -1) {

							postV1InverterSettingWrite(125, soc); // AC Charge 9 Upper
																	// SOC %
																	// Limit
						}
					} else if ("J".equalsIgnoreCase(args[3])) {

						// macro J HH:mm HH:mm 0-100
						// set start time, end time and SoC of timed battery charge

						postV1InverterSettingWriteString(126, args.length < 5 ? "00:00" : args[4]); // AC
						// Charge
						// 10
						// Start
						// Time or
						// midnight

						postV1InverterSettingWriteString(127, args.length < 6 ? "00:00" : args[5]); // AC
						// Charge
						// 10
						// End
						// Time or
						// midnight

						if (soc > -1) {

							postV1InverterSettingWrite(128, soc); // AC Charge 10 Upper
																	// SOC %
																	// Limit
						}
					}
				}

			} else if ("setting".equalsIgnoreCase(args[2])) {

				if (args.length > 4) {

					Integer id = Integer.parseInt(args[4]);

					if ("read".equalsIgnoreCase(args[3])) {

						System.out.println("read setting with id:" + id);

						Object value = null;

						switch (id) {

						case 17: // Enable AC Charge Upper % Limit
						case 24: // Enable Eco Mode
						case 56: // Enable DC Discharge
						case 66: // AC Charge Enable
						case 271: // Enable EPS
						case 380: // Enable Plant EMS Control

							value = postV1InverterSettingReadBoolean(id);
							break;

						case 28: // AC Charge 2 Start Time
						case 29: // AC Charge 2 End Time
						case 41: // DC Discharge 2 Start Time
						case 42: // DC Discharge 2 End Time
						case 53: // DC Discharge 1 Start Time
						case 54: // DC Discharge 1 End Time
						case 64: // AC Charge 1 Start Time
						case 65: // AC Charge 1 End Time
						case 102: // AC Charge 2 Start Time
						case 103: // AC Charge 2 End Time
						case 105: // AC Charge 3 Start Time
						case 106: // AC Charge 3 End Time
						case 108: // AC Charge 4 Start Time
						case 109: // AC Charge 4 End Time
						case 111: // AC Charge 5 Start Time
						case 112: // AC Charge 5 End Time
						case 114: // AC Charge 6 Start Time
						case 115: // AC Charge 6 End Time
						case 117: // AC Charge 7 Start Time
						case 118: // AC Charge 7 End Time
						case 120: // AC Charge 8 Start Time
						case 121: // AC Charge 8 End Time
						case 123: // AC Charge 9 Start Time
						case 124: // AC Charge 9 End Time
						case 126: // AC Charge 10 Start Time
						case 127: // AC Charge 10 End Time
						case 131: // DC Discharge 3 Start Time
						case 132: // DC Discharge 3 End Time
						case 134: // DC Discharge 4 Start Time
						case 135: // DC Discharge 4 End Time
						case 137: // DC Discharge 5 Start Time
						case 138: // DC Discharge 5 End Time
						case 140: // DC Discharge 6 Start Time
						case 141: // DC Discharge 6 End Time
						case 143: // DC Discharge 7 Start Time
						case 144: // DC Discharge 7 End Time
						case 146: // DC Discharge 8 Start Time
						case 147: // DC Discharge 8 End Time
						case 149: // DC Discharge 9 Start Time
						case 150: // DC Discharge 9 End Time
						case 152: // DC Discharge 10 Start Time
						case 153: // DC Discharge 10 End Time
						case 155: // Pause Battery Start Time
						case 156: // Pause Battery End Time
						case 265: // Export Power Priority
						case 384: // Discharge Start Time Slot 1
						case 385: // Discharge End Time Slot 1
						case 387: // Discharge Start Time Slot 2
						case 388: // Discharge End Time Slot 2
						case 390: // Discharge Start Time Slot 3
						case 391: // Discharge End Time Slot 3
						case 393: // Charge Start Time Slot 1
						case 394: // Charge End Time Slot 1
						case 396: // Charge Start Time Slot 2
						case 397: // Charge End Time Slot 2
						case 399: // Charge Start Time Slot 3
						case 400: // Charge End Time Slot 3

							value = postV1InverterSettingReadString(id);
							break;

						case 72: // Battery Charge Power
						case 73: // Battery Discharge Power
						default:
							value = postV1InverterSettingReadInteger(id);
							break;
						}

						System.out.println("response is: ");

						renderInverterValue(value);

					} else if ("write".equalsIgnoreCase(args[3]) && args.length > 5) {

						System.out.println("write setting with id:" + id + " new value:" + args[5]);

						Object value = null;

						switch (id) {

						case 17: // Enable AC Charge Upper % Limit
						case 24: // Enable Eco Mode
						case 56: // Enable DC Discharge
						case 66: // AC Charge Enable
						case 271: // Enable EPS
						case 380: // Enable Plant EMS Control

							Boolean bool = Boolean.parseBoolean(args[5]);

							value = postV1InverterSettingWriteBoolean(id, bool);
							break;

						case 28: // AC Charge 2 Start Time
						case 29: // AC Charge 2 End Time
						case 41: // DC Discharge 2 Start Time
						case 42: // DC Discharge 2 End Time
						case 53: // DC Discharge 1 Start Time
						case 54: // DC Discharge 1 End Time
						case 64: // AC Charge 1 Start Time
						case 65: // AC Charge 1 End Time
						case 102: // AC Charge 2 Start Time
						case 103: // AC Charge 2 End Time
						case 105: // AC Charge 3 Start Time
						case 106: // AC Charge 3 End Time
						case 108: // AC Charge 4 Start Time
						case 109: // AC Charge 4 End Time
						case 111: // AC Charge 5 Start Time
						case 112: // AC Charge 5 End Time
						case 114: // AC Charge 6 Start Time
						case 115: // AC Charge 6 End Time
						case 117: // AC Charge 7 Start Time
						case 118: // AC Charge 7 End Time
						case 120: // AC Charge 8 Start Time
						case 121: // AC Charge 8 End Time
						case 123: // AC Charge 9 Start Time
						case 124: // AC Charge 9 End Time
						case 126: // AC Charge 10 Start Time
						case 127: // AC Charge 10 End Time
						case 131: // DC Discharge 3 Start Time
						case 132: // DC Discharge 3 End Time
						case 134: // DC Discharge 4 Start Time
						case 135: // DC Discharge 4 End Time
						case 137: // DC Discharge 5 Start Time
						case 138: // DC Discharge 5 End Time
						case 140: // DC Discharge 6 Start Time
						case 141: // DC Discharge 6 End Time
						case 143: // DC Discharge 7 Start Time
						case 144: // DC Discharge 7 End Time
						case 146: // DC Discharge 8 Start Time
						case 147: // DC Discharge 8 End Time
						case 149: // DC Discharge 9 Start Time
						case 150: // DC Discharge 9 End Time
						case 152: // DC Discharge 10 Start Time
						case 153: // DC Discharge 10 End Time
						case 155: // Pause Battery Start Time
						case 156: // Pause Battery End Time
						case 265: // Export Power Priority
						case 384: // Discharge Start Time Slot 1
						case 385: // Discharge End Time Slot 1
						case 387: // Discharge Start Time Slot 2
						case 388: // Discharge End Time Slot 2
						case 390: // Discharge Start Time Slot 3
						case 391: // Discharge End Time Slot 3
						case 393: // Charge Start Time Slot 1
						case 394: // Charge End Time Slot 1
						case 396: // Charge Start Time Slot 2
						case 397: // Charge End Time Slot 2
						case 399: // Charge Start Time Slot 3
						case 400: // Charge End Time Slot 3

							String string = args[5];

							value = postV1InverterSettingWriteString(id, string);
							break;

						case 72: // Battery Charge Power
						case 73: // Battery Discharge Power

						case 101: // AC Charge 1 Upper SOC % Limit
						case 104: // AC Charge 2 Upper SOC % Limit
						case 107: // AC Charge 3 Upper SOC % Limit
						case 110: // AC Charge 4 Upper SOC % Limit
						case 113: // AC Charge 5 Upper SOC % Limit
						case 116: // AC Charge 6 Upper SOC % Limit
						case 119: // AC Charge 7 Upper SOC % Limit
						case 122: // AC Charge 8 Upper SOC % Limit
						case 125: // AC Charge 9 Upper SOC % Limit
						case 128: // AC Charge 10 Upper SOC % Limit

						default:
							Integer integer = Integer.parseInt(args[5]);

							value = postV1InverterSettingWrite(id, integer);
							break;
						}

						System.out.println("response is: ");

						renderInverterValue(value);
					}
				}
			}
		}
	}

	private static void renderGridDataAsCSV(InternalGridData internalGridData, boolean streamToFile) {

		PrintStream ps = System.out;

		File file = null;

		List<InternalGridDataPoint> data = internalGridData.getData();

		if (streamToFile) {

			InternalGridDataPoint recent = data.get(0);

			String recentDate = recent.getTime().substring(0, 10);

			file = new File(recentDate + ".csv");

			try {

				ps = new PrintStream(file);

			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
		}

		ps.println("Time, Timestamp, Id, Voltage, Frequency, Power");

		for (InternalGridDataPoint dataPoint : data) {

			StringBuffer sbLine = new StringBuffer();

			sbLine.append(dataPoint.getTime());

			sbLine.append(',');

			sbLine.append(dataPoint.getTimestamp());

			sbLine.append(',');

			sbLine.append(dataPoint.getId());

			sbLine.append(',');

			sbLine.append(dataPoint.getGridVoltage());

			sbLine.append(',');

			sbLine.append(dataPoint.getGridFrequency());

			sbLine.append(',');

			sbLine.append(dataPoint.getGridPower());

			ps.println(sbLine.toString());
		}

		if (streamToFile) {

			ps.flush();

			ps.close();
		}

	}

	private static InternalGridData filterGridOnly(InternalData internalDataDaily) {

		InternalGridData result = new InternalGridData();

		List<InternalDataPoint> internalDataPoints = internalDataDaily.getData();

		List<InternalGridDataPoint> gridDataPoints = new ArrayList<InternalGridDataPoint>(internalDataPoints.size());

		for (InternalDataPoint dataPoint : internalDataPoints) {

			InternalGridDataPoint gridDataPoint = new InternalGridDataPoint();

			gridDataPoint.setGridCurrent(dataPoint.getGridCurrent());

			gridDataPoint.setGridExport(dataPoint.getGridExport());

			gridDataPoint.setGridExportEnergyToday(dataPoint.getGridExportEnergyToday());

			gridDataPoint.setGridFrequency(dataPoint.getGridFrequency());

			gridDataPoint.setGridImport(dataPoint.getGridImport());

			gridDataPoint.setGridImportEnergyToday(dataPoint.getGridImportEnergyToday());

			gridDataPoint.setGridPower(dataPoint.getGridPower());

			gridDataPoint.setGridVoltage(dataPoint.getGridVoltage());

			gridDataPoint.setId(dataPoint.getId());

			gridDataPoint.setTimestamp(dataPoint.getTimestamp());

			gridDataPoint.setTime(dataPoint.getTime());

			gridDataPoints.add(gridDataPoint);
		}

		result.setData(gridDataPoints);

		return result;
	}

	private static V1CommunicationDeviceData getV1CommunicationDevices(String optionalSerialNumber)
			throws MalformedURLException, IOException {

		if (null == optionalSerialNumber) {

			optionalSerialNumber = "";
		}

		String json = getRequest(new URL(baseUrl + "/communication-device/" + optionalSerialNumber + "?page=1"),
				"inverter");

		V1CommunicationDeviceData result = null;

		if (null == json || 0 == json.trim().length()) {

			System.err.println("Error obtaining data. Check the token in property file!");

			result = new V1CommunicationDeviceData(); // empty object

		} else {

			if ("".equals(optionalSerialNumber)) {

				result = mapper.readValue(json, V1CommunicationDeviceData.class);

			} else {

				V1CommunicationDeviceDatum datum = mapper.readValue(json, V1CommunicationDeviceDatum.class);

				result = new V1CommunicationDeviceData(); // empty object

				List<V1CommunicationDevice> communicationDevices = new ArrayList<V1CommunicationDevice>();

				communicationDevices.add(datum.getData());

				result.setCommunicationDevices(communicationDevices);

			}
		}

		return result;
	}

	private static List<V1TimeAndPower> reverse(List<V1TimeAndPower> timeAndPowerList) {

		List<V1TimeAndPower> result = new ArrayList<V1TimeAndPower>();

		final int size = timeAndPowerList.size();

		for (int index = size - 1; index > -1; index--) {

			result.add(timeAndPowerList.get(index));
		}

		return result;
	}

	private static List<V1SmartDevice> getListOfSmartDevice()
			throws MalformedURLException, IOException, InterruptedException {

		List<V1SmartDevice> results = new ArrayList<V1SmartDevice>();

		Integer page = 0;

		String next = null;

		do {

			page++;

			V1SmartDevices devices = getV1SmartDevices(page, pageSize);

			results.addAll(devices.getSmartDeviceData());

			V1Links links = devices.getLinks();

			next = links.getNext();

			if (null != next) {

				Thread.sleep(250L); // have sympathy for the API host
			}

		} while (null != next);

		return results;
	}

	private static List<V1TimeAndPower> getListOfTimeAndPower(String uuid)
			throws MalformedURLException, IOException, InterruptedException {

		List<V1TimeAndPower> results = new ArrayList<V1TimeAndPower>();

		Integer page = 0;

		String next = null;

		do {

			page++;

			V1SmartDeviceData data = getV1SmartDeviceData(uuid, page, pageSize);

			results.addAll(data.getTimeAndPower());

			V1Links links = data.getLinks();

			next = links.getNext();

			if (null != next) {

				Thread.sleep(250L); // have sympathy for the API host
			}

		} while (null != next);

		return results;
	}

	private static List<V1TimeAndPower> processDeviceDataByAlias(String alias, OffsetDateTime from, OffsetDateTime to,
			ZoneId ourZoneId, List<V1TimeAndPower> cache, boolean summaryOnly)

			throws MalformedURLException, IOException, InterruptedException {

		if (null == from) {

			from = OffsetDateTime.ofInstant(Instant.EPOCH, ourZoneId);
		}

		if (null == to) {

			to = OffsetDateTime.now();
		}

		if (!summaryOnly) {

			System.out.println(propertyFileName + " \"" + alias + "\" " + from.toString() + " " + to.toString());
		}

		String uuid = properties.getProperty(alias);

		List<V1TimeAndPower> timeAndPowerList = null == cache ? getListOfTimeAndPower(uuid) : cache;

		long fromEpochSecond = from.toEpochSecond();
		long toEpochSecond = to.toEpochSecond();

		Long earliestEpochSecond = null;
		Long latestEpochSecond = null;

		final int size = timeAndPowerList.size();

		// times are most recent first, so access the list backwards
		// to get the oldest before the latest

		float accWattSeconds = 0f;

		OffsetDateTime timestamp = null;

		OffsetDateTime nextTimestamp = null;

		long epochSecond = 0L;

		long accSeconds = 0L;

		for (int index = size - 1; index > -1; index--) {

			V1TimeAndPower timeAndPower = timeAndPowerList.get(index);

			timestamp = OffsetDateTime.parse(timeAndPower.getTime(), defaultDateTimeFormatter);

			if (index > 0) {

				nextTimestamp = OffsetDateTime.parse(timeAndPowerList.get(index - 1).getTime(),
						defaultDateTimeFormatter);

			} else {

				nextTimestamp = null;

			}

			epochSecond = timestamp.toEpochSecond();

			if (epochSecond >= fromEpochSecond && epochSecond <= toEpochSecond) {

				if (null == earliestEpochSecond || epochSecond < earliestEpochSecond) {

					earliestEpochSecond = epochSecond;
				}

				if (null == latestEpochSecond || epochSecond > latestEpochSecond) {

					latestEpochSecond = epochSecond;
				}

				Float power = timeAndPower.getPower();

				if (null == nextTimestamp || null == power) {

					continue;
				}

				long elapsedSeconds = nextTimestamp.toEpochSecond() - epochSecond;

				// how many watt-seconds does this represent?

				float wattSeconds = elapsedSeconds * power;

				accWattSeconds += wattSeconds;

				if (wattSeconds > 0) {

					accSeconds += elapsedSeconds;
				}

				if (!summaryOnly) {

					System.out.println(timestamp + "\t" + String.format("%7.1f", power) + "\twatts for\t"
							+ String.format("%8d", elapsedSeconds) + "\tseconds\t"
							+ String.format("%12.2f", wattSeconds) + " watt-seconds ("
							+ String.format("%12.2f", accWattSeconds) + " accumulated)");
				}
			}
		}

		if (null == earliestEpochSecond) {

			System.out.println("<no data>");

		} else {

			Instant instantLowest = Instant.ofEpochSecond(earliestEpochSecond);

			LocalDateTime ldtLowest = LocalDateTime.ofInstant(instantLowest, ourZoneId);

			Instant instantHighest = Instant.ofEpochSecond(latestEpochSecond);

			LocalDateTime ldtHighest = LocalDateTime.ofInstant(instantHighest, ourZoneId);

			long fromDay = ldtLowest.get(ChronoField.DAY_OF_YEAR);
			long toDay = ldtHighest.get(ChronoField.DAY_OF_YEAR);

			float accWattHours = accWattSeconds / 3600;

			float kWhr = accWattHours / 1000;

			if (null != cache) {

				System.out.println((toDay - fromDay + 1) + " day(s) from: " + ldtLowest.format(formatter24HourClock)
						+ " on day " + fromDay + " to " + ldtHighest.format(formatter24HourClock) + " on day " + toDay
						+ " " + String.format("%8.3f", kWhr) + " kWhr consumed via " + alias + " (" + accSeconds
						+ " secs using power) ");
			}

		}

		return timeAndPowerList;
	}

	private static V1SmartDeviceData getV1SmartDeviceData(String uuid, Integer page, Integer pageSize)
			throws MalformedURLException, IOException {

		String json = getRequest(
				new URL(baseUrl + "/smart-device/" + uuid + "/data?page=" + (null == page ? "1" : String.valueOf(page))
						+ "&pageSize=" + (null == pageSize ? DEFAULT_PAGE_SIZE : String.valueOf(pageSize))),
				"smart-device");

		V1SmartDeviceData result = null;

		if (null == json || 0 == json.trim().length()) {

			System.err.println("Error obtaining data. Check the token in property file!");

			result = new V1SmartDeviceData(); // empty object

		} else {

			result = mapper.readValue(json, V1SmartDeviceData.class);
		}

		return result;

	}

//	/* TODO */
//	private static void getV1Site(Integer page, Integer pageSize) throws MalformedURLException, IOException {
//
//		String json = getRequest(new URL(baseUrl + "/site"), "site");
//
//		System.out.println(json);
//	}
//
//	/* TODO */
//	private static void getV1Account(Integer page, Integer pageSize) throws MalformedURLException, IOException {
//
//		String json = getRequest(new URL(baseUrl + "/account"), "account");
//
//		System.out.println(json);
//	}

	private static V1SmartDevices getV1SmartDevices(Integer page, Integer pageSize)
			throws MalformedURLException, IOException {

		String json = getRequest(new URL(baseUrl + "/smart-device?page=" + (null == page ? "1" : String.valueOf(page))
				+ "&pageSize=" + (null == pageSize ? DEFAULT_PAGE_SIZE : String.valueOf(pageSize))), "smart-device");

		V1SmartDevices result = null;

		if (null == json || 0 == json.trim().length()) {

			System.err.println("Error obtaining data. Check the token in property file!");

			result = new V1SmartDevices(); // empty object

		} else {

			result = mapper.readValue(json, V1SmartDevices.class);
		}

		return result;

	}

	// postV1InverterSettingReadBoolean

	private static V1DataBooleanValue postV1InverterSettingReadBoolean(int id)
			throws MalformedURLException, IOException, URISyntaxException {

		String json = postRequest(new URL(baseUrl + "/inverter/" + properties.getProperty("serial") + "/settings/"
				+ String.valueOf(id) + "/read"), "inverter", body);

		V1DataBooleanValue result = null;

		if (null == json || 0 == json.trim().length()) {

			System.err.println("Error obtaining data. Check the token in property file!");

			result = new V1DataBooleanValue(); // empty object

		} else {

			result = mapper.readValue(json, V1DataBooleanValue.class);
		}

		return result;
	}

	private static V1DataIntegerValue postV1InverterSettingReadInteger(int id)
			throws MalformedURLException, IOException, URISyntaxException {

		String json = postRequest(new URL(baseUrl + "/inverter/" + properties.getProperty("serial") + "/settings/"
				+ String.valueOf(id) + "/read"), "inverter", body);

		V1DataIntegerValue result = null;

		if (null == json || 0 == json.trim().length()) {

			System.err.println("Error obtaining data. Check the token in property file!");

			result = new V1DataIntegerValue(); // empty object

		} else {

			result = mapper.readValue(json, V1DataIntegerValue.class);
		}

		return result;
	}

	private static V1DataStringValue postV1InverterSettingReadString(int id)
			throws MalformedURLException, IOException, URISyntaxException {

		String json = postRequest(new URL(baseUrl + "/inverter/" + properties.getProperty("serial") + "/settings/"
				+ String.valueOf(id) + "/read"), "inverter", body);

		V1DataStringValue result = null;

		if (null == json || 0 == json.trim().length()) {

			System.err.println("Error obtaining data. Check the token in property file!");

			result = new V1DataStringValue(); // empty object

		} else {

			result = mapper.readValue(json, V1DataStringValue.class);
		}

		return result;
	}

	private static V1DataIntegerValue postV1Notification(String value)
			throws MalformedURLException, IOException, URISyntaxException {

		V1Notification notification = new V1Notification();

		List<String> platforms = new ArrayList<String>();

		platforms.add("persist");

		notification.setPlatforms(platforms);

		notification.setBody(value);

		notification.setTitle("Here is some information about the amazing thing that has happened");

		notification.setIcon("mdi-information-box");

		String payload = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(notification);

		String json = postRequest(new URL(baseUrl + "/notification/send/"), "notification-send", payload);

		V1DataIntegerValue result = null;

		if (null == json || 0 == json.trim().length()) {

			System.err.println("Error obtaining data. Check the token in property file!");

			result = new V1DataIntegerValue(); // empty object

		} else {

			result = mapper.readValue(json, V1DataIntegerValue.class);
		}

		return result;

	}

	// postV1InverterSettingWrite
	private static V1DataIntegerValue postV1InverterSettingWrite(int id, Integer value)
			throws MalformedURLException, IOException, URISyntaxException {

		V1DataIntegerValue result = null;

		String bodyValue = "{\"value\": " + value + ", " + context + "}";

		int count = 0;

		do {

			String json = postRequest(new URL(baseUrl + "/inverter/" + properties.getProperty("serial") + "/settings/"
					+ String.valueOf(id) + "/write"), "inverter", bodyValue);

			if (null == json || 0 == json.trim().length()) {

				System.err.println("Error obtaining data. Check the token in property file!");

				result = new V1DataIntegerValue(); // empty object

			} else {

				result = mapper.readValue(json, V1DataIntegerValue.class);
			}

			if (result.getData().getSuccess()) {

				break;
			}

			System.err.println(count + "\t" + result.getData().getSuccess() + "\t" + result.getData().getMessage()
					+ "\t" + result.getData().getValue());

			try {
				Thread.sleep(5000L);

			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		} while (++count < 5);

		return result;

	}

	private static V1DataStringValue postV1InverterSettingWriteString(int id, String value)
			throws MalformedURLException, IOException, URISyntaxException {

		V1DataStringValue result = null;

		String bodyValue = "{\"value\": \"" + value + "\"" + ", " + context + "}";

		int count = 0;

		do {

			String json = postRequest(new URL(baseUrl + "/inverter/" + properties.getProperty("serial") + "/settings/"
					+ String.valueOf(id) + "/write"), "inverter", bodyValue);

			if (null == json || 0 == json.trim().length()) {

				System.err.println("Error obtaining data. Check the token in property file!");

				result = new V1DataStringValue(); // empty object

			} else {

				result = mapper.readValue(json, V1DataStringValue.class);
			}

			if (result.getData().getSuccess()) {

				break;
			}

			System.err.println(count + "\t" + result.getData().getSuccess() + "\t" + result.getData().getMessage()
					+ "\t" + result.getData().getValue());

			try {
				Thread.sleep(5000L);

			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		} while (++count < 5);

		return result;
	}

	private static V1DataBooleanValue postV1InverterSettingWriteBoolean(int id, Boolean value)
			throws MalformedURLException, IOException, URISyntaxException {

		V1DataBooleanValue result = null;

		String bodyValue = "{\"value\": " + value + ", " + context + "}";

		int count = 0;

		do {

			String json = postRequest(new URL(baseUrl + "/inverter/" + properties.getProperty("serial") + "/settings/"
					+ String.valueOf(id) + "/write"), "inverter", bodyValue);

			if (null == json || 0 == json.trim().length()) {

				System.err.println("Error obtaining data. Check the token in property file!");

				result = new V1DataBooleanValue(); // empty object

			} else {

				result = mapper.readValue(json, V1DataBooleanValue.class);
			}

			if (result.getData().getSuccess()) {

				break;
			}

			System.err.println(count + "\t" + result.getData().getSuccess() + "\t" + result.getData().getMessage()
					+ "\t" + result.getData().getValue());

			try {
				Thread.sleep(5000L);

			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		} while (++count < 5);

		return result;
	}

	private static V1DataSettings getV1InverterSettings() throws MalformedURLException, IOException {

		String json = getRequest(new URL(baseUrl + "/inverter/" + properties.getProperty("serial") + "/settings"),
				"inverter");

		V1DataSettings result = null;

		if (null == json || 0 == json.trim().length()) {

			System.err.println("Error obtaining data. Check the token in property file!");

			result = new V1DataSettings(); // empty object

		} else {

			result = mapper.readValue(json, V1DataSettings.class);
		}

		return result;

	}

	private static V1DataDescriptor getV1InverterPresets(Integer page, Integer pageSize)
			throws MalformedURLException, IOException {

		String json = getRequest(new URL(baseUrl + "/inverter/" + properties.getProperty("serial") + "/presets?page="
				+ (null == page ? "1" : String.valueOf(page)) + "&pageSize="
				+ (null == pageSize ? DEFAULT_PAGE_SIZE : String.valueOf(pageSize))), "inverter");

		V1DataDescriptor result = null;

		if (null == json || 0 == json.trim().length()) {

			System.err.println("Error obtaining data. Check the token in property file!");

			result = new V1DataDescriptor(); // empty object

		} else {

			result = mapper.readValue(json, V1DataDescriptor.class);
		}

		return result;

	}

	private static InternalData getInternalInverterDailyData(String dateYYYYMMDD)
			throws MalformedURLException, IOException {

		String json = getRequest(new URL(baseInternalUrl + "/inverter/data/" + properties.getProperty("serial") + "/"
				+ dateYYYYMMDD + "?daily=true"), "inverter");

		InternalData result = null;

		if (null == json || 0 == json.trim().length()) {

			System.err.println("Error obtaining data. Check the token in property file!");

			result = new InternalData(); // empty object

		} else {

			result = mapper.readValue(json, InternalData.class);
		}

		return result;

	}

	private static Object getInternalInverterPresets() throws MalformedURLException, IOException {

		String json = getRequest(
				new URL(baseInternalUrl + "/inverter/" + properties.getProperty("serial") + "/presets"), "inverter");

		return json;
	}

	private static InternalLogData getInternalInverterLog(int fromPage, int toPage, int itemsPerPage)
			throws MalformedURLException, IOException, URISyntaxException {

		InternalLogData result = new InternalLogData();

		String bodyValue = "{\"search\":\"\",\"filter_errors\":true,\"filter_same_value_responses\":true,\"server_user\":0,\"read_write\":0,\"inverter_serial\":\""
				+ properties.getProperty("serial") + "\"}";

		List<InternalLogDataEvent> logDataEvents = new ArrayList<InternalLogDataEvent>();

		for (int p = fromPage; p < toPage; p++) {

			if (0 != logDataEvents.size()) {

				// be sympathetic to server upon repeated calls

				try {

					Thread.sleep(5000L);

				} catch (InterruptedException e) {

					System.err.println("Error: interruption before obtaining all data: " + e.getMessage());
					e.printStackTrace();

					break;
				}
			}

			String json = postRequest(new URL(baseInternalUrl + "/paginate/inverter-register-record?page="
					+ String.valueOf(p) + "&itemsPerPage=" + String.valueOf(itemsPerPage)), "inverter", bodyValue);

			InternalLogData pageOfEvents = null;

			if (null == json || 0 == json.trim().length()) {

				System.err.println("Error obtaining data. Check the token in property file!");

				break;
			}

			pageOfEvents = mapper.readValue(json, InternalLogData.class);

			int numOnPage = pageOfEvents.getData().size();

			for (int e = 0; e < numOnPage; e++) {

				InternalLogDataEvent event = pageOfEvents.getData().get(e);

				logDataEvents.add(event);
			}
		}

		result.setData(logDataEvents);

		return result;
	}

	private static V1DataSystem getV1InverterSystem() throws MalformedURLException, IOException {

		String json = getRequest(
				new URL(baseUrl + "/inverter/" + properties.getProperty("serial") + "/system-data/latest"), "inverter");

		V1DataSystem result = null;

		if (null == json || 0 == json.trim().length()) {

			System.err.println("Error obtaining data. Check the token in property file!");

			result = new V1DataSystem(); // empty object

		} else {

			result = mapper.readValue(json, V1DataSystem.class);
		}

		return result;

	}

	private static V1DataMeter getV1InverterMeter() throws MalformedURLException, IOException {

		String json = getRequest(
				new URL(baseUrl + "/inverter/" + properties.getProperty("serial") + "/meter-data/latest"), "inverter");

		V1DataMeter result = null;

		if (null == json || 0 == json.trim().length()) {

			System.err.println("Error obtaining data. Check the token in property file!");

			result = new V1DataMeter(); // empty object

		} else {

			result = mapper.readValue(json, V1DataMeter.class);
		}

		return result;

	}

	private static String getRequest(URL url, String tokenKey) throws IOException {

		return getRequest(url, true, tokenKey);
	}

	private static String postRequest(URL url, String tokenKey, String body) throws IOException, URISyntaxException {

		return postRequest(url, true, tokenKey, body);
	}

	/*
	 * throws IOException if token has expired or is invalid
	 * 
	 */
	private static String verifiedBearerToken(String tokenKey) throws IOException {

		String tokenValue = properties.getProperty(tokenKey);

		if (null == tokenValue) {

			throw new IOException("no property value for tokenKey " + tokenKey);
		}

		String[] chunks = tokenValue.split("\\.");

		if (3 != chunks.length) {

			throw new IOException("property value for tokenKey " + tokenKey
					+ " does not appear to be in format header.payload.signature");
		}

		Base64.Decoder decoder = Base64.getUrlDecoder();

		String payload = new String(decoder.decode(chunks[1]));

		int p = 6 + payload.indexOf("\"exp\":");

		int q = payload.indexOf(",", p);

		String timestamp = payload.substring(p, q);

		int r = 9 + payload.indexOf("\"scopes\":", q);

		int s = 1 + payload.indexOf("]", q);

		String scopes = payload.substring(r, s);

		Float f = Float.valueOf(timestamp);

		long l = 1000L * f.longValue();

		Date timeWhen = new Date(l);

		Date now = new Date();

		boolean expired = now.after(timeWhen);

		String text = (expired ? "EXCEPTION" : "INFO") + ": Property key '" + tokenKey + "' containing JWT token for "
				+ scopes + " " + (expired ? "has expired" : "will expire") + " " + timeWhen.toString();

		System.err.println(text);

		if (expired) {

			throw new IOException(text);
		}

		return tokenValue;
	}

	private static String getRequest(URL url, boolean authorisationRequired, String tokenKey) throws IOException {

		int status = 0;

		HttpURLConnection con = null;

		con = (HttpURLConnection) url.openConnection();

		con.setRequestProperty("Content-Type", contentType);
		con.setRequestProperty("user-agent", userAgent);

		con.setRequestMethod("GET");

		if (authorisationRequired) {

			con.setRequestProperty("Authorization", "Bearer " + verifiedBearerToken(tokenKey));
		}

		try {
			con.connect();

			status = con.getResponseCode();

		} catch (java.net.SocketException e) {

			System.err.println("API not available temporarily.  Please try again.");
			System.exit(-1);
		}

		String json = "";

		if (200 == status) {

			BufferedReader in = null;

			try {

				in = new BufferedReader(new InputStreamReader(con.getInputStream()));

				String inputLine;
				StringBuffer content = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {

					content.append(inputLine);
				}

				json = content.toString();

			} catch (IOException e) {

				e.printStackTrace();
			}

			if (null != in) {

				in.close();
			}

		} else {

			System.err.println("HTTP Status: " + status);
		}

		if (null != con) {

			con.disconnect();
		}

		try {
			Thread.sleep(2000L);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		return json;
	}

	private static String postRequest(URL url, boolean authorisationRequired, String tokenKey, String body)
			throws IOException {

		int status = 0;

		HttpURLConnection con = null;

		con = (HttpURLConnection) url.openConnection();

		con.setRequestProperty("Content-Type", contentType);
//		con.setRequestProperty("user-agent", userAgent);

		if (authorisationRequired) {

			con.setRequestProperty("Authorization", "Bearer " + properties.getProperty(tokenKey));
		}

		con.setRequestMethod("POST");

		con.setRequestProperty("Accept", accept);

		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(body.getBytes());
		os.flush();
		os.close();

		try {
			con.connect();

			status = con.getResponseCode();

		} catch (java.net.SocketException e) {

			System.err.println("API not available temporarily.  Please try again.");
			System.exit(-1);
		}

		String json = "";

		if (200 == status || 201 == status) {

			BufferedReader in = null;

			try {

				in = new BufferedReader(new InputStreamReader(con.getInputStream()));

				String inputLine;
				StringBuffer content = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {

					content.append(inputLine);
				}

				json = content.toString();

			} catch (IOException e) {

				e.printStackTrace();
			}

			if (null != in) {

				in.close();
			}
		} else {

			System.err.println("HTTP Status: " + status);
		}

		if (null != con) {

			con.disconnect();
		}

		try {
			Thread.sleep(2000L);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		return json;
	}

	private static void renderInverterValue(Object value) throws JsonProcessingException {

		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);

		System.out.println(json);
	}

}
