package fr.owle.application.extensions

import org.gradle.api.Task

class FrontContainer {

    final String name

    Task buildTask
    String from
    String into
    boolean deleteResources = true

    FrontContainer(String name) {
        this.name = name
    }

    void buildTask(def task) {
        buildTask = task
    }

    void from(String from) {
        this.from = from
    }

    void into(String into) {
        this.into = into
    }

    void deleteResources(boolean deleteResources) {
        this.deleteResources = deleteResources
    }


}