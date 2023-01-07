package com.chillycheesy.modulo.tasks

import com.chillycheesy.modulo.extensions.ModuleExtension
import org.gradle.api.Project

/**
 * Define the runModuloServer task.
 * This task download and run a modulo server.
 * The server is downloaded from the official repository at the <code>modulo-server</code> folder.
 */
class RunModuloServer implements ModuloTask {

    /**
     * Official repository url.
     */
    private final def LINK = 'https://s01.oss.sonatype.org/content/repositories/releases/com/chillycheesy/modulo-server'

    /**
     * The ModuleExtension store plugin.yml data and other information.
     */
    private ModuleExtension moduleExtension

    /**
     * Create the task.
     * @param moduleExtension The module extension who store plugin.yml data and other information.
     */
    RunModuloServer(ModuleExtension moduleExtension) {
        this.moduleExtension = moduleExtension
    }

    /**
     * Create the task.
     * @param project Target project.
     * @return The new task
     */
    @Override
    def generate(Project project) {
        return project.task('runModuloServer') {
            group = 'modulo'
            description = 'Run a Modulo server.'
            doLast {
                final file = new File("${project.rootDir.path}/${moduleExtension.testServerPath}/modulo-server-${moduleExtension.moduloVersion}.jar")
                assert file.parentFile.exists() || file.parentFile.mkdirs()
                project.delete(project.fileTree(file.parent) {
                    include '*.jar'
                })
                if (!file.exists()) downloadFile(file)
                project.javaexec {
                    main = '-jar'
                    args = [file.absolutePath]
                    workingDir = moduleExtension.testServerPath
                    standardInput = System.in
                    standardOutput = System.out
                }
            }
        }
    }

    /**
     * Download the modulo server jar file.
     * @param file The file to download.
     */
    private downloadFile(File file) {
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
