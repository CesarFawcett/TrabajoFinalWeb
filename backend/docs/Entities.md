# entities

## 🔍 Entidad Booking

|   Campo            |	  Tipo         |	Descripción                            |	 Restricciones      |
|--------------------|-----------------|-------------------------------------------|------------------------|
| `id`	             | `Int`           | Identificador único autogenerado.         | Primary Key.           |
| `status`	         | `BookingStatus` | Estado de la reserva.                     | No nulo.               |
| `outboundFlight`   | `Flight`        | Vuelo de ida asociado.                    | Relación muchos a uno. |
| `checkedIn`	     | `boolean`       | Indica si el pasajero hizo check-in.      | No nulo.               |
| `user`	         | `User`          | Usuario que realizó la reserva.           | Relación muchos a uno. |
| `bookingReference` | `String`        | Código de referencia único de la reserva. | No nulo.               |

## 🔍 Entidad User
|   Campo        |	  Tipo       |	Descripción                      | Restricciones  |
|----------------|---------------|-----------------------------------|----------------|
| `id`	         | `Int`         | Identificador único autogenerado. | Primary Key.   |
| `fullname`	 | `String`      | Nombre completo del usuario.      | No nulo.       |
| `username`	 | `String`      | Nombre de usuario para login.     | No nulo.       |
| `password`	 | `String`      | Contraseña encriptada.            | No nulo.       |

## 🔍 Entidad Flight
| Campo                 | Tipo           | Descripción                     |
|-----------------------|----------------|---------------------------------|
| `id`                  | `Long`         | ID autogenerado                 |
| `departureDate`       | `String`       | Fecha de salida                 |
| `departureAirportCode`| `String`       | Código del aeropuerto           |
| `departureAirportName`| `String`       | Nombre del aeropuerto           |
| `departureCity`       | `String`       | Ciudad de salida                |
| `departureLocale`     | `String`       | Región de salida                |
| `arrivalDate`         | `LocalDateTime`| Fecha de llegada                |
| `arrivalAirportCode`  | `String`       | Código del aeropuerto           |
| `arrivalAirportName`  | `String`       | Nombre del aeropuerto           |
| `arrivalCity`         | `String`       | Ciudad de llegada               |
| `arrivalLocale`       | `String`       | Región de llegada               |
| `ticketPrice`         | `int`          | Precio del billete              |
| `ticketCurrency`      | `String`       | Moneda del valor                |
| `flightNumber`        | `int`          | Número de vuelo                 |
| `seatCapacity`        | `int`          | Capacidad de asientos           |
| `outboundFlight`      | `List<Booking>`| ID Lista de reservas            |