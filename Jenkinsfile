
pipeline{
    environment{
        registry = "vulcanos/be-test"
        dockImage = ""
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
        stage ('dockerbuild'){
            steps{
                script{
                    // docker build
                    dockImage = docker.build(registry + ":${env.BUILD_NUMBER}")
                }
            }
        }
        stage('Push image') {
            steps{
                script{
                    // docker push
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-hub'){
                        docker.image(registry).push("${env.BUILD_NUMBER}")
                        docker.image(registry).push("latest")
                    }
                }
            }
        }
        stage('Deploy'){
            steps{
                // deploy
                echo 'deploy'
            }
        }
    }
}