# GivEnergy
## __Experiments with GivEnergy API for Smart Plugs & Inverters__

There is a wiki giving context about this project here https://github.com/V999TEC/GivEnergyPlugs/wiki/The-Home-of-Icarus  

The main problem with the GE API to GET datapoints for a smart-device is that it does not currently support a 'from' or 'to'  date/time range.
For the client application it means that potentially several pages needs to be scanned until the required time range is covered.

This java code creates a cache in uuid.tmp which reflects the timestamp and power readings for each datapoint.
Subsequent requests will maintain the cache while minimising the traffic needed against the GE API for smart plugs.


Latest snapshot icarus.jar in __/download/__ or choose release version

### See download folder for wrapper scripts and latest snapshot prebuilt jar
Or see https://github.com/V999TEC/GivEnergyPlugs/releases  
for complete source code & release version of __icarus.jar__

### Syntax

Key "smart-device" value needs to be defined in the property file for GivEnergy API Token [api:smart-device] for successful use of following option:

```java -jar icarus.jar property_file_name  [alias [from_timestamp [to_timestamp]]]```

Key "inverter" value needs to be defined in the property file for GivEnergy API Token [api:inverter] for successful use of following options:

```java -jar icarus.jar ./my.properties inverter  settings```

```java -jar icarus.jar ./my.properties inverter  setting read id```

```java -jar icarus.jar ./my.properties inverter  setting write id HH:MM```

### Extended syntax

```java -jar icarus.jar ./my.properties inverter meter [today [ac_charge|consumption|solar|battery [charge|discharge] | grid [import|export]]]```

```java -jar icarus.jar ./my.properties inverter system [battery [percent|power|temperature] |inverter [power|temperature|eps_power_output_voltage|output_frequency]]```

### Usage
Define a property file (such as __My.properties__) for your smart devices containing your generated api:smart-device token for example:

```
smart-device=insert_api_token_here
inverter=insert_api_token_here
```
You can generate a GivEnergy API token for your api:smart-device and/or api:inverter here https://givenergy.cloud/account-settings/security

When you execute the icarus.jar - see examples  below - it will always display a warning about the expiry of the API token  

```INFO: Property key 'inverter' containing JWT token for ["api:inverter"] will expire Thu Sep 05 15:15:28 BST 2024```


Check you API token is good by enumerating all the smart devices as follows
```java -jar icarus.jar My.properties```

```
PlugA=08f6b4f9-0000-4d41-0000-95214d78e740
PlugB=aba03ce8-0000-41f9-0000-91876bb0cd45
PlugC=bb1c988e-0000-4ad5-0000-53c5a99f5841
PlugD=76412d97-0000-4ac0-0000-238ed1e1a6e2
plug\ E=9cc9b483-0000-44ff-0000-1c47395fbc67
```

Edit the property file and paste the results so that the alias can be used on subsequent requests

```
smart-device=insert_api_token_here
# list the smart device alias and ids 
PlugA=08f6b4f9-0000-4d41-0000-95214d78e740
PlugB=aba03ce8-0000-41f9-0000-91876bb0cd45
PlugC=bb1c988e-0000-4ad5-0000-53c5a99f5841
etc

inverter=insert_api_token_here
```

N.B.
If the alias name contains spaces, it will be escaped in the properties file.  
So, for instance, __Washing Machine__ would become __Washing\ Machine=uuid__

Also, when passing parameters containing spaces to the jar, be sure to put them in quotes:

```java -jar icarus.jar My.properties "Washing Machine"```


### Example 1   
Analyse consumption for a device with an alias defined in My.properties for the specified timespan
 
```java -jar icarus.jar My.properties "plug E" 2023-10-01T05:59:06Z 2023-10-01T06:15:00Z```
```
Device <plug E>
From <2023-10-01T05:59:06Z> to <2023-10-01T06:15Z>
2023-10-01T05:59:06Z	    0.0 watts for       87 seconds	        0.00 watt-seconds (        0.00 accumulated)
2023-10-01T06:00:33Z	   18.1 watts for       29 seconds	      524.90 watt-seconds (      524.90 accumulated)
2023-10-01T06:01:02Z	    0.0 watts for       89 seconds	        0.00 watt-seconds (      524.90 accumulated)
2023-10-01T06:02:31Z	   18.0 watts for       33 seconds	      594.00 watt-seconds (     1118.90 accumulated)
2023-10-01T06:03:04Z	    0.0 watts for       88 seconds	        0.00 watt-seconds (     1118.90 accumulated)
2023-10-01T06:04:32Z	   18.1 watts for       31 seconds	      561.10 watt-seconds (     1680.00 accumulated)
2023-10-01T06:05:03Z	    0.0 watts for       91 seconds	        0.00 watt-seconds (     1680.00 accumulated)
2023-10-01T06:06:34Z	   17.9 watts for       27 seconds	      483.30 watt-seconds (     2163.30 accumulated)
2023-10-01T06:07:01Z	    0.0 watts for   301959 seconds	        0.00 watt-seconds (     2163.30 accumulated)
1 day(s) from: 06:59 on day 274 to 07:07 on day 274    0.001 units consumed via plug E ( 120 secs using power) 
```

### Example 2   
Analyse consumption for "plug E" from the specified time until now
 
```java -jar icarus.jar My.properties "plug E" 2023-10-01T05:59:06Z```

### Example 3   
Analyse consumption for "plug E" for today, yesterday, past 7 days & past 30 days
 
```java -jar icarus.jar My.properties "plug E"```

```
Device <plug E>

Today:
From <2023-10-06T00:00+01:00> to <2023-10-06T23:59:59+01:00>
<no data>

Yesterday:
From <2023-10-05T00:00+01:00> to <2023-10-06T00:00+01:00>
1 day(s) from: 09:52 on day 278 to 17:52 on day 278    0.178 units consumed via plug E (2485 secs using power)

Past 7 days:
From <2023-09-29T00:00+01:00> to <2023-10-06T00:00+01:00>
7 day(s) from: 01:21 on day 272 to 17:52 on day 278    0.370 units consumed via plug E (55659 secs using power)

Past 30 days:
From <2023-09-06T00:00+01:00> to <2023-10-06T00:00+01:00>
10 day(s) from: 15:26 on day 269 to 17:52 on day 278    3.903 units consumed via plug E (123466 secs using power)
```

### Example 4    
Analyse consumption for "plug E" for all datapoints
 
```java -jar icarus.jar My.properties "plug E"  1970-01-01T00:00Z```

### Example 5    
Display all the id values & associated validation rules that can be used
 
```java -jar icarus.jar ./my.properties inverter  settings```

### Example 6    
Display the AC Charge 1 End Time
 
```java -jar icarus.jar ./my.properties inverter setting read 65```

### Example 7    
Display the battery percentage, power and temperature
 
```java -jar icarus.jar ./my.properties inverter system battery```

### Example 8   
Display the solar generation for today
 
```java -jar icarus.jar ./my.properties inverter meter today solar```

### Example 9   
Display the today's grid import
 
```java -jar icarus.jar ./my.properties inverter meter today grid import```

### Example 10   
Get the grid related data in csv format for the specified date and dump it in file "2024-07-01.csv"
 
```java -jar icarus.jar ./my.properties inverter system internal 2024-07-01 grid csv file```

### Example 11   
Get the grid related data in json format for the specified date and display to stdout
 
```java -jar icarus.jar ./my.properties inverter system internal 2024-07-01 grid```


