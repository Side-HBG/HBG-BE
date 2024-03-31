
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
            echo 'wuktyb-sywjop-3gugJo' | docker login -u 'vulcanos' --password-stdin
            sh 'docker build -t vulcanos/be-test:1.0 .'
            sh 'docker push vulcanos/be-test:1.0'
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
