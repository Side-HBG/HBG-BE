
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
        stage ('dockerbuild'){
            steps{
                script{
                    // docker build
                    docker.build("vulcanos/be-test")
                }
            }
        }
        stage('Push image') {
            steps{
                script{
                    // docker push
                    withCredentials([string(credentialsId: 'docker-hub', variable: 'DOCKER_HUB')]) {
                        sh "docker login -u vulcanos -p ${DOCKER_HUB}"
                    }
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-hub'){
                        app.push("${env.BUILD_NUMBER}")
                        app.push("latest")
                }
            }
        }
        stage('deploy'){
            steps{
                // dep
                echo 'deploy'
            }
        }
    }
}