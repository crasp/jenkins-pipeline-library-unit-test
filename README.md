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
-- TODO --

## Useful links for developers

### Other extensive libraries for inspiration

* https://github.com/fabric8io/fabric8-pipeline-library
* https://github.com/kubic-project/jenkins-library
* https://github.com/jenkins-infra/pipeline-library
