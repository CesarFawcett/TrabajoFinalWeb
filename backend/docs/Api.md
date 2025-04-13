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

### Reservas (`/users`)
| Método | Ruta                              | Descripción                     |
|--------|-----------------------------------|---------------------------------|
| GET    | `/{id}`                           | Obtener usuario por ID          |
| GET    | `/`                               | Obtener lista de usuarios       |
| POST   | `/`                               | Crear usuario                   |
| PUT    | `/{id}`                           | actualizar usuaio               |
| DELETE | `/{id}`                           | Elimina USUARIO                 |

### Reservas (`/catalog`)
| Método | Ruta                                           | Descripción                          |
|--------|------------------------------------------------|--------------------------------------|
| GET    | `/`                                            | Todos los vuelos                     |
| GET    | `/?departureDate=...&departureAirportCode=...` | Búsqueda con filtros                 |
| POST   | `/`                                            | Crear nuevo vuelo                    |
| PUT    | `/{id}`                                        | Actualizar vuelo                     |
| DELETE | `/{id}`                                        | Eliminar vuelo                       |



