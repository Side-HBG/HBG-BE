
pipeline{
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
        stage ('test'){
            agent { dockerfile true }
            steps{
                // 테스트시 할 step
                echo 'test'
                sh 'java --version'
            }
        }
    }
}