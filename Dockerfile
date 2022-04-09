FROM bitnami/wildfly:23.0.2
VOLUME /tmp
ARG EAR_FILE
ADD carrental-ear/target/${EAR_FILE} opt/bitnami/wildfly/standalone/deployments/app.ear