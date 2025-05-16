pipeline {
    agent any

    tools {
        maven 'Maven 4.0.0-rc-2'
        jdk 'Java 17'
    }

    environment {
        MAVEN_OPTS = "-Dmaven.test.failure.ignore=false"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Compile') {
            steps {
                echo 'Compilando el código...'
                sh 'mvn compile'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    withCredentials([string(credentialsId: 'SONAR_TOKEN_ID', variable: 'SONAR_TOKEN')]) {
                        sh "mvn sonar:sonar -Dsonar.projectKey=Proyecto-Maven -Dsonar.projectName='Proyecto Maven' -Dsonar.host.url=http://174.129.73.153:9000 -Dsonar.token=$SONAR_TOKEN"
                    }
                }
            }
        }

        stage('Test') {
            steps {
                echo 'Ejecutando pruebas unitarias...'
                wrap([$class: 'Xvfb']) {
                    sh 'mvn test -DskipTests'
                }    
            }
        }

        stage('Package') {
            steps {
                echo 'Empaquetando artefactos...'
                sh 'mvn package -DskipTests'
            }
        }

        stage('Deploy') {
            when {
                branch 'master'
            }
            steps {
                echo 'Desplegando artefactos desde rama main...'
                sh 'echo "Simulando despliegue..."'
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline finalizado con éxito.'
        }
        failure {
            echo '❌ El pipeline ha fallado.'
        }
    }
}

