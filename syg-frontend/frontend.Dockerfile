FROM node:14 AS builder

WORKDIR /app

ENV REACT_APP_BACKEND_HOST=syg-backend.evcjhqa3dabdhtfj.westeurope.azurecontainer.io:8080
ENV REACT_APP_KEYCLOAK_HOST=https://syg-keycloak.hdg5fghpcef7a8a2.westeurope.azurecontainer.io:8090

COPY syg-frontend/package*.json ./

RUN npm install

COPY syg-frontend/. .

RUN npm run build

FROM nginx:alpine

COPY --from=builder /app/build /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]