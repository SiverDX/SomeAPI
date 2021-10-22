FROM maven:3.8.3-jdk-11-slim AS BUILDER

# Create .war file
COPY src /home/SomeAPI/src
COPY pom.xml /home/SomeAPI

RUN mvn -f /home/SomeAPI/pom.xml clean package

FROM tomcat:9.0.54-jre11

# Create the acutal image
LABEL maintainer="https://github.com/SiverDX"

COPY --from=BUILDER /home/SomeAPI/target/some-api.war /usr/local/tomcat/webapps