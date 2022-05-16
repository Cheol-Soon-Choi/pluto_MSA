#!/bin/sh

echo "********************************************************"
echo "Waiting for the eureka server to start on port $EUREKASERVER_PORT"
echo "********************************************************"
while ! `nc -z eurekaserver  $EUREKASERVER_PORT`; do sleep 3; done
echo "******* Eureka Server has started"

echo "********************************************************"
echo "Waiting for the configuration server to start on port $CONFIGSERVER_PORT"
echo "********************************************************"
while ! `nc -z configserver $CONFIGSERVER_PORT`; do sleep 3; done
echo "*******  Configuration Server has started"

echo "********************************************************"
echo "Waiting for the ZIPKIN server to start  on port $ZIPKIN_PORT"
echo "********************************************************"
while ! `nc -z zipkin $ZIPKIN_PORT`; do sleep 10; done
echo "******* ZIPKIN has started"

java -Dserver.port=$SERVER_PORT \
     -Deureka.client.serviceUrl.defaultZone=$EUREKASERVER_URI \
     -Dspring.cloud.config.uri=$CONFIGSERVER_URI \
     -Dspring.zipkin.baseUrl=$ZIPKIN_URI \
     -Dspring.profiles.active=$PROFILE \
     -jar zuulserver.jar