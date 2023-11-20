# Spring Cloud Config Server Wrapper

## About

This project implements
the [Spring Cloud Config server](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/), a centralized
configuration management tool.

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

Refer to
the [official product documentation](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/#_spring_cloud_config_server)
for main configuration values.

- **Access Configurations**:
    - Without Docker: Use Basic Auth (credentials in `application-dev.yml`) with the 'dev' profile. Configurations are
      loaded from the server classpath.
      ```shell
      curl http://cloudconfig:AoM4jK829FW7MssBhhdBBdfTH@localhost:8888/application/dev
      ```
    - With Docker Compose: Basic Auth credentials are in `docker-compose.yml`. Configurations are from the `sample`
      folder.
      ```shell
      curl http://cloudconfig:AoM4jK829FW7MssBhhdBBdfTH@localhost:8888/application/dev/someenv
      ```
- **File Naming Convention**: The 'application' name is *magic*, that is applied to all configurations. Your application
  configuration should be in ```yourapp.yml``` configuration file. The server merges configurations
  from `application.yml` and specific application files.

### Storing Secrets

- Encrypt sensitive data. A sample encrypted password is included in the sample directory. It can be decrypted using
  cloud config. Example:
   ```shell
   curl http://cloudconfig:AoM4jK829FW7MssBhhdBBdfTH@localhost:8888/encrypt -s -d mysecret
   ```
   ```shell
   curl http://cloudconfig:AoM4jK829FW7MssBhhdBBdfTH@localhost:8888/decrypt -s -d 280eff95c987bad5994959dc5fcd24aa32ad7492b0f4d1dc522033ab08edf8e1
   ```
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
    - Sample compose file for local setup:
   ```yaml
    version: '3.7'
    services:
      config-server:
        image: codehouseou/cloud-config-server:latest
        ports:
          - 8888:8888
        environment:
          # local configuration setup
          - SPRING_PROFILES_ACTIVE=native
          - SPRING_CLOUD_CONFIG_SERVER_NATIVE_SEARCHLOCATIONS=file:///config-repo #make the volume is mounted
          # set up symmetric encryption
          - ENCRYPT_KEY=FxUjrRSbAb3q9fxdUAFKZPBK2
          # set up API authentication
          - SPRING_SECURITY_USER_NAME=cloudconfig
          - SPRING_SECURITY_USER_PASSWORD=AoM4jK829FW7MssBhhdBBdfTH
        volumes:
          - ./sample:/config-repo
      ```
    - Sample compose file for remote setup:
   ```yaml
    version: '3.7'
    services:
      config-server:
        image: codehouseou/cloud-config-server:latest
        ports:
          - 8888:8888
        environment:
          # VCS based configuration setup
          - SPRING_CLOUD_CONFIG_SERVER_GIT_URI=https://github.com/ORG/REPO.git
          - SPRING_CLOUD_CONFIG_SERVER_GIT_USERNAME=USER
          - SPRING_CLOUD_CONFIG_SERVER_GIT_PASSWORD=ACCESS_TOKEN
          # set up symmetric encryption
          - ENCRYPT_KEY=FxUjrRSbAb3q9fxdUAFKZPBK2
          # set up API authentication
          - SPRING_SECURITY_USER_NAME=cloudconfig
          - SPRING_SECURITY_USER_PASSWORD=AoM4jK829FW7MssBhhdBBdfTH
      ```
   #### Some relevant environment variables:

   | Environment Variable                               | Description                                                                                    |
   |----------------------------------------------------|------------------------------------------------------------------------------------------------|
   | SPRING_PROFILES_ACTIVE                             | The active profiles. The `native` profile is required for local setups. Omit when using GIT    |
   | SPRING_CLOUD_CONFIG_SERVER_NATIVE_SEARCHLOCATIONS  | The location of the configuration files. The `file://` prefix is required for local setups.    |
   | ENCRYPT_KEY                                        | The encryption key.                                                                            |
   | SPRING_SECURITY_USER_NAME                          | The username for Basic Auth. Default: `user`                                                   |
   | SPRING_SECURITY_USER_PASSWORD                      | The password for Basic Auth. Default: random password generated by Spring Boot, found in logs. |
   | SPRING_CLOUD_CONFIG_SERVER_GIT_URI                 | The Git repository URI.                                                                        |
   | SPRING_CLOUD_CONFIG_SERVER_GIT_USERNAME            | The username for Git repository access.                                                        |
   | SPRING_CLOUD_CONFIG_SERVER_GIT_PASSWORD            | The password for Git repository access.                                                        |
   | SPRING_CLOUD_CONFIG_SERVER_GIT_SEARCHPATHS         | The search paths in Git repository, for configurations in a subfolder.                         |
   | SPRING_CLOUD_CONFIG_LABEL                          | The Git branch or tag name. Default: master                                                    |

## Troubleshooting

- **Config Server Errors**: Verify the correct setup of configurations, whether running with the 'native' profile or
  configuring Git repository access.

## Contributing

We welcome contributions to enhance this wrapper.
