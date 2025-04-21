# Endpoint (estructuracion de los controller)

## 🚀 Endpoints Principales

### Reservas (`/bookings`)
| Método | Ruta                              | Descripción                                                        |
|--------|-----------------------------------|--------------------------------------------------------------------|
| GET    | `/{id}`                           | Obtiene reserva por ID                                             |
| GET    | `/`                               | Obtiene toda las reservas                                          |
| GET    | `/?status={status}`               | Filtra por estado   ("Filtra por estado y/o nombre de cliente)     |
| GET    | `/flight/{flightId}`              | Reservas por vuelo                                                 |
| POST   | `/`                               | Crea reserva                                                       |
| POST   | `/flight/{flighId}/user/{userId}` | Crear reserva para usuario y vuelo, se confirmada con check-in     |
| DELETE | `/{id}`                           | Elimina reserva por ID                                             |

## Post ("http://localhost:8080/nookings/")
{
  "status": "CONFIRMED",
  "outboundFlightId": 1,
  "checkedIn": false,
  "userId": 1,
  "bookingReference": "AB_123"
}

### Reservas (`/users`)
| Método | Ruta                              | Descripción                     |
|--------|-----------------------------------|---------------------------------|
| GET    | `/{id}`                           | Obtener usuario por ID          |
| GET    | `/`                               | Obtener lista de usuarios       |
| POST   | `/`                               | Crear usuario                   |
| PUT    | `/{id}`                           | actualizar usuaio               |
| DELETE | `/{id}`                           | Elimina USUARIO                 |

## Post ("http://localhost:8080/users/")
{
    "fullname": "Fawcet Doe",
    "username": "juan",
    "password": "secret123"
}

### Reservas (`/catalog`)
| Método | Ruta                                           | Descripción                          |
|--------|------------------------------------------------|--------------------------------------|
| GET    | `/`                                            | Todos los vuelos                     |
| GET    | `/?departureDate=...&departureAirportCode=...` | Búsqueda con filtros                 |
| POST   | `/`                                            | Crear nuevo vuelo                    |
| PUT    | `/{id}`                                        | Actualizar vuelo                     |
| DELETE | `/{id}`                                        | Eliminar vuelo                       |

## Post ("http://localhost:8080/catalog/")
{
    "departureDate": "2022-04-29",
    "departureAirportCode": "AND",
    "departureAirportName": "Airport 1",
    "departureCity": "City 1",
    "departureLocale": "Locale 1",
    "arrivalDate": "2022-04-29T11:30:00",
    "arrivalAirportCode": "ARG",
    "arrivalAirportName": "Airport 2",
    "arrivalCity": "City 2",
    "arrivalLocale": "Locale 2",
    "ticketPrice": 100,
    "ticketCurrency": "USD",
    "flightNumber": 123,
    "seatCapacity": 200
}

