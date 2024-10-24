-- Creación de la base de datos
CREATE DATABASE bd_webs;
USE bd_webs;

-- Creación de la tabla tbl_permisos
CREATE TABLE tbl_permisos (
 id_permiso INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
 nombre_permiso VARCHAR(80) NOT NULL
);

-- Creación de la tabla tbl_grupos
CREATE TABLE tbl_grupos (
 id_grupo INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
 nombre_grupo VARCHAR(80) NOT NULL
);

-- Creación de la tabla tbl_gruposPermisos
CREATE TABLE tbl_gruposPermisos (
 id_grupoPer INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
 id_grupo_fk INT(11) NOT NULL,
 id_permiso_fk INT(11) NOT NULL,
 CONSTRAINT fk1 FOREIGN KEY (id_grupo_fk) REFERENCES tbl_grupos(id_grupo),
 CONSTRAINT fk2 FOREIGN KEY (id_permiso_fk) REFERENCES tbl_permisos(id_permiso)
);

-- Creación de la tabla tbl_usuarios
CREATE TABLE tbl_usuarios (
 id_codUsuarios INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
 nombre_usuario VARCHAR(80) NOT NULL,
 apellido_usuarios VARCHAR(80) NOT NULL,
 usuario_usuarios VARCHAR(80) NOT NULL,
 password_usuarios VARCHAR(80) NOT NULL,
 Estado_usuarios BOOLEAN NOT NULL,
 id_grupo_fk INT(11) NOT NULL,
 CONSTRAINT fk3 FOREIGN KEY (id_grupo_fk) REFERENCES tbl_grupos(id_grupo)
);

-- Consulta para ver los usuarios con sus grupos
SELECT u.id_codUsuarios AS id, u.nombre_usuario, u.apellido_usuarios, u.usuario_usuarios, u.password_usuarios, u.Estado_usuarios, g.nombre_grupo, g.id_grupo
FROM tbl_usuarios AS u
JOIN tbl_grupos AS g ON u.id_grupo_fk = g.id_grupo;

-- Creación de la tabla tbl_tipo_cliente
CREATE TABLE tbl_tipo_cliente (
 id_tipoCliente INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
 descripcion VARCHAR(80) NOT NULL
);

-- Creación de la tabla tbl_Cliente
CREATE TABLE tbl_Cliente (
 id_cliente INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
 nit VARCHAR(10),
 nombre VARCHAR(80) NOT NULL,
 apellido VARCHAR(80) NOT NULL,
 direccion VARCHAR(80) NOT NULL,
 telefono VARCHAR(80) NOT NULL,
 fecha_nacimiento VARCHAR(80) NOT NULL,
 id_tipoCliente INT(11) NOT NULL,
 CONSTRAINT fk4 FOREIGN KEY (id_tipoCliente) REFERENCES tbl_tipo_cliente(id_tipoCliente)
);

-- Creación de la tabla tbl_venta
CREATE TABLE tbl_venta (
 id_venta INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
 nofactura_venta VARCHAR(80) NOT NULL,
 fecha_venta VARCHAR(80) NOT NULL,
 total_venta VARCHAR(80) NOT NULL,
 id_codUsuariosfk INT(11) NOT NULL,
 id_clientefk INT(11) NOT NULL,
 CONSTRAINT fk5 FOREIGN KEY (id_codUsuariosfk) REFERENCES tbl_usuarios(id_codUsuarios),
 CONSTRAINT fk6 FOREIGN KEY (id_clientefk) REFERENCES tbl_Cliente(id_cliente)
);

-- Ajuste del valor de AUTO_INCREMENT de la tabla tbl_venta
ALTER TABLE tbl_venta AUTO_INCREMENT = 1;

-- Creación de la tabla tbl_detalleventa
CREATE TABLE tbl_detalleventa (
 id_detalle_venta INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
 descripcion VARCHAR(80) NOT NULL,
 monto VARCHAR(80) NOT NULL,
 id_ventafk INT(11) NOT NULL,
 CONSTRAINT fk7 FOREIGN KEY (id_ventafk) REFERENCES tbl_venta(id_venta)
);

SELECT * FROM tbl_venta;
SELECT * FROM tbl_detalleventa;
-- Consulta para obtener el máximo id de venta
SELECT MAX(id_venta) FROM tbl_venta;

-- Inserción de datos en la tabla tbl_detalleventa
INSERT INTO tbl_detalleventa (descripcion, monto, id_ventafk)
VALUES ('pago1', '300', 13);

select * from tbl_detalleventa;
-- Inserción de datos en la tabla tbl_venta
INSERT INTO tbl_venta (nofactura_venta, fecha_venta, total_venta, id_codUsuariosfk, id_clientefk)
VALUES ('00001', '2022/04/11', '200', 3, 6);

-- Inserción de datos en la tabla tbl_Cliente
INSERT INTO tbl_Cliente (nit, nombre, apellido, direccion, telefono, fecha_nacimiento, id_tipoCliente)
VALUES ('123456-7', 'Jonathan', 'Herrera', 'Guatemala', '12345678', '31/05/1999', 1);

-- Inserción de datos en la tabla tbl_tipo_cliente
INSERT INTO tbl_tipo_cliente (id_tipoCliente, descripcion)
VALUES 
(1, 'Mayorista'),
(2, 'Minorista');

-- Inserción de permisos en la tabla tbl_permisos
INSERT INTO tbl_permisos (id_permiso, nombre_permiso)
VALUES 
(1, 'usuario'),
(2, 'grupos'),
(3, 'grupo permisos'),
(4, 'permisos'),
(5, 'clientes');

-- Inserción de grupos en la tabla tbl_grupos
INSERT INTO tbl_grupos (id_grupo, nombre_grupo)
VALUES 
(1, 'administrador'),
(2, 'ventas'),
(3, 'inventario'),
(4, 'compras');

-- Inserción de relaciones en la tabla tbl_gruposPermisos
INSERT INTO tbl_gruposPermisos (id_grupoPer, id_grupo_fk, id_permiso_fk)
VALUES 
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 1, 4),
(5, 1, 5),
(6, 2, 1),
(7, 2, 2);

-- Inserción de usuarios en la tabla tbl_usuarios
INSERT INTO tbl_usuarios (nombre_usuario, apellido_usuarios, usuario_usuarios, password_usuarios, Estado_usuarios, id_grupo_fk)
VALUES 
('Jonathan', 'Herrera', 'admin', '123', 1, 1),
('Jorge', 'Lemus', 'jorge', '123', 1, 2),
('John', 'Rodriguez', 'john', '123', 1, 2);

-- Consulta de todos los clientes
SELECT * FROM tbl_Cliente;

-- Consulta de todos los tipos de cliente
SELECT * FROM tbl_usuarios;

SELECT 
    v.id_venta AS id,
    v.nofactura_venta AS NoFactura,
    v.fecha_venta,
    d.descripcion,
    d.monto,
    CONCAT(c.nombre, ' ', c.apellido) AS Cliente,
    CONCAT(u.nombre_usuario, ' ', u.apellido_usuarios) AS Vendedor
FROM 
    tbl_cliente AS c, 
    tbl_venta AS v, 
    tbl_usuarios AS u, 
    tbl_detalleventa AS d
WHERE 
    v.id_clientefk = c.id_cliente 
    AND v.id_codUsuariosfk = u.id_codUsuarios 
    AND d.id_ventafk = v.id_venta
    AND v.nofactura_venta = '00003';
    

SELECT u.nombre_usuario, COUNT(v.id_venta) AS totalVentas
FROM tbl_venta v
JOIN tbl_usuarios u ON v.id_codUsuariosfk = u.id_codUsuarios
GROUP BY u.nombre_usuario;
select * from tbl_venta;


