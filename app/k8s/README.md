# NovaCart Backend - Kubernetes Deployment

This directory contains Kubernetes manifests for deploying the NovaCart backend services to Amazon EKS.

## Architecture

- **Auth Service**: User authentication and JWT token management
- **Product Service**: Product CRUD operations
- **Order Service**: User product orders
- **MongoDB**: External SaaS MongoDB (not deployed in cluster)

## Prerequisites

1. **EKS Cluster**: Running Amazon EKS cluster
2. **Docker Registry**: Accessible Docker registry for images
3. **MongoDB SaaS**: External MongoDB instance
4. **Ingress Controller**: NGINX Ingress Controller installed
5. **Cert Manager**: For SSL certificate management

## Environment Variables

Update the following in your Jenkins pipeline:

```bash
AWS_REGION=us-east-1
EKS_CLUSTER_NAME=backend-dev-cluster
DOCKER_REGISTRY=your-docker-registry.com
```

## Deployment Structure

```
k8s/
├── namespace.yaml          # NovaCart namespace
├── configmap.yaml          # Shared configuration
├── ingress.yaml            # API routing and SSL
└── README.md              # This file

backend/
├── auth-service/
│   ├── k8s/
│   │   ├── deployment.yaml
│   │   └── service.yaml
│   └── Jenkinsfile
├── product-service/
│   ├── k8s/
│   │   ├── deployment.yaml
│   │   └── service.yaml
│   └── Jenkinsfile
└── order-service/
    ├── k8s/
    │   ├── deployment.yaml
    │   └── service.yaml
    └── Jenkinsfile
```

## Services

### Auth Service
- **Port**: 8081
- **Health Check**: `/api/auth/health`
- **Database**: `novocart_auth`

### Product Service
- **Port**: 8082
- **Health Check**: `/api/products/health`
- **Database**: `novocart_products`

### Order Service
- **Port**: 8083
- **Health Check**: `/api/orders/health`
- **Database**: `novocart_orders`

## Ingress Configuration

The ingress routes traffic from `api.novocart.in` to the appropriate services:

- `https://api.novocart.in/api/auth/*` → Auth Service
- `https://api.novocart.in/api/products/*` → Product Service
- `https://api.novocart.in/api/orders/*` → Order Service

## Deployment Commands

### Manual Deployment

```bash
# Create namespace
kubectl apply -f k8s/namespace.yaml

# Create configmap
kubectl apply -f k8s/configmap.yaml

# Deploy services
kubectl apply -f backend/auth-service/k8s/
kubectl apply -f backend/product-service/k8s/
kubectl apply -f backend/order-service/k8s/

# Deploy ingress
kubectl apply -f k8s/ingress.yaml
```

### Check Deployment Status

```bash
# Check pods
kubectl get pods -n novocart

# Check services
kubectl get services -n novocart

# Check ingress
kubectl get ingress -n novocart

# Check logs
kubectl logs -f deployment/auth-service -n novocart
kubectl logs -f deployment/product-service -n novocart
kubectl logs -f deployment/order-service -n novocart
```

## Jenkins Pipeline

Each service has its own Jenkinsfile for individual deployment, and there's a main Jenkinsfile in the `backend/` directory for deploying all services together.

### Individual Service Deployment
```bash
# Deploy auth service only
cd backend/auth-service
# Run Jenkins pipeline for auth-service
```

### Full Backend Deployment
```bash
# Deploy all services
cd backend/
# Run Jenkins pipeline for all services
```

## Monitoring and Health Checks

All services include:
- **Liveness Probes**: Check if the service is running
- **Readiness Probes**: Check if the service is ready to receive traffic
- **Resource Limits**: CPU and memory limits
- **Health Endpoints**: `/health` endpoints for monitoring

## Security

- **JWT Secret**: Shared across auth and order services
- **CORS**: Configured for `https://novocart.in`
- **SSL/TLS**: Automatic certificate management with Let's Encrypt
- **Network Policies**: Can be added for additional security

## Scaling

Each service is configured with:
- **Replicas**: 2 instances for high availability
- **Resource Requests**: 256Mi memory, 250m CPU
- **Resource Limits**: 512Mi memory, 500m CPU

To scale a service:
```bash
kubectl scale deployment auth-service --replicas=3 -n novocart
```

## Troubleshooting

### Common Issues

1. **Pod Not Starting**: Check resource limits and MongoDB connectivity
2. **Health Check Failures**: Verify health endpoints are accessible
3. **Ingress Issues**: Check NGINX ingress controller and SSL certificates
4. **Database Connection**: Verify MongoDB SaaS connectivity

### Debug Commands

```bash
# Describe pod for events
kubectl describe pod <pod-name> -n novocart

# Check service endpoints
kubectl get endpoints -n novocart

# Port forward for local testing
kubectl port-forward service/auth-service 8081:8081 -n novocart
```

## Production Considerations

1. **Secrets Management**: Use Kubernetes secrets for sensitive data
2. **Monitoring**: Implement Prometheus and Grafana
3. **Logging**: Centralized logging with ELK stack
4. **Backup**: Database backup strategies
5. **Disaster Recovery**: Multi-region deployment
6. **Security**: Network policies and RBAC

