package com.chillycheesy.modulo.tasks

import org.gradle.api.Project

/**
 * Define a custom Modulo Task.
 */
interface ModuloTask {

    /**
     * Generate the custom task and return it.
     * @param project The target project where add the task.
     * @return The created task.
     */
    def generate(Project project)
}
