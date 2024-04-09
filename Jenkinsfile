
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
            // tdd
            app = docker.build("vulcanos/be-test") #Push Image 단계에서 빌드번호를 붙이기 때문에 옵션 제거
          }
    }
     stage('Push image') {
         docker.withRegistry('https://registry.hub.docker.com', 'docker-hub'){
             app.push("${env.BUILD_NUMBER}")
             app.push("latest")
     }
    stage('deploy'){
        steps{
          // dep
            echo 'deploy'
        }
    }
  }
}
