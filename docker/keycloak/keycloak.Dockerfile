# Usar la imagen oficial de Keycloak
FROM quay.io/keycloak/keycloak:24.0.1

# Definir variables de entorno para el administrador de Keycloak
# Para mayor seguridad, estas credenciales se deberían manejar externamente
ENV KEYCLOAK_ADMIN=admin
ENV KEYCLOAK_ADMIN_PASSWORD=admin

# Copiar archivos de certificado y clave privada
# COPY ./certs /opt/keycloak/certs

# Copiar el archivo de importación del realm al directorio de importación
COPY ./imports/realm.json /opt/keycloak/data/import/

# Exponer los puertos necesarios para el tráfico
EXPOSE 8080
EXPOSE 8090
EXPOSE 80

# Comando para iniciar Keycloak en modo de desarrollo con puerto y otros argumentos
CMD ["start-dev", "--import-realm", "--hostname-url=http://syg-keycloak.hdg5fghpcef7a8a2.westeurope.azurecontainer.io"]
# CMD ["start", "--import-realm", "--http-port=8100", "--https-port=8090", "--hostname-url=https://syg-keycloak.hdg5fghpcef7a8a2.westeurope.azurecontainer.io:8090", "--https-certificate-file=/opt/keycloak/certs/keycloakcert.pem", "--https-certificate-key-file=/opt/keycloak/certs/keycloakkey.pem"]
