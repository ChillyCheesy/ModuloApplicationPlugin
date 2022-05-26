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
     * Modulo api version.
     */
    String moduloVersion = 'BINKS-0.1.1'

    /**
     * Init the extension with default values.
     * @param project The target project.
     */
    ModuleExtension(Project project) {
        moduleName = project.name
        target = "${project.projectDir.path}/src/main/resources"
    }

}
