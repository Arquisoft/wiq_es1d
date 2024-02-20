FROM mysql:latest

# Variables de entorno
ENV MYSQL_DATABASE syg-db
ENV MYSQL_USER sygAdmin
ENV MYSQL_PASSWORD sygAdmin
ENV MYSQL_ROOT_PASSWORD sygAdmin 

EXPOSE 5432

CMD ["mysqld"]