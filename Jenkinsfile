def docker_password = credentials('wuktyb-sywjop-3gugJo')

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
            sh 'docker login -u vulcanos -p ${docker_password}'
            sh 'docker build -t vulcanos/be-test:1.0 .'
            sh 'docker push vulcanos/be-test:1.0'
          }
    }
    stage('deploy'){
        steps{
          // dep
            echo 'deploy'
            sh 'docker image ls'
        }
    }
  }
}
