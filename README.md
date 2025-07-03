# SMS Validator Service

A Spring Boot microservice for validating SMS messages with Kafka integration for asynchronous processing.

## Overview

The SMS Validator Service provides REST APIs to validate SMS messages, detect spam, and process validation results asynchronously using Apache Kafka. The service supports phone number validation, content analysis, and provides comprehensive metrics and monitoring.

## Features

- **Phone Number Validation**: International phone number format validation
- **Spam Detection**: Advanced spam detection algorithms
- **Asynchronous Processing**: Kafka-based message processing
- **Metrics & Monitoring**: Comprehensive observability with Micrometer
- **API Documentation**: OpenAPI/Swagger integration
- **Containerized**: Docker and Docker Compose support
- **Testing**: Unit, integration, and performance tests
- **CI/CD Ready**: GitHub Actions workflows

## Tech Stack

- **Java 17+**
- **Spring Boot 3.2+**
- **Spring Data JPA**
- **Spring Kafka**
- **PostgreSQL**
- **Apache Kafka**
- **Docker & Docker Compose**
- **Gradle**
- **JUnit 5**
- **Testcontainers**

## Quick Start

### Prerequisites

- Java 17 or higher
- Docker and Docker Compose
- Git