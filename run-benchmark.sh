#!/bin/sh
#
# Script to run the benchmark
#

mvn clean install || { echo >&2 "Please install Maven to run this script.  Aborting."; exit 1; }
java -jar target/benchmarks.jar $* l || { echo >&2 "Please install Java to run this script.  Aborting."; exit 1; }
