package com.chillycheesy.modulo.tasks

import com.chillycheesy.modulo.extensions.ModuleExtension
import org.gradle.api.Project

class RunModuloServer implements ModuloTask {

    private final def LINK = 'https://s01.oss.sonatype.org/content/repositories/releases/com/chillycheesy/modulo-server/'

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
                final File file = new File("modulo/modulo-server-${moduleExtension.version}.jar")
                if (!file.exists()) downloadFile(file)
                ////////////////////////////////////////////////////////////////////////////////////////////////
            }
        }
    }

    private def downloadFile(File file) {
        def url = "$LINK:${moduleExtension.version}/${file.getName()}"
        new URL(url).openConnection().with { connection ->
            connection.instanceFollowRedirects = false
            url = connection.getHeaderField("Location")
            if(!url) {
                file.withOutputStream { outputStream ->
                    connection.inputStream.with { inputStream ->
                        outputStream << inputStream
                        inputStream.close()
                    }
                }
            }
        }
    }

}
