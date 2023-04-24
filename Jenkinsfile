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

        stage('Tag, Build and Push image') {
            steps {
                script {
                    sh([script: 'git fetch --tag', returnStdout: true]).trim()
                    env.MAJOR_VERSION = sh([script: 'git tag | sort --version-sort | tail -1 | cut -d . -f 1', returnStdout: true]).trim()
                    env.MINOR_VERSION = sh([script: 'git tag | sort --version-sort | tail -1 | cut -d . -f 2', returnStdout: true]).trim()
                    env.PATCH_VERSION = sh([script: 'git tag | sort --version-sort | tail -1 | cut -d . -f 3', returnStdout: true]).trim()
                    env.IMAGE_TAG = "${env.MAJOR_VERSION}.\$((${env.MINOR_VERSION} + 1)).${env.PATCH_VERSION}"
                }
                sh "docker build -t mirceap24/hello-img:${env.IMAGE_TAG} ."
                sh "docker login docker.io -u mirceap24 -p parola123!"
                sh "docker push mirceap24/hello-img:${env.IMAGE_TAG}"
                sh "git tag ${env.IMAGE_TAG}"
                sh "git push https://$GITHUB_TOKEN@github.com/Papanash/service.git ${env.IMAGE_TAG}"
            }
        }

        stage('Docker/Kubernetes execution') {
            steps {
                //sh "IMAGE_TAG=${env.IMAGE_TAG} docker-compose up -d hello"
                sh 'kubectl apply -f kubernetes/hello.yaml'
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
