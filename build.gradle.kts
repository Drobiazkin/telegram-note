plugins {
    id("java-library")
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.springframework.boot") version "2.5.8"
    id("org.openapi.generator") version "6.0.1"
    id("java")
}

group = "com.example"
version = "0.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

allprojects {
    repositories {
        mavenCentral()
    }
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.24")
    implementation("org.opensearch.client:opensearch-java:2.1.0")
    implementation("org.opensearch.client:opensearch-rest-high-level-client:2.4.1")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.6")
    implementation("io.swagger.core.v3:swagger-core:2.2.6")
    implementation("io.springfox:springfox-swagger2:3.0.0")
    implementation("io.springfox:springfox-swagger-ui:3.0.0")
    implementation("org.openapitools:jackson-databind-nullable:0.2.4")
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    implementation("jakarta.json:jakarta.json-api:2.1.1")
    implementation("javax.validation:validation-api:2.0.1.Final")
    implementation("org.telegram:telegrambots-spring-boot-starter:6.3.0")
    implementation("org.telegram:telegrambotsextensions:6.3.0")
    implementation("org.telegram:telegrambots:6.3.0")
    implementation("org.telegram:Bots:6.3.0")
    implementation("org.telegram:telegramapi:66.2")
    implementation("org.telegram:telegrambots-meta:6.3.0")
    implementation("org.telegram:telegrambotsextensions:6.3.0")
    implementation("org.telegram:telegrambots-abilities:6.3.0")
    implementation("org.telegram:telegrambots-chat-session-bot:6.3.0")
    implementation("org.mapstruct:mapstruct:1.5.3.Final")

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")
    annotationProcessor("org.projectlombok:lombok:1.18.24")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")

}


tasks.withType<JavaCompile> {
    options.compilerArgs.addAll(
            listOf(
                    "-Amapstruct.unmappedTargetPolicy=IGNORE",
                    "-Amapstruct.defaultComponentModel=spring",
                    "-Amapstruct.defaultInjectionStrategy=constructor"))
}

tasks.withType<Test> {
    useJUnitPlatform()
}
