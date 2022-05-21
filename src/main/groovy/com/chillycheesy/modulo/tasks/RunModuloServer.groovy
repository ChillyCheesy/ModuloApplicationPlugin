package com.chillycheesy.modulo.tasks

import com.chillycheesy.modulo.extensions.ModuleExtension
import org.gradle.api.Project

class RunModuloServer implements ModuloTask {

    private final def LINK = 'https://s01.oss.sonatype.org/content/repositories/releases/com/chillycheesy/modulo-server'

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
                final file = new File("${project.rootDir.path}/modulo/modulo-server-${moduleExtension.moduloVersion}.jar")
                assert file.parentFile.exists() || file.parentFile.mkdirs()
                project.delete(project.fileTree(file.parent) {
                    include '*.jar'
                })
                if (!file.exists()) downloadFile(file)
                project.javaexec {
                    main = '-jar'
                    args = [file.absolutePath]
                    workingDir = 'modulo'
                    standardInput = System.in
                    standardOutput = System.out
                }
            }
        }
    }

    private def downloadFile(File file) {
        def url = "$LINK/${moduleExtension.moduloVersion}/${file.getName()}"
        println "Downloading ${url} to ${file.getPath()}..."
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
