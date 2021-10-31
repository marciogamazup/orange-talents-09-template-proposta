## Builder Image
FROM maven:3.8.3-openjdk-11 AS builder
MAINTAINER Marcio Gama
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests

## Runner Image
FROM openjdk:11
COPY --from=builder /usr/src/app/target/*.jar /usr/app/app.jar
EXPOSE 3000
ENTRYPOINT ["java","-jar","/usr/app/app.jar"]