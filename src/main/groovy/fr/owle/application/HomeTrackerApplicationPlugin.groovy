package fr.owle.application

import fr.owle.application.extensions.BuildModuleConfigurationExtension
import fr.owle.application.extensions.ModuleExtension
import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.tasks.Copy

class HomeTrackerApplicationPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.pluginManager.apply 'java-library'
        def moduleExtension = project.extensions.create('module', ModuleExtension, project)
        def buildModuleConfigurationExtension = project.extensions.create('buildModuleConfiguration', BuildModuleConfigurationExtension, project, this)
        buildModuleConfigurationExtension.resourceTask project.tasks.processResources
        createGenerateModuleYmlTask(project, moduleExtension)
    }

    static def createBuildModuleTask(Project project, BuildModuleConfigurationExtension buildModuleConfigurationExtension) {
        buildModuleConfigurationExtension.fronts.each { front ->
            final def taskName = "${front.name}CopyResource";
            project.task(taskName, type: Copy) {
                group = 'hometracker'
                description = "Copy resource for ${front.name} configuration."
                dependsOn front.buildTask
                println "${front.from} -> ${front.into}"
                from front.from
                into front.into
            }
            buildModuleConfigurationExtension.resourceTask().dependsOn project.tasks.getByName(taskName)
        }
        project.task('buildModule') {
            group = 'hometracker'
            description = 'Build the module.'
            finalizedBy buildModuleConfigurationExtension.buildTask()
        }
    }

    static def createGenerateModuleYmlTask(Project project, ModuleExtension moduleExtension) {
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
                file.text += createSection('mainPageName', moduleExtension.mainPageName)
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
