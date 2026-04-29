pipeline {
    agent any
    tools {
        jdk 'jdk-25'
    }
    stages {
        stage('1. Checkout Code') {
            steps {
                // UPDATE THIS URL TO YOUR NEW REPO
                git branch: 'main', url: 'https://github.com/VictorAndreiDan/minibank.git'
            }
        }
        stage('2. Build & Scan') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    // Using standard Maven since we don't need the wrapper (mvnw) for this tiny app
                    sh 'mvn clean package sonar:sonar -Dsonar.projectKey=Mini-Bank'
                }
            }
        }
        stage('3. Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}