def call(Map parameters = [:]) {
    String goals = parameters.containsKey('goals') ? parameters.goals : 'clean install'
    String options = parameters.containsKey('options') ? parameters.options : ''

    options += ' --update-snapshots --batch-mode'

    sh "mvn ${goals} ${options}"
}
