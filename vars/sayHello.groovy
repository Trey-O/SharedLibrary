#!/usr/bin/env groovy

def call(String name = 'human') {
  checkout([$class: 'GitSCM', branches: [[name: '*/master']], 
            doGenerateSubmoduleConfigurations: false, extensions: [], 
            gitTool: 'Default', submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/Trey-O/pipeline-deployment-demo']]])
  checkout([$class: 'GitSCM', branches: [[name: '*/master']], 
            doGenerateSubmoduleConfigurations: false, extensions: [], 
            gitTool: 'Default', submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/Trey-O/mvntest-core']]])

  setupStage()
}

def setupStage() {
  checkpoint("Rerun STAGE_NAME Deployment")
  
  input message: "Deploy to next STAGE?", submitter: "admin"
  
  node("master"){
    echo "Master Node request block" 
  }
  
  echo "Line just prior to deployTomcatCode method"
  
  server = "FAKE_SERVER_NAME"
  deployTomcatCode(server)
  
}

def deployTomcatCode(Object server) {
    echo "First Line in deployTomcatNode method before requesting fake node name"
    //return {
        node(server) {
          echo "IN the node(server) block"
            try {
                //Create symbolic link
                //fileSystemActions("createsym", "/app/tomcatprofile/" + env.tomcatprofiledirectory)
                //Shutdown Tomcat
                //commandActions("shutdown", server)
                //Remove existing application directories
                //fileSystemActions("remove", "/app/tomcatprofile/" + env.tomcatprofiledirectory + "/webapps/" + env.deploy_as + "*")
                //Download application war file from Artifactory
                //commandActions("downloadartifact", server)
                ////Start Tomcat
                //commandActions("startup", server)
                //Validate tomcat process started
                //commandActions("getpid", server)
                //Validate application started
                //commandActions("apprunning", server)

                //messagebox(pipe.setDeploymentDate())
                echo "RUNNING IN SERVER FAKE"
                return true
            } catch (Exception e) {
                errorMessage = e.getMessage()
                //emailext(body: "Deployment Exception - See ${env.BUILD_URL} \n\nNote: For additional Troubleshooting/Resolution information and assistance, please go to the following link OR contact your Product Champion: " + env.emailDocumentLink, subject: "ACTION REQUIRED: " + env.productname + " Jenkins Job has failed - Puppet Error (Please review Logs/Documentation)", to: env.emailAddressFail)
                error(errorMessage)
            } finally {
                try {
                    //Remove lock file
                    //fileSystemActions("remove", "/app/tomcatprofile/" + env.tomcatprofiledirectory + "/deploy.lock")
                    //Remove symbolic link
                    //fileSystemActions("remove", "/app/tomcatprofile/" + env.tomcatprofiledirectory + "/deploy")
                } catch (Exception e) {
                }
            }
        //}
    }
}
