#!/bin/sh
# Maven Wrapper script
# ----------------------------------------------------------------------------
MAVEN_WRAPPER_JAR=".mvn/wrapper/maven-wrapper.jar"
MAVEN_WRAPPER_PROPERTIES=".mvn/wrapper/maven-wrapper.properties"

if [ -r "$MAVEN_WRAPPER_JAR" ]; then
    exec java -jar "$MAVEN_WRAPPER_JAR" "$@"
else
    exec mvn "$@"
fi
