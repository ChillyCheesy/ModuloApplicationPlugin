package com.chillycheesy.modulo

import com.chillycheesy.modulo.extensions.ModuleExtension
import com.chillycheesy.modulo.tasks.GenerateModuleYmlTask
import org.gradle.api.Project
import org.gradle.api.Plugin

/**
 * The plugin class with the apply method.
 */
class ModuloApplicationPlugin implements Plugin<Project> {

    /**
     * Init the plugin with the project.
     * @param project The project who apply the plugin.
     */
    @Override
    void apply(Project project) {
        project.pluginManager.apply 'java-library'
        def moduleExtension = project.extensions.create('module', ModuleExtension, project)
        def generateModuleYml = new GenerateModuleYmlTask(moduleExtension)

        def generateModuleYmlTask = generateModuleYml.generate(project)
        project.processResources.dependsOn generateModuleYmlTask
    }
}
