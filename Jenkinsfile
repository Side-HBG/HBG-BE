
pipeline{
    environment{
        // 환경변수
        DOCKER_REGISTRY = 'docker.io'
        DOCKER_REPO = 'vulcanos/hgb-be'
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
                    sh 'docker rmi -f ${DOCKER_REPO}:${env.BUILD_NUMBER}'
                    sh 'docker rmi -f ${DOCKER_REPO}:latest'
                }
            }
        }

        stage('deploy'){
            steps{
                script{
                    sh "kubectl apply -f deployment.yaml"
                }
            }
        }
    }
}