# Hexagonal Architecture Example With Spring Boot

![Gradle Build](https://github.com/webcane/spring-boot-hexagonal-architecture/workflows/Gradle%20Build/badge.svg)

This repository contains an example Spring Boot application that follows the Hexagonal Architecture pattern.
The structure was inspired by a [blog post](https://www.arhohuttunen.com/hexagonal-architecture-spring-boot/) and addresses some practical challenges that are often overlooked in similar examples.

# Project Structure
This project uses a root Gradle project to build the Spring Boot JAR.
Only the root project is responsible for producing the final artifact—there are no nested deliverable subprojects.

```groovy
bootJar {
    enabled = true
}
```
The root project includes dependencies from subprojects:

```groovy
dependencies {
    implementation project(':usecase')
    implementation project(':coffeeshop-api')
    implementation project(':coffeeshop-core')
    implementation project(':coffeeshop-infrastructure')
    implementation 'org.springframework.boot:spring-boot-starter'
}
```
Each of these subprojects acts as a library:
```groovy
plugins {
    id 'java-library'
}
```

# Hexagonal Architecture Breakdown
Hexagonal architecture typically consists of two layers:
- **Core** (Application Layer)
Contains business logic (usecase), domain classes (domain and shared), and Ports—interfaces for communication with the outside world.

- **Infrastructure** Layer
Contains adapters:
  - Inbound: e.g., REST API controllers (used to expose the application)
  - Outbound: e.g., JPA repositories (used for persistence)

# Adapter Separation
In this example, the infrastructure is split into two distinct subprojects:

- `coffeeshop-api` – Inbound Adapters
- `coffeeshop-infrastructure` – Outbound Adapters

This separation is motivated by four goals:

1. **Logical Clarity**

   Different types of adapters serve different purposes and belong in different modules.

2. **Scope Control**

   When using IDEs that default to public class visibility, it’s easy to accidentally break encapsulation.
   By organizing adapters into different subprojects, scope violations are easier to detect and avoid.
the `coffeeshop-infrastructure` subproject do not depend on `coffeeshop-api`:
```dotenv
dependencies {
    implementation project(':usecase')
    implementation project(':coffeeshop-core')
}
```
Other tools to enforce visibility and scope include:
   - Spring Modulith
   - ArchUnit rules
   - Java Modules

3. **Dependency Management**

   Inbound (e.g., REST) and outbound (e.g., JPA) adapters require different dependencies.
   By separating them, it becomes easier to manage and understand which libraries are truly needed.

4. **Testing Strategy**

   Different testing strategies are appropriate for different adapter types:
   - Inbound: `@WebMvcTest`
   - Outbound: `@DataJpaTest`

Keeping these concerns isolated simplifies testing and avoids unnecessary complexity.

# Use Cases
This structure is particularly useful for:
- Hexagonal architecture projects
- Gradle multi-project builds
- Applications where only one subproject defines a `@SpringBootApplication`

