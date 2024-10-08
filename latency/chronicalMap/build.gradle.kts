plugins {
    java
}

group="com.pineapple"
version="1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.openhft:chronicle-map:3.26ea4")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.11.2")
    testRuntimeOnly( "org.junit.jupiter:junit-jupiter-engine:5.11.2")    
}

tasks.named<Test>("test") {
    //jvmArgs("-Xmx1536M --add-opens=java.base/java.lang.reflect=ALL-UNNAMED --add-exports=java.base/java.lang.reflect=ALL-UNNAMED")
    useJUnitPlatform()
}
