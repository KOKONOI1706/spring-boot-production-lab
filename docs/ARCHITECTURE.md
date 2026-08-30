# Architecture

## Architectural Style

The first version uses a modular layered architecture inside a single Spring Boot application.

```text
Client
  |
  v
REST Controllers
  |
  v
Application Services
  |
  +--------------------+
  |                    |
  v                    v
Repositories        Domain Rules
  |                    |
  +----------+---------+
             |
             v
        PostgreSQL

Redis is introduced as a separate infrastructure component for
cacheable reads and selected coordination use cases.
```

## Layers

### Controller

Responsible for HTTP concerns only:

- Request mapping
- DTO binding
- Validation trigger
- Authentication context access
- HTTP response mapping

Controllers should not contain core business rules.

### Service

Owns application use cases and business rules:

- User registration/authentication flows
- Product lifecycle operations
- Order creation and status transitions
- Transaction boundaries for multi-step operations
- Cache interaction where it belongs to the use case

### Repository

Responsible for persistence access through Spring Data JPA.

Repositories should not contain application-level business decisions.

### Domain / Model

Represents persistent entities and domain concepts such as User, Product, Order, OrderItem, and OrderStatusHistory.

### DTO

Defines stable API contracts independently from persistence entities.

```text
HTTP Request
    ↓
Request DTO
    ↓
Controller
    ↓
Service
    ↓
Repository
    ↓
Entity
```

Responses follow the reverse direction through dedicated response DTOs.

## Authentication Flow

```text
Client
  ↓
POST /auth/login
  ↓
Authentication Service
  ↓
Password verification
  ↓
Access Token + Refresh Token
  ↓
Client
```

For protected requests:

```text
Client
  ↓
Authorization header
  ↓
Spring Security filter chain
  ↓
Authenticated principal
  ↓
Role / ownership authorization
  ↓
Controller
```

## Order Transaction Boundary

Order creation is treated as a transaction because several changes must succeed or fail together:

```text
BEGIN
  ├── validate products
  ├── validate availability
  ├── calculate server-side total
  ├── create order
  ├── create order items
  └── update required inventory state
COMMIT
```

If a critical operation fails, the transaction should roll back instead of leaving a partially created order.

## Caching Strategy

Redis will be used selectively rather than caching every database operation.

Initial candidate:

```text
GET /products
      ↓
Cache lookup
  ┌───┴────┐
 HIT     MISS
  │        │
  ↓        ↓
Return   PostgreSQL
           ↓
        Store cache
           ↓
         Return
```

Product mutations must invalidate or update affected cache entries so stale product information is not served indefinitely.

## Design Principles

- Keep controllers thin.
- Keep business rules out of controllers and repositories.
- Prefer explicit transaction boundaries for state-changing workflows.
- Do not expose persistence entities directly as public API contracts.
- Introduce infrastructure components only when a concrete use case exists.
- Document trade-offs instead of presenting the architecture as universally optimal.

## Evolution Path

The architecture is intentionally a modular monolith first.

```text
Modular Monolith
      ↓
Performance / Testing / Observability
      ↓
Identify actual service boundaries
      ↓
Selective extraction into services
```

Microservices and Kubernetes are deliberately outside the first version so the project focuses on understanding the fundamentals before adding distributed-system complexity.