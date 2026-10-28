plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.assertj:assertj-core:3.27.7")
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<Test>("runAllTest") {
    group = "verification"
    useJUnitPlatform()
    doLast {
        println("Test run is over")
    }
}

tasks.withType<JavaExec>().configureEach {
    jvmArgs(
        "-Dfile.encoding=UTF-8",
        "-Dsun.stdout.encoding=UTF-8",
        "-Dsun.stderr.encoding=UTF-8"
    )
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}
