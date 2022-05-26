package com.chillycheesy.modulo.tasks

import org.gradle.api.Project

/**
 * Define deployModuleToLocalModuloServer Task.
 * This task build your module and copy it on the ```modulo-server/modules``` folder at the root of your project.
 */
class DeployModuleTask implements ModuloTask {

    /**
     * Create the task.
     * @param project Target project.
     * @return The new task
     */
    @Override
    def generate(Project project) {
        return project.task('deployModuleToLocalModuloServer') {
            group = 'modulo'
            description = 'Deploy the module to the local modulo server module file.'
            doLast {
                final moduleSrc = project.fileTree("$project.buildDir/libs")
                final moduleDest = project.file('modulo-server/modules')
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
