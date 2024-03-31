pipeline{
agent any
  
stages {
  stage('build'){
      steps{
       // 빌드시 할 step
        echo 'build'
        ch 'chmod +x gradlew'
        ch './gradlew clean'
        ch './gradlew build'
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
