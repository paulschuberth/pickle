plugins {
    kotlin("jvm") version "2.2.20"
}

group = "de.pschuberth"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.junit:junit-bom:6.0.1"))
    implementation("org.junit.jupiter:junit-jupiter")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
