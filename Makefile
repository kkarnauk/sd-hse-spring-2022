all:
	./gradlew installDist;  \
	docker compose -f docker-compose.yml build; \
	docker compose -f docker-compose.yml up -d

clean:
	#./gradlew clean; \
	docker compose -f docker-compose.yml down
