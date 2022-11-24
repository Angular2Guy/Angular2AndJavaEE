FROM quay.io/wildfly/wildfly:26.1.2.Final-jdk17
VOLUME /tmp
ARG EAR_FILE
ADD carrental-ear/target/${EAR_FILE} /opt/jboss/wildfly/standalone/deployments/