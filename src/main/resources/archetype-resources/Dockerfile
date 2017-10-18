FROM openjdk:8-jre-alpine
LABEL maintainer "Synoa GmbH <we@synoa.de>"

ENV _JAVA_OPTIONS="-Xms64m -Xmx128m"

ENTRYPOINT ["/usr/bin/java", "-jar", "/${artifactId}.jar"]

ADD version.sh /
RUN chmod +x /version.sh
ADD target/git.properties /
ADD target/${artifactId}.jar /
ADD application.properties /
