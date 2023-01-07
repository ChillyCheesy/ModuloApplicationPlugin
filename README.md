<div align="center">

# Modulo Application Plugin
![Release App](https://github.com/ChillyCheesy/ModuloGradleApplication/actions/workflows/release-app.yml/badge.svg?branch=master)
[![Gradle Plugin Portal](https://img.shields.io/gradle-plugin-portal/v/com.chillycheesy.modulo-application)](https://plugins.gradle.org/plugin/com.chillycheesy.modulo-application)

</div>

---  

## Overview.
The HomeTracker Application Plugin give help to deploy HomeTracker's modules.

## Table of Content.
* [Getting Started Using the Plugin](#start)
    * [Step 1](#start-1)
    * [Step 2](#start-2)
    * [Step 3](#start-3)
    * [Step 4](#start-4)
* [Plugins Documentation](#doc)
    * [Closures section](#closures)
        * [module](#closures-module)
    * [Tasks section](#tasks)
        * [generateModuleYml](#tasks-generateModuleYml)
        * [runModuloServer](#tasks-runModuloServer)
        * [deployModuleToLocalModuloServer](#tasks-deployModuleToLocalModuloServer)
  
## Getting Started Using the Plugin. <a id="start"></a>
### *Step 1: Apply the plugin to your Gradle build script.* <a id="start-1"></a>
To apply the plugin, please add this following part of code.  

```groovy
plugins {
    id 'com.chillycheesy.modulo-application' version '1.2.1'
}

group 'your.group'
version '1.0.0'
```

### *Step 2: Add the module configuration to your project.* <a id="start-2"></a>
Add the below **module** closure with your module's information inside.  

```groovy
module {
    moduleName = 'MyAwesomeModule'
    version = '1.0.0'
    authors = ['Wicket', 'Nippet']
    main = 'com.your.module.MainClass'
}
```

### *Step 3: Run the build task.* <a id="start-3"></a>
The **modulo-application** plugin include the **java-library** plugin. Therefore, you have access to all **java-library**'s tasks include the **build** task.  
Run this following command.  
```bash
$> ./gradlew build
```

### *Step 4: Use your module.* <a id="start-4"></a>
Take the built module and put it in the *modules* file of your Modulo server.
> Enjoy 🍻 🌶🧀 

## Plugins Documentation. <a id="doc"></a>

In this section we will see more information about its closures and added tasks.

## Closures section. <a id="closures"></a>
### module. <a id="closures-module"></a>
The module closure represent the *module.yml* file inside your module.
```yaml
name: MyAwesomeModule
version: 1.0.0
authors:
  - Wicket
  - Nippet
main: com.your.module.MainClass
```
The above example part of code can be replaced by the below closure.
```groovy
module {
    moduleName = 'MyAwesomeModule'
    version = '1.0.0'
    authors = ['Wicket', 'Nippet']
    main = 'com.your.module.MainClass'
}
```

The following array was the exhaustive list of **module**'s parameters.  
  
|       Key        | Description                                              | Equivalent of YAML |                                  Default value |
|:----------------:|:---------------------------------------------------------|:------------------:|-----------------------------------------------:|
|    moduleName    | Module name.                                             |        name        |                      Your Gradle project name. |
|     version      | Module version.                                          |      version       |                   Your Gradle project version. |
|     authors      | Module authors.                                          |      authors       |                               ['ChillyCheesy'] |
|       main       | Module main.                                             |        main        | Concat the project group and the project name. |
|   mainPageName   | Module main page name.                                   |    mainPageName    |                                        'index' |
|   dependencies   | Module dependencies.                                     |    dependencies    |                                    Empty list. |
| softDependencies | Module soft dependencies.                                |  softDependencies  |                                    Empty list. |
|      target      | The output folder for the generated **module.yml** file. |        none        |                           'src/main/resources' |
|  moduloVersion   | The target version for the modulo server.                |        none        |                                  'BINKS-0.3.0' |
|  testServerPath  | The path to the test server.                             |        none        |                                'modulo-server' | 


## Tasks section. <a id="tasks"></a>
### generateModuleYml. <a id="tasks-generateModuleYml"></a>
This task generate the **module.yml** file.  
If you run the processResources task. It will automatically call the generateModuleYml task.  
The processResources task depends on the generateModuleYml task.


### runModuloServer. <a id="tasks-runModuloServer"></a>
This task run a modulo server. The version of the modulo server is defined by the **moduloVersion** parameter.  
*[(See moduloVersion closure for more information.)](#closures-module)*  
The server name is automatically downloaded and placed in the ```modulo-server``` folder at the root of your project.  
The runModuloServer task depends on the deployModuleToLocalModuloServer task.

### deployModuleToLocalModuloServer. <a id="tasks-deployModuleToLocalModuloServer"></a>
This task build your module and copy it on the ```modulo-server/modules``` folder at the root of your project.

🌶🧀
