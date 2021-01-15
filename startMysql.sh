#!/bin/bash

docker ps | grep "mysql8"
if [[ $? == 0 ]]; then
	echo "MYSQL DOCKER CONTAINER IS ALREADY RUNNING"
else
	echo "STARTING MYSQL DOCKER CONTAINER"
	docker run --name mysql8 -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql:8.0.20
fi
