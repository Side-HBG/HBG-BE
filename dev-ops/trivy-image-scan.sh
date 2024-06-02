#!/bin/bash
#get the image name from Dockerfile file
dockerImageName=$1
echo $dockerImageName

#-e TRIVY_GITHUB_TOKEN=$token: Sets an environment variable TRIVY_GITHUB_TOKEN with the provided token value. This is used to access private GitHub repositories during vulnerability scanning.
docker run --rm -v $WORKSPACE:/root/.cache/ -v /var/run/docker.sock:/var/run/docker.sock aquasec/trivy:latest image --exit-code 1 --severity CRITICAL --ignore-unfixed -f table $dockerImageName

exit_code=$?
echo "Exit Code : $exit_code"

# Check scan results
if [ "${exit_code}" -eq 1 ]; then
    echo "Image scanning failed. Vulnerabilities found"
    exit 1;
else
    echo "Image scanning passed. No CRITICAL vulnerabilities found"
fi;