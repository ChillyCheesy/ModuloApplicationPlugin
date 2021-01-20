package fr.owle.application.extensions

import fr.owle.application.HomeTrackerApplicationPlugin
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

import javax.inject.Inject

class BuildModuleConfigurationExtension {

    private final Project project
    private final Plugin<Project> plugin

    private Task buildTask
    private Task resourceTask
    final NamedDomainObjectContainer<FrontContainer> fronts

    @Inject
    BuildModuleConfigurationExtension(Project project, HomeTrackerApplicationPlugin plugin) {
        this.project = project
        this.plugin = plugin
        fronts = project.objects.domainObjectContainer(FrontContainer)
    }

    def buildTask(def buildTask) {
        this.buildTask = buildTask
        plugin.createBuildModuleTask(project, this)
    }

    def buildTask() {
        return this.buildTask
    }

    def resourceTask(def resourceTask) {
        this.resourceTask = resourceTask
    }

    def resourceTask() {
        return this.resourceTask
    }


}
