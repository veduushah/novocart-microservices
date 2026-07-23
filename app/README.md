# NovaCart

NovaCart is a three-tier e-commerce application built for practice with React, Spring Boot microservices, and MongoDB.

## Architecture

1. **Presentation tier**: React + Vite storefront (`frontend`, port 5173).
2. **Application tier**: Auth, Product, and Order Spring Boot services (ports 8081–8083).
3. **Data tier**: MongoDB databases for authentication, products, and orders.

## Services

| Service | Port | API | Database | Responsibility |
| --- | ---: | --- | --- | --- |
| Auth Service | 8081 | `/api/auth` | `novocart_auth` | Registration, login, and JWT validation |
| Product Service | 8082 | `/api/products` | `novocart_products` | Product catalog and inventory |
| Order Service | 8083 | `/api/orders` | `novocart_orders` | Authenticated purchase history |

The product service seeds three sample products on its first startup. An order stores the selected product, quantity, price, total, date, and status.

## Run locally

```bash
cd app
docker compose up --build
```

Open `http://localhost:5173`. Register an account, browse the products, and place an order. The frontend is configured with:

```env
VITE_AUTH_API=http://localhost:8081/api/auth
VITE_PRODUCT_API=http://localhost:8082/api/products
VITE_ORDER_API=http://localhost:8083/api/orders
```

## Local development without Docker

Start MongoDB, then run each backend service in a separate terminal:

```bash
cd backend/auth-service && ./mvnw spring-boot:run
cd backend/product-service && ./mvnw spring-boot:run
cd backend/order-service && ./mvnw spring-boot:run
```

For the frontend:

```bash
cd frontend
npm install
npm run dev
```

## Health checks

```bash
curl http://localhost:8081/api/auth/health
curl http://localhost:8082/api/products/health
curl http://localhost:8083/api/orders/health
```

## API examples

Create an account:

```bash
curl -X POST http://localhost:8081/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"name":"Alex Shopper","email":"alex@example.com","password":"password123"}'
```

List products:

```bash
curl http://localhost:8082/api/products
```

Place an order after signing in and replacing `TOKEN` and `PRODUCT_ID`:

```bash
curl -X POST http://localhost:8083/api/orders \
  -H "Authorization: Bearer TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"productId":"PRODUCT_ID","productName":"Wireless Noise-Cancelling Headphones","price":129.99,"quantity":1}'
```
