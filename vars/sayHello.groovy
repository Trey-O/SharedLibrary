#!/usr/bin/env groovy

def call(String name = 'human') {
  checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], gitTool: 'Default', submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/Trey-O/pipeline-deployment-demo']]])
  echo "Hello, ${name}."
}
