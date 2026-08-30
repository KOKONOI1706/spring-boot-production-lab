# Requirements

## Project

Production-oriented REST API for a small commerce platform.

## Goal

Build a backend that demonstrates practical Spring Boot engineering skills beyond basic CRUD: authentication, authorization, validation, persistence, caching, testing, API documentation, and containerized development.

## Functional Requirements

### Authentication

- Register a user with a securely hashed password.
- Authenticate with email and password.
- Issue an access token and refresh token.
- Refresh an expired access token using a valid refresh token.
- Retrieve the authenticated user's profile.
- Logout and invalidate the refresh-token session.

### Authorization

The system supports three roles:

- `CUSTOMER`
- `STAFF`
- `ADMIN`

Protected operations must verify both authentication and the required role or resource ownership.

### Products

- Create, update, and deactivate products.
- Retrieve a product by ID.
- List products with pagination.
- Search products by keyword.
- Filter products by category and availability.
- Sort products by supported fields.

### Orders

- Create an order from available products.
- Calculate the order total on the server.
- Prevent ordering unavailable products.
- Retrieve an order owned by the authenticated customer.
- Allow staff/admin users to update order status.
- Maintain order status history.

## Non-Functional Requirements

### Security

- Passwords must never be stored in plaintext.
- Secrets must be supplied through environment variables.
- Protected endpoints must reject unauthenticated requests.
- Authorization must be enforced server-side.
- Input validation must happen at API boundaries.

### Reliability

- API errors must return consistent error responses.
- Database transactions must protect multi-step order operations.
- Critical business operations should be designed to be safe against duplicate requests.

### Performance

- Product listing/search should support pagination.
- Frequently accessed read data should be eligible for Redis caching.
- Cache invalidation must be explicitly considered when product data changes.

### Maintainability

- Separate controller, service, repository, and domain responsibilities.
- Avoid exposing persistence entities directly through API responses.
- Use DTOs for request and response contracts.
- Keep business rules in the service/domain layer rather than controllers.

### Testing

The project should contain:

- Unit tests for business services.
- Mockito-based tests for isolated dependencies.
- Integration tests for important API/database flows.
- Testcontainers for reproducible integration infrastructure where practical.

## Out of Scope for the First Version

- Real payment gateway integration.
- Microservices.
- Kubernetes.
- Cloud deployment.
- Complex frontend implementation.

These may be explored in later projects in the learning roadmap.