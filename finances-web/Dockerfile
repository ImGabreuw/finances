FROM maven:3.6-alpine AS dependencies

WORKDIR /app

COPY pom.xml ./
RUN mvn -B -e -C org.apache.maven.plugins:maven-dependency-plugin:3.2.0:go-offline

FROM amazoncorretto:17-alpine3.15

WORKDIR /app

COPY --from=dependencies /root/.m2 /root/.m2
COPY ./ ./

RUN chmod +x ./mvnw
RUN ./mvnw -B -e clean install

EXPOSE 8080
ENTRYPOINT java -jar ./target/finances-*.jar