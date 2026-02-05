FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn clean package

FROM scratch AS export
COPY --from=builder /build/target/keycloak-sync-data-required-action.jar /
