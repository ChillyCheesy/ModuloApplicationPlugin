package fr.owle.application

import org.gradle.api.Project


class ModuleExtension {

    String moduleName
    String main
    String version
    String target
    List<String> authors = ['You']
    List<String> dependencies = []
    List<String> softDependencies = []

    def init(Project project) {
        moduleName = project.name
        version = project.version
        main = "${project.group.toString()}.${moduleName}"
        target = "src/main/resources"
    }

}
