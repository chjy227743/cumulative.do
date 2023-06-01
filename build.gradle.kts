plugins {
	java
	id("org.springframework.boot") version "3.0.6"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("plugin.lombok") version "1.8.21"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.jsoup:jsoup:1.11.1")
	implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
	compileOnly("org.projectlombok:lombok:1.18.24")
	implementation("com.google.code.gson:gson:2.8.8")
}

kotlinLombok {
	lombokConfigurationFile(file("lombok.config"))
}

tasks.withType<Test> {
	useJUnitPlatform()
}
