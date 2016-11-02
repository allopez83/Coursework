#!/bin/sh
mkdir out
java -jar /usr/local/lib/antlr-4.5.3-complete.jar -no-listener -visitor Expr.g4 -o out/
cp *.java out/
javac out/*.java
java -classpath .:out/:${CLASSPATH} Calc $1

