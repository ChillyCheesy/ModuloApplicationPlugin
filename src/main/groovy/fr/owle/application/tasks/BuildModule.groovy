package fr.owle.application.tasks

import fr.owle.application.extensions.FrontContainer
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class BuildModule extends DefaultTask {

    BuildModule() {
        def frontContainer = project.container(FrontContainer)
        extensions.add('fronts', frontContainer)
        extensions.getByName('fronts').each { fc ->
            dependsOn fc.buildTask
        }
    }

    @TaskAction
    def call() {
        extensions.getByName('fronts').each { frontContainer ->
            new AntBuilder().copy(todir: frontContainer.deployFrontContainer.into) {
                fileset(dir: frontContainer.deployFrontContainer.from)
            }
        }
    }

}
