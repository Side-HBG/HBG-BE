
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
                    sh "cat <<EOF | kubectl apply -f -  \n
                    apiVersion: apps/v1 \n
                    kind: Deployment \n
                    metadata: \n
                      name: test-deploy \n
                      namespace: test-backend   \n
                    spec:   \n
                      replicas: 1   \n
                      selector:  \n
                        matchLabels:\n
                          app: test\n
                      template:\n
                        metadata:\n
                          labels:\n
                            app: test\n
                        spec:\n
                          containers:\n
                          - name: test\n
                            image: ${DOCKER_REPO}:${env.BUILD_NUMBER}\n
                            resources:\n
                              requests:\n
                                memory: "400Mi"\n
                                cpu: "1000m"\n
                              limits:\n
                                memory: "4000Mi"\n
                                cpu: "3000m"\n
                            ports:\n
                              - name: http-port\n
                                containerPort: 80"
                }
            }
        }
    }
}