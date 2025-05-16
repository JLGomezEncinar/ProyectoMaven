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
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar \
                      -Dsonar.projectKey=Proyecto-Maven \
                      -Dsonar.projectName='Proyecto Maven' \
                      -Dsonar.host.url=http://174.129.73.153:9000 \
                      -Dsonar.token=sqp_c1f307cbc2a1857898f6e7ac2fa81e166d040826'
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
                anyOf {
            branch 'master'
            expression { env.BRANCH_NAME == 'origin/master' }
            expression { env.GIT_BRANCH == 'origin/master' }
        }
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
