pipeline {

    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Compile') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package -DskipTests'
            }
        }
    }

    post {

        success {
            echo 'Employee Access Eligibility CI/CD pipeline completed successfully.'
        }

        failure {
            echo 'Pipeline failed. Please check the build logs.'
        }
    }
}