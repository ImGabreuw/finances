FROM maven:3.9-eclipse-temurin-17-alpine AS dependencies

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

FROM maven:3.9-eclipse-temurin-17-alpine AS build

WORKDIR /app

COPY --from=dependencies /root/.m2 /root/.m2

COPY src ./src
COPY pom.xml .

RUN mvn package -DskipTests

FROM eclipse-temurin:17-jre-alpine AS runtime
WORKDIR /app

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

RUN addgroup --system java && adduser --system java --ingroup java
USER java

COPY --from=build /app/target/finances-*.jar app.jar

EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=3s CMD curl -sf -o /dev/null http://localhost:8080/actuator/health || exit 1

ENTRYPOINT java $JAVA_OPTS -jar app.jar -Dspring.profiles.active=dev