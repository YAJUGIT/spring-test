#!/bin/bash
nohup java -jar h2-database/target/h2-database-1.0-SNAPSHOT.jar >  h2-log.txt 2>&1 &
echo $! >> pid.file
nohup java -jar rest-microservices/target/rest-microservices-1.0-SNAPSHOT.jar >  rest-log.txt 2>&1 &
echo $! >> pid.file
