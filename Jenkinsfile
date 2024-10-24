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
                    docker.withRegistry('https://registry.hub.docker.com/', 'DOCKER_HUB_CREDENTIALS') {
                        def image = docker.image("${DOCKER_IMAGE}")
                        sh "docker buildx create --use --name multiarch"
                        sh """
                        docker buildx build \
                        --platform linux/amd64,linux/arm64 \
                        -t ${image.imageName()} \
                        --push .
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