-- Base de datos
CREATE DATABASE IF NOT EXISTS FVGames;
USE FVGames;

-- Tabla de Métodos de Pago
CREATE TABLE MetodosPago (
    codigo INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    detalles VARCHAR(255)
);

-- Tabla de Usuarios 
CREATE TABLE Usuarios (
    cedula VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    direccion VARCHAR(255),
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,  -- Campo para la contraseña
    dinero_cuenta DECIMAL(10, 2) DEFAULT 0.00,
    permiso ENUM('Funcionario', 'Cliente') NOT NULL,
    metodo_pago_preferido INT,
    FOREIGN KEY (metodo_pago_preferido) REFERENCES MetodosPago(codigo)
);

-- Tabla de Productos
CREATE TABLE Productos (
    codigo INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    categoria ENUM('Videojuegos', 'Videoconsolas', 'Accesorios para Videojuegos', 'Suscripciones Virtuales') NOT NULL,
    cantidad INT NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    imagen VARCHAR(255)
);

-- Tabla de Paquetes
CREATE TABLE Paquetes (
    codigo INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descuento DECIMAL(5, 2) DEFAULT 15.00,
    precio DECIMAL(10, 2) NOT NULL
);

-- Tabla intermedia Paquetes_Productos (relación muchos a muchos entre Paquetes y Productos)
CREATE TABLE Paquetes_Productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paquete_id INT,
    producto_id INT,
    FOREIGN KEY (paquete_id) REFERENCES Paquetes(codigo) ON DELETE CASCADE,
    FOREIGN KEY (producto_id) REFERENCES Productos(codigo) ON DELETE CASCADE
);

-- Tabla de Transacciones
CREATE TABLE Transacciones (
    codigo INT AUTO_INCREMENT PRIMARY KEY,
    cliente_cedula VARCHAR(20),
    monto DECIMAL(10, 2) NOT NULL,
    detalle VARCHAR(255),
    FOREIGN KEY (cliente_cedula) REFERENCES Usuarios(cedula) ON DELETE CASCADE
);

-- Tabla de Carrito de Compras
CREATE TABLE CarritoCompras (
    codigo INT AUTO_INCREMENT PRIMARY KEY,
    cliente_cedula VARCHAR(20),
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    estado ENUM('Pagado', 'Sin Pagar') NOT NULL,
    metodo_pago INT,
    detalle VARCHAR(255),
    subtotal DECIMAL(10, 2) NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (cliente_cedula) REFERENCES Usuarios(cedula) ON DELETE CASCADE,
    FOREIGN KEY (metodo_pago) REFERENCES MetodosPago(codigo)
);

-- Tabla intermedia Carrito_Productos (relación muchos a muchos entre Carrito y Productos)
CREATE TABLE Carrito_Productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    carrito_id INT,
    producto_id INT,
    cantidad INT NOT NULL,
    FOREIGN KEY (carrito_id) REFERENCES CarritoCompras(codigo) ON DELETE CASCADE,
    FOREIGN KEY (producto_id) REFERENCES Productos(codigo) ON DELETE CASCADE
);

USE FVGames;

-- Insertando datos en MetodosPago
INSERT INTO MetodosPago (nombre, detalles)
VALUES
('Efectivo', 'Pago en efectivo directamente en la tienda'),
('Transferencia Bancaria', 'Transferencias a través de banca en línea'),
('Tarjeta de Crédito', 'Pago mediante tarjeta de crédito'),
('PayPal', 'Pago a través de cuenta PayPal'),
('Tarjeta de Débito', 'Pago mediante tarjeta de débito');

-- Insertando usuario admin en Usuarios
INSERT INTO Usuarios (cedula, nombre, apellidos, direccion, email, password, dinero_cuenta, permiso, metodo_pago_preferido)
VALUES
('admin', 'Administrador', 'Admin', 'Dirección de Admin', 'admin@example.com', 'admin', 0.00, 'Funcionario', 1);

-- Insertando datos en Usuarios
INSERT INTO Usuarios (cedula, nombre, apellidos, direccion, email, password, dinero_cuenta, permiso, metodo_pago_preferido)
VALUES
('1-2345-6789', 'Juan', 'Pérez', 'Avenida Siempre Viva 123', 'juan.perez@example.com', 'password123', 1500.00, 'Cliente', 3),  
('2-3456-7890', 'Ana', 'Gonzalez', 'Calle de la Luz 456', 'ana.gonzalez@example.com', 'password456', 300.00, 'Cliente', 2),  
('3-4567-8901', 'Carlos', 'Vargas', 'Boulevard del Mar 789', 'carlos.vargas@example.com', 'password789', 0.00, 'Funcionario', 1), 
('4-5678-9012', 'Luisa', 'Méndez', 'Carrera Norte 987', 'luisa.mendez@example.com', 'password987', 750.00, 'Cliente', 3),  
('5-6789-0123', 'Sofía', 'Quirós', 'Pasaje del Sol 654', 'sofia.quiros@example.com', 'password654', 2000.00, 'Cliente', 1);  

-- Insertando datos en Productos
INSERT INTO Productos (nombre, categoria, cantidad, precio, imagen)
VALUES
('The Legend of Zelda', 'Videojuegos', 10, 59.99, 'zelda.jpg'),
('PlayStation 5', 'Videoconsolas', 5, 499.99, 'ps5.jpg'),
('Control Xbox', 'Accesorios para Videojuegos', 15, 49.99, 'xbox_control.jpg'),
('Xbox Game Pass', 'Suscripciones Virtuales', 25, 14.99, 'game_pass.jpg'),
('Nintendo Switch', 'Videoconsolas', 8, 299.99, 'switch.jpg');

-- Insertando datos en Paquetes
INSERT INTO Paquetes (nombre, descuento, precio)
VALUES
('Paquete Aventura', 10.00, 99.99),
('Paquete Acción', 15.00, 129.99),
('Paquete Familiar', 5.00, 189.99),
('Paquete Deluxe', 20.00, 249.99),
('Paquete Básico', 0.00, 59.99);

-- Insertando datos en Transacciones
INSERT INTO Transacciones (cliente_cedula, monto, detalle)
VALUES
('1-2345-6789', 99.99, 'Compra de videojuego'),
('2-3456-7890', 299.99, 'Compra de consola'),
('3-4567-8901', 149.97, 'Compra de tres juegos'),
('4-5678-9012', 499.99, 'Compra de PlayStation 5'),
('5-6789-0123', 100.00, 'Compra de accesorios');

-- Insertando datos en CarritoCompras
INSERT INTO CarritoCompras (cliente_cedula, estado, metodo_pago, detalle, subtotal, total)
VALUES
('1-2345-6789', 'Pagado', 3, 'Compra de varios juegos', 150.00, 150.00),
('2-3456-7890', 'Sin Pagar', 2, 'Reserva de consola', 499.99, 499.99),
('3-4567-8901', 'Pagado', 1, 'Compra de accesorios', 49.99, 49.99),
('4-5678-9012', 'Sin Pagar', 3, 'Compra en proceso', 249.99, 249.99),
('5-6789-0123', 'Pagado', 1, 'Compra de paquete de juegos', 99.99, 99.99);

-- Insertando datos en Paquetes_Productos
INSERT INTO Paquetes_Productos (paquete_id, producto_id)
VALUES
(1, 1), (1, 2), (2, 3), (2, 4), (3, 5);

-- Insertando datos en Carrito_Productos
INSERT INTO Carrito_Productos (carrito_id, producto_id, cantidad)
VALUES
(1, 1, 1), (1, 2, 1), (2, 3, 1), (2, 4, 1), (3, 5, 1);
