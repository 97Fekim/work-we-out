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
                        docker stop ${CONTAINER_NAME}
                        sleep 1
                        docker rm ${CONTAINER_NAME}
                    }catch(e){
                        echo "Error caused in Clean"
                    }
                }
            }
        }
        stage('Build') {
            steps {
                docker build -t ${NAME} .
            }
        }
        stage('Deploy'){
            steps {
                docker tag ${NAME}:latest ${NAME}:${VERSION}
                docker run -d --name=${CONTAINER_NAME} -p 8080:8080 ${NAME}:latest
            }
        }
    }
}
