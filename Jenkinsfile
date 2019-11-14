node{
    def app
    
    stage('Clone Repository'){
        git 'https://github.com/NNelo/IS3'
    }
    stage('Build Image')
    {
        dir('payroll/server/'){
            pwd()
            sh label: '', script: 'mvn clean package spring-boot:repackage'
            
            archive "target/surefire-reports/*.xml"
            archive "target/*.jar"
            junit 'target/surefire-reports/*.xml'
            
            app = docker.build("nnelo/test-java")
        }
    }
    stage('Test Image'){
        dir('payroll/server/'){
            //metricas de codigo
            sh label: '', script: 'mvn verify sonar:sonar -Dsonar.projectKey=NNelo_IS3 -Dsonar.organization=nnelo -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=e302ab970db0aeca67cdff50a5266c7ed92c6b98 -Dmaven.test.failure.ignore=true'
        }
        
        app.withRun('-p 8080:8080'){ c->
            sh "docker logs ${c.id}"
            sleep 20
        
            // test de integracion
            dir('payroll/intTest/'){
                sh label: '', script: 'npm i mocha-junit-reporter mocha-multi --save'
                sh label: '', script: 'npx codeceptjs run --steps --reporter mocha-multi'
            
                archive 'output/result.xml'
                junit 'output/result.xml'
            }
        }
    }
    stage('Push Image'){
        withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'password', usernameVariable: 'user')]) {
            sh "docker login -u ${user} -p ${password}"
            sh label: '', script: 'docker push nnelo/test-java'
        }
    }
}
