FROM openjdk:8-jdk-alpine
MAINTAINER "ilyasimonovets"

COPY target/test_webapp.jar /demo.jar

CMD [ "java", "-jar", "/demo.jar"]