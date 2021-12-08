package com.owle.modulo.extensions

import org.gradle.api.Project

class ModuleExtension {

    String moduleName
    String main
    String version
    String target
    List<String> authors = ['Owle']
    List<String> dependencies = []
    List<String> softDependencies = []

    ModuleExtension(Project project) {
        moduleName = project.name
        version = project.version
        main = "${project.group.toString()}.${moduleName}"
        target = "${project.projectDir.path}/src/main/resources"
    }

    void moduleName(String moduleName) {
        this.moduleName = moduleName
    }

    void main(String main) {
        this.main = main
    }

    void version(String version) {
        this.version = version
    }

    void target(String target) {
        this.target = target
    }

    void mainPageName(String mainPageName) {
        this.mainPageName = mainPageName
    }

    void authors(List<String> authors) {
        this.authors = authors
    }

    void dependencies(List<String> dependencies) {
        this.dependencies = dependencies
    }

    void softDependencies(List<String> softDependencies) {
        this.softDependencies = softDependencies
    }

}
