# Build stage
FROM maven:3.8.4 AS builder
WORKDIR /usr/src/app
COPY pom.xml .
COPY src ./src
RUN mvn clean install

# Run stage
FROM openjdk:17
WORKDIR /usr/src/app
COPY --from=builder /usr/src/app/target/*.jar ./user-service.jar
ENTRYPOINT ["java", "-jar", "./user-service.jar"]
EXPOSE 8090
