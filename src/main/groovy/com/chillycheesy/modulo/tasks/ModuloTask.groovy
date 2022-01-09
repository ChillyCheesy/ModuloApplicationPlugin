package com.chillycheesy.modulo.tasks

import org.gradle.api.Project

interface ModuloTask {
    def generate(Project project)
}
