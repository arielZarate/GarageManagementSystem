# 🧩 Workshop Management System (Spring Boot + Thymeleaf + Tailwind)

## 📌 Project Description

Web-based system for managing a generic repair workshop (cars, motorcycles, trucks).
Manages customers, vehicles, repair orders, quotes (budgets), and provides real-time status tracking.

The system allows:
- Customer and vehicle registration
- Quote/budget generation (independent)
- Repair order management
- Public status consultation (by license plate)
- Multi-employee access (owner + mechanics)

---

## 🎯 Objectives

- Implement Hexagonal Architecture (Ports & Adapters)
- Manage customers, vehicles, quotes, and repair orders (CRUD)
- Generate detailed quotes/budgets with labor and parts
- Track repair status in real-time
- Public consultation via license plate
- Apply backend best practices
- Professional admin dashboard interface
- Relational database (PostgreSQL)

---

## 🏗️ Architecture

### 🔹 Backend

- Java 21 + Spring Boot
- Spring MVC
- Spring Data JPA
- Spring Security + JWT (basic authentication)

### 🔹 Frontend

- Thymeleaf (server-side rendering)
- Tailwind CSS (styling)
- Material Design 3 Theme System (CSS custom properties)

### 🔹 Database

- PostgreSQL

---

## 📦 Project Structure (Hexagonal Architecture)

```
src/main/java/com/arielzarate/GarageManagementSystem/
│
├── domain/
│   ├── model/              # Pure business POJOs (no framework annotations)
│   ├── ports/
│   │   ├── in/             # Inbound port interfaces (use cases)
│   │   └── out/            # Outbound port interfaces (repositories)
│   └── services/           # Business logic implementations
│
├── application/
│   └── services/           # Application-level orchestration services
│
├── interfaces/
│   ├── errors/             # Error handling
│   ├── middleware/         # Middleware (filters, interceptors)
│   ├── rest/               # REST controllers (inbound adapters)
│   │   ├── dto/            # Request/Response DTOs per entity
│   │   └── mappers/        # DTO ↔ Domain MapStruct mappers
│   └── security/           # Security config
│
└── infraestructure/
    ├── adapters/           # Outbound adapter implementations
    │   └── mappers/        # Domain ↔ JPA entity MapStruct mappers
    ├── config/             # Spring/application config
    └── persistence/        # JPA persistence layer
        ├── entities/       # JPA entities (@Entity, extends BaseEntity)
        ├── enums/          # JPA enums (Role, VehicleType, RepairStatus, QuoteStatus)
        ├── mappers/        # Domain ↔ Entity mapping
        └── repositories/   # Spring Data JPA repositories
```

```
src/main/resources
│
├── templates/
│   ├── layout/             # base.html, fragments.html (navbar, sidebar, footer)
│   ├── customer/           # list, detail, form
│   ├── employee/           # list, detail, form
│   ├── company/            # view, form
│   └── index.html
├── static/
│   ├── css/
│   │   ├── input.css       # Tailwind source + theme variables
│   │   └── output.css      # Compiled Tailwind
│   └── js/
│       └── darkMode-theme.js   # Theme + dark mode manager
├── application.yml
└── application.properties
```

---

## 🎨 Theme System (Material Design 3)

### 5 Color Themes

All colors are defined as CSS custom properties (`--md-*`) in `input.css` and mapped to Tailwind utility classes via `@theme`.

| Theme   | Class           | Primary    | Navbar (inverse-surface) | Description |
|---------|-----------------|------------|--------------------------|-------------|
| Azul    | `theme-azul`    | `#1976D2`  | `#0D47A1`                | Default, corporate blue |
| Verde   | `theme-verde`   | `#B0FF42`  | `#33691E`                | Lime green |
| Violeta | `theme-violeta` | `#9142FF`  | `#4527A0`                | Purple |
| Naranja | `theme-naranja` | `#F57C00`  | `#BF360C`                | Warm orange |
| Celeste | `theme-celeste` | `#42B0FF`  | `#0288D1`                | Sky blue |

### Theme Tokens (CSS Variables)

Each theme defines these tokens, usable as Tailwind classes:

| CSS Variable          | Tailwind Class          | Usage                     |
|-----------------------|-------------------------|---------------------------|
| `--md-primary`        | `bg-primary` / `text-primary` | Buttons, links       |
| `--md-on-primary`     | `text-on-primary`       | Text on primary buttons   |
| `--md-primary-container` | `bg-primary-container` | Badges, hover backgrounds |
| `--md-on-primary-container` | `text-on-primary-container` | Text on badges      |
| `--md-secondary`      | `bg-secondary`          | Role badges, labels       |
| `--md-surface`        | `bg-surface`            | Page background           |
| `--md-surface-container` | `bg-surface-container` | Cards, tables, modals   |
| `--md-inverse-surface` | `bg-inverse-surface`    | Navbar, sidebar, footer   |
| `--md-on-surface`     | `text-on-surface`       | General text color        |
| `--md-outline`        | `border-outline`        | Input borders             |
| `--md-outline-variant` | `border-outline-variant` | Table borders, dividers  |

### Dark Mode

- Unified slate theme, independent of color theme
- Controlled via `.dark` class on `<html>`
- Toggle in navbar (moon/sun icon)
- Persisted in `localStorage`
- Dark mode CSS is defined last in `input.css` to override all themes

### Theme Selector

- Located in the **sidebar** under "Configuración → Temas"
- Five clickable rows with colored dots
- Active theme highlighted with border
- Persisted in `localStorage`

---

## 🧩 Entities

All entities extend `BaseEntity` (id, createdAt, updatedAt, deletedAt).

### 🔸 COMPANY (Empresa)

Configuration for invoices/quotes (logo, business name, legal name, address, phone, hours, CUIT).

| Field | Type | Description |
|-------|------|-------------|
| id | Long | Primary key (inherited) |
| businessName | String | Commercial name |
| legalName | String | Legal name (razón social) |
| logo | String | Logo path/URL |
| address | AddressEmbeddable | Full address |
| phone | String | Phone number |
| email | String | Email |
| horario | String | Business hours |
| cuit | String | CUIT (tax ID) |

### 🔸 EMPLOYEE (Usuario/Empleado)

System users with roles. Legajo tracks all repairs performed. Active/inactive status managed via radio buttons in edit form.

| Field | Type | Description |
|-------|------|-------------|
| id | Long | Primary key (inherited) |
| legajo | String | Employee ID (auto-generated) |
| firstName | String | First name |
| lastName | String | Last name |
| dni | String | ID number |
| birthDate | LocalDate | Date of birth |
| CUIT | String | CUIT (tax ID) |
| role | Role (enum) | ADMIN, MECHANIC |
| email | String | Email (unique) |
| password | String | Hashed password |
| phone | String | Phone |
| address | AddressEmbeddable | Home address |
| active | Boolean | Active/inactive |
| joinDate | LocalDate | Start date |

### 🔸 CUSTOMER (Cliente)

Vehicle owner/customer. Active/inactive status managed via radio buttons in edit form.

| Field | Type | Description |
|-------|------|-------------|
| id | Long | Primary key (inherited) |
| customerCode | String | Customer ID (auto-generated, unique) |
| firstName | String | First name |
| lastName | String | Last name |
| phone | String | Phone |
| email | String | Email |
| cuit | String | CUIT (tax ID) |
| dni | String | ID number |
| address | AddressEmbeddable | Full address |
| active | Boolean | Active/inactive |

### 🔸 VEHICLE

Registered vehicle in the system.

| Field | Type | Description |
|-------|------|-------------|
| id | Long | Primary key (inherited) |
| licensePlate | String | License plate (unique) |
| brand | String | Brand (VW, Ford, Yamaha) |
| model | String | Model (Gol, Ranger, Fazer) |
| year | Integer | Year |
| version | String | Version (1.6, Sport, S) |
| vehicleType | VehicleType (enum) | CAR, MOTORCYCLE, TRUCK |
| color | String | Color |
| kilometers | Integer | Current km |
| chassisNumber | String | Chassis number (VIN) |
| engineNumber | String | Engine number |
| customer | Customer | Owner |

### 🔸 REPAIR_ORDER (Orden de Reparación)

Repair work order.

| Field | Type | Description |
|-------|------|-------------|
| id | Long | Primary key (inherited) |
| orderNumber | String | Order number (unique) |
| vehicle | Vehicle | Vehicle |
| customer | Customer | Customer |
| employee | Employee | Assigned mechanic |
| status | RepairStatus (enum) | PENDING, IN_PROGRESS, WAITING_PARTS, COMPLETED, DELIVERED |
| diagnosis | Text | Initial diagnosis |
| admissionDate | LocalDateTime | Admission date |
| estimatedFinish | LocalDateTime | Estimated finish |
| totalCost | BigDecimal | Total cost |
| notes | Text | Additional notes |

### 🔸 REPAIR_DETAIL

Line items for repair order (labor + parts).

| Field | Type | Description |
|-------|------|-------------|
| id | Long | Primary key (inherited) |
| repairOrder | RepairOrder | Parent order |
| description | String | Description |
| partName | String | Part name |
| partPrice | BigDecimal | Part price (per unit) |
| quantity | Integer | Quantity |
| laborPrice | BigDecimal | Fixed labor price |
| subtotal | BigDecimal | Line total |
| photos | List\<String\> | Photo paths |

### 🔸 QUOTE (Presupuesto)

Budget/quote (independent flow).

| Field | Type | Description |
|-------|------|-------------|
| id | Long | Primary key (inherited) |
| quoteNumber | String | Quote number (unique) |
| quoteDate | LocalDate | Quote date |
| vehicle | Vehicle | Vehicle |
| customer | Customer | Customer |
| status | QuoteStatus (enum) | DRAFT, SENT, ACCEPTED, REJECTED |
| total | BigDecimal | Total |
| validUntil | LocalDate | Valid until date |

### 🔸 QUOTE_DETAIL

Line items for quote (labor + parts).

| Field | Type | Description |
|-------|------|-------------|
| id | Long | Primary key (inherited) |
| quote | Quote | Parent quote |
| description | String | Description |
| partName | String | Part name |
| partPrice | BigDecimal | Part price (per unit) |
| quantity | Integer | Quantity |
| laborPrice | BigDecimal | Fixed labor price |
| subtotal | BigDecimal | Line total |

---

## 📊 Enums

### ROLE
- ADMIN - Owner/administrator
- MECHANIC - Mechanic

### VEHICLE_TYPE
- CAR - Automobile
- MOTORCYCLE - Motorcycle
- TRUCK - Truck

### REPAIR_STATUS
- PENDING - Waiting to start
- IN_PROGRESS - Being worked on
- WAITING_PARTS - Waiting for parts
- COMPLETED - Work finished
- DELIVERED - Delivered to customer

### QUOTE_STATUS
- DRAFT - In edit
- SENT - Sent to customer
- ACCEPTED - Customer accepted
- REJECTED - Customer rejected

### ADDRESS (Embeddable)
Used in COMPANY, CUSTOMER, EMPLOYEE.

| Field | Type | Description |
|-------|------|-------------|
| province | String | Province/state |
| city | String | City |
| neighborhood | String | Neighborhood |
| street | String | Street name |
| number | String | Street number |
| postalCode | String | Postal code |

---

## ☁️ Cloud Synchronization

Data sync to cloud (Google Drive, Dropbox, etc.) via scheduled cron:
- **Export**: JSON dump of all data (customers, vehicles, repairs, quotes)
- **Schedule**: Daily at configurable time (e.g., 22:00)
- **Format**: Compressed JSON with timestamp
- **Storage**: Simple folder, no complex sync solution
- **Manual trigger**: Admin can force sync anytime

Purpose: Backup and data portability between locations.

---

## 🔄 System Flow

```
1. Customer arrives → Register vehicle (if new)
2. Create quote/budget → Add labor + parts details
3. Customer approves quote → Auto-create RepairOrder
4. Mechanic works → Updates status
5. Customer checks status via license plate (public screen)
6. Repair completed → Customer picks up vehicle
```

---

## 🖥️ Interface Design

### 🔷 General Layout

#### HEADER (Navbar)
- Hamburger menu (opens sidebar)
- System name
- Dark mode toggle
- Logged user

#### SIDEBAR
- Navegación: Dashboard, Customers, Vehicles, Quotes, Repair Orders
- Administración: Company config, Employees
- Configuración: Themes (5 color options)
- User profile

#### MAIN CONTENT
- Dynamic content per section

---

## 🎨 Visual Style

- Tailwind CSS with Material Design 3 theming
- 5 color themes (Azul, Verde, Violeta, Naranja, Celeste)
- Dark mode (unified slate theme)
- Theme selector in sidebar (Configuración → Temas)
- Color tokens mapped via CSS custom properties
- Professional admin panel with consistent cards, shadows, and buttons
- Fixed green/red badges for active/inactive status
- Button hover effects: scale + background transitions
- Radio buttons for status selection (custom styled with appearance-none)

---

## 📱 Public Status Consultation

Two methods:
1. **Public screen**: Display in workshop with license plate input
2. **Web URL**: Public page with license plate parameter

Customer enters license plate and sees:
- Vehicle info
- Current repair status
- Estimated completion

---

## ⚙️ Base Configuration (application.yml)

```yaml
spring:
  application:
    name: GarageManagementSystem

  datasource:
    url: jdbc:postgresql://localhost:5432/garage_db
    username: postgres
    password: 1111
    hikari:
      minimum-idle: 5
      maximum-idle: 20
      idle-timeout: 30000
      max-lifetime: 1800000
      connection-timeout: 30000

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    open-in-view: false

  thymeleaf:
    cache: false
    check-template-location: false

server:
  port: 8080
  servlet:
    context-path: /api/v1
```

---

## 🧱 Layer Rules

| Layer | Depends on | Contains |
|-------|-----------|----------|
| `domain/` | Nothing | Models, Ports interfaces, Service implementations |
| `application/` | `domain/` | Application orchestration services |
| `interfaces/` | `domain/` | REST controllers, error handling, security, middleware |
| `infraestructure/` | `domain/` | JPA entities, repositories, mappers, Spring config |

- **Domain models** have zero framework annotations (no `@Entity`, no `@Spring`).
- **Ports** define contracts: inbound (use cases) and outbound (repositories).
- **Infrastructure** adapters implement outbound ports and map between domain ↔ JPA entities.

---

## 🚀 Future Improvements

- Spring Security with authentication
- Audit logs (change tracking)
- PDF export for quotes/invoices
- Integration with parts supplier API
- SMS/WhatsApp notifications
- Cloud sync (Firebase/Supabase) [Phase 2]
- Mobile app for mechanics

---

## 💼 Portfolio Value

This project demonstrates:

- Hexagonal Architecture with Spring Boot
- Relational database design
- Complex entity relationships
- Business logic implementation
- Professional admin interface
- Quote/budget generation
- Real-time status tracking
- Material Design 3 theming system
- Dark mode implementation

---

## 🧠 Conclusion

The system represents a real-world use case for generic repair workshops,
combining simplicity and functionality. Designed to be scalable and evolve
toward more complex architectures in the future.

---
