FROM amazoncorretto:16
RUN mkdir /app
COPY build/install/repository /app/
WORKDIR /app/bin
CMD ["./repository --mode init"]
