#!/bin/bash
minikube image load analytics-service:latest
minikube image load restaurant-service:latest
minikube image load billing-service:latest
minikube image load auth-service:latest
minikube image load api-gateway:latest
minikube image load config-service:latest

#minikube image rm  your-image-name