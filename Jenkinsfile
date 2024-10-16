pipeline {
    agent any

    tools {
        maven "my-maven"
    }

    stages {
        stage('Build with Maven') {
            steps {
                sh 'mvn --version'
                sh 'java -version'
                sh 'mvn clean package -Dmaven.test.failure.ignore=true'
            }
        }

        stage('Building image') {

            steps {
                sh 'docker build -t fs-cms-v2-be .'
            }
        }

        stage('Deploying') {
            steps {
                echo 'Deploying and cleaning'
                sh 'echo y | docker container prune '

            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}