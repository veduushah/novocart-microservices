<div align="center">

# 🛒 NovaCart

### Production-Style Microservices E-Commerce Application

**Docker • Docker Compose • AWS EC2 • Spring Boot • React • MongoDB**

A production-style e-commerce application built using a microservices architecture and deployed on an AWS EC2 instance using Docker and Docker Compose. The project demonstrates containerized deployment, service orchestration, inter-service communication, and end-to-end application validation.

</div>
# 📸 Application Preview

<table align="center">
<tr>
<td align="center">
<img src="images/Novocart%20SS1.png" width="220"/><br>
<b>🔐 Login</b>
</td>

<td align="center">
<img src="images/novocart%20ss%204.png" width="220"/><br>
<b>👋 Sign In</b>
</td>

<td align="center">
<img src="images/novocart%20ss3.png" width="220"/><br>
<b>🛍️ Home</b>
</td>

<td align="center">
<img src="images/novocart%20ss2.png" width="220"/><br>
<b>📦 Orders</b>
</td>
</tr>
</table>

---

# 📖 Overview

NovaCart is a full-stack e-commerce application developed using a microservices architecture. Each service is independently containerized and orchestrated using Docker Compose.

The application was deployed on an Ubuntu EC2 instance where every service was built, configured, and validated successfully.

The complete user journey—including registration, login, product browsing, order placement, and viewing order history—was tested successfully.

---

# ✨ Features

- User Registration
- User Login
- JWT Authentication
- Product Catalogue
- Product Purchase
- Order Management
- Order History
- Responsive React Frontend
- REST APIs
- MongoDB Persistence
- Dockerized Microservices

---

# 🏗️ Architecture

```
                      User
                        │
                        ▼
                React + Vite Frontend
                     (Nginx)
                        │
        ┌───────────────┼────────────────┐
        ▼               ▼                ▼
  Auth Service    Product Service   Order Service
        │               │                │
        └───────────────┼────────────────┘
                        ▼
                     MongoDB
```

---

# ⚙️ Technology Stack

### Cloud

- AWS EC2

### Backend

- Java
- Spring Boot
- Maven

### Frontend

- React
- Vite
- Nginx

### Database

- MongoDB

### DevOps

- Docker
- Docker Compose
- Linux
- Git
- GitHub

---

# 📂 Project Structure

```
novocart-microservices

├── app
│   ├── backend
│   │   ├── auth-service
│   │   ├── product-service
│   │   └── order-service
│   │
│   ├── frontend
│   │
│   ├── docker-compose.yml
│   │
│   └── k8s
│
├── infra
│
├── images
│
└── README.md
```

---

# ☁️ AWS Deployment

The application was deployed manually on an Ubuntu EC2 instance.

Deployment steps included:

- Launched an EC2 instance
- Configured Security Groups
- Installed Docker
- Installed Docker Compose
- Cloned the repository
- Built all Docker images
- Created the Docker network
- Started MongoDB
- Deployed backend microservices
- Deployed React frontend
- Configured environment variables
- Verified inter-service communication
- Validated complete application workflow

---

# 🐳 Containerized Services

| Service | Port |
|----------|------|
| Frontend | 5173 |
| Auth Service | 8081 |
| Product Service | 8082 |
| Order Service | 8083 |
| MongoDB | 27017 |

---

# 🔄 Service Communication

The application follows a microservices architecture where each service runs in its own Docker container.

```
Frontend
     │
     ▼
Authentication Service
     │
     ├────────► Product Service
     │
     └────────► Order Service
                   │
                   ▼
                MongoDB
```

All services communicate over a dedicated Docker network using service discovery provided by Docker Compose.

---

# 🔐 Authentication

Authentication is implemented using JWT.

Features include:

- User Registration
- User Login
- Secure Token Generation
- Protected REST APIs
- User Session Validation

---

# 🛍️ User Workflow

A user can:

- Register
- Login
- Browse Products
- Purchase Products
- View Orders
- Logout

---

# ✅ Validation & Testing

The application was tested after deployment to ensure complete functionality.

### Authentication

- ✅ User Registration
- ✅ User Login
- ✅ JWT Validation

### Product Service

- ✅ Product Listing
- ✅ Product Retrieval
- ✅ Product Availability

### Order Service

- ✅ Order Placement
- ✅ Order Retrieval
- ✅ Order History

### Infrastructure

- ✅ Docker Networking
- ✅ MongoDB Connectivity
- ✅ Environment Variables
- ✅ Inter-Service Communication
- ✅ Container Health Validation

---

# 🚀 Run Locally

Clone the repository

```bash
git clone https://github.com/veduushah/novocart-microservices.git
```

Navigate to the application

```bash
cd novocart-microservices/app
```

Build Docker images

```bash
docker compose build
```

Start containers

```bash
docker compose up -d
```

Stop containers

```bash
docker compose down
```

---

# 🎯 DevOps Skills Demonstrated

- Docker
- Docker Compose
- AWS EC2
- Linux Administration
- Microservices Deployment
- Container Networking
- Environment Variable Management
- Service Troubleshooting
- Production-Style Deployment
- Git & GitHub

---

# 📈 Future Enhancements

- Kubernetes Deployment
- Amazon EKS
- Jenkins CI/CD Pipeline
- Terraform Infrastructure Provisioning
- Prometheus Monitoring
- Grafana Dashboards
- Kubernetes Ingress
- Horizontal Pod Autoscaler

---

# 📌 Project Status

| Component | Status |
|-----------|--------|
| Frontend | ✅ Running |
| Auth Service | ✅ Running |
| Product Service | ✅ Running |
| Order Service | ✅ Running |
| MongoDB | ✅ Running |
| Registration | ✅ Tested |
| Login | ✅ Tested |
| Product Purchase | ✅ Tested |
| Order History | ✅ Tested |

---

# 👨‍💻 Author

## Ved Shah

**DevOps Engineer**

**Skills**

AWS • Docker • Kubernetes • Terraform • Jenkins • Linux • Git • Prometheus • Grafana

---

⭐ If you found this project interesting, feel free to give it a Star.
