# Spring Cloud Config Server Wrapper

## About
This project implements the [Spring Cloud Config server](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/), a centralized configuration management tool.

## Features
- **Easy Configuration**: Operational out of the box when running locally.
- **Docker Compose Support**: Includes a Docker Compose file with sample configurations.
- **Environment Specific Configuration**: Facilitates different configurations for various environments.
- **Secure Secret Management**: Provides encrypted storage of secrets, apt for production use.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 17 or higher.
- Docker and Docker Compose for containerized setups.

### Configuration
Refer to the [official product documentation](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/#_spring_cloud_config_server) for main configuration values.

- **Access Configurations**:
   - Without Docker: Use Basic Auth (credentials in `application-dev.yml`) with the 'dev' profile. Configurations are loaded from the server classpath. Example:
     ```shell
     curl http://cloudconfig:AoM4jK829FW7MssBhhdBBdfTH@localhost:8888/application/dev
     ```
   - With Docker Compose: Basic Auth credentials are in `docker-compose.yml`. Configurations are from the `sample` folder. Example:
     ```shell
     curl http://cloudconfig:AoM4jK829FW7MssBhhdBBdfTH@localhost:8888/application/dev/someenv
     ```
- **File Naming Convention**: Application file names (e.g., `myapp.yml`) should be general. The server merges configurations from `application.yml` and specific application files.

### Storing Secrets
- Encrypt sensitive data. A sample encrypted password is included.
- Encryption keys are specified in the Docker Compose file.
- **Recommendation**: Opt for asymmetric encryption in production for increased security.

### Running the Server

1. **Using Gradle** (for the application's own configuration):
   ```shell
   ./gradlew bootRun --args='--spring.profiles.active=native,dev'
   ```

2. **Using Docker Compose** (to access sample configurations in the `sample` folder):
   ```shell
   docker-compose up
   ```

## Troubleshooting
- **Config Server Errors**: Verify the correct setup of configurations, whether running with the 'native' profile or configuring Git repository access.

## Contributing
We welcome contributions to enhance this wrapper.
