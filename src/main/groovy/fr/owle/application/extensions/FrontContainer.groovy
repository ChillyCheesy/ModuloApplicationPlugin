package fr.owle.application.extensions

import org.gradle.api.Task

class FrontContainer {

    final String name

    Task buildTask
    String from
    String into

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


}