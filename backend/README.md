# 📦 Data Archival Service – Documentation

This document explains the complete architecture, components, and deployment of the Data Archival Service.

---

## 🏗️ System Architecture

### 🔹 High-Level Diagram

```
+----------------------+
|  Frontend (React)    |---> http://themadtie.art/data-archival-service
+----------------------+
            |
            v
+----------------------+
|  REST API Gateway    |---> [Optional: AWS ALB or NGINX]
+----------------------+
            |
            v
+----------------------+
| Spring Boot App (ECS)|
| Container on Fargate |
+----------------------+
            |
            v
+----------------------+
| PostgreSQL Databases |
| (Archival + Archived)|
+----------------------+

+----------------------+
| React (UI hosting)   |
| Amplify (Frontend CI)|
+----------------------+
```

---

## ⚙️ Component Breakdown

### 🔸 Spring Boot Backend

| Layer        | Description |
|--------------|-------------|
| Controller   | Exposes REST endpoints |
| Service      | Business logic for archiving/deleting/viewing |
| Repository   | JPA Repositories (2 DBs) |
| Entity       | Table mappings for archival/archived |
| DTO          | Request objects |
| Security     | JWT auth, role-based access |
| Config       | Datasource setup for dual PostgreSQL DBs |

---

## 🚀 Deployment Flow (CI/CD)

1. **Code pushed to GitHub**
2. **GitHub Actions**:
    - Maven build
    - Docker image built + pushed to Amazon ECR
3. **ECS Fargate**:
    - Auto-pull new image from ECR
    - Deploys container (public IP)
4. **Amplify** (React UI):
    - Hosted on `/data-archival-service` via domain `themadtie.art`
    - Integrates with backend via ECS IP

---

## 🔐 Security

- JWT Authentication (HS256, 1-hour expiry)
- Spring Security with `@PreAuthorize` on role checks
- Dual EntityManager isolation between archival and archived DBs

---

## 📄 API Summary

| Endpoint | Method | Auth | Description |
|----------|--------|------|-------------|
| `/api/auth/register` | POST | ❌ | Register user |
| `/api/auth/login` | POST | ❌ | Get JWT |
| `/api/archival/config` | GET/POST | ✅ | View or update config |
| `/api/archival/delete` | POST | ✅ | Delete data |
| `/api/archival/run` | POST | ✅ | Archive data |
| `/api/archival/view/{table}` | GET | ✅ (RBAC) | View archived data |
| `/actuator/health` | GET | ❌ | App health |
| `/swagger-ui/index.html` | GET | ❌ | API UI |
| `/v3/api-docs` | GET | ❌ | OpenAPI spec |

---

## 🧪 Testing

- Unit Tests: JUnit 5 + Mockito
- Coverage: IntelliJ coverage + JaCoCo ready
- Directory: `src/test/java/com/sumitsee/archival_service`

---

## 🌐 Hosting

| Component | Host | Tech |
|----------|------|------|
| Backend | ECS Fargate | Spring Boot |
| Frontend | Amplify + S3 | React |
| Domain | themadtie.art | Namecheap → Amplify → S3 → ECS (manual route) |

---

## 📦 Tools Used

- Spring Boot 3.4.5
- Maven
- Docker + ECR
- ECS Fargate
- PostgreSQL
- Swagger (springdoc)
- ReactJS (Vite)
- GitHub Actions (CI/CD)
- Amplify + S3

---

## 📎 GitHub

- Backend: [https://github.com/ultimate-vw/data-archival-service](https://github.com/ultimate-vw/data-archival-service)
- Frontend: [https://github.com/ultimate-vw/data-archival-ui](https://github.com/ultimate-vw/data-archival-ui)