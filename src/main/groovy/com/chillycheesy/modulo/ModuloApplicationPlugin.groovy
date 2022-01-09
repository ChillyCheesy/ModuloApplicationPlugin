package com.chillycheesy.modulo

import com.chillycheesy.modulo.extensions.ModuleExtension
import com.chillycheesy.modulo.tasks.GenerateModuleYmlTask
import org.gradle.api.Project
import org.gradle.api.Plugin

class ModuloApplicationPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.pluginManager.apply 'java-library'
        def moduleExtension = project.extensions.create('module', ModuleExtension, project)
        def generateModuleYml = new GenerateModuleYmlTask(moduleExtension)

        def generateModuleYmlTask = generateModuleYml.generate(project)
        project.processResources.dependsOn generateModuleYmlTask
    }
}
