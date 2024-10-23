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
                    dockerImage = docker.build("${DOCKER_IMAGE}")
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('', DOCKER_HUB_CREDENTIALS) {
                        dockerImage.push()
                    }
                }
            }
        }

        stage('Deploy to Server') {
            steps {
                sshagent([SSH_CREDENTIALS]) {
                    sh """
                    ssh -o StrictHostKeyChecking=no ubuntu@${DEPLOY_SERVER} '
                        docker pull ${DOCKER_IMAGE} &&
                        docker-compose down &&
                        docker-compose up -d
                    '
                    """
                }
            }
        }
    }
}
