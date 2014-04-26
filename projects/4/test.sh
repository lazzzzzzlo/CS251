#!/bin/bash

for i in {1..50}
do
    java -classpath .:stdlib.jar:algs4.jar CriticalEdges graphtests/circlesG.txt 50 >> out.txt
done
