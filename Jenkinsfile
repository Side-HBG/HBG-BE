
pipeline{
    environment{
        // 환경변수
        DOCKER_REGISTRY = 'vulcanos/hgb-be'
        NAMESPACE = 'hgb-backend'
        DEPLOYMENT = 'hgb-backend-deploy'
        K8S_PATH = './dev-ops/k8s/'
        BUILD_VERSION = """${sh(
                returnStdout:true,
                script:'git describe --tags --abbrev=0 | tr -d \'\12\''
            )}"""
    }
    agent any
    stages {
        stage('build test') {
            steps {
                // 테스트시 할 step
                echo 'build test step'
                sh '''
                    ./gradlew test
                '''
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
                echo 'build step'
                sh './gradlew clean build -Pversion=${BUILD_VERSION}-SNAPSHOT'
            }
        }
        stage ('docker-build'){
            steps{
                script{
                    dockerImage = docker.build("${DOCKER_REGISTRY}", "--build-arg BUILD_VERSION=${BUILD_VERSION} .")
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
                        dockerImage.push("${BRANCH_NAME}")
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
                        kubectl apply -f ${K8S_PATH}
                        kubectl rollout -n ${NAMESPACE} restart statefulset ${DEPLOYMENT}
                    '''
                }
            }
        }
    }
}