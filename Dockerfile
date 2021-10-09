FROM maven:3.8.3-adoptopenjdk-16 AS builder

# Create .war file
COPY src /home/SomeAPI/src
COPY pom.xml /home/SomeAPI

RUN mvn -f /home/SomeAPI/pom.xml clean package

FROM tomcat:9.0.54-jdk8-temurin-focal

# Create acutal image
LABEL maintainer="https://github.com/SiverDX"

COPY --from=builder /home/SomeAPI/target/SomeAPI*.war /usr/local/tomcat/webapps

EXPOSE 8081
