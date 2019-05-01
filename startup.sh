#!/usr/bin/env bash

mvn clean package swagger:generate
mvn clean package swagger:generate --file=../ctv-api
mvn clean package swagger:generate --file=../watering-api
mvn clean package swagger:generate --file=../climate-api

docker-compose up --build --force-recreate