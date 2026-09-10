pipeline {
    agent any

    tools {
        jdk 'JDK 21'
        maven 'M3'
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                checkout scm
            }
        }

        stage('Clean') {
            steps {
                echo 'Cleaning project...'
                bat 'mvn clean'
            }
        }

        stage('Compile') {
            steps {
                echo 'Compiling project...'
                bat 'mvn compile'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                echo 'Creating Maven package...'
                bat 'mvn package -DskipTests'
            }
        }
    }

    post {
        success {
            echo 'ALL PIPELINE STAGES PASSED SUCCESSFULLY'
        }

        failure {
            echo 'PIPELINE FAILED - CHECK THE FAILED STAGE'
        }

        always {
            echo 'Pipeline execution completed.'
        }
    }
}