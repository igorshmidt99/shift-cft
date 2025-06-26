plugins {
    id("java")
}

group = "org.cft"

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "org.cft.Main"
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}