
pipeline{
    environment{
        // 환경변수
        DOCKER_REGISTRY = 'vulcanos/hgb-be'
        NAMESPACE = 'hgb-be'
        DEPLOYMENT = 'hgb-backend-deploy'
    }
    agent any
    stages {
        stage('build test') {
            steps {
                // 테스트시 할 step
                echo 'build test'
                sh './gradlew test'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube server') {
                    sh "./gradlew sonar --warning-mode all"
                }
            }
        }
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
                    dockerImage = docker.build("${DOCKER_REGISTRY}")
                }
            }
        }
        stage ('vulerability-scan'){
            steps{
                script{
                    sh 'sh ./dev-ops/trivy-image-scan.sh ${DOCKER_REGISTRY}'
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
                    sh '''
                        docker system prune -f --all
                    '''
                }
            }
        }

        stage('deploy'){
            steps{
                script{
                    sh '''
                        kubectl apply -f ./dev-ops/k8s-yaml/deployment.yaml
                        kubectl apply -f ./dev-ops/k8s-yaml/service.yaml
                        kubectl rollout restart -n `cat ./dev-ops/k8s-yaml/deployment.yaml| awk '/namespace/{ print $2 }' | head -1` deployment `cat ./dev-ops/k8s-yaml/deployment.yaml| awk '$1 == "name:" { print $2}' | head -1`
                    '''
                }
            }
        }
    }
}