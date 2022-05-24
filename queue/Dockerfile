FROM amazoncorretto:16
EXPOSE 10004
RUN mkdir /app
COPY build/install/queue /app/
WORKDIR /app/bin
CMD ["./queue"]
