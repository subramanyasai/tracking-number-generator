
# Tracking-number-generator API
Generates the tracking number for parcles



## Overview

This project provides a REST API to generate unique tracking numbers based on various input parameters such as origin country, destination country, order weight, creation timestamp, and customer information.

The generated tracking number follows specific constraints to ensure uniqueness, efficiency, and scalability.

---

## Features

- **POST /next-tracking-number** endpoint to generate a unique tracking number.
- Accepts request body with order and customer details.
- Validates input parameters with proper formats (ISO 3166-1 alpha-2 for countries, RFC 3339 for timestamps, UUID for customer ID).
- Generates tracking numbers matching regex `^[A-Z0-9]{1,16}$`.
- Ensures no duplicate tracking numbers are generated in-memory.
- Thread-safe and scalable for concurrent requests.
- Returns generated tracking number along with timestamp in RFC 3339 format.

---

## API Specification

### Endpoint


### Request Body (JSON)

| Field                | Type    | Required | Description                                  | Format / Example                     |
|----------------------|---------|----------|----------------------------------------------|------------------------------------|
| `origin_country_id`    | String  | Yes      | Origin country code (ISO 3166-1 alpha-2)     | `"MY"`                             |
| `destination_country_id`| String  | Yes      | Destination country code (ISO 3166-1 alpha-2)| `"ID"`                             |
| `weight`               | Decimal | Yes      | Order weight in kilograms                      | `"1.234"`                         |
| `created_at`           | String  | Yes      | Order creation timestamp (RFC 3339)           | `"2018-11-20T19:29:32+08:00"`    |
| `customer_id`          | UUID    | Yes      | Customer UUID                                  | `"de619854-b59b-425e-9db4-943979e1bd49"` |
| `customer_name`        | String  | Yes      | Customer’s name                               | `"RedBox Logistics"`              |
| `customer_slug`        | String  | Yes      | Customer’s slug-case/kebab-case name          | `"redbox-logistics"`              |

Example:

```json
{
  "origin_country_id": "MY",
  "destination_country_id": "ID",
  "weight": 1.234,
  "created_at": "2018-11-20T19:29:32+08:00",
  "customer_id": "de619854-b59b-425e-9db4-943979e1bd49",
  "customer_name": "RedBox Logistics",
  "customer_slug": "redbox-logistics"
}

### Response (JSON)
{
  "tracking_number": "A1B2C3D4E5F6",
  "created_at": "2025-05-26T12:34:56+00:00"
}

Usage
1.Clone the repository.

2.Build the project using Maven or your preferred build tool:
mvn clean install

3.Run the Spring Boot application:
mvn spring-boot:run

4.Call the API using a POST request with JSON body:
curl -X POST http://localhost:8080/next-tracking-number \
-H "Content-Type: application/json" \
-d '{
  "origin_country_id": "MY",
  "destination_country_id": "ID",
  "weight": 1.234,
  "created_at": "2018-11-20T19:29:32+08:00",
  "customer_id": "de619854-b59b-425e-9db4-943979e1bd49",
  "customer_name": "RedBox Logistics",
  "customer_slug": "redbox-logistics"
}'


Implementation Notes
The tracking number generator uses an in-memory thread-safe set (ConcurrentHashMap.newKeySet()) to maintain uniqueness during runtime.

No database or external storage is used for uniqueness persistence.

Tracking numbers are composed of uppercase alphanumeric characters, length 1 to 16.

Designed for concurrency and horizontal scalability within a single application instance.

For production, consider external storage or distributed ID generators for global uniqueness.

Dependencies
Java 17+

Spring Boot 3.4.0

springdoc-openapi 2.8.0 (for API documentation)

Reactor Netty (for reactive web support)

Other dependencies as defined in pom.xml

Documentation
Swagger UI available at /swagger-ui/index.html for API exploration.
http://localhost:8080/webjars/swagger-ui/index.html#/

