# Database Design

## Core Entities

```text
users
  |
  +----< orders

products
  |
  +----< order_items >---- orders

orders
  |
  +----< order_status_history
```

## Users

| Column | Type | Notes |
| --- | --- | --- |
| id | UUID | Primary key |
| email | VARCHAR | Unique login identifier |
| password_hash | VARCHAR | Hashed password only |
| full_name | VARCHAR | Display name |
| role | VARCHAR | CUSTOMER / STAFF / ADMIN |
| active | BOOLEAN | Account status |
| created_at | TIMESTAMP | Creation time |
| updated_at | TIMESTAMP | Last update |

## Products

| Column | Type | Notes |
| --- | --- | --- |
| id | UUID | Primary key |
| name | VARCHAR | Product name |
| description | TEXT | Product description |
| price | DECIMAL | Monetary value |
| category | VARCHAR | Product category |
| available_quantity | INTEGER | Current available quantity |
| active | BOOLEAN | Soft availability flag |
| created_at | TIMESTAMP | Creation time |
| updated_at | TIMESTAMP | Last update |

## Orders

| Column | Type | Notes |
| --- | --- | --- |
| id | UUID | Primary key |
| customer_id | UUID | FK → users.id |
| status | VARCHAR | Order lifecycle status |
| total_amount | DECIMAL | Server-calculated total |
| created_at | TIMESTAMP | Creation time |
| updated_at | TIMESTAMP | Last update |

## Order Items

| Column | Type | Notes |
| --- | --- | --- |
| id | UUID | Primary key |
| order_id | UUID | FK → orders.id |
| product_id | UUID | FK → products.id |
| quantity | INTEGER | Ordered quantity |
| unit_price | DECIMAL | Price captured at order time |
| subtotal | DECIMAL | quantity × unit_price |

The order item stores `unit_price` instead of relying on the current product price. This preserves the price that was actually used when the order was created.

## Order Status History

| Column | Type | Notes |
| --- | --- | --- |
| id | UUID | Primary key |
| order_id | UUID | FK → orders.id |
| previous_status | VARCHAR | Previous state |
| new_status | VARCHAR | New state |
| changed_by | UUID | FK → users.id |
| created_at | TIMESTAMP | Transition time |

## Relationships

```text
User 1 ────── * Order
Order 1 ───── * OrderItem
Product 1 ─── * OrderItem
Order 1 ───── * OrderStatusHistory
User 1 ────── * OrderStatusHistory
```

## Data Integrity Rules

1. Email must be unique.
2. Product price must be non-negative.
3. Product quantity cannot be negative.
4. Order quantity must be positive.
5. An order must have at least one order item.
6. Order totals are calculated by the server.
7. Historical order item prices must not change when the product price changes.
8. Orders may only be accessed by their owner unless the requester has an authorized staff/admin role.
9. State transitions must be validated by business rules.
10. Password hashes must never be returned through API DTOs.

## Future Considerations

The first implementation intentionally avoids premature optimization. Indexes, optimistic locking, and more advanced inventory consistency mechanisms will be introduced when the corresponding workload or concurrency scenario is demonstrated.