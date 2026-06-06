# Build-Stage
FROM maven:3.9.16-eclipse-temurin-25-alpine AS build
WORKDIR /app
ARG MODULE

# Parent POM + ALLE Modul-POMs
COPY pom.xml .
COPY energy.server/pom.xml energy.server/pom.xml
COPY energy.producer/pom.xml energy.producer/pom.xml
COPY energy.user/pom.xml energy.user/pom.xml
COPY current.percentage.service/pom.xml current.percentage.service/pom.xml
COPY usage.service/pom.xml usage.service/pom.xml

RUN mvn dependency:go-offline -pl ${MODULE} -am -q

# Source bauen
COPY ${MODULE}/src ${MODULE}/src
RUN mvn package -pl ${MODULE} -am -DskipTests -q

# Run-Stage
FROM eclipse-temurin:25-jre
ARG MODULE
WORKDIR /app
COPY --from=build /app/${MODULE}/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]