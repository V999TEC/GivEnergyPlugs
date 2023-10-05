# GivEnergy
## __Experiments with GivEnergy API for Smart Plugs__

### Syntax

```java -jar plugs.jar property_file_name  [alias [from_timestamp [to_timestamp]]]```

### Usage
Define a property file (such as __My.properties__) for your smart devices containining your generated api:smart-device token for example:

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

### Example 1   
Analyse consumption for a device with an alias defined in My.properties for the specified timespan
 
```java -jar plugs.jar My.properties PlugE 2023-10-01T05:59:06Z 2023-10-01T06:15:00Z```
```
Device <PlugE>
From <2023-10-01T05:59:06Z> to <2023-10-01T06:15Z>
2023-10-01T05:59:06Z	    0.0 watts for    87 seconds	        0.00 watt-seconds (        0.00 accumulated)
2023-10-01T06:00:33Z	   18.1 watts for    29 seconds	      524.90 watt-seconds (      524.90 accumulated)
2023-10-01T06:01:02Z	    0.0 watts for    89 seconds	        0.00 watt-seconds (      524.90 accumulated)
2023-10-01T06:02:31Z	   18.0 watts for    33 seconds	      594.00 watt-seconds (     1118.90 accumulated)
2023-10-01T06:03:04Z	    0.0 watts for    88 seconds	        0.00 watt-seconds (     1118.90 accumulated)
2023-10-01T06:04:32Z	   18.1 watts for    31 seconds	      561.10 watt-seconds (     1680.00 accumulated)
2023-10-01T06:05:03Z	    0.0 watts for    91 seconds	        0.00 watt-seconds (     1680.00 accumulated)
2023-10-01T06:06:34Z	   17.9 watts for    27 seconds	      483.30 watt-seconds (     2163.30 accumulated)
1 day(s) from: 06:59 on day 274 to 07:07 on day 274	   0.001 units consumed by PlugE
```

### Example 2   
Analyse consumption for PlugE from the specified time until now
 
```java -jar plugs.jar My.properties PlugE 2023-10-01T05:59:06Z```

### Example 3   
Analyse consumption for PlugE from first available datapoint until now
 
```java -jar plugs.jar My.properties PlugE```



