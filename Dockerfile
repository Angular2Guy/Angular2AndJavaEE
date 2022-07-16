FROM bitnami/wildfly:26.0.1
VOLUME /tmp
ARG EAR_FILE
ADD carrental-ear/target/${EAR_FILE} opt/bitnami/wildfly/standalone/deployments/app.ear