package com.chillycheesy.modulo.extensions

import org.gradle.api.Project

/**
 * The ModuleExtension store plugin.yml data.
 */
class ModuleExtension {

    /**
     * Define name.
     * **Default: the project name.**
     */
    String moduleName
    /**
     * Define main.
     * **Default: {the project group}.{the project name}**
     */
    String main
    /**
     * Define version.
     * **Default: the project version.**
     */
    String version
    /**
     * Define the path where generate the plugin.yml file
     */
    String target
    /**
     * Define authors.
     * **Default: src/main/resources**
     */
    List<String> authors = ['ChillyCheesy']
    /**
     * Define dependencies.
     */
    List<String> dependencies = []
    /**
     * Define softDependencies
     */
    List<String> softDependencies = []

    /**
     * Init the extension with default values.
     * @param project The target project.
     */
    ModuleExtension(Project project) {
        moduleName = project.name
        version = project.version
        main = "${project.group.toString()}.${moduleName}"
        target = "${project.projectDir.path}/src/main/resources"
    }

    /**
     * Set the name.
     * @param moduleName The new name.
     */
    void moduleName(String moduleName) {
        this.moduleName = moduleName
    }

    /**
     * Set the main.
     * @param main The new main.
     */
    void main(String main) {
        this.main = main
    }

    /**
     * Set the version.
     * @param version The new Version.
     */
    void version(String version) {
        this.version = version
    }

    /**
     * Set the path where generate the plugin.yml file.
     * @param target The new path
     */
    void target(String target) {
        this.target = target
    }

    /**
     * Set the authors.
     * @param authors The new authors List.
     */
    void authors(List<String> authors) {
        this.authors = authors
    }

    /**
     * Set the dependencies
     * @param dependencies The new dependencies List.
     */
    void dependencies(List<String> dependencies) {
        this.dependencies = dependencies
    }

    /**
     * Set the soft dependencies
     * @param softDependencies The new soft dependencies List.
     */
    void softDependencies(List<String> softDependencies) {
        this.softDependencies = softDependencies
    }

}
