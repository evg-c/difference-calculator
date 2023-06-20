.DEFAULT_GOAL := build-run

clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew clean install

run-dist:
	./build/install/app/bin/app src/test/resources/file1.json src/test/resources/file2.json

runjson:
	./build/install/app/bin/app src/test/resources/file1.json src/test/resources/file2.json

runyml:
	./build/install/app/bin/app src/test/resources/file1.yml src/test/resources/file2.yml

runjsonnested:
	./build/install/app/bin/app src/test/resources/file3.json src/test/resources/file4.json

runymlnested:
	./build/install/app/bin/app src/test/resources/file5.yml src/test/resources/file6.yml

runjsonplain:
	./build/install/app/bin/app src/test/resources/file1.json src/test/resources/file2.json -f=plain

runjsonnestedplain:
	./build/install/app/bin/app src/test/resources/file3.json src/test/resources/file4.json -f=plain

runymlplain:
	./build/install/app/bin/app src/test/resources/file1.yml src/test/resources/file2.yml -f=plain

runymlnestedplain:
	./build/install/app/bin/app src/test/resources/file5.yml src/test/resources/file6.yml -f=plain

runhelp:
	./build/install/app/bin/app -h

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain checkstyleTest

build-run: build run
	
.PHONY: build

