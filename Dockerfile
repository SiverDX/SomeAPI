LABEL maintainer="https://github.com/SiverDX"

# Create .war file
FROM maven:3.8.3-adoptopenjdk-16 AS builder

COPY src /home/SomeAPI/src
COPY pom.xml /home/SomeAPI

RUN mvn -f /home/SomeAPI/pom.xml clean package

FROM tomcat:9.0.54-jdk8-temurin-focal

COPY --from=builder /home/SomeAPI/target/SomeAPI*.war /usr/local/tomcat/webapps

