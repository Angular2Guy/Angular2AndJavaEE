#!/bin/sh
#./mvnw clean install -Dnpm.test.script=test-chromium
./mvnw clean install -Ddocker=true
docker build -t angular2guy/angular2andjavaee:latest --build-arg EAR_FILE=carrental-ear.ear --no-cache .
docker run --name=mywildfly -p 8080:8080 -e WILDFLY_PASSWORD=my_password --memory="512m" --cpus=1.0 angular2guy/angular2andjavaee:latest