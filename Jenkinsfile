
pipeline{
    agent any
    checkout scm
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
                // 도커 빌드시 할 step
                echo 'docker-build'
                sh 'docker build -t hbg-be .'
            }
        }

    }
}