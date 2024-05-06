
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
                sh './gradlew clean'
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
                    kubernetesDeploy(configs: "deployment.yaml")
                }
            }
        }
    }
}