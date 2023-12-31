import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
	extra["openAPIVersion"] = "2.0.0"
	extra["openFeignVersion"] = "4.0.3"
	extra["springValidationVersion"] = "3.1.0"
}

plugins {
	id("org.springframework.boot") version "3.1.1"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
}

group = "sb"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter-validation:${property("springValidationVersion")}")
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign:${property("openFeignVersion")}")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${property("openAPIVersion")}")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
