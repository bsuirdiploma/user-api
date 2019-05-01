#!/usr/bin/env bash

docker rm $(docker ps -qa) -f
docker volume prune -f
docker network prune -f

mvn clean package swagger:generate
mvn clean package swagger:generate --file=../ctv-api
mvn clean package swagger:generate --file=../watering-api
mvn clean package swagger:generate --file=../climate-api
docker-compose down
docker-compose build
docker-compose up