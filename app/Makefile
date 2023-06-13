.DEFAULT_GOAL := build-run

clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew clean install

run-dist:
	./build/install/app/bin/app src/test/resources/file1.json src/test/resources/file2.json

run:
	./build/install/app/bin/app src/test/resources/file1.json src/test/resources/file2.json

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain checkstyleTest

build-run: build run
	
.PHONY: build

