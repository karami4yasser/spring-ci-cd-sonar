
pipeline {
    agent any
    options {
        timeout(time: 1, unit: 'HOURS')
    }
    tools {
        maven '3.5.0'
    }
    environment {
        SONAR_PROJECT_KEY = "springApp"
        SONAR_PROJECT_NAME = "springApp"
        SONAR_HOST_URL = "http://sonarqube:9000 "

    }
    stages {
        stage("Compile Code") {
            steps {
                script {
                    def dockerHome = tool 'docker'
                    env.PATH = "${dockerHome}/bin:${env.PATH}"
                    sh "mvn clean compile -DskipTests"
                }
            }
        }

        stage("Run Tests") {
            steps {
                script {
                    sh "mvn test"
                }
            }
        }

        stage("Check Sonar Quality") {
            steps {
                script {
                    withCredentials([string(credentialsId: 'sonar_token', variable: 'SONAR_TOKEN')]) {

                        sh "mvn clean verify sonar:sonar -Dsonar.projectKey=${SONAR_PROJECT_KEY} " +
                                "-Dsonar.projectName=${SONAR_PROJECT_NAME} " +
                                "-Dsonar.host.url=${SONAR_HOST_URL} " +
                                " -Dsonar.token=${SONAR_TOKEN} " +
                                "-Dsonar.qualitygate.wait=true"
                    }
                }
            }
        }
        stage("build and publish docker image") {
            steps{
                script{
                    withCredentials([usernamePassword(credentialsId: 'DOCKER_HUB_CRED', passwordVariable: 'DOCKER_HUB_ACCESSTOKEN', usernameVariable: 'DOCKER_HUB_USERNAME')]) {

                        sh "docker build -t docker.io/${DOCKER_HUB_USERNAME}/springciapp:latest ."
                        sh "docker login -u ${DOCKER_HUB_USERNAME} -p ${DOCKER_HUB_ACCESSTOKEN}"
                        sh "docker push docker.io/${DOCKER_HUB_USERNAME}/springciapp:latest"
                    }

                }
            }
        }
        stage("run application") {
            steps{
                script{
                     withCredentials([usernamePassword(credentialsId: 'DOCKER_HUB_CRED', passwordVariable: 'DOCKER_HUB_ACCESSTOKEN', usernameVariable: 'DOCKER_HUB_USERNAME')]) {
                        sh 'docker stop springci 2> /dev/null || true'
                        sh 'docker rm springci 2> /dev/null || true'
                        sh "docker run -d --name springci -p 8400:8400 docker.io/${DOCKER_HUB_USERNAME}/springciapp:latest"
                    }
                }
            }
        }
    }
}