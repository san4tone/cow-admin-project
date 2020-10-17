FROM java:8
EXPOSE 8080

VOLUME /tmp
ADD cow-admin-project-1.0-SNAPSHOT.jar  /app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar","--spring.profiles.active=prod"]
