.DEFAULT_GOAL := build-run

setup:
	./gradlew wrapper --gradle-version 8.5

clean:
	./gradlew clean

build:
	gradle clean build

install:
	gradle clean install

run-dist:
	./build/install/app/bin/app java-project-71/app/src/main/resources/file1.json java-project-71/app/src/main/resources/file2.json

run:
	./gradlew run

test:
	gradle test

report:
	gradle jacocoTestReport

checkstyleMain:
	gradle checkstyleMain

check-deps:
	./gradlew dependencyUpdates -Drevision=release


build-run: build run

.PHONY: build