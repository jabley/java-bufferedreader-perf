
all: test.txt install benchmark
	java -jar 

clean:
	mvn clean

install: clean
	mvn install

benchmark:
	java -jar target/benchmarks.jar

test.txt:
	python gen.py > src/main/resources/test.txt
