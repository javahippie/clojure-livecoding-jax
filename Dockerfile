FROM openjdk:8-alpine

COPY target/uberjar/jax.jar /jax/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/jax/app.jar"]
