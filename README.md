# Calculator API

A Spring Boot REST API for performing mathematical calculations. Supports basic arithmetic operations and chain calculations.

## Features

- Basic arithmetic operations (ADD, SUBTRACT, MULTIPLY, DIVIDE)
- Chain calculations
- Swagger API documentation
- Actuator health monitoring
- Docker support
- Comprehensive test coverage

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Docker (optional)
Before running the project, ensure you have the following installed:

- **Java 17** or higher
- **Maven 3.6** or higher

## Quick Start

1. Clone the repository:
```powershell
git clone https://github.com/yourusername/calculator-project.git
cd calculator-project
```

2. Build the project:
```powershell
mvn clean install
```

3. Run the application:
```powershell
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

## API Endpoints

### Basic Calculation
```http
POST /api/calculator/calculate
Content-Type: application/json

{
  "operation": "ADD",
  "num1": 5,
  "num2": 3
}
```
Supported operations:
- ADD
- SUBTRACT
- MULTIPLY
- DIVIDE

### Chain Calculation
```http
POST /api/calculator/chainCalculate?initial=10
Content-Type: application/json

[
  {
    "operation": "ADD",
    "number": 5
  },
  {
    "operation": "MULTIPLY",
    "number": 2
  }
]
```

## Documentation

- API Documentation: `http://localhost:8080/swagger-ui.html`
- API Specs: `http://localhost:8080/api-docs`
## Testing via Swagger UI
Once the application is running (mvn spring-boot:run), open your browser and go to http://localhost:8080/swagger-ui.html.

You'll see the Swagger UI where you can test all available API endpoints.

Select the endpoint you want to test (e.g., /api/calculator/calculate).

Enter the parameters (for example, operation, num1, and num2), and click Execute to test it.
## Running with Docker

For Docker image, follow the following steps

1. Build the Docker image:
```powershell
docker build -t calculator-api .
```

2. Run the container:
```powershell
docker run -p 8080:8080 calculator-api
```

Or using Docker Compose:
```powershell
docker-compose up -d
```

## Monitoring

Health and metrics endpoints:
- Health: `http://localhost:8080/actuator/health`
- Metrics: `http://localhost:8080/actuator/metrics`

## Testing

Run unit tests:
```powershell
mvn test
```

Generate test coverage report:
```powershell
mvn verify
```

Coverage report will be available at: `target/site/jacoco/index.html`

## Project Structure

```
calculator-project/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/calculator/
│   │   │       ├── controller/
│   │   │       ├── service/
│   │   │       ├── model/
│   │   │       └── operations/
│   │   └── resources/
│   └── test/
├── Dockerfile
├── docker-compose.yml
└── pom.xml
```

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.