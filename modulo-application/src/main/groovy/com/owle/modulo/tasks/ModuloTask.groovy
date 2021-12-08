package com.owle.modulo.tasks

import org.gradle.api.Project

interface ModuloTask {
    def generate(Project project)
}
