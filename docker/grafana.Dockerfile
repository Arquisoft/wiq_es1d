FROM grafana/grafana

COPY ./grafana/provisioning /etc/grafana/provisioning

ENV GF_SERVER_HTTP_PORT=9091
ENV GF_AUTH_DISABLE_LOGIN_FORM=true
ENV GF_AUTH_ANONYMOUS_ENABLED=true
ENV GF_AUTH_ANONYMOUS_ORG_ROLE=Admin

EXPOSE 9091

CMD ["grafana-server", "--homepath=/usr/share/grafana", "--config=/etc/grafana/grafana.ini", "--packaging=docker"]