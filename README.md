# GivEnergy
## __Experiments with GivEnergy API for Smart Plugs__

### See download folder for wrapper scripts and latest snapshot prebuilt jar
Or see https://github.com/V999TEC/GivEnergyPlugs/releases  
for complete source code & release version of __plugs.jar__

### Syntax

```java -jar plugs.jar property_file_name  [alias [from_timestamp [to_timestamp]]]```

### Usage
Define a property file (such as __My.properties__) for your smart devices containing your generated api:smart-device token for example:

```
token=insert_api_token_here
```
You can generate a GivEnergy API token for your api:smart-device here https://givenergy.cloud/account-settings/security


Check you API token is good by enumerating all the smart devices as follows
```java -jar plugs.jar My.properties```

```
PlugA=08f6b4f9-0000-4d41-0000-95214d78e740
PlugB=aba03ce8-0000-41f9-0000-91876bb0cd45
PlugC=bb1c988e-0000-4ad5-0000-53c5a99f5841
PlugD=76412d97-0000-4ac0-0000-238ed1e1a6e2
PlugE=9cc9b483-0000-44ff-0000-1c47395fbc67
```

Edit the property file and paste the results so that the alias can be used on subsequent requests

```
token=insert_api_token_here
# list the smart device alias and ids 
PlugA=08f6b4f9-0000-4d41-0000-95214d78e740
PlugB=aba03ce8-0000-41f9-0000-91876bb0cd45
PlugC=bb1c988e-0000-4ad5-0000-53c5a99f5841
etc
```

N.B.
If the alias name contains spaces, it will be escaped in the properties file.  
So, for instance, __Washine Machine__ would become __Washing\ Machine=uuid__

Also, when passing parameters containing spaces to the jar, be sure to put them in quotes:

```java -jar plugs.jar My.properties "Washing Machine"```


### Example 1   
Analyse consumption for a device with an alias defined in My.properties for the specified timespan
 
```java -jar plugs.jar My.properties PlugE 2023-10-01T05:59:06Z 2023-10-01T06:15:00Z```
```
Device <PlugE>
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
1 day(s) from: 06:59 on day 274 to 07:07 on day 274    0.001 units consumed by PlugE ( 120 secs using power) 
```

### Example 2   
Analyse consumption for PlugE from the specified time until now
 
```java -jar plugs.jar My.properties PlugE 2023-10-01T05:59:06Z```

### Example 3   
Analyse consumption for PlugE for today, yesterday, past 7 days & past 30 days
 
```java -jar plugs.jar My.properties PlugE```

```
Device <PlugE>

Today:
From <2023-10-06T00:00+01:00> to <2023-10-06T23:59:59+01:00>
<no data>

Yesterday:
From <2023-10-05T00:00+01:00> to <2023-10-06T00:00+01:00>
1 day(s) from: 09:52 on day 278 to 17:52 on day 278    0.178 units consumed by PlugE (2485 secs using power)

Past 7 days:
From <2023-09-29T00:00+01:00> to <2023-10-06T00:00+01:00>
7 day(s) from: 01:21 on day 272 to 17:52 on day 278    0.370 units consumed by PlugE (55659 secs using power)

Past 30 days:
From <2023-09-06T00:00+01:00> to <2023-10-06T00:00+01:00>
10 day(s) from: 15:26 on day 269 to 17:52 on day 278    3.903 units consumed by PlugE (123466 secs using power)
```

### Example 4    
Analyse consumption for PlugE for all datapoints
 
```java -jar plugs.jar My.properties PlugE  1970-01-01T00:00Z```

