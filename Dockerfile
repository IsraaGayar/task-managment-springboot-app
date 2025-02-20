# Use a multi-stage build to reduce the final image size
FROM maven:3.8.6-amazoncorretto-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn package -DskipTests

# Create a smaller image for the final deployment
FROM openjdk:17-alpine

WORKDIR /app

COPY --from=build /app/target/taskchallenge-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080
# Expose the port your Spring Boot app runs on
CMD ["java", "-jar", "/app/app.jar"]