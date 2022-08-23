pipeline {
   agent any
   
   tools { maven 'Maven' }

   environment {
     SERVICE_NAME = "api-gateway"
     REPOSITORY_TAG="644435390668.dkr.ecr.eu-central-1.amazonaws.com/yair-fleetman"
   }

   stages {

      stage('Build') {
         steps {
            echo '====================================='
            echo 'BUILD'
            echo '====================================='
            sh 'mvn clean package'
         }
      }

      stage ('test') {
         steps {
            echo 'No Tests Yet'
         }
      }

      stage('Build and Push Image') {
         when { expression { env.GIT_BRANCH == 'master' } }
         steps {
            script {
               sh """#!/bin/bash
                  aws ecr get-login-password --region eu-central-1 | \
                  docker login --username AWS --password-stdin 644435390668.dkr.ecr.eu-central-1.amazonaws.com
                  docker build -t ${REPOSITORY_TAG}:${SERVICE_NAME}-1.0 .
                  docker push ${REPOSITORY_TAG}:${SERVICE_NAME}-1.0
                  """
            }
         }
      }

      stage('Trigger update manifests') {
         when { expression { env.GIT_BRANCH == 'master' } }
         steps {
            script {
               echo '====================================='
               echo 'Triggering update manifest job'
               echo '====================================='
               build job: 'update-fleetman-manifest', parameters: [string(name: 'SERVICE', value: env.SERVICE_NAME),
               string(name: 'GIT_COMMITTER_NAME', value: env.GIT_COMMITTER_NAME),
               string(name: 'GIT_COMMITTER_EMAIL', value: env.GIT_COMMITTER_EMAIL)

               
               ]
            }
         }
      }      
   }

   post {
    always {
      cleanWs()
    }
  }
}
