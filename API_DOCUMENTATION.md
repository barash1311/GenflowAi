# GenFlow AI - Backend API Documentation

**Version**: 1.0.0  
**Status**: Production-Ready Backend  
**Framework**: Spring Boot 4.0.2 | Java 17 | PostgreSQL

---

## 📋 Overview

GenFlow AI is a production-ready, microservice-oriented AI workflow platform providing:
- Secure authentication with JWT-based access control
- Dataset management and organization
- Prompt creation and versioning
- ML model registration and versioning
- Asynchronous prediction job tracking
- Role-based access control (ADMIN/USER)
- RESTful API with comprehensive documentation

The system is intentionally decoupled from the ML inference layer, enabling future integration with Python/FastAPI services.

---

## 🏗️ Architecture

```
┌─────────────────┐
│   Frontend      │
└────────┬────────┘
         │ HTTP/REST
┌────────▼──────────────────────────────┐
│   Spring Boot Backend (Java 17)        │
│  ├─ Authentication (JWT)               │
│  ├─ User Management                    │
│  ├─ Dataset Management                 │
│  ├─ Prompt Management                  │
│  ├─ Model Registry                     │
│  └─ Prediction Job Tracking            │
└────────┬──────────────────────────────┘
         │ JDBC
┌────────▼──────────┐
│  PostgreSQL DB    │
│  ├─ users         │
│  ├─ datasets      │
│  ├─ prompts       │
│  ├─ models        │
│  ├─ predictions   │
│  └─ prediction_jobs
└───────────────────┘
```

**Future Integration**: Python ML Service (FastAPI/Flask) - Decoupled for independent scaling

---

## 🔐 Security

- **Authentication**: JWT Bearer tokens (valid for 24 hours)
- **Password Encryption**: BCrypt with salt
- **Session Management**: Stateless (JWTY-based)
- **Authorization**: Role-based (ADMIN/USER)
- **CSRF Protection**: Disabled (stateless API)
- **CORS**: Ready for configuration

**Secret Key**: Store in environment variables, not in code

---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- PostgreSQL 12+
- Maven 3.8+

### Database Setup

```bash
# Create PostgreSQL database
createdb genflowai

# User credentials (in application.yaml)
username: postgres
password: postgres
```

### Configuration

Edit `src/main/resources/application.yaml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/genflowai
    username: postgres
    password: postgres

security:
  jwt:
    secret: your-256-bit-secret-key-here (change in production!)
    expiration: 86400000  # 24 hours in milliseconds
```

### Build & Run

```bash
# Build
./mvnw clean package

# Run
./mvnw spring-boot:run

# Or using JAR
java -jar target/genflowai-0.0.1-SNAPSHOT.jar
```

Server runs on `http://localhost:8080`

---

## 📚 API Endpoints

### Base URL
```
http://localhost:8080/api/v1
```

### Authentication

#### Register User
```
POST /auth/register
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "securepassword123"
}

Response: 201 Created
{
  "accessToken": "eyJhbGc...",
  "user": {
    "id": "uuid",
    "email": "user@example.com",
    "role": "USER",
    "createdAt": "2026-02-04T10:00:00"
  }
}
```

#### Login
```
POST /auth/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "securepassword123"
}

Response: 200 OK
{
  "accessToken": "eyJhbGc...",
  "user": {...}
}
```

#### Get Current User
```
GET /auth/me
Authorization: Bearer <accessToken>

Response: 200 OK
{
  "id": "uuid",
  "email": "user@example.com",
  "role": "USER",
  "createdAt": "2026-02-04T10:00:00"
}
```

#### Logout
```
POST /auth/logout
Authorization: Bearer <accessToken>

Response: 200 OK
```

---

### User Management (ADMIN endpoints)

#### Create User
```
POST /users
Authorization: Bearer <adminToken>
Content-Type: application/json

{
  "email": "newuser@example.com",
  "password": "password123",
  "role": "USER"
}

Response: 201 Created
```

#### Get All Users
```
GET /users?page=0&size=10
Authorization: Bearer <adminToken>

Response: 200 OK
{
  "data": [...],
  "page": 0,
  "size": 10,
  "totalElements": 45,
  "totalPages": 5
}
```

#### Get User by ID
```
GET /users/{userId}
Authorization: Bearer <token>
```

#### Update User
```
PUT /users/{userId}
Authorization: Bearer <token>
Content-Type: application/json

{
  "email": "newemail@example.com",
  "password": "newpassword",
  "role": "ADMIN"
}
```

#### Delete User
```
DELETE /users/{userId}
Authorization: Bearer <adminToken>
```

---

### Dataset Management

#### Create Dataset
```
POST /datasets
Authorization: Bearer <token>
Content-Type: application/json

{
  "name": "Customer Data 2025",
  "source": "s3://bucket/path/data.csv",
  "description": "Customer demographics and behavior",
  "rowCount": 10000
}

Response: 201 Created
{
  "id": "uuid",
  "name": "Customer Data 2025",
  "source": "s3://...",
  "description": "...",
  "rowCount": 10000,
  "uploadedBy": "user-uuid",
  "createdAt": "2026-02-04T10:00:00"
}
```

#### Get All Datasets
```
GET /datasets?page=0&size=10
Authorization: Bearer <token>
```

#### Get Dataset by ID
```
GET /datasets/{datasetId}
Authorization: Bearer <token>
```

#### Update Dataset
```
PUT /datasets/{datasetId}
Authorization: Bearer <token>
Content-Type: application/json

{
  "name": "Updated Name",
  "source": "...",
  "description": "...",
  "rowCount": 15000
}
```

#### Delete Dataset
```
DELETE /datasets/{datasetId}
Authorization: Bearer <token>
```

---

### Prompt Management

#### Create Prompt
```
POST /prompts
Authorization: Bearer <token>
Content-Type: application/json

{
  "datasetId": "dataset-uuid",
  "promptText": "Predict customer churn for next quarter"
}

Response: 201 Created
{
  "id": "uuid",
  "userId": "user-uuid",
  "userEmail": "user@example.com",
  "datasetId": "dataset-uuid",
  "datasetName": "Customer Data 2025",
  "promptText": "Predict customer churn...",
  "createdAt": "2026-02-04T10:00:00"
}
```

#### Get All Prompts
```
GET /prompts?page=0&size=10
Authorization: Bearer <token>
```

#### Get Prompts by Dataset
```
GET /prompts/dataset/{datasetId}?page=0&size=10
Authorization: Bearer <token>
```

#### Get Prompt by ID
```
GET /prompts/{promptId}
Authorization: Bearer <token>
```

#### Delete Prompt
```
DELETE /prompts/{promptId}
Authorization: Bearer <token>
```

---

### Model Management

#### Create Model
```
POST /models
Authorization: Bearer <adminToken>
Content-Type: application/json

{
  "name": "Churn Predictor v2",
  "version": "2.1.0",
  "algorithm": "RandomForest",
  "description": "Advanced churn prediction model",
  "accuracy": 0.92
}

Response: 201 Created
```

#### Get All Models
```
GET /models?page=0&size=10
Authorization: Bearer <token>
```

#### Get Model by ID
```
GET /models/{modelId}
Authorization: Bearer <token>
```

#### Update Model
```
PUT /models/{modelId}
Authorization: Bearer <adminToken>
```

#### Delete Model
```
DELETE /models/{modelId}
Authorization: Bearer <adminToken>
```

---

### Prediction Management

#### Submit Prediction
```
POST /predictions
Authorization: Bearer <token>
Content-Type: application/json

{
  "promptId": "prompt-uuid",
  "modelId": "model-uuid"
}

Response: 202 Accepted
{
  "id": "job-uuid",
  "promptId": "prompt-uuid",
  "status": "PENDING",
  "startedAt": "2026-02-04T10:00:00",
  "finishedAt": null
}
```

#### Get Prediction by ID
```
GET /predictions/{predictionId}
Authorization: Bearer <token>

Response:
{
  "id": "prediction-uuid",
  "promptId": "prompt-uuid",
  "modelId": "model-uuid",
  "result": "{...json...}",
  "status": "SUCCESS",
  "executionTimeMs": 1250,
  "createdAt": "2026-02-04T10:00:00"
}
```

#### Get All Predictions
```
GET /predictions?page=0&size=10
Authorization: Bearer <token>
```

#### Get Predictions by Prompt
```
GET /predictions/prompt/{promptId}?page=0&size=10
Authorization: Bearer <token>
```

---

### Prediction Job Tracking

#### Get Job Status
```
GET /prediction-jobs/{jobId}
Authorization: Bearer <token>

Response:
{
  "id": "job-uuid",
  "promptId": "prompt-uuid",
  "status": "RUNNING",
  "startedAt": "2026-02-04T10:00:00",
  "finishedAt": null
}
```

**Status Values**: `PENDING` → `RUNNING` → `SUCCESS` or `FAILED`

---

## 🗄️ Database Schema

### Users
```sql
CREATE TABLE users (
    id UUID PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);
```

### Datasets
```sql
CREATE TABLE datasets (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    source VARCHAR(255) NOT NULL,
    description TEXT,
    row_count INTEGER,
    uploaded_by UUID NOT NULL REFERENCES users(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);
```

### Prompts
```sql
CREATE TABLE prompts (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(id),
    dataset_id UUID NOT NULL REFERENCES datasets(id),
    prompt_text TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);
```

### Models
```sql
CREATE TABLE models (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    version VARCHAR(50) NOT NULL,
    algorithm VARCHAR(255) NOT NULL,
    description TEXT,
    accuracy FLOAT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);
```

### Predictions
```sql
CREATE TABLE predictions (
    id UUID PRIMARY KEY,
    prompt_id UUID NOT NULL REFERENCES prompts(id),
    model_id UUID NOT NULL REFERENCES models(id),
    result JSON,
    status VARCHAR(50) NOT NULL,
    execution_time_ms INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);
```

### Prediction Jobs
```sql
CREATE TABLE prediction_jobs (
    id UUID PRIMARY KEY,
    prompt_id UUID NOT NULL UNIQUE REFERENCES prompts(id),
    status VARCHAR(50) NOT NULL,
    started_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    finished_at TIMESTAMP
);
```

---

## 🔄 Workflows

### User Registration & Login Flow
```
1. POST /auth/register → Create user
2. POST /auth/login → Get JWT token
3. GET /auth/me → Verify identity
```

### Dataset & Prediction Workflow
```
1. POST /datasets → Upload dataset metadata
2. POST /prompts → Create prompt against dataset
3. POST /predictions → Submit prediction job
4. GET /prediction-jobs/{jobId} → Poll job status
5. GET /predictions/{predictionId} → Retrieve results
```

### Model Versioning
```
1. POST /models → Register initial model
2. PUT /models/{id} → Update with new version
3. GET /predictions?modelId → Track predictions per model
```

---

## 🛡️ Error Handling

All errors return standardized response:

```json
{
  "message": "Resource not found",
  "errorCode": "RESOURCE_NOT_FOUND",
  "timestamp": "2026-02-04T10:00:00"
}
```

### Status Codes
- `200 OK` - Success
- `201 Created` - Resource created
- `202 Accepted` - Async job submitted
- `204 No Content` - Deleted successfully
- `400 Bad Request` - Invalid input
- `401 Unauthorized` - Missing/invalid token
- `403 Forbidden` - Insufficient permissions
- `404 Not Found` - Resource doesn't exist
- `500 Internal Server Error` - Server error

### Error Codes
- `RESOURCE_NOT_FOUND`
- `INVALID_REQUEST`
- `ACCESS_DENIED`
- `BAD_CREDENTIALS`
- `PREDICTION_EXECUTION_ERROR`
- `INTERNAL_SERVER_ERROR`

---

## 📖 Swagger/OpenAPI Documentation

Interactive API docs available at:
```
http://localhost:8080/swagger-ui.html
```

JSON Schema:
```
http://localhost:8080/v3/api-docs
```

---

## 🔮 Future Enhancements

1. **ML Integration**
   - FastAPI/Flask service for model inference
   - Async job queue (Celery/RabbitMQ)
   - Model serving with TensorFlow/PyTorch

2. **Advanced Features**
   - Token refresh mechanism
   - Token blacklisting for logout
   - Redis caching for datasets
   - Batch prediction support
   - Model explainability (SHAP/LIME)

3. **Production Readiness**
   - Comprehensive unit & integration tests
   - Docker containerization
   - Kubernetes deployment
   - CI/CD pipeline (GitHub Actions)
   - Centralized logging (ELK stack)
   - APM monitoring (DataDog/New Relic)

4. **Scalability**
   - Horizontal scaling with load balancing
   - Database connection pooling optimization
   - Distributed caching
   - API rate limiting

---

## 📝 Best Practices Implemented

✅ **Clean Architecture**
- Separation of concerns (Controller → Service → Repository)
- Dependency injection
- Single responsibility principle

✅ **Security**
- JWT token-based authentication
- Password encryption with BCrypt
- Role-based access control
- Stateless session management

✅ **Database**
- UUID-based identifiers
- Timestamped auditing (createdAt, updatedAt)
- Proper foreign keys and constraints
- Enum types for status/roles

✅ **Error Handling**
- Centralized exception handling
- Custom exceptions for domain concepts
- Consistent error response format

✅ **API Design**
- RESTful conventions
- Pagination support
- Swagger documentation
- Versioned endpoints (`/api/v1/...`)

✅ **Code Quality**
- Lombok for boilerplate reduction
- Constructor injection
- Immutable DTOs with AllArgsConstructor
- Consistent naming conventions

---

## 📧 Support & Contributions

For issues or feature requests, contact: `barash1311@gmail.com`

