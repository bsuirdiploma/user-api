#!/usr/bin/env bash

docker rm $(docker ps -qa) -f
docker volume prune -f
docker network prune -f

mvn clean package swagger:generate
docker-compose down
docker-compose build
docker-compose up