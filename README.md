# Registro de Aplicaciones

Sistema de gestión para registro de aplicaciones, clientes y licencias.

## Características

- Gestión de clientes
- Registro de aplicaciones
- Control de licencias
- Notificaciones por email automáticas

## Configuración del Email

Para habilitar el envío de correos electrónicos, necesitas configurar:

1. Crear archivo `src/main/resources/email.properties`
2. Añadir las siguientes propiedades:

```properties
mail.smtp.user=your-email@gmail.com
mail.smtp.password=your-app-password
