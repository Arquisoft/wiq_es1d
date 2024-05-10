FROM mysql:8.0.36

COPY sql/scheme.sql /docker-entrypoint-initdb.d/

# Variables de entorno
ENV MYSQL_DATABASE syg-db
ENV MYSQL_ROOT_PASSWORD sygAdmin 

EXPOSE 3306

CMD ["mysqld"]