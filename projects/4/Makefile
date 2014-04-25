JC=javac
JFLAGS= -cp .:stdlib.jar:algs4.jar

all: SAP.class CriticalEdges.class CriticalVertices.class

SAP.class: SAP.java
	$(JC) $(JFLAGS) SAP.java
CriticalEdges.class: CriticalEdges.java
	$(JC) $(JFLAGS) CriticalEdges.java
CriticalVertices.class: CriticalVertices.java
	$(JC) $(JFLAGS) CriticalVertices.java

clean:
	rm *.class
