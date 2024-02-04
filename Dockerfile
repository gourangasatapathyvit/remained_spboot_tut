FROM maven:3.8.4-openjdk-17
WORKDIR /app
COPY pom.xml /app/
RUN mvn dependency:go-offline
COPY . /app/
EXPOSE 8090
CMD ["mvn", "spring-boot:run"]
