# Dockerfile
FROM openjdk:21-jdk-slim
ARG JAR_FILE=app.jar
COPY ${JAR_FILE} /app/app.jar
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]