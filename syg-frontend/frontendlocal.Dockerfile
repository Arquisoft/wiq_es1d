FROM node:14 AS builder

WORKDIR /app

ENV REACT_APP_BACKEND_HOST=localhost:8080
ENV REACT_APP_KEYCLOAK_HOST=http://localhost:8090

COPY syg-frontend/package*.json ./

RUN npm install

COPY syg-frontend/. .

RUN npm run build

FROM nginx:alpine

COPY --from=builder /app/build /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]