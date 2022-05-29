FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} superchat.jar
ENTRYPOINT ["java","-jar","/superchat.jar"]
EXPOSE 8080