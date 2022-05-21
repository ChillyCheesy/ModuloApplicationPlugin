package com.chillycheesy.modulo.tasks

import org.gradle.api.Project

class DeployModuleTask implements ModuloTask {

    @Override
    def generate(Project project) {
        return project.task('deployModuleToLocalModuloServer') {
            group = 'modulo'
            description = 'Deploy the module to the local modulo server module file.'
            doLast {
                final moduleSrc = project.fileTree("$project.buildDir/libs")
                final moduleDest = project.file('modulo/modules')
                assert moduleDest.exists() || moduleDest.mkdirs()
                project.copy {
                    from moduleSrc
                    into moduleDest
                    include '*.jar'
                }
            }
        }
    }

}
