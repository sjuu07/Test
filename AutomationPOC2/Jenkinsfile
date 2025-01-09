pipeline {
    agent any

    stages {
         stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Publish Test Results') {
            steps {
               publishTestNGResults testResults: '**/test-output/testng-results.xml'   // For TestNG test reports

            }
        }

        stage('Publish Reports') {
            steps {
                publishHTML(target: [
                    reportDir: 'target/html-report',
                    reportFiles: 'index.html',
                    reportName: 'Test Automation Report'
                ])
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
