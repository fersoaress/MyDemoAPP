plugins {
    id("java")
}

group = "br.com.iterasys"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.testng:testng:7.10.2")
    testImplementation("org.hamcrest:hamcrest:2.2")
    implementation("io.appium:java-client:9.3.0")



}

tasks.test {
    useTestNG()
}