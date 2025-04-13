# entities

## 🔍 Entidad Booking
| Campo             | Tipo          | Descripción                         |
|-------------------|---------------|-------------------------------------|
| `id`              | `Int`         | ID autogenerado                     |
| `status`          | `Enum`        | `CONFIRMED`/`CANCELLED`/`PENDING`   |
| `outboundFlight`  | `Flight`      | Vuelo asociado                      |
| `checkedIn`       | `Boolean`     | Check-in realizado                  |
| `user`            | `User`        | Usuario que hizo la reserva         |
| `bookingReference`| `String`      | Código de referencia                |

## 🔍 Entidad User
| Campo            | Tipo          | Descripción                          |
|------------------|---------------|--------------------------------------|
| `id`             | `Long`        | ID autogenerado                      |
| `fullname`       | `String`      | Nombre completo                      |
| `username`       | `String`      | Nombre de usuario para login         |
| `password`       | `String`      | Contraseña                           |

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

