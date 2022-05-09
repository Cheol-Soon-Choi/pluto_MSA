FROM openjdk:8-jdk-alpine
RUN apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/itemserver

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} itemserver.jar
COPY run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh