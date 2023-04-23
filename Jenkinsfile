pipeline {
    agent any
    environment {
        DOCKER_PASSWORD = credentials("docker_password")
        VERSION_MAJOR = 0
        VERSION_MINOR = 0
        VERSION_PATCH = 0
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
                    VERSION_MAJOR = sh([script: 'git describe --abbrev=0 --tags | cut -d . -f 1', returnStdout: true]).trim()
                    VERSION_MINOR = sh([script: 'git describe --abbrev=0 --tags | cut -d . -f 2', returnStdout: true]).trim()
                    VERSION_PATCH = sh([script: 'git describe --abbrev=0 --tags | cut -d . -f 3', returnStdout: true]).trim()
                    NEW_MINOR_VERSION = Integer.parseInt(VERSION_MINOR) + 1
                }
                sh "docker build -t mirceap24/hello-img:${VERSION_MAJOR}.${NEW_MINOR_VERSION}.${VERSION_PATCH} ."
                sh "docker login docker.io -u mirceap24 -p parola123!"
                sh "docker push mirceap24/hello-img:${VERSION_MAJOR}.${NEW_MINOR_VERSION}.${VERSION_PATCH}"
            }
        }
    }
}