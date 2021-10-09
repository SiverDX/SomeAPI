FROM tomcat:9.0.54-jdk8-temurin-focal

LABEL maintainer="https://github.com/SiverDX"

COPY /target/SomeAPI*.war /usr/local/tomcat/webapps