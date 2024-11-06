pipeline {
    agent any
    tools{
        maven 'maven_3_5_0'
    }
    stages{
        stage('Build Maven'){
            steps{
                checkout scmGit(branches: [[name: '*/football-application-1']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Mayurbhatunchalli/football-application']])
                bat 'mvn clean install'
            }
        }
        stage('Build docker image'){
            steps{
                bat 'docker build -t mayurbhatunchalli/football-devops-integration .'
            }
        }
        stage('Push image to the hub'){
            steps{
                script{
                    withCredentials([usernamePassword(credentialsId: 'docker-pwd', passwordVariable: 'DOCKER_PASS', usernameVariable: 'DOCKER_USER')]) {
                        bat "docker login -u %DOCKER_USER% -p %DOCKER_PASS%"
                    }
                    bat 'docker push  mayurbhatunchalli/football-devops-integration'
                }
            }
        }
    }
}