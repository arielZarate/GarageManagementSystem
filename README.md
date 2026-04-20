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

- Implement MVC architecture with Spring Boot
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

- Java + Spring Boot
- Spring MVC
- Spring Data JPA
- Spring Security + JWT (basic authentication)

### 🔹 Frontend

- Thymeleaf (server-side rendering)
- Tailwind CSS (styling)

### 🔹 Database

- PostgreSQL

---

## 📦 Project Structure

```
src/main/java/com/workshop
│
├── controller/
├── service/
├── repository/
├── entity/
├── dto/
├── config/
└── enums/
```

```
src/main/resources
│
├── templates/      (Thymeleaf)
├── static/
│   ├── css/
│   └── js/
└── application.yml
```

---

## 🧩 Entities

### 🔸 COMPANY (Empresa)

Configuration for invoices/quotes (logo, business name, address, phone, hours, CUIT).

| Field | Type | Description |
|-------|------|-------------|
| id | Long | Primary key |
| businessName | String | Company name |
| logo | String | Logo path/URL |
| address | Address | Full address |
| phone | String | Phone number |
| email | String | Email |
| horario | String | Business hours |
| cuit | String | CUIT (tax ID) |

### 🔸 EMPLOYEE (Usuario/Empleado)

System users with roles. Legajo tracks all repairs performed.

| Field | Type | Description |
|-------|------|-------------|
| id | Long | Primary key |
| legajo | String | Employee ID (unique) |
| firstName | String | First name |
| lastName | String | Last name |
| alias | String | Short name/nickname |
| role | Enum | ADMIN, MECHANIC |
| password | String | Hashed password |
| phone | String | Phone |
| address | Address | Home address |
| active | Boolean | Active/inactive |
| joinDate | LocalDate | Start date |

**Tracking**: Each repair order records the mechanic. Query by legajo shows:
- Total repairs performed
- Date and duration of each repair
- Reasons/motifs
- Performance history

### 🔸 CUSTOMER (Cliente)

Vehicle owner/customer.

| Field | Type | Description |
|-------|------|-------------|
| id | Long | Primary key |
| firstName | String | First name |
| lastName | String | Last name |
| phone | String | Phone |
| email | String | Email (optional) |
| address | Address | Full address |
| createdAt | LocalDateTime | Created date |
| active | Boolean | Active/inactive |

### 🔸 VEHICLE

Registered vehicle in the system.

| Field | Type | Description |
|-------|------|-------------|
| id | Long | Primary key |
| licensePlate | String | License plate (unique) |
| brand | String | Brand (VW, Ford, Yamaha) |
| model | String | Model (Gol, Ranger, Fazer) |
| year | Integer | Year |
| version | String | Version (1.6, Sport, S) |
| vehicleType | Enum | CAR, MOTORCYCLE, TRUCK |
| color | String | Color |
| kilometers | Integer | Current km |
| vin | String | VIN/Chassis (optional) |
| customer | Customer | Owner |
| createdAt | LocalDateTime | Created date |

### 🔸 REPAIR_ORDER (Orden de Reparación)

Repair work order.

| Field | Type | Description |
|-------|------|-------------|
| id | Long | Primary key |
| orderNumber | String | Order number (unique) |
| vehicle | Vehicle | Vehicle |
| customer | Customer | Customer |
| employee | Employee | Assigned mechanic |
| status | Enum | PENDING, IN_PROGRESS, WAITING_PARTS, COMPLETED, DELIVERED |
| diagnosis | Text | Initial diagnosis |
| admissionDate | LocalDateTime | Admission date |
| estimatedFinish | LocalDateTime | Estimated finish |
| laborCost | BigDecimal | Labor cost |
| totalCost | BigDecimal | Total cost |
| notes | Text | Additional notes |
| createdAt | LocalDateTime | Created date |
| updatedAt | LocalDateTime | Last update |

### 🔸 REPAIR_DETAIL

Line items for repair order (labor + parts).

| Field | Type | Description |
|-------|------|-------------|
| id | Long | Primary key |
| repairOrder | RepairOrder | Parent order |
| description | String | Description |
| partName | String | Part name |
| partPrice | BigDecimal | Part price |
| quantity | Integer | Quantity |
| laborHours | BigDecimal | Labor hours |
| laborPrice | BigDecimal | Hourly rate |
| subtotal | BigDecimal | Line total |

### 🔸 QUOTE (Presupuesto)

Budget/quote (independent flow).

| Field | Type | Description |
|-------|------|-------------|
| id | Long | Primary key |
| quoteNumber | String | Quote number (unique) |
| vehicle | Vehicle | Vehicle |
| customer | Customer | Customer |
| status | Enum | DRAFT, SENT, ACCEPTED, REJECTED |
| laborSubtotal | BigDecimal | Labor subtotal |
| partsSubtotal | BigDecimal | Parts subtotal |
| total | BigDecimal | Total |
| details | List<QuoteDetail> | Line items |

### 🔸 QUOTE_DETAIL

Line items for quote (labor + parts).

| Field | Type | Description |
|-------|------|-------------|
| id | Long | Primary key |
| quote | Quote | Parent quote |
| description | String | Description |
| partName | String | Part name |
| partPrice | BigDecimal | Part price |
| quantity | Integer | Quantity |
| laborHours | BigDecimal | Labor hours |
| laborPrice | BigDecimal | Hourly rate |
| subtotal | BigDecimal | Line total |
| notes | Text | Additional notes |
| validUntil | LocalDate | Valid until |
| createdAt | LocalDateTime | Created date |
| acceptedAt | LocalDateTime | Accepted date |

---

## 📊 Enums

### EMPLOYEE_ROLE
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

#### HEADER
- System name
- Logged user
- Role

#### SIDEBAR
- Dashboard
- Customers
- Vehicles
- Quotes (Presupuestos)
- Repair Orders
- Employees (Admin only)
- Settings (Company config)
- Public Status (Screen mode)

#### MAIN CONTENT
- Dynamic content per section

---

## 🎨 Visual Style

- Tailwind CSS
- Professional admin panel
- Neutral colors (gray, white, blue)
- Soft shadows
- Consistent buttons

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
  datasource:
    url: jdbc:postgresql://localhost:5432/workshop_db
    username: postgres
    password: password

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

  thymeleaf:
    cache: false

server:
  port: 8080
```

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

- MVC architecture with Spring Boot
- Relational database design
- Complex entity relationships
- Business logic implementation
- Professional admin interface
- Quote/budget generation
- Real-time status tracking

---

## 🧠 Conclusion

The system represents a real-world use case for generic repair workshops,
combining simplicity and functionality. Designed to be scalable and evolve
toward more complex architectures in the future.

---