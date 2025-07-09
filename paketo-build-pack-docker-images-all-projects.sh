#!/bin/bash

# Define an array of application directories
declare -a app_dirs=("backend/java/microservices/analytics-service" "backend/java/microservices/api-gateway" "backend/java/microservices/auth-service" "backend/java/microservices/billing-service" "backend/java/microservices/config-service" "backend/java/microservices/restaurant-service")

for app_dir in "${app_dirs[@]}"; do
    echo "Building application in: $app_dir"
    if [ -d "$app_dir" ]; then
        cd "$app_dir" || { echo "Error: Could not change directory to $app_dir"; exit 1; }
        if [ -f "pom.xml" ]; then
            # Maven project
            mvn clean install || { echo "Error: Maven build failed for $app_dir"; exit 1; }
            mvn spring-boot:build-image ||  { echo "Error: Maven build image failed for $app_dir"; exit 1; }
        elif [ -f "build.gradle" ]; then
            # Gradle project
            ./gradlew clean build || { echo "Error: Gradle build failed for $app_dir"; exit 1; }
        else
            echo "No Maven (pom.xml) or Gradle (build.gradle) build file found in $app_dir"
        fi
        cd - || { echo "Error: Could not return to previous directory"; exit 1; } # Return to the original directory
    else
        echo "Warning: Directory not found: $app_dir"
    fi
    echo "-------------------------------------"
done

echo "All specified applications have been processed."
