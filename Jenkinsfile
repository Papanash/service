pipeline {
    agent any
    environment {
        DOCKER_USER = credentials("docker_hub_username")
        DOCKER_PASSWORD = credentials("docker_hub_password")
        GITHUB_TOKEN = credentials("github_token")
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
                    sh([script: 'git fetch --tag', returnStdout: true]).trim()
                    env.LAST_TAG = sh([script: 'git tag | sort --version-sort | tail -1', returnStdout: true]).trim()
                    if (env.LAST_TAG) {
                        (env.MAJOR_VERSION, env.MINOR_VERSION, env.PATCH_VERSION) = env.LAST_TAG.tokenize('.')
                        env.MINOR_VERSION = env.MINOR_VERSION.toInteger() + 1
                    } else {
                        env.MAJOR_VERSION = 0
                        env.MINOR_VERSION = 0
                        env.PATCH_VERSION = 0
                    }
                    env.IMAGE_TAG = "${env.MAJOR_VERSION}.${env.MINOR_VERSION}.${env.PATCH_VERSION}"
                }
                sh "docker build -t mirceap24/hello-img:${env.IMAGE_TAG} ."
                sh "docker login docker.io -u ${DOCKER_USER} -p ${DOCKER_PASSWORD}"
                sh "docker push mirceap24/hello-img:${env.IMAGE_TAG}"
                sh "git tag ${env.IMAGE_TAG}"
                sh "git push https://$GITHUB_TOKEN@github.com/Papanash/service.git ${env.IMAGE_TAG}"
            }
        }

        stage('Docker execution') {
            steps {
                sh "IMAGE_TAG=${env.IMAGE_TAG} docker-compose up -d hello"
            }
        }

        stage('Tests') {
            steps {
                sh "./gradlew test"
                sh "./gradlew testIT"
            }
        }
    }
}
