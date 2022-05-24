FROM amazoncorretto:16
EXPOSE 10003
RUN mkdir /app
COPY build/install/WebServerApi /app/
WORKDIR /app/bin
CMD ["./WebServerApi"]
