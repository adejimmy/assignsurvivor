pipeline {
 agent any

 environment{
  GITHUB_ORG='userdetails'
  CONTAINER_REGISTRY="ghcr.io/${GITHUB_ORG}/"
  ARTIFACT_ID=readMavenPom().getArtifactId()
  JAR_NAME="${ARTIFACT_ID}-${BUILD_NUMBER}"
  IMAGE_NAME="${CONTAINER_REGISTRY}${ARTIFACT_ID}"
 }
 stages{
   stage('Build Application'){
     step{
       sh  'echo Performing Maven Build using Shell command: ${CONTAINER_REGISTRY}'
      }
   }


     stage('Build Container Image'){
       step{
         sh  'echo Performing Container Image: ${IMAGE_NAME}'
        }
     }


      stage('Publishing Container Image'){
        step{
          sh  'echo Publishing Container Image: ${CONTAINER_REGISTRY}'
         }
       }

 }
}
