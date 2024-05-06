
pipeline{
    environment{
        // 환경변수
        DOCKER_REGISTRY = 'docker.io'
        DOCKER_REPO = 'vulcanos/hbg-be'
    }
    agent any
    stages {
        stage('build'){
            steps{
                // 빌드시 할 step
                echo 'build'
                sh 'chmod +x gradlew'
                sh './gradlew build'
            }
        }
        stage ('docker-build'){
            steps{
                script{
                    dockerImage = docker.build("${DOCKER_REPO}")
                }
            }
        }
        stage('docker-push'){
            steps{
                script{
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credential'){
                        dockerImage.push("${env.BUILD_NUMBER}")
                        dockerImage.push("latest")
                    }
                }
            }
        }
        stage('deploy'){
            steps{
                script{
                    sh "cat <<EOF | kubectl apply -f -
                    apiVersion: apps/v1
                    kind: Deployment
                    metadata:
                      name: test-deploy
                      namespace: test-backend
                    spec:
                      replicas: 1
                      selector:
                        matchLabels:
                          app: test
                      template:
                        metadata:
                          labels:
                            app: test
                        spec:
                          containers:
                          - name: test
                            image: ${DOCKER_REPO}:${env.BUILD_NUMBER}
                            resources:
                              requests:
                                memory: "400Mi"
                                cpu: "1000m"
                              limits:
                                memory: "4000Mi"
                                cpu: "3000m"
                            ports:
                              - name: http-port
                                containerPort: 80"
                }
            }
        }
    }
}