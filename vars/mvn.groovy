def call(parameters = [:]) {
    String goals = parameters.containsKey('goals') ? parameters.goals : 'clean install'
    String options = parameters.containsKey('mavenOpts') ? parameters.mavenOpts : ''
    String properties = parameters.containsKey('properties') ? parameters.properties : ''

    options += "${options.isEmpty() ? '' : ' '}--update-snapshots --batch-mode"

    sh "mvn ${goals} ${options}${properties.isEmpty() ? '' : " ${properties}"}"
}
