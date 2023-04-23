pipeline {
    agent any
    environment {
        DOCKER_USER = credentials("docker_hub_username")
        DOCKER_PASSWORD = credentials("docker_hub_password")
    }

    stages {
        stage('Build & Test') {
            steps {
                sh './gradlew clean build'
            }
        }
        stage('Tag image') {
            steps {
                script {
                    env.MAJOR_VERSION = sh(script: 'git tag | sort --version-sort | tail -1 | cut -d . -f 1', returnStdout: true).trim()
                    env.MINOR_VERSION = sh(script: 'git tag | sort --version-sort | tail -1 | cut -d . -f 2', returnStdout: true).trim().toInteger()
                    env.PATCH_VERSION = sh(script: 'git tag | sort --version-sort | tail -1 | cut -d . -f 3', returnStdout: true).trim()
                    env.NEW_MINOR_VERSION = env.MINOR_VERSION + 1
                    env.IMAGE_VERSION = "${env.MAJOR_VERSION}.${env.NEW_MINOR_VERSION}.${env.PATCH_VERSION}"
                }
                sh "docker build -t ${DOCKER_USER}/hello-img:${env.IMAGE_VERSION} ."
                withCredentials([string(credentialsId: 'docker_hub_password', variable: 'DOCKER_PASSWORD')]) {
                    sh "docker login docker.io -u ${DOCKER_USER} -p ${DOCKER_PASSWORD}"
                }
                sh "docker push ${DOCKER_USER}/hello-img:${env.IMAGE_VERSION}"
            }
        }
    }
}
