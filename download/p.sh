##!/bin/bash
if [ "$#" -eq  "0" ]
	then
	p1="My.properties"
	p2="PlugE"
	p3=""
	p4=""
else
	p1="$1"
	p2="$2"
	p3="$3"
	p4="$4"
fi

task="java -jar plugs.jar $p1 $p2 $p3 $p4"

$task

