JAVAC=javac
sources = $(filter-out code.java, $(wildcard *.java))
classes = $(sources:.java=.class)

all: $(classes)

clean :
	rm -f *.class

%.class : %.java
	$(JAVAC) $<