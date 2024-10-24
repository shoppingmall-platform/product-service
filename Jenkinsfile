pipeline {
    agent any

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/shoppingmall-platform/product-service.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    dockerImage = docker.build("${DOCKER_IMAGE}", "--platform linux/arm64 .")
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com/', 'DOCKER_HUB_CREDENTIALS') {
                        dockerImage.push()
                    }
                }
            }
        }

        stage('Deploy to Server') {
            steps {
                sshagent(['SSH_CREDENTIALS']) {
                    sh """
                    ssh -o StrictHostKeyChecking=no ubuntu@${DEPLOY_SERVER} '
                        cd docker-compose &&
                        docker pull ${DOCKER_IMAGE} &&
                        docker compose down &&
                        docker compose up -d
                    '
                    """
                }
            }
        }
    }
}