pipeline {
    agent any

    tools {
        maven 'Maven 4.0.0-rc-2' // Asegúrate de configurar esta versión en Jenkins (Manage Jenkins > Global Tool Configuration)
        jdk 'Java 17'       // Lo mismo: configura en Jenkins la instalación del JDK y dale este nombre
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

        stage('Test') {
            steps {
                echo 'Ejecutando pruebas unitarias...'
                wrap([$class: 'Xvfb']) {
                    sh 'mvn test'
                }    
            }
        }

        stage('Package') {
            steps {
                echo 'Empaquetando artefactos...'
                sh 'mvn package'
            }
        }

        stage('Deploy') {
            when {
                branch 'master'
            }
            steps {
                echo 'Desplegando artefactos desde rama main...'
                // Reemplaza esta línea por tu estrategia real (por ejemplo: scp, docker push, etc.)
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
