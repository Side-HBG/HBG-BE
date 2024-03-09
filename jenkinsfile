pipeline{
agent any
stages {
  stage('build'){
      steps{
       // 빌드시 할 step
        echo 'build'
      }
    }
    stage ('test'){
          steps{
            // tdd
            echo 'test'
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
