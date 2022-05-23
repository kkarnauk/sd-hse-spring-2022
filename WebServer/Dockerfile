FROM amazoncorretto:16
EXPOSE 10002:10002
RUN mkdir /app
COPY build/install/WebServer /app/
WORKDIR /app/bin
CMD ["./WebServer"]
