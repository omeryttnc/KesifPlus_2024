pipeline {
  agent any

  tools {nodejs "21.7.1"}

  stages {

    stage('Running collection') {
      steps {
        bat 'newman run "https://api.getpostman.com/collections/23507450-c8154f91-92de-49d4-8007-464bdc8fea15?apikey=PMAK-65f95816480e7100010a97ec-dd63c133bec185c4a6e753a6337d2fb9fd" -e "https://api.getpostman.com/environments/23507450-d5752f0c-ecf8-420d-999b-48e7fcd7094b?apikey=PMAK-65f95816480e7100010a97ec-dd63c133bec185c4a6e753a6337d2fb9fd" -d "FridayProjects/src/test/resources/PostmanResource/userinfo.json" -r htmlextra --reporter-htmlextra-export "target/report/newman.html"'
      }
    }
  }

  post {
      always {
          emailext attachmentsPattern: 'target/report/newman.html', body: '', subject: 'newman rapor', to: 'schukrukilic@gmail.com'

      }
  }
}