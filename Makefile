help:
	@echo "Please use \`make <target>' where <target> is one of"
	@echo "  test           to perform unit tests"
	@echo "  compile        compile project"
	@echo "  server        compile project"

test:
	mvn test-compile
	mvn test

server:
	mvn package
	java -jar target/digital-commerce-1.0-SNAPSHOT.jar

