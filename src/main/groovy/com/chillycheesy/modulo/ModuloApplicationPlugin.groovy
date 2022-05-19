package com.chillycheesy.modulo

import com.chillycheesy.modulo.extensions.ModuleExtension
import com.chillycheesy.modulo.tasks.DeployModuleTask
import com.chillycheesy.modulo.tasks.GenerateModuleYmlTask
import com.chillycheesy.modulo.tasks.RunModuloServer
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
        final def moduleExtension = project.extensions.create('module', ModuleExtension, project)
        final def generateModuleYml = new GenerateModuleYmlTask(moduleExtension)
        final def runModuloServer = new RunModuloServer(moduleExtension)
        final def deployModule = new DeployModuleTask()

        final def generateModuleYmlTask = generateModuleYml.generate(project)
        final def runModuloServerTask = runModuloServer.generate(project)
        final def deployModuleTask = deployModule.generate(project)

        project.processResources.dependsOn generateModuleYmlTask
        project.assemble.dependsOn deployModuleTask
        deployModuleTask.dependsOn project.assemble
        runModuloServerTask.dependsOn deployModuleTask
    }
}
