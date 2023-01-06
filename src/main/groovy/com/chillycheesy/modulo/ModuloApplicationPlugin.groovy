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
        final moduleExtension = project.extensions.create('module', ModuleExtension, project)
        final generateModuleYml = new GenerateModuleYmlTask(moduleExtension)
        final runModuloServer = new RunModuloServer(moduleExtension)
        final deployModule = new DeployModuleTask()

        final generateModuleYmlTask = generateModuleYml.generate(project)
        final runModuloServerTask = runModuloServer.generate(project)
        final deployModuleTask = deployModule.generate(project)

        project.processResources.dependsOn generateModuleYmlTask
        deployModuleTask.dependsOn project.assemble
        runModuloServerTask.dependsOn deployModuleTask

        project.compileJava.options.compilerArgs << '-parameters'
        project.compileTestJava.options.compilerArgs << '-parameters'
    }
}
