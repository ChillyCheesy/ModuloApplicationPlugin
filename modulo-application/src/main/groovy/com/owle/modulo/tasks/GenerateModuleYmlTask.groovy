package com.owle.modulo.tasks

import com.owle.modulo.extensions.ModuleExtension
import org.gradle.api.Project
import org.yaml.snakeyaml.Yaml

class GenerateModuleYmlTask implements ModuloTask {

    private ModuleExtension moduleExtension

    GenerateModuleYmlTask(ModuleExtension moduleExtension) {
        this.moduleExtension = moduleExtension
    }

    @Override
    def generate(Project project) {
        return project.task('generateModuleYml') {
            group = 'modulo'
            description = 'Generate the module.yml file for a Modulo module.'
            doLast {
                final moduleConfig = getModuleConfigFile()
                final Yaml yaml = new Yaml()
                moduleConfig.text = yaml.dump([
                        name: moduleExtension.moduleName,
                        version: moduleExtension.version,
                        authors: moduleExtension.authors,
                        main: moduleExtension.main,
                        dependencies: moduleExtension.dependencies,
                        softDependencies: moduleExtension.softDependencies
                ])
            }
        }
    }

    private def getModuleConfigFile() {
        final resources = new File("${moduleExtension.target}")
        resources.mkdirs()
        final file = new File("${resources.getPath()}/module.yml")
        if (!file.exists()) file.createNewFile()
        return file
    }

}
