# NegreiraJeremy_pruebatec4
 
# Proyecto Java Spring - README

Este repositorio contiene un proyecto Java Spring que aborda la gestión de datos de habitaciones y vuelos con un enfoque específico. A continuación, se proporciona información clave sobre la estructura y las decisiones de diseño del proyecto.

## Estructura del Proyecto

El proyecto se organiza en diferentes paquetes para una mejor modularidad y mantenimiento. A continuación, se describen los paquetes más relevantes:

- **models.common:** Contiene clases base que proporcionan datos básicos como id, createdAt y updatedAt. Se implementa un borrado lógico mediante herencias comunes.

- **models:** Incluye clases específicas para representar datos de habitaciones, vuelos y la clase provisional "PersonData". Estas clases utilizan las herencias comunes de models.common.

- **listeners:** Se ocupan de los timestamps de los models.

- **controllers:** Contiene controladores para gestionar las reservas, destacando el controlador "BookingController" para abordar las particularidades de las direcciones de reserva.

- **config:** Contiene la clase "DocsConfig" dedicada a la configuración de Swagger para la documentación del proyecto.

## Consideraciones Específicas

- **Habitaciones y Vuelos:** Se ha asumido que los "Hoteles" representan datos de habitación y que los vuelos son atómicos, es decir, ida y vuelta, como se ejemplifica en el código.

- **Datos Útiles:** Se han incorporado campos como createdAt, updatedAt y deletedAt para mantener un seguimiento adecuado de la persistencia de datos.

- **Borrado Lógico:** Se ha implementado borrado lógico mediante herencias comunes en el paquete models.common, con clases base que incluyen funcionalidades compartidas.

- **Clase Provisional PersonData:** Se ha utilizado temporalmente la clase "PersonData" para representar datos de personas, con la intención de facilitar la implementación futura de datos específicos para el cliente.

- **Reservas y Direcciones:** Dada la estructura única de las direcciones de reserva, se ha creado un controlador específico ("BookingController") para gestionar estas operaciones de manera especializada.

- **Swagger Documentation:** Se ha integrado Swagger para facilitar la documentación del proyecto. La configuración se encuentra en la clase "DocsConfig" en el paquete config.

- **Sentido de Vuelos:** El sentido de los vuelos se determina según la dirección del vuelo, ya sea origen-destino o destino-origen.

## Uso de Swagger

Para acceder a la documentación generada por Swagger, ejecuta la aplicación y visita la siguiente URL en tu navegador:

```plaintext
http://localhost:8080/swagger-ui.html
