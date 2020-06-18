import kotlin.reflect.KProperty
import kotlin.reflect.full.memberProperties

fun allModules(): Array<String> {
    return Depends.Modules::class.memberProperties
            .map(KProperty<*>::call)
            .filterIsInstance<String>()
            .toTypedArray()
}

fun setModulesPath() {
    allModules().forEach { moduleName ->
        if (moduleName.contains("-")) {
            val path = moduleName.replace(":", "").replace("-", "/")
            System.out.println("modulePath ${moduleName} = ${path}")
            project(moduleName).projectDir = File(rootDir, path)
        }
    }
}

include(*allModules())
setModulesPath()
