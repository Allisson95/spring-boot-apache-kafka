#
# Build stage
#
FROM maven:3.8.4-openjdk-17 AS build
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package -DskipTests=true

#
# Package stage
#
FROM openjdk:17
COPY --from=build /app/target/*.jar /app/app.jar
WORKDIR /app
CMD [ "java", "-jar", "app.jar" ]