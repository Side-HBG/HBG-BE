// Example usage
node {

    // 환경변수
    DOCKER_REGISTRY = 'vulcanos/hgb-be'
    NAMESPACE = 'hgb-backend'
    DEPLOYMENT = 'hgb-backend-deploy'
    K8S_PATH = './dev-ops/k8s/'

    git url: 'https://github.com/jenkinsci/git-tag-message-plugin'
    env.GIT_TAG_NAME = gitTagName()
    env.GIT_TAG_MESSAGE = gitTagMessage()

    // pipeline
    stage('build test') {
            // 테스트시 할 step
            echo 'build test'
            sh './gradlew test'
        }// Example usage
node {

    // 환경변수
    DOCKER_REGISTRY = 'vulcanos/hgb-be'
    NAMESPACE = 'hgb-backend'
    DEPLOYMENT = 'hgb-backend-deploy'
    K8S_PATH = './dev-ops/k8s/'

    git url: 'https://github.com/jenkinsci/git-tag-message-plugin'
    env.GIT_TAG_NAME = gitTagName()
    env.GIT_TAG_MESSAGE = gitTagMessage()

    // pipeline
    stage('build test') {
        // 테스트시 할 step
        echo 'build test'
        sh './gradlew test'
    }
    stage('SonarQube Analysis') {
        withSonarQubeEnv('sonarqube server') {
            sh "./gradlew sonar --warning-mode all"
        }
    }
    stage('build'){
        // 빌드시 할 step
        echo 'build'
        sh 'chmod +x gradlew'
        sh './gradlew build'
    }
    stage ('docker-build'){
        script{
            dockerImage = docker.build("${DOCKER_REGISTRY}")
        }
    }
    stage ('vulerability-scan'){
        script{
            sh 'sh ./dev-ops/trivy-image-scan.sh ${DOCKER_REGISTRY}'
        }
    }
    stage('docker-push'){
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

    stage('deploy'){
        script{
            sh '''
                kubectl apply -f ${K8S_PATH}
                kubectl rollout -n ${NAMESPACE} restart statefulset ${DEPLOYMENT}
            '''
        }
    }
}

/** @return The tag name, or `null` if the current commit isn't a tag. */
String gitTagName() {
    commit = getCommit()
    if (commit) {
        desc = sh(script: "git describe --tags ${commit}", returnStdout: true)?.trim()
        if (isTag(desc)) {
            return desc
        }
    }
    return null
}

/** @return The tag message, or `null` if the current commit isn't a tag. */
String gitTagMessage() {
    name = gitTagName()
    msg = sh(script: "git tag -n10000 -l ${name}", returnStdout: true)?.trim()
    if (msg) {
        return msg.substring(name.size()+1, msg.size())
    }
    return null
}

String getCommit() {
    return sh(script: 'git rev-parse HEAD', returnStdout: true)?.trim()
}

@NonCPS
boolean isTag(String desc) {
    match = desc =~ /.+-[0-9]+-g[0-9A-Fa-f]{6,}$/
    result = !match
    match = null // prevent serialisation
    return result
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
            echo 'build'
            sh 'chmod +x gradlew'
            sh './gradlew build'
        }
    }
    stage ('docker-build'){
        steps{
            script{
                dockerImage = docker.build("${DOCKER_REGISTRY}")
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

/** @return The tag name, or `null` if the current commit isn't a tag. */
String gitTagName() {
    commit = getCommit()
    if (commit) {
        desc = sh(script: "git describe --tags ${commit}", returnStdout: true)?.trim()
        if (isTag(desc)) {
            return desc
        }
    }
    return null
}

/** @return The tag message, or `null` if the current commit isn't a tag. */
String gitTagMessage() {
    name = gitTagName()
    msg = sh(script: "git tag -n10000 -l ${name}", returnStdout: true)?.trim()
    if (msg) {
        return msg.substring(name.size()+1, msg.size())
    }
    return null
}

String getCommit() {
    return sh(script: 'git rev-parse HEAD', returnStdout: true)?.trim()
}

@NonCPS
boolean isTag(String desc) {
    match = desc =~ /.+-[0-9]+-g[0-9A-Fa-f]{6,}$/
    result = !match
    match = null // prevent serialisation
    return result
}
