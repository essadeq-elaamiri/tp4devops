pipeline {
  environment {
    registry = "elaamiri/tp4devops"
    registryCredential = '<dockerhub_credential_ID>'
    dockerImage = ''
  }
  agent any
  stages {
    stage('Cloning Git') {
      steps {
        
        git branch: 'main',
        url: 'https://github.com/essadeq-elaamiri/tp4devops.git'
      }
    }
    stage('Building image') {
      steps {
        script {
          dockerImage = docker.build registry + ":$BUILD_NUMBER"
        }
      }
    }
    stage('Publish Image') {
      steps {
        script {
          docker.withRegistry('', registryCredential) {
            dockerImage.push()
          }
        }
      }
    }
  }
}


