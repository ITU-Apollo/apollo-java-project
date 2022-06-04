# FROM maven:3.8.2-openjdk-16-slim as builder
# WORKDIR /
# COPY pom.xml .
# COPY src ./src
# RUN --mount=type=cache,target=/root/.m2 ["mvn", "-T", "1C", "package", "-DskipTests"]

FROM openjdk:17-slim
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 9671
