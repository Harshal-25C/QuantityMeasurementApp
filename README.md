<div align="center">
 
<h1 align="center">🚀 Quantity Measurement App</h1>
<h3 align="center">Microservice Architecture | Spring Boot | Spring Cloud | JWT</h3>

<br/>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring_Boot-3.2.0-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring_Cloud-2023.0.0-6DB33F?style=for-the-badge&logo=spring&logoColor=white"/>
  <img src="https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white"/>
  <img src="https://img.shields.io/badge/JWT-Security-000000?style=for-the-badge&logo=json-web-tokens&logoColor=white"/>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Services-4_Microservices-blueviolet?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Eureka-Service_Discovery-FF6B35?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/OAuth2-GitHub_Login-181717?style=for-the-badge&logo=github"/>
  <img src="https://img.shields.io/badge/Swagger-API_Docs-85EA2D?style=for-the-badge&logo=swagger&logoColor=black"/>
</p>

<br/>

> **A fully-featured Spring Boot Microservice Architecture** for performing quantity measurement operations — Add, Subtract, Multiply, Divide, Compare & Convert — across **Length, Weight, Volume, and Temperature** units. Built with enterprise-grade security, service discovery, and full operation history tracking.

<br/>

</div>

---

## 📌 Table of Contents

| # | Section |
|---|---------|
| 1 | [📖 Project Overview](#-project-overview) |
| 2 | [🏗️ Architecture Diagram](#️-microservice-architecture) |
| 3 | [🛠️ Tech Stack](#️-tech-stack) |
| 4 | [📁 Project Structure](#-project-structure) |
| 5 | [📋 Services Description](#-services-description) |
| 6 | [✅ Prerequisites](#-prerequisites) |
| 7 | [🗄️ Database Setup](#️-database-setup) |
| 8 | [🚀 How to Run](#-how-to-run) |
| 9 | [📡 API Endpoints](#-api-endpoints) |
| 10 | [📝 Request & Response Examples](#-request--response-examples) |
| 11 | [🔒 Security Flow](#-security-flow) |
| 12 | [🔗 Service Communication](#-service-communication) |
| 13 | [📊 Eureka Dashboard](#-eureka-dashboard) |
| 14 | [📚 Swagger UI](#-swagger-ui) |
| 15 | [⚙️ Environment Configuration](#️-environment-configuration) |
| 16 | [❌ Common Errors & Fixes](#-common-errors--fixes) |
| 17 | [✅ Microservice Rules Followed](#️-microservice-rules-followed) |

---

## 📖 Project Overview

<div align="center">

```
╔══════════════════════════════════════════════════════════════════════╗
║  Originally a Monolithic Spring Boot App → Converted to Microservices ║
╚══════════════════════════════════════════════════════════════════════╝
```

</div>

**Quantity Measurement App** is a production-ready **Spring Boot Microservice** project that supports:

| Operation | Description |
|-----------|-------------|
| ➕ **Add** | Add two quantities (auto unit conversion) |
| ➖ **Subtract** | Subtract two quantities |
| ✖️ **Multiply** | Multiply a quantity by a factor |
| ➗ **Divide** | Divide a quantity by a factor |
| ⚖️ **Compare** | Compare if two quantities are equal |
| 🔄 **Convert** | Convert a value to another unit |

### 🧮 Supported Measurement Types

```
📏 LengthUnit      →   FEET  |  INCHES  |  YARDS  |  CENTIMETERS
⚖️  WeightUnit     →   MILLIGRAM  |  GRAM  |  KILOGRAM  |  POUND  |  TONNE
🧪 VolumeUnit      →   LITRE  |  MILLILITRE  |  GALLON
🌡️  TemperatureUnit →   CELSIUS  |  FAHRENHEIT  |  KELVIN
```

### 🔑 Core Capabilities

- 🔐 **JWT-based Authentication** — secure, stateless sessions
- 🐙 **GitHub OAuth2 Login** — social login support
- 🗃️ **Operation History** — every operation is persisted to MySQL
- ⚡ **Reactive API Gateway** — non-blocking, WebFlux-based routing
- 🔍 **Eureka Service Discovery** — dynamic service registration
- 📚 **Swagger Documentation** — interactive API explorer on each service

---

## 🏗️ Microservice Architecture

<div align="center">

```
                    ┌──────────────────────────────┐
                    │      Frontend (React)         │
                    │       localhost:3000          │
                    └──────────────┬───────────────┘
                                   │  HTTP Requests
                                   ▼
                    ╔══════════════════════════════╗
                    ║         API GATEWAY          ║  ◄── Single Entry Point
                    ║        localhost:8080        ║
                    ║   • JWT Validation           ║
                    ║   • Request Routing          ║
                    ║   • CORS Handling            ║
                    ║   • Load Balancing (lb://)   ║
                    ╚══════════════┬═══════════════╝
                                   │
              ┌────────────────────┼────────────────────┐
              │                    │                    │
              ▼                    ▼                    ▼
   ╔══════════════════╗  ╔══════════════════╗  ╔══════════════════╗
   ║  SECURITY        ║  ║  QUANTITY        ║  ║  EUREKA SERVER   ║
   ║  SERVICE         ║  ║  MEASUREMENT     ║  ║  localhost:8761  ║
   ║  localhost:8081  ║  ║  APP             ║  ║                  ║
   ║                  ║  ║  localhost:8082  ║  ║  • Service       ║
   ║  • Register      ║  ║                  ║  ║    Registry      ║
   ║  • Login         ║  ║  • Add/Subtract  ║  ║  • Heartbeats    ║
   ║  • JWT Issue     ║  ║  • Multiply/Div  ║  ║  • Health Check  ║
   ║  • OAuth2        ║  ║  • Compare       ║  ║  • Dashboard     ║
   ║  • BCrypt        ║  ║  • Convert       ║  ║                  ║
   ║  • User CRUD     ║  ║  • History       ║  ╚══════════════════╝
   ╚════════╤═════════╝  ╚════════╤═════════╝
            │                    │
            ▼                    ▼
   ┌─────────────────┐  ┌─────────────────┐
   │   security_db   │  │   quantity_db   │
   │    (MySQL)      │  │    (MySQL)      │
   |  [User Details] |   |   [Operations]  |
   └─────────────────┘  └─────────────────┘
```

</div>

> 💡 **Key Design:** The API Gateway is the **only public-facing component**. All other services are internal and communicate via Eureka discovery.

---

## 🛠️ Tech Stack

<div align="center">

| Technology | Version | Purpose | Badge |
|------------|---------|---------|-------|
| **Java** | 17 | Programming Language | ![Java](https://img.shields.io/badge/Java-17-ED8B00?style=flat-square&logo=openjdk) |
| **Spring Boot** | 3.2.0 | Application Framework | ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.0-6DB33F?style=flat-square&logo=spring-boot) |
| **Spring Cloud Gateway** | 2023.0.0 | API Gateway + Routing | ![Gateway](https://img.shields.io/badge/Spring_Cloud_Gateway-2023.0.0-6DB33F?style=flat-square) |
| **Spring Cloud Eureka** | 2023.0.0 | Service Registry & Discovery | ![Eureka](https://img.shields.io/badge/Eureka-2023.0.0-FF6B35?style=flat-square) |
| **Spring Security** | 6.x | Auth & Authorization | ![Security](https://img.shields.io/badge/Spring_Security-6.x-6DB33F?style=flat-square) |
| **Spring Data JPA** | 3.2.0 | ORM & Database Access | ![JPA](https://img.shields.io/badge/Spring_Data_JPA-3.2.0-6DB33F?style=flat-square) |
| **Spring OAuth2 Client** | 3.2.0 | GitHub Social Login | ![OAuth2](https://img.shields.io/badge/OAuth2-GitHub-181717?style=flat-square&logo=github) |
| **OpenFeign** | 2023.0.0 | Declarative REST Client | ![Feign](https://img.shields.io/badge/OpenFeign-2023.0.0-blue?style=flat-square) |
| **JWT (jjwt)** | 0.12.3 | Token Generation & Validation | ![JWT](https://img.shields.io/badge/JWT-0.12.3-000000?style=flat-square&logo=json-web-tokens) |
| **MySQL** | 8.0 | Production Database | ![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=flat-square&logo=mysql) |
| **Lombok** | Latest | Boilerplate Reduction | ![Lombok](https://img.shields.io/badge/Lombok-Latest-red?style=flat-square) |
| **Swagger / OpenAPI** | 2.1.0 | API Documentation | ![Swagger](https://img.shields.io/badge/Swagger-2.1.0-85EA2D?style=flat-square&logo=swagger&logoColor=black) |
| **Spring Boot Actuator** | 3.2.0 | Health Checks & Monitoring | ![Actuator](https://img.shields.io/badge/Actuator-3.2.0-6DB33F?style=flat-square) |
| **Maven** | 3.8+ | Build Tool | ![Maven](https://img.shields.io/badge/Maven-3.8+-C71A36?style=flat-square&logo=apache-maven) |

</div>

---

## 📁 Project Structure

```
📦 microservice-architecture/
│
├── 📂 eureka-server/                         🔵 Service Registry (Port: 8761)
│   ├── 📂 src/main/java/com/eureka/
│   │   └── 📄 EurekaServerApplication.java
│   ├── 📂 src/main/resources/
│   │   └── 📄 application.yml
│   └── 📄 pom.xml
│
├── 📂 security-service/                      🟢 Auth Service (Port: 8081)
│   ├── 📂 src/main/java/com/security/
│   │   ├── 📄 SecurityServiceApplication.java
│   │   ├── 📂 controller/
│   │   │   └── 📄 AuthController.java
│   │   ├── 📂 service/
│   │   │   ├── 📄 JwtService.java
│   │   │   └── 📄 CustomUserDetailsService.java
│   │   ├── 📂 repository/
│   │   │   └── 📄 UserRepository.java
│   │   ├── 📂 model/
│   │   │   └── 📄 User.java
│   │   ├── 📂 dto/
│   │   │   ├── 📄 LoginRequest.java
│   │   │   ├── 📄 RegisterRequest.java
│   │   │   └── 📄 AuthResponse.java
│   │   ├── 📂 security/
│   │   │   ├── 📄 JwtAuthFilter.java
│   │   │   └── 📄 OAuth2SuccessHandler.java
│   │   ├── 📂 config/
│   │   │   ├── 📄 SecurityConfig.java
│   │   │   └── 📄 SwaggerConfig.java
│   │   └── 📂 exception/
│   │       ├── 📄 GlobalExceptionHandler.java
│   │       ├── 📄 DuplicateEmailException.java
│   │       ├── 📄 UserNotFoundException.java
│   │       └── 📄 ErrorResponse.java
│   ├── 📂 src/main/resources/
│   │   └── 📄 application.yml
│   └── 📄 pom.xml
│
├── 📂 quantity-measurement-app/              🟡 Core Business Service (Port: 8082)
│   ├── 📂 src/main/java/com/quantity/
│   │   ├── 📄 QuantityMeasurementApplication.java
│   │   ├── 📂 controller/
│   │   │   └── 📄 QuantityMeasurementController.java
│   │   ├── 📂 service/
│   │   │   ├── 📄 IQuantityMeasurementService.java
│   │   │   └── 📄 QuantityMeasurementServiceImpl.java
│   │   ├── 📂 repository/
│   │   │   └── 📄 QuantityMeasurementRepository.java
│   │   ├── 📂 model/
│   │   │   └── 📄 QuantityMeasurementEntity.java
│   │   ├── 📂 dto/
│   │   │   ├── 📄 QuantityDTO.java
│   │   │   ├── 📄 QuantityInputDTO.java
│   │   │   ├── 📄 QuantityMeasurementDTO.java
│   │   │   └── 📄 OperationType.java
│   │   ├── 📂 core/
│   │   │   ├── 📄 IMeasurable.java
│   │   │   ├── 📄 SupportsArithmetic.java
│   │   │   ├── 📄 Quantity.java           ◄── Generic Quantity<U> Engine
│   │   │   ├── 📄 LengthUnit.java
│   │   │   ├── 📄 WeightUnit.java
│   │   │   ├── 📄 VolumeUnit.java
│   │   │   └── 📄 TemperatureUnit.java
│   │   ├── 📂 config/
│   │   │   ├── 📄 SecurityConfig.java
│   │   │   └── 📄 SwaggerConfig.java
│   │   └── 📂 exception/
│   │       ├── 📄 GlobalExceptionHandler.java
│   │       ├── 📄 QuantityMeasurementException.java
│   │       ├── 📄 DatabaseException.java
│   │       └── 📄 ErrorResponse.java
│   ├── 📂 src/main/resources/
│   │   └── 📄 application.yml
│   └── 📄 pom.xml
│
└── 📂 api-gateway/                           🔴 API Gateway (Port: 8080)
    ├── 📂 src/main/java/com/gateway/
    │   ├── 📄 ApiGatewayApplication.java
    │   ├── 📂 filter/
    │   │   └── 📄 JwtAuthenticationFilter.java
    │   └── 📂 config/
    │       └── 📄 GatewayConfig.java
    ├── 📂 src/main/resources/
    │   └── 📄 application.yml
    └── 📄 pom.xml
```

---

## 📋 Services Description

### 🔵 1. Eureka Server — `Port: 8761`

> The **Service Registry** — acts as a phonebook for all microservices.

Every service registers itself here on startup. The API Gateway queries Eureka to discover the location of other services **dynamically** — no hardcoded URLs.

| Feature | Details |
|---------|---------|
| 🔍 Service Discovery | Registers all services on startup |
| 💓 Heartbeat Monitoring | Health checks via periodic heartbeats |
| 📊 Dashboard | Visual UI to view all registered services |
| 🛡️ Self-Preservation | Disabled in dev mode for clean restarts |

---

### 🟢 2. Security Service — `Port: 8081`

> Handles all **authentication and user management** operations.

Responsible for user registration, login, JWT token generation, and GitHub OAuth2 login.

| Feature | Details |
|---------|---------|
| 📝 Registration | User registration with field validation |
| 🔑 JWT Generation | Stateless tokens valid for 24 hours |
| 🐙 GitHub OAuth2 | Social login via GitHub |
| 🔐 BCrypt Encryption | Password hashing with BCrypt |
| 🚫 Session Management | Stateless — no server-side sessions |

---

### 🟡 3. Quantity Measurement App — `Port: 8082`

> The **core business logic** service — handles all quantity operations.

Powered by a **Generic `Quantity<U>` engine** that performs automatic base unit conversions before any arithmetic operation.

| Feature | Details |
|---------|---------|
| ➕➖✖️➗ Operations | Add, Subtract, Multiply, Divide |
| ⚖️ Compare | Equality comparison with auto-conversion |
| 🔄 Convert | Convert between units in same category |
| 📏 Length Units | FEET, INCHES, YARDS, CENTIMETERS |
| ⚖️ Weight Units | MILLIGRAM, GRAM, KILOGRAM, POUND, TONNE |
| 🧪 Volume Units | LITRE, MILLILITRE, GALLON |
| 🌡️ Temperature Units | CELSIUS, FAHRENHEIT, KELVIN |
| 🗃️ History Tracking | All operations saved to MySQL |
| ⚠️ Error Tracking | Failed operations also recorded |

---

### 🔴 4. API Gateway — `Port: 8080`

> The **single entry point** for all client requests.

Routes requests to appropriate services, validates JWT tokens, and handles CORS — all built on **Spring WebFlux (reactive/non-blocking)**.

| Feature | Details |
|---------|---------|
| 🔐 JWT Validation | Validates tokens BEFORE forwarding requests |
| 🔀 Dynamic Routing | Routes via Eureka `lb://` service names |
| 🌐 CORS | Global CORS configuration |
| ⚡ Reactive | Built on WebFlux — non-blocking I/O |
| 🔁 Load Balancing | Automatic via `lb://` prefix |

---

## ✅ Prerequisites

> Make sure the following tools are installed before running the project.

| Tool | Version | Purpose | Download |
|------|---------|---------|----------|
| ☕ **Java JDK** | 17+ | Runtime Environment | [adoptium.net](https://adoptium.net/) |
| 🔨 **Maven** | 3.8+ | Build Tool | [maven.apache.org](https://maven.apache.org/) |
| 🗄️ **MySQL** | 8.0+ | Database Server | [dev.mysql.com](https://dev.mysql.com/downloads/) |
| 💡 **IntelliJ IDEA** | Latest | IDE | [jetbrains.com](https://www.jetbrains.com/idea/) |
| 📮 **Postman** | Latest | API Testing | [postman.com](https://www.postman.com/) |

---

## 🗄️ Database Setup

> Run these SQL commands in **MySQL Workbench** or your terminal before starting services.

```sql
-- ============================================================
-- Step 1: Create both databases
-- ============================================================
CREATE DATABASE security_db;
CREATE DATABASE quantity_db;

-- ============================================================
-- Step 2: Verify creation
-- ============================================================
SHOW DATABASES;

-- Expected output:
-- +--------------------+
-- | Database           |
-- +--------------------+
-- | quantity_db        |
-- | security_db        |
-- +--------------------+
```

> 💡 **Note:** Tables are **auto-created by Hibernate** on first startup due to `ddl-auto: update`. You only need to create the databases manually.

---

## 🚀 How to Run

### Step 1 — Clone / Open Projects

Open all **4 projects** in IntelliJ IDEA or Eclipse as **separate windows**:

```
File → Open → select each project folder → Open in New Window
```

| Project Folder | Opens On |
|---------------|----------|
| `eureka-server/` | Port 8761 |
| `security-service/` | Port 8081 |
| `quantity-measurement-app/` | Port 8082 |
| `api-gateway/` | Port 8080 |

---

### Step 2 — Maven Reload

For **each project**, reload Maven dependencies:

```
Right click pom.xml → Maven → Reload Project
```

---

### Step 3 — Update MySQL Credentials

In `security-service/src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/security_db
    username: root
    password: YOUR_MYSQL_PASSWORD   # ← 🔴 Update this
```

In `quantity-measurement-app/src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/quantity_db
    username: root
    password: YOUR_MYSQL_PASSWORD   # ← 🔴 Update this
```

---

### Step 4 — Start Services in Order

> ⚠️ **CRITICAL:** Services MUST be started in this exact order!

```
┌─────────────────────────────────────────────────────────┐
│                   STARTUP ORDER                         │
├─────────────────────────────────────────────────────────┤
│                                                         │
│  1️⃣  eureka-server            → wait for port 8761     │
│          ↓  (wait ~10 seconds)                          │
│  2️⃣  security-service         → wait for port 8081     │
│          ↓  (wait ~10 seconds)                          │
│  3️⃣  quantity-measurement-app → wait for port 8082     │
│          ↓  (wait ~10 seconds)                          │
│  4️⃣  api-gateway              → wait for port 8080     │
│                                                         │
└─────────────────────────────────────────────────────────┘
```

---

### Step 5 — Verify Everything is Running

Open your browser and navigate to:

```
http://localhost:8761
```

You should see **3 services registered** in the Eureka Dashboard:

```
┌──────────────────────────────────────┬──────────────────┐
│ Application                          │ Status           │
├──────────────────────────────────────┼──────────────────┤
│ SECURITY-SERVICE                     │ ✅ UP (1)        │
│ QUANTITY-MEASUREMENT-APP             │ ✅ UP (1)        │
│ API-GATEWAY                          │ ✅ UP (1)        │
└──────────────────────────────────────┴──────────────────┘
```

---

## 📡 API Endpoints

> All requests go through the **API Gateway on port 8080**

### 🔓 Auth Endpoints — Public (No Token Required)

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/auth/register` | Register a new user |
| `POST` | `/auth/login` | Login and receive JWT token |
| `GET` | `/auth/oauth-success` | GitHub OAuth2 callback |

---

### 🔐 Quantity Endpoints — Secured (JWT Token Required)

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/v1/quantities/add` | ➕ Add two quantities |
| `POST` | `/api/v1/quantities/subtract` | ➖ Subtract two quantities |
| `POST` | `/api/v1/quantities/multiply` | ✖️ Multiply two quantities |
| `POST` | `/api/v1/quantities/divide` | ➗ Divide two quantities |
| `POST` | `/api/v1/quantities/compare` | ⚖️ Compare two quantities |
| `POST` | `/api/v1/quantities/convert` | 🔄 Convert to another unit |
| `GET` | `/api/v1/quantities/history/operation/{op}` | 📜 History by operation type |
| `GET` | `/api/v1/quantities/history/type/{type}` | 📜 History by measurement type |
| `GET` | `/api/v1/quantities/count/{operation}` | 🔢 Count of successful operations |
| `GET` | `/api/v1/quantities/history/errored` | ⚠️ All errored operations |

---

## 📝 Request & Response Examples

### 1️⃣ Register a New User

```http
POST http://localhost:8080/auth/register
Content-Type: application/json

{
    "name": "John Doe",
    "email": "john@gmail.com",
    "mobileNumber": "9876543210",
    "password": "Password@123"
}
```

**✅ Response:**
```
"User registered successfully"
```

---

### 2️⃣ Login & Get JWT Token

```http
POST http://localhost:8080/auth/login
Content-Type: application/json

{
    "email": "john@gmail.com",
    "password": "Password@123"
}
```

**✅ Response:**
```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huQGdtYWlsLmNvbSIsImlhdCI6...",
    "message": "Login successful"
}
```

> 💡 Copy the `token` value — you'll need it in the `Authorization` header for all secured endpoints!

---

### 3️⃣ Add Two Quantities (Length)

```http
POST http://localhost:8080/api/v1/quantities/add
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
Content-Type: application/json

{
    "quantity1": {
        "value": 1.0,
        "unit": "FEET",
        "measurementType": "LengthUnit"
    },
    "quantity2": {
        "value": 12.0,
        "unit": "INCHES",
        "measurementType": "LengthUnit"
    }
}
```

**✅ Response:** *(1 FOOT + 12 INCHES = 2 FEET)*
```json
{
    "thisValue": 1.0,
    "thisUnit": "FEET",
    "thisMeasurementType": "LengthUnit",
    "thatValue": 12.0,
    "thatUnit": "INCHES",
    "thatMeasurementType": "LengthUnit",
    "operation": "add",
    "resultValue": 2.0,
    "resultUnit": "FEET",
    "resultMeasurementType": "LengthUnit",
    "error": false
}
```

---

### 4️⃣ Convert Temperature

```http
POST http://localhost:8080/api/v1/quantities/convert
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
Content-Type: application/json

{
    "quantity1": {
        "value": 100.0,
        "unit": "CELSIUS",
        "measurementType": "TemperatureUnit"
    },
    "quantity2": {
        "value": 0.0,
        "unit": "FAHRENHEIT",
        "measurementType": "TemperatureUnit"
    }
}
```

**✅ Response:** *(100°C = 212°F)*
```json
{
    "operation": "convert",
    "resultValue": 212.0,
    "resultUnit": "FAHRENHEIT",
    "resultMeasurementType": "TemperatureUnit",
    "error": false
}
```

---

### 5️⃣ Get Operation History

```http
GET http://localhost:8080/api/v1/quantities/history/operation/add
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

**✅ Response:**
```json
[
    {
        "operation": "add",
        "resultValue": 2.0,
        "resultUnit": "FEET",
        "resultMeasurementType": "LengthUnit",
        "error": false
    }
]
```

---

## 🔒 Security Flow

```
 CLIENT                  GATEWAY              SECURITY-SERVICE       QUANTITY-SERVICE
    │                       │                        │                      │
    │  POST /auth/login      │                        │                      │
    │──────────────────────►│                        │                      │
    │                       │   Forward to :8081     │                      │
    │                       │───────────────────────►│                      │
    │                       │                        │  Validate credentials │
    │                       │                        │  Generate JWT token   │
    │                       │◄───────────────────────│                      │
    │◄──────────────────────│  { token: "eyJ..." }   │                      │
    │                       │                        │                      │
    │  POST /api/v1/... + Bearer Token               │                      │
    │──────────────────────►│                        │                      │
    │                       │  🔐 Validate JWT       │                      │
    │                       │  ✅ Valid →             │                      │
    │                       │  Add X-Auth-User header│                      │
    │                       │──────────────────────────────────────────────►│
    │                       │                        │  Process operation    │
    │                       │◄──────────────────────────────────────────────│
    │◄──────────────────────│  { result... }         │                      │
    │                       │                        │                      │
    │  POST /api/v1/... + Invalid Token              │                      │
    │──────────────────────►│                        │                      │
    │                       │  ❌ Invalid JWT         │                      │
    │◄──────────────────────│  401 Unauthorized      │                      │
```

> 🔑 **Key Rule:** The API Gateway validates every JWT. Downstream services **trust the gateway** and don't re-validate tokens.

---

## 🔗 Service Communication

### Load-Balanced Routing via Eureka

```yaml
# API Gateway routes using lb:// (load balanced)
spring:
  cloud:
    gateway:
      routes:
        - id: security-service
          uri: lb://security-service        # Eureka resolves → localhost:8081
          predicates:
            - Path=/auth/**
            
        - id: quantity-service
          uri: lb://quantity-measurement-app # Eureka resolves → localhost:8082
          predicates:
            - Path=/api/v1/quantities/**
```

### OpenFeign Declarative REST Client

```java
// Declarative REST client — no boilerplate HTTP code needed!
@FeignClient(name = "quantity-measurement-app")
public interface QuantityClient {

    @PostMapping("/api/v1/quantities/add")
    QuantityMeasurementDTO add(@RequestBody QuantityInputDTO input);
    
    @PostMapping("/api/v1/quantities/convert")
    QuantityMeasurementDTO convert(@RequestBody QuantityInputDTO input);
}
```

> 💡 `lb://` means **load balanced** — Eureka dynamically resolves the service address. Works even if the service's IP or port changes!

---

## 📊 Eureka Dashboard

After starting all services, visit:

```
🌐  http://localhost:8761
```

You will see the Eureka dashboard with all registered services:

```
┌─────────────────────────────────────────────────────────────────────────┐
│  🔵  Eureka — Instances currently registered with Eureka                │
├──────────────────────────────┬───────┬──────────────────┬───────────────┤
│  Application                 │  AMIs │  Availability    │  Status       │
├──────────────────────────────┼───────┼──────────────────┼───────────────┤
│  API-GATEWAY                 │  n/a  │  (1)             │  ✅ UP (1)    │
│  SECURITY-SERVICE            │  n/a  │  (1)             │  ✅ UP (1)    │
│  QUANTITY-MEASUREMENT-APP    │  n/a  │  (1)             │  ✅ UP (1)    │
└──────────────────────────────┴───────┴──────────────────┴───────────────┘
```

---

## 📚 Swagger UI

> Swagger is available on individual service ports (not the gateway)

| Service | Swagger URL | Status |
|---------|------------|--------|
| 🟢 Security Service | [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html) | ✅ Available |
| 🟡 Quantity Measurement App | [http://localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html) | ✅ Available |
| 🔴 API Gateway | ❌ Not Available | Gateway only routes — no business APIs |

---

## ⚙️ Environment Configuration

### Port Summary

```
┌────────────────────────────────┬──────────┐
│  Service                       │  Port    │
├────────────────────────────────┼──────────┤
│  🌐 Eureka Server              │  8761    │
│  🔴 API Gateway                │  8080    │
│  🟢 Security Service           │  8081    │
│  🟡 Quantity Measurement App   │  8082    │
│  🗄️  MySQL                     │  3306    │
│  ⚛️  Frontend (React)          │  3000    │
└────────────────────────────────┴──────────┘
```

---

### JWT Configuration

```yaml
jwt:
  secret: **YOUR_JWT_SECRET**
  expiration: 86400000   # 24 hours in milliseconds (86400 seconds)
```

> ⚠️ **IMPORTANT:** The **exact same** JWT secret must be configured in **both** `security-service` AND `api-gateway`. If they differ, the gateway will reject all tokens!

---

### GitHub OAuth2 Configuration

```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          github:
            client-id: YOUR_GITHUB_CLIENT_ID         # ← from GitHub OAuth App
            client-secret: YOUR_GITHUB_CLIENT_SECRET  # ← from GitHub OAuth App
            scope: user:email
```

> 📖 **How to get GitHub credentials:**
> 1. Go to GitHub → Settings → Developer Settings → OAuth Apps
> 2. Click "New OAuth App"
> 3. Set callback URL to `http://localhost:8081/login/oauth2/code/github`
> 4. Copy Client ID and Client Secret

---

## ❌ Common Errors & Fixes

| ❌ Error | 🔍 Cause | ✅ Fix |
|---------|---------|-------|
| `Connection refused on 8761` | Eureka Server not running | Start `eureka-server` first and wait for full startup |
| `401 Unauthorized` | Missing or invalid JWT token | Add `Authorization: Bearer <token>` header |
| `No instances available` | Service not yet registered | Wait ~30 seconds after service startup for Eureka registration |
| `Access denied for user 'root'` | Wrong MySQL password | Update `password` in `application.yml` |
| `Unknown database 'security_db'` | Database not created | Run `CREATE DATABASE security_db;` and `CREATE DATABASE quantity_db;` |
| `Port 8080 already in use` | Another process using the port | Kill it: `taskkill /PID <pid> /F` (Windows) or `kill -9 <pid>` (Mac/Linux) |
| `Could not resolve placeholder` | Wrong YAML indentation | Check spacing in `application.yml` — use spaces, not tabs |
| `Failed to load ApplicationContext` | Dependency issue or missing config | Check `pom.xml` and run Maven reload |
| `JWT signature mismatch` | Different secrets in services | Ensure **same** JWT secret in security-service AND api-gateway |

---

## ✔️ Microservice Rules Followed

| # | Rule | Description | Status |
|---|------|-------------|--------|
| 1 | **Single Responsibility** | Each service does exactly one job | ✅ |
| 2 | **Own Database** | Each service has its own isolated database | ✅ |
| 3 | **Service Registry** | All services register with Eureka on startup | ✅ |
| 4 | **API Gateway** | Single entry point for all incoming traffic | ✅ |
| 5 | **Load Balanced URLs** | `lb://` prefix instead of hardcoded host:port | ✅ |
| 6 | **Same JWT Secret** | Consistent secret shared across security + gateway | ✅ |
| 7 | **Gateway Handles JWT** | Downstream services trust gateway — no re-validation | ✅ |
| 8 | **Unique Ports** | Each service runs on its own port | ✅ |
| 9 | **Correct Startup Order** | Eureka → Services → Gateway | ✅ |
| 10 | **Unique Package Names** | `com.security`, `com.quantity`, `com.gateway`, `com.eureka` | ✅ |
| 11 | **DTOs for Communication** | No JPA entities exposed via REST | ✅ |
| 12 | **Actuator Health Checks** | All services expose `/actuator/health` | ✅ |
| 13 | **YAML Configuration** | `application.yml` used in all services | ✅ |

---

### Author👨‍💻

[Harshal Choudhary](https://github.com/Harshal-25C) - Software Developer👨‍💻 | Cloud Enthusiast              
B.Tech - `[Computer Science & Engineering]`         
Java | Spring Boot | Maven | JWT & Security | OAuth | React.js | Clean Architecture 