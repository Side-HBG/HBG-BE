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
            echo 'dockerbuild'
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
