#!/bin/bash
set -e # Stops the script if any command fails
#pyenv local 3.13.30
awslocal --endpoint-url=http://localhost:4566 cloudformation delete-stack \
    --stack-name patient-management

echo "S3 Configuration started"

awslocal --endpoint-url=http://localhost:4566 s3 mb s3://pms-bucket

echo "S3 Configured"

awslocal --endpoint-url=http://localhost:4566 cloudformation deploy \
    --stack-name patient-management \
    --s3-bucket pms-bucket \
    --template-file "./cdk.out/localstack.template.json" \

awslocal --endpoint-url=http://localhost:4566 elbv2 describe-load-balancers \
    --query "LoadBalancers[0].DNSName" --output text