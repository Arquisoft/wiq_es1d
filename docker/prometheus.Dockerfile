FROM prom/prometheus

COPY ./prometheus /etc/prometheus

ENV BACKEND_HOST=syg-backend.evcjhqa3dabdhtfj.westeurope.azurecontainer.io:8080

EXPOSE 9090

ENTRYPOINT ["prometheus", "--config.file=/etc/prometheus/prometheus.yml"]