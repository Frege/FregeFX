def base = '/Users/dierkkoenig/Downloads/javafx-src/'
File sources = new File(base)

sources.eachFileRecurse { File file ->
    def name = file.path - base
    if (! name.endsWith(".java")) return
    if (! name.contains("javafx")) return
    if (name.contains("com/sun")) return

    name -= ".java"

    def className = name.replaceAll(/\//, '.' )

    println "processing $name"

    "gradlew -DnativeClassName=$className -DoutputFile=build/generated/frege/${name}.fr :preFregeFX:fNG".execute()

    sleep 3000
}