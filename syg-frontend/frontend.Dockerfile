FROM node:14 AS builder

WORKDIR /app

COPY syg-frontend/package*.json ./

RUN npm install

COPY syg-frontend/. .

RUN npm run build

FROM nginx:alpine

COPY --from=builder /app/build /usr/share/nginx/html

EXPOSE 3000

CMD ["nginx", "-g", "daemon off;"]