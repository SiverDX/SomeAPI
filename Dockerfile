FROM maven:3.8.3-adoptopenjdk-16 AS builder

# Create .war file
COPY src /home/SomeAPI/src
COPY pom.xml /home/SomeAPI

RUN mvn -f /home/SomeAPI/pom.xml clean package spring-boot:repackage

FROM tomcat:9.0.54-jre11

# Create acutal image
LABEL maintainer="https://github.com/SiverDX"

COPY --from=builder /home/SomeAPI/target/some-api*.war /usr/local/tomcat/webapps