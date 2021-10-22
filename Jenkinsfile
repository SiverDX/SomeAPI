pipeline {
    agent { dockerfile true }

    stages {
        stage('test') {
            steps {
                sh 'curl get -v localhost:8081/orders'
            }
        }
    }
}
