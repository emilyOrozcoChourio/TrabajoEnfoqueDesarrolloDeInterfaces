
-- CREACIÓN DE BASE DE DATOS Y USO

CREATE DATABASE smartoccupation;
USE smartoccupation;




-- TABLA CLIENTES

CREATE TABLE clientes (
  id INT AUTO_INCREMENT PRIMARY KEY,          -- Identificador único
  nombre VARCHAR(100) NOT NULL,               -- Nombre completo
  dni VARCHAR(20) UNIQUE NOT NULL,            -- Documento de identidad único
  email VARCHAR(100),                         -- Correo electrónico
  telefono VARCHAR(20),                       -- Teléfono de contacto
  direccion VARCHAR(200),                     -- Dirección física
  datos_facturacion VARCHAR(200)              -- Información de facturación
);

-- TABLA VIVIENDAS


CREATE TABLE viviendas (
  id INT AUTO_INCREMENT PRIMARY KEY,          -- Identificador único
  codigo_referencia VARCHAR(50) UNIQUE NOT NULL, -- Código único de referencia
  ubicacion VARCHAR(200) NOT NULL,            -- Dirección/ubicación
  metros INT,                                 -- Metros cuadrados
  habitaciones INT,                           -- Número de habitaciones
  banos INT,                                  -- Número de baños
  precio_mensual DECIMAL(10,2) NOT NULL       -- Precio mensual del alquiler
);

-- TABLA ALQUILERES

CREATE TABLE alquileres (
  id INT AUTO_INCREMENT PRIMARY KEY,          -- Identificador único
  numero_expediente VARCHAR(50) UNIQUE NOT NULL, -- Número de expediente
  fecha_entrada DATE NOT NULL,                -- Fecha de inicio del alquiler
  tiempo_estimado_meses INT NOT NULL,         -- Duración estimada en meses
  cliente_id INT NOT NULL,                    -- Relación con cliente
  vivienda_id INT NOT NULL,                   -- Relación con vivienda
  estado_cobro VARCHAR(20) DEFAULT 'pendiente', -- Estado del cobro
  FOREIGN KEY (cliente_id) REFERENCES clientes(id),
  FOREIGN KEY (vivienda_id) REFERENCES viviendas(id)
);


-- INSERCIÓN DE CLIENTES (5 registros)

INSERT INTO clientes (nombre, dni, email, telefono, direccion, datos_facturacion) VALUES
('Juan Pérez', '12345678A', 'juan@example.com', '600111111', 'Calle Mayor 10', 'Factura estándar'),
('María López', '23456789B', 'maria@example.com', '600222222', 'Av. Andalucía 25', 'Factura preferente'),
('Carlos García', '34567890C', 'carlos@example.com', '600333333', 'Plaza Nueva 5', 'Factura empresa'),
('Ana Torres', '45678901D', 'ana@example.com', '600444444', 'Calle Real 12', 'Factura estándar'),
('Luis Fernández', '56789012E', 'luis@example.com', '600555555', 'Camino Viejo 8', 'Factura especial');


-- INSERCIÓN DE VIVIENDAS (5 registros)

INSERT INTO viviendas (codigo_referencia, ubicacion, metros, habitaciones, banos, precio_mensual) VALUES
('REF-001', 'Almería Centro', 80, 3, 1, 650.00),
('REF-002', 'Roquetas de Mar', 100, 4, 2, 850.00),
('REF-003', 'Aguadulce', 60, 2, 1, 500.00),
('REF-004', 'El Ejido', 120, 5, 2, 950.00),
('REF-005', 'Níjar', 75, 3, 1, 600.00);


-- INSERCIÓN DE ALQUILERES (5 registros)

INSERT INTO alquileres (numero_expediente, fecha_entrada, tiempo_estimado_meses, estado_cobro, cliente_id, vivienda_id) VALUES
('EXP-001', '2025-12-01', 12, 'Pagado', 1, 1),
('EXP-002', '2025-12-05', 6, 'Pendiente', 2, 2),
('EXP-003', '2025-12-10', 24, 'Pagado', 3, 3),
('EXP-004', '2025-12-15', 18, 'Pendiente', 4, 4),
('EXP-005', '2025-12-20', 9, 'Pagado', 5, 5);
