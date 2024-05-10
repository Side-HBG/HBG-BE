
pipeline{
    environment{
        // 환경변수
        DOCKER_REGISTRY = 'docker.io'
        DOCKER_REPO = 'vulcanos/hgb-be'
    }
    agent any
    stages {
            stage('SonarQube Analysis') {
                withSonarQubeEnv() {
                    sh "./gradlew sonar"
                w}
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