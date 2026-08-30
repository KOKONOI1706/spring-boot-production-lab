# Learning Log

This repository is a hands-on engineering lab. Each feature should answer a concrete question rather than simply add another technology to the stack.

## Phase 1 — Spring Boot Foundations

- [ ] Layered architecture
- [ ] Spring Data JPA
- [ ] PostgreSQL
- [ ] DTO request/response contracts
- [ ] Bean Validation
- [ ] Global exception handling
- [ ] Transaction boundaries

**Questions to answer**

- Why should controllers remain thin?
- When should a transaction start and end?
- Why should API DTOs be separated from JPA entities?

## Phase 2 — Security

- [ ] Spring Security filter chain
- [ ] Password hashing
- [ ] JWT access token
- [ ] Refresh token
- [ ] Role-based authorization
- [ ] Resource ownership checks

**Questions to answer**

- Authentication vs authorization?
- Where should authorization rules live?
- What should happen when a token is expired or revoked?

## Phase 3 — Redis

- [ ] Product cache
- [ ] Cache invalidation
- [ ] TTL strategy
- [ ] Rate limiting
- [ ] Idempotency experiment

**Questions to answer**

- What data is actually worth caching?
- What consistency problems does caching introduce?
- What happens when Redis is unavailable?

## Phase 4 — Testing

- [ ] JUnit 5
- [ ] Mockito
- [ ] Service unit tests
- [ ] Controller/API integration tests
- [ ] Testcontainers

**Questions to answer**

- What should be unit tested?
- When is mocking useful and when does it hide integration problems?
- Which business rules deserve regression tests?

## Phase 5 — Containerization and Operations

- [ ] Dockerfile
- [ ] Docker Compose
- [ ] Environment-specific configuration
- [ ] Structured logging
- [ ] API documentation

**Questions to answer**

- What belongs in an image and what belongs in runtime configuration?
- How should local development infrastructure be reproduced?

## Phase 6 — Future Experiments

These are intentionally postponed until the fundamentals are solid:

- [ ] Kafka / event-driven processing
- [ ] AWS deployment
- [ ] Linux + Nginx
- [ ] GitHub Actions CI/CD
- [ ] Microservice extraction
- [ ] Kubernetes

## Rule

Do not mark a technology as learned because the dependency was added. Mark it complete only after implementing a feature, testing it, documenting the design decision, and being able to explain the trade-off in an interview.