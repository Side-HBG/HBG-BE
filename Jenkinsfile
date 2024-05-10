
pipeline{
    environment{
        // 환경변수
        DOCKER_REGISTRY = 'vulcanos/hgb-be'
    }
    agent any
    stages {
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube server') {
                    sh "./gradlew sonar"
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
        stage('docker-push'){
            steps{
                script{
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credential'){
                        dockerImage.push("${env.BUILD_NUMBER}")
                        dockerImage.push("latest")
                    }
                    sh 'docker rmi -f ${DOCKER_REGISTRY}'
                }
            }
        }

        stage('deploy'){
            steps{
                script{
                    sh '''
                        kubectl apply -f k8s-yaml/deployment.yaml
                        kubectl apply -f k8s-yaml/service.yaml
                        kubectl rollout restart -n `cat k8s-yaml/deployment.yaml| awk '/namespace/{ print $2 }'` deployment `cat k8s-yaml/deployment.yaml| awk '$1 == "name:" { print $2}'
                    '''
                }
            }
        }
    }
}