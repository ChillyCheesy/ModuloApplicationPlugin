package fr.owle.application

import org.gradle.api.Project
import org.gradle.api.Plugin

class HomeTrackerApplicationPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.pluginManager.apply 'java-library'
        def moduleExtension = project.extensions.create('module', ModuleExtension)
        moduleExtension.init(project)
        project.task('generateModuleYml') {
            group = 'hometracker'
            description = 'Generate the module.yml file for a HomeTracker module.'
            doLast {
                def resources = new File(moduleExtension.target)
                resources.mkdirs()
                def file = new File("${resources.getPath()}/module.yml")
                if (!file.exists())
                    file.createNewFile()
                file.text =  createSection('name', moduleExtension.moduleName)
                file.text += createSection('version', moduleExtension.version)
                file.text += createSection('authors', moduleExtension.authors)
                file.text += createSection('softDependencies', moduleExtension.softDependencies)
                file.text += createSection('dependencies', moduleExtension.dependencies)
                file.text += createSection('main', moduleExtension.main)
            }
        }
        project.processResources.dependsOn project.generateModuleYml
    }

    static def createSection(def name, String value) {
        def result = "${name}: ${value}\n"
        return result
    }

    static def createSection(def name, List<String> values) {
        def result = ''
        if (!values.isEmpty()) {
            result = "${name}:\n"
            values.forEach {
                result += "  - ${it}\n"
            }
        }
        return result
    }

}
