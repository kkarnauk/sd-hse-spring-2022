FROM amazoncorretto:16
EXPOSE 10001
RUN mkdir /app
COPY build/install/repository /app/
WORKDIR /app/bin
CMD ["./repository --mode api"]
