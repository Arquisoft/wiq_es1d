FROM maven:3.8.7-openjdk-18 as build

# Create app directory
WORKDIR /usr/src/app

COPY . .
RUN mvn clean package -DskipTests 
# RUN mvn clean install 

FROM openjdk:18 as deployment

WORKDIR /app/syg

COPY --from=build /usr/src/app/SYG-bootstrap/target/*.jar ./app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]