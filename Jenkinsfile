
pipeline{
    environment{
        // 환경변수
        DOCKER_REGISTRY = 'hbg-be'
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
                    dockerImage = docker.build("${DOCKER_REGISTRY}:${BUILD_NUMBER}")
                }
            }
        }

    }
}