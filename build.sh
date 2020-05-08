#!/bin/bash

echo "Performing a clean Maven build"
./mvnw clean package -DskipTests=true

echo "Building the service-registry"
cd service-registry
docker build --tag service-registry .
cd ..