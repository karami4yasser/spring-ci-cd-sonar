# First stage: build the application
FROM maven:3.9.6-eclipse-temurin-17 AS build
COPY . /app
WORKDIR /app
RUN mvn package -DskipTests

# Second stage: create a slim image
FROM openjdk:17
COPY --from=build /app/target/springcicd.jar /springcicd.jar
ENTRYPOINT ["java", "-jar", "/springcicd.jar"]