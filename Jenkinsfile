pipeline {
    agent any
    
    tools {
        jdk 'jdk-25'
        maven 'maven-3'
    }
    
    stages {
        stage('1. Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/VictorAndreiDan/minibank.git'
            }
        }
        
        stage('2. Build & Scan') {
            steps {
                withSonarQubeEnv('SonarQube') {
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