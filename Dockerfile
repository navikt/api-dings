FROM navikt/java:12
ENV JAVA_OPTS="-Dlogback.configurationFile=logback-remote.xml -Djavax.net.debug=all"
COPY build/libs/*-all.jar app.jar