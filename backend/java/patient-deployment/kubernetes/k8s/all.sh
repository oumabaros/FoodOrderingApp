kubectl apply -f backing-services/postgresql
kubectl apply -f backing-services/kafka
kubectl apply -f services/config-service.yml
kubectl apply -f services/api-gateway.yml
kubectl apply -f services/auth-service.yml
kubectl apply -f services/billing-service.yml
kubectl apply -f services/analytics-service.yml
kubectl apply -f services/patient-service.yml

#kubectl delete -f backing-services/kafka
#kubectl delete -f backing-services/postgresql
#kubectl delete -f services