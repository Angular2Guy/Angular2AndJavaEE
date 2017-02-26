FROM websphere-liberty:javaee7
MAINTAINER Sven Loesekann
COPY ./docker/conf/server.xml /config/
COPY ./docker/conf/import.sql /config/
COPY ./docker/lib/h2-1.4.193.jar /opt/ibm/wlp/usr/shared/resources/
COPY ./target/carrental-ear.ear /config/dropins/
