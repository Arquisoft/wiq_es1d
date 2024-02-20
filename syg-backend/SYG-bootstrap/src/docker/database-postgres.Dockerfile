FROM postgres:alpine

# Variables de entorno
ENV POSTGRES_DB=syg-db
ENV POSTGRES_USER=sygAdmin
ENV POSTGRES_PASSWORD=sygAdmin

EXPOSE 5432

CMD ["postgres"]