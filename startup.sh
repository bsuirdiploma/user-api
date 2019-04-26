#!/usr/bin/env bash

mvn clean package swagger:generate
docker-compose up --build --force-recreate