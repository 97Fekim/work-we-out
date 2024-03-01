pipeline{
    agent any

    environment {
       CONTAINER_NAME="work-we-out"
       NAME = "work-we-out"
	   VERSION = "${env.BUILD_ID}-${env.GIT_COMMIT}"
	   GIT_URL="https://github.com/97Fekim/work-we-out.git"
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
                        bat "docker stop ${CONTAINER_NAME}"
                        sleep 1
                        bat "docker rm ${CONTAINER_NAME}"
                    }catch(e){
                        bat 'exit 0'
                    }
                }
            }
        }
        stage('Build') {
            steps {
                bat "docker build -t ${NAME} ."
            }
        }
        stage('Deploy'){
            steps {
                bat "docker tag ${NAME}:latest ${NAME}:${VERSION}"
                bat "docker run -d --name=${CONTAINER_NAME} -p 8080:8080 ${NAME}:latest"
            }
        }
    }
}
