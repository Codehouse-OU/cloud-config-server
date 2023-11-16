# Spring Cloud Config Server Wrapper

## About
This project is an implementation of the [Spring Cloud Config server](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/), a powerful tool for centralized configuration management in a microservices architecture.

## Features
- **Easy Configuration**: Works out of the box when running locally.
- **Docker Compose Support**: Includes a compose file with sample configurations.
- **Environment Specific Configuration**: Supports different folders for different environments.
- **Secure Secret Management**: Offers a way to store encrypted secrets, suitable for production environments.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 11 or higher.
- Docker and Docker Compose (for containerized setup).

### Configuration
Main configuration values are detailed on the [official product page](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/#_spring_cloud_config_server).

- **Accessing Configurations**: After starting the server, access combined configuration for an application at `http://localhost:8888/application/dev/someenv`. Replace `someenv` with your environment.
- **File Naming**: The application file name should be generic, e.g., `myapp.yml`. The server combines configurations from `application.yml` and `myapp.yml`.

### Storing Secrets
For secret management:
- Use encrypted values for sensitive information. A sample encrypted password is included in the configuration.
- The encryption key is set in the Docker Compose file.
- **Recommendation**: Use asymmetric encryption in production for enhanced security.

### Running the Server

1. **Using Gradle** (this will present the applications own configuration):
   ```shell
   ./gradlew bootRun --args='--spring.profiles.active=native,dev'
   ```

2. **Using Docker Compose** (this will present the sample configuration from the ```sample``` folder):
   ```shell
   docker-compose up
   ```

## Troubleshooting
- **Invalid Config Server Configuration**: Ensure that you have the necessary configuration in place. This could involve running the server with the 'native' profile or setting up access to a Git repository.

## Contributing
Contributions to enhance this wrapper are welcome.
