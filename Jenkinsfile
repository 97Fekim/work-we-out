pipeline{
    agent any

    environment {
       CONTAINER_NAME="work-we-out"
       NAME = "work-we-out"
	   VERSION = "${env.BUILD_ID}-${env.GIT_COMMIT}"
	   GIT_URL="http://180.233.234.220:2014/git/work-we-out.git"
    }
    stages {
        stage('Pull') {
            steps {
                git url:"${GIT_URL}", branch:"master", poll:true, changelog:true,credentialsId: 'Fekim'
            }
        }
        stage('Clean'){
            steps{
                script {
                    try{
                        sh "docker stop ${CONTAINER_NAME}"
                        sleep 1
                        sh "docker rm ${CONTAINER_NAME}"
                    }catch(e){
                        sh 'exit 0'
                    }
                }
            }
        }
        stage('Build') {
            steps {
                sh "docker build -t ${NAME} ."
            }
        }
        stage('Deploy'){
            steps {
                sh "docker tag ${NAME}:latest ${NAME}:${VERSION}"
                sh "docker run -d --name=${CONTAINER_NAME} -p 8080:8080 ${NAME}:latest"
            }
        }
    }
}
