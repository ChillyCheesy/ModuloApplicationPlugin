package com.chillycheesy.modulo.tasks

import com.chillycheesy.modulo.extensions.ModuleExtension
import org.gradle.api.Project

class RunModuloServer implements ModuloTask {

    private ModuleExtension moduleExtension

    RunModuloServer(ModuleExtension moduleExtension) {
        this.moduleExtension = moduleExtension
    }

    @Override
    def generate(Project project) {
        return project.task('runModuloServer') {
            group = 'modulo'
            description = 'Run the Modulo server.'
            doLast {

            }
        }
    }

}
