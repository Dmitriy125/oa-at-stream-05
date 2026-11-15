plugins {
    java
    id("org.openapi.generator") version "7.10.0"
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "org.example"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.22.3")
    testImplementation("junit:junit:4.13.2")
    implementation("org.assertj:assertj-core:3.27.7")
    implementation("io.rest-assured:rest-assured:6.2.0")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.22")
    implementation("org.openapitools:jackson-databind-nullable:0.2.6")
    implementation("com.fasterxml.jackson.core:jackson-core:2.15.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.15.2")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("jakarta.annotation:jakarta.annotation-api:2.1.1")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okio:okio:3.6.0")
    implementation("com.google.code.gson:gson:2.10.1")
    testImplementation("org.slf4j:slf4j-api:2.0.9")
    testImplementation("ch.qos.logback:logback-classic:1.4.11")
    testImplementation("org.apache.httpcomponents.client5:httpclient5:5.3.1")
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

tasks.register<Test>("runApiTests") {
    description = "Run API tests."
    group = "verification"

    useJUnitPlatform {
        filter {
            includeTags("api")
        }
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

openApiGenerate {
    generatorName.set("spring")
    inputSpec.set("$projectDir/src/main/resources/openapi/swagger.json".replace("\\", "/"))
    outputDir.set("${layout.buildDirectory.get()}/generated".replace("\\", "/"))

    apiPackage.set("com.example.project.api")
    modelPackage.set("com.example.project.model")
    invokerPackage.set("com.example.project.invoker")

    configOptions.set(
        mapOf(
            "interfaceOnly" to "true",
            "useSpringBoot3" to "true",
            "useTags" to "true",
            "openApiNullable" to "false",
            "documentationProvider" to "none"
        )
    )
}

sourceSets {
    main {
        java {
            srcDirs("${layout.buildDirectory.get()}/generated/src/main/java")
        }
    }
}

tasks.compileJava {
    dependsOn(tasks.openApiGenerate)
}