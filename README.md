# TrabajoFinalWeb
Sistema de Gestión de Reservas de Vuelos
# ✈️ Sistema de Gestión de Reservas de Vuelos - Backend API
Un sistema backend desarrollado con Spring Boot para gestionar reservas de vuelos, usuarios y estados de reservación.

# 🛠️ Tecnologías Java 17 Spring Boot 3.x Spring Data JPA (Persistencia) Base de datos: PostgreSQL / H2 (para desarrollo) Maven (Gestión de dependencias) Docker (Contenedorización)

📂 Estructura del Proyecto TRABAJOFINALWEB/
├── backend/
│ ├── src/
│ │ ├── main/
│ │ │ ├── java/edu/unimag/api/
│ │ │ │ ├── controllers/
│ │ │ │ ├── Dto/
│ │ │ │ ├── entities/
│ │ │ │ │ ├── Booking.java
│ │ │ │ │ ├── BookingStatus.java
│ │ │ │ │ ├── Flight.java
│ │ │ │ │ └── User.java
│ │ │ │ ├── exceptions/
│ │ │ │ ├── repositories/ │ │ │ │ └── services/
│ │ │ └── resources/
│ │ │ └── application.properties
│ │ └── test/
│ ├── .mvn/
│ ├── target/
│ ├── compose.yaml
│ └── .gitignore
└── .vscode/
