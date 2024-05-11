FROM maven:3.8.7-openjdk-18 as build

# Create app directory
WORKDIR /usr/src/app

COPY syg-backend/. .
RUN mvn clean install -DskipTests -DskipITs

FROM openjdk:18 as deployment

WORKDIR /app/syg

ENV OAUTH_SECURE=unsecured
ENV OAUTH_ISSUER_URI=http://host.docker.internal:8090/realms/syg
ENV CERTIFICATES_URL=http://host.docker.internal:8090/realms/syg/protocol/openid-connect/certs
ENV DB_URL=jdbc:mysql://host.docker.internal:3306/syg-db
ENV DB_USERNAME=root
ENV DB_PASSWORD=sygAdmin

COPY --from=build /usr/src/app/SYG-bootstrap/target/*.jar ./app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]