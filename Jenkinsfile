pipeline {
    agent none
    stages {
        stage('Clone Repository') {
            agent { label 'build-agent' }
            steps {
                git branch: 'main', url: 'https://github.com/shoppingmall-platform/product-service.git'
            }
        }

        stage('Build Docker Image & Push') {
            agent { label 'build-agent' }
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com/', 'DOCKER_HUB_CREDENTIALS') {
                        sh """
                        docker buildx create --use
                        docker buildx build --platform linux/arm64 -t ${DOCKER_IMAGE} --push .
                        """
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
