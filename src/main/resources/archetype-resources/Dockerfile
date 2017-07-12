FROM openjdk:8-jre-alpine
LABEL maintainer "Synoa GmbH <we@synoa.de>"

ENV JAVA_OPTS="-Xms64m -Xmx128m"

ENTRYPOINT exec /usr/bin/java $JAVA_OPTS -jar /${artifactId}.jar

ADD target/${artifactId}.jar /
ADD application.properties /