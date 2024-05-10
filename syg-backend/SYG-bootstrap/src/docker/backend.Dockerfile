FROM maven:3.8.7-openjdk-18 as build

# Create app directory
WORKDIR /usr/src/app

COPY syg-backend/. .
RUN mvn clean install -DskipTests -DskipITs

FROM openjdk:18 as deployment

WORKDIR /app/syg

ENV OAUTH_SECURE=unsecured
ENV OAUTH_ISSUER_URI=https://syg-keycloak.hdg5fghpcef7a8a2.westeurope.azurecontainer.io:8090/realms/syg
ENV CERTIFICATES_URL=https://syg-keycloak.hdg5fghpcef7a8a2.westeurope.azurecontainer.io:8090/realms/syg/protocol/openid-connect/certs
ENV DB_URL=jdbc:mysql://syg-docker-db.b6e8h8eqdkhrcza9.westeurope.azurecontainer.io/syg-db
ENV DB_USERNAME=root
ENV DB_PASSWORD=sygAdmin

COPY --from=build /usr/src/app/SYG-bootstrap/target/*.jar ./app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]