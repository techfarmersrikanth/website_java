pipeline {
    agent any
    tools {
        maven 'maven'
    }
    
    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/techfarmersrikanth/website_java.git' // Use your Git repository URL
            }
        }
        
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        
        stage('Test') {
            steps {
                sh 'mvn package'
            }
        }
        
        stage('SonarQube Analysis') {
            environment {
                SONAR_HOST_URL = 'http://54.166.83.75:9000' // Replace with your SonarQube URL
                SONAR_AUTH_TOKEN = credentials('sonarqube') // Store your token in Jenkins credentials
            }
            steps {
                sh 'mvn sonar:sonar -Dsonar.projectKey=java-website -Dsonar.host.url=$SONAR_HOST_URL -Dsonar.login=$SONAR_AUTH_TOKEN'
            }
        }
        stage('artifact upload') {
            steps {
                nexusArtifactUploader artifacts: [[artifactId: 'devops-learning', classifier: '', file: 'target/devops-learning-1.0.war', type: '.war']], credentialsId: 'nexus', groupId: 'com.demo', nexusUrl: '54.205.116.26:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'netflix', version: '1.0'
        }
        
        }
        stage('Deploy to Tomcat') {
            steps {
                 script {
                    // Dynamically detect the WAR file built by Maven
                    def warFile = sh(script: "ls target/*.war | head -n 1", returnStdout: true).trim()

                    echo "Deploying WAR file: ${warFile}"

                    deploy adapters: [tomcat9(alternativeDeploymentContext: '', credentialsId: 'tomcat-deploy', path: '', url: 'http://44.204.240.182:8080')], contextPath: 'myapp', war: warFile
                                 }     
            }
        }
                
            }
}
