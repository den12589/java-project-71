plugins {
    id("java")
    checkstyle
    application
    jacoco
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("info.picocli:picocli:4.7.6")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.10.0")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.18.0-rc1")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

}
application {
    mainClass = "hexlet.code.App"
}


tasks.test {
    useJUnitPlatform()
}
tasks.jacocoTestReport {
    reports { xml.required.set(true) }
}