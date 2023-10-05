setlocal
set p1=My.properties
set p2=PlugE
set p3=2023-10-04T12:00:00+01:00
set p4=

if NOT "%4" == "" set p3=%4
if NOT "%3" == "" set p3=%3
if NOT "%2" == "" set p2=%2
if NOT "%1" == "" set p1=%1

set task=java -jar plugs.jar %p1% %p2% %p3% %p4%

%task%