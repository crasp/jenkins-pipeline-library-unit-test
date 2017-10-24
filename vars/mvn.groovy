def call(parameters = [:]) {
    String goals = parameters.containsKey('goals') ? parameters.goals : 'clean install'
    String options = parameters.containsKey('options') ? parameters.options : ''

    List<String> optionList = []
    if (!options.isEmpty()) {
        optionList.add(options)
    }

    // Make sure these options are always active when running on Jenkins
    if (!(options.contains('-U') || options.contains('--update-snapshots'))) {
        optionList.add('--update-snapshots')
    }
    if (!(options.contains('-B') || options.contains('--batch-mode'))) {
        optionList.add('--batch-mode')
    }

    sh "mvn ${goals} ${optionList.join(' ')}"
}
