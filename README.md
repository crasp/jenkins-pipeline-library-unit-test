# jenkins-pipeline-library-unit-test

This is a [Jenkins pipeline library](https://plugins.jenkins.io/workflow-cps-global-lib) that defines common tasks used
in Jenkins [declarative pipelines](https://jenkins.io/doc/book/pipeline/syntax/) with a high focus on testing.

## Jenkinsfile Code Examples

### Execute Maven
```
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                // Use standard goals and options
                mvn()
                // Use custom goals
                mvn(goals: 'clean compile')
                // Use custom options
                mvn(options: '-DskipTests=false')
            }
        }
    }
}
```

## Tests

In order to be able to test what goes into this library, we make use of 
[JenkinsPipelineUnit](https://github.com/jenkinsci/JenkinsPipelineUnit) to test our changes.
This framework is designed to test pipeline definitions and external loaded pipeline libraries.
To get it to work with this project we do a little trick:
- Replace the default compiler with the gmavenplus compiler to properly handle groovy sources
- During the maven `validate` phase, copy the `${basedir}/vars` directory 
to `${basedir}/target/library-under-test@master/vars`
- Configure all unit tests to have those variables registered as `localSource`
- After running `mvn clean compile` all global variables are up-to-date for testing


## Useful links for developers

### Other extensive libraries for inspiration

* https://github.com/fabric8io/fabric8-pipeline-library
* https://github.com/kubic-project/jenkins-library
* https://github.com/jenkins-infra/pipeline-library
