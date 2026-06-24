# GarageManagementSystem - Context

## Stack
- Java 21 + Spring Boot 4.0.4 + Maven
- Thymeleaf + Tailwind CSS v4 (CLI)
- PostgreSQL
- Lombok + MapStruct

## Hexagonal Architecture

```
domain/
  model/              Pure POJOs (Address, Company, Customer, Employee, Vehicle, RepairOrder, RepairDetail, Quote, QuoteDetail)
    enums/            Role, VehicleType, RepairStatus, QuoteStatus
  ports/
    in/               CompanyService (interfaces de caso de uso)
    out/              CompanyProvider (interfaces de repositorio)
  services/
    CodeGenerator
    validators/
      CUITValidator       validate() / isValid() — formato XX-XXXXXXXX-X o 11 dígitos
      EmailValidator      validate() / isValid() — regex email completo
      PhoneValidator      validate() / isValid() / normalize() — solo Córdoba (351), +54 opcional

application/
  services/               CompanyUseCase (orquestación + validación de negocio)

interfaces/
  rest/                   CompanyController (Thymeleaf MVC, @Valid + BindingResult)
    dto/
      company/            CompanyRequest (con Jakarta Validation: @NotBlank, @Size, @Email, @Valid), CompanyResponse
      address/            AddressDTO (con Jakarta Validation: @NotBlank, @Size)

infraestructure/
  adapters/
    mappers/              CompanyMapper (MapStruct: DTO ↔ Domain ↔ Entity)
  config/
  persistence/
    entities/             BaseEntity, CompanyEntity, Customer, Employee, Vehicle, RepairOrder, RepairDetail, Quote, QuoteDetail, AddressEmbeddable
    repositories/         CompanyRepository, CustomerRepository, EmployeeRepository, VehicleRepository, RepairOrderRepository, RepairDetailRepository, QuoteRepository, QuoteDetailRepository
```

## Entities

All extend BaseEntity (id, createdAt, updatedAt, deletedAt).

### CompanyEntity
businessName, legalName, logo, phone, email, horario, cuit, address (AddressEmbeddable)

### Employee
legajo, firstName, lastName, dni, birthDate, CUIT, email (unique), password, phone, address, role (ADMIN/MECHANIC), active, joinDate

### Customer
legajo (unique), firstName, lastName, phone, email, cuit, dni, address, active

### Vehicle
licensePlate (unique), brand, model, year, version, vehicleType (CAR/MOTORCYCLE/TRUCK), color, kilometers, chassisNumber, engineNumber, customer (ManyToOne)

### RepairOrder
orderNumber (unique), vehicle, customer, employee, status (PENDING/IN_PROGRESS/WAITING_PARTS/COMPLETED/DELIVERED), diagnosis, admissionDate, estimatedFinish, totalCost, notes

### RepairDetail
repairOrder (parent), description, partName, partPrice, quantity, laborPrice, subtotal, photos (List<String> via ElementCollection)

### Quote
quoteNumber (unique), quoteDate, vehicle, customer, status (DRAFT/SENT/ACCEPTED/REJECTED), total, validUntil

### QuoteDetail
quote (parent), description, partName, partPrice, quantity, laborPrice, subtotal

## Flujo completo (Company)
```
companyForm.html → CompanyController
  → CompanyMapper.toDomain(CompanyRequest) → Company (domain)
    → CompanyService.addCompany()
      → CompanyServiceImpl → CompanyProvider.create()
        → CompanyAdapter → CompanyMapper.toEntity(CompanyEntity)
          → CompanyRepository.save()
        ← CompanyMapper.toDomain(entity) → Company
      ← Company
    ← Company
  ← redirect
```

## Validation Strategy (implemented)
- **DTO (capa web)**: Jakarta Validation (@NotBlank, @Size, @Email, @Valid, @Pattern) — validación de estructura y formato básico, early fail
- **Servicio (capa dominio)**: Validators específicos (CUITValidator, EmailValidator, PhoneValidator) con regex propios — validación de reglas de negocio, reutilizables desde cualquier punto de entrada
- **Controller**: @ModelAttribute + @Valid + BindingResult, captura IllegalArgumentException del servicio como error global

## Commands
- `npm run build:css` - Build Tailwind CSS
- `npm run watch:css` - Watch Tailwind CSS
- `mvn spring-boot:run` - Run app on port 8080/api/v1
- `mvn compile` - Compile
- `mvn test` - Run tests

## Next Steps (roadmap)
1. ✅ Company CRUD (model → port → adapter → usecase → controller → template + validations)
2. Repeat full flow for Customer (model → port → adapter → mapper → usecase → controller → template)
3. Same for Vehicle, RepairOrder, Quote
4. Layout base (sidebar + header) for all screens
5. Login/security

## Features planning

### Roles & Permissions
- **ADMIN** (owner): full access, can create/edit employees, see everything
- **MANAGER** (ventas): can see customers, quotes, repairs, vehicles by status. CANNOT see employees, CANNOT create employees, CANNOT edit own info, CANNOT modify already-created quotes
- **MECHANIC**: only sees quotes and repairs assigned

### Multi-user
- 3+ mechanics + 1 manager (sales) simultaneously
- Owner accesses remotely via HTTP (browser)

### Price List
- Predefined repair prices (e.g., clutch = $300k)
- Anyone can calculate/estimate price upfront without asking
- Each repair type has a base price

### Discounts / Promotions
- Applied to quotes based on payment method
- Cash → discount / bonus
- Card → no discount or different rate
- Payment type field on quote

### Attendance / Time Tracking
- Employees log in and mark entry/exit time
- Daily attendance record (check-in, check-out)
- Only ADMIN can edit attendance records
- Dashboard shows active/logged-in employees

### Dashboard per Role
- ADMIN: full dashboard (employees, company config, all records)
- MANAGER: sales dashboard (customers, quotes, repairs, vehicles)
- MECHANIC: personal dashboard (assigned repairs only)

### UI Layout
- No sidebar. Top navbar with hamburger menu ([☰])
- Footer: company info (name, address, hours)
- Responsive design (Tailwind), works on mobile/Android
- Menu items per role:
  - ADMIN: Dashboard | Clientes | Vehículos | Presupuestos | Órdenes | Empleados | Config
  - MANAGER: Dashboard | Clientes | Vehículos | Presupuestos | Órdenes
  - MECHANIC: Mis Reparaciones | Mi Perfil | Jornada Laboral

### To model
- PaymentMethod enum (CASH, CARD, TRANSFER)
- PriceCatalog entity (repair type → base price)
- Discount rules (payment method → discount %)
- Add discount fields to Quote
- AttendanceRecord entity (employee, date, checkIn, checkOut)
