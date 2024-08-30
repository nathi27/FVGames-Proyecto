# FVGames-Proyecto

Descripción del problema:

La empresa FVGames enfrenta la necesidad de un software integral que permita administrar inventarios, usuarios, y transacciones, así como gestionar la venta de productos y paquetes especializados en videojuegos. Para abordar estas necesidades, han decidido contratar a una empresa de software la cual proveerá una solución adecuada a estos problemas. 

Requerimientos Generales
•	Inicio de Sesión:
  o	Los usuarios deberán iniciar sesión utilizando su cédula y contraseña.
  o	El sistema permitirá que múltiples usuarios se conecten simultáneamente.
•	Patrón de Diseño:
  o	El desarrollo del código seguirá el patrón de diseño MVC (Modelo-Vista-Controlador) y capa de datos (conexión a la base de datos).
•	Tecnologías Utilizadas:
  o	MySQL para la base de datos.
  o	Java con NetBeans para el desarrollo.
  o	Swing para la interfaz gráfica.
•	Gestión de Permisos:
  o	Los permisos estarán diferenciados entre "Funcionario" y "Cliente".

Entidades y Funcionalidades CRUD
  1.	Productos (CRUD):
    o	Gestión completa del inventario de productos.
    o	Campos:
      	Código
      	Nombre
      	Categoría (Videojuegos, Videoconsolas, Accesorios para Videojuegos, Suscripciones Virtuales)
      	Cantidad
      	Precio
      	Imagen
2.	Paquetes (CRUD):
  o	Creación de paquetes que pueden contener de dos a cinco productos (pueden repetirse).
  o	El precio del paquete incluirá un descuento del 15% por defecto, configurable por el funcionario.
  o	Campos:
    	Código
    	Nombre
    	Productos (lista de productos incluidos)
    	Descuento
    	Precio (con el descuento aplicado)
3.	Usuarios (CRUD):
  o	Gestión de usuarios que pueden ingresar como invitados o registrados.
  o	Invitados: Solo pueden ver productos, no realizar compras.
  o	Registrados: Pueden comprar y gestionar su información personal.
  o	Campos:
    	Cédula
    	Nombre
    	Apellidos
    	Dirección Exacta
    	Email
    	Dinero en la Cuenta
    	Permiso (Funcionario o Cliente)
    	Método de Pago Preferido (Efectivo, Transferencia Bancaria, Tarjeta de Crédito)
4.	Métodos de Pago (CRUD):
  o	Gestión de los métodos de pago aceptados por la tienda.
  o	Campos:
    	Código
    	Nombre
    	Detalles
5.	Transacciones (CRUD):
  o	Los clientes podrán realizar transacciones para recargar dinero en su cuenta.
  o	Campos:
    	Código
    	Cliente
    	Monto
    	Detalle
6.	Carrito de Compras (CRUD):
  o	Gestión del carrito de compras para los clientes.
  o	Verificación de disponibilidad de productos al añadirlos al carrito.
  o	Funcionalidad de filtrado para el funcionario:
    	Todas las compras.
    	Consulta por intervalo de fecha.
    	Consulta por usuario.
    o	Campos:
    	Código
    	Cliente
    	Productos (lista de productos en el carrito)
    	Fecha
    	Estado (Pagado o Sin Pagar)
    	Método de Pago
    	Detalle
    	Subtotal
    	Total (incluyendo IVA del 13%)

Requerimientos de Concurrencia
  •	Conexión Simultánea de Clientes:
      o	Se utilizarán sockets para permitir la conexión simultánea de múltiples clientes al sistema.
  •	Procesamiento de Lógica de Negocio:
      o	Toda la información y lógica de negocio se procesará en el servidor.
      o	Los accesos, solicitudes y visualizaciones se realizarán desde el cliente.
      o	El cliente realizará las solicitudes y el servidor procesará las operaciones solicitadas.
