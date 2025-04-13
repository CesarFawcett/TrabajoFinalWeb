# Endpoint (estructuracion de los controller)

## 🚀 Endpoints Principales

### Reservas (`/bookings`)
| Método | Ruta                              | Descripción                     |
|--------|-----------------------------------|---------------------------------|
| POST   | `/`                               | Crea nueva reserva              |
| GET    | `/{id}`                           | Obtiene reserva por ID          |
| GET    | `/?status={status}`               | Filtra por estado               |
| DELETE | `/{id}`                           | Elimina reserva                 |
| GET    | `/flight/{flightId}`              | Reservas por vuelo              |
|--------|-----------------------------------|---------------------------------|



