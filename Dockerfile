FROM openjdk:8-jdk-stretch

WORKDIR /srv/color

COPY target/color.jar bin/color.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","bin/color.jar"]
