FROM openjdk:8u92-jre-alpine
WORKDIR /sampleservice
ADD ./build/libs/sampleservice-0.1.0.jar sampleservice.jar
CMD java -jar sampleservice.jar
