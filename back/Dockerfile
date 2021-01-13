FROM openjdk:8-jre-slim

ADD build/resources/main/application.properties /usr/local/back/application.properties
ADD build/libs/back-0.0.1.jar /usr/local/back/back-0.0.1.jar

EXPOSE 8088

CMD ["java","-Dspring.config.location=/usr/local/back/application.properties", "-jar", "/usr/local/back/back-0.0.1.jar"]