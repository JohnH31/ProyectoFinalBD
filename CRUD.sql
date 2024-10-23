CREATE DATABASE bd_web; USE bd_web;
CREATE TABLE tbl_permisos (
 id_permiso INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
 nombre_permiso VARCHAR(80) NOT NULL
)
CREATE TABLE tbl_grupos (
 id_grupo INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
 nombre_grupo VARCHAR(80) NOT NULL
)


CREATE TABLE tbl_gruposPermisos (
 id_grupoPer INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
 id_grupo_fk INT(11) NOT NULL,
 id_permiso_fk INT(11) NOT NULL
)
CREATE TABLE tbl_usuarios (
 id_codUsuarios INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
 nombre_usuario VARCHAR(80) NOT NULL,
 apellido_usuarios VARCHAR(80) NOT NULL,
 usuario_usuarios VARCHAR(80) NOT NULL,
 password_usuarios VARCHAR(80) NOT NULL,
 Estado_usuarios BOOLEAN NOT NULL,
 id_grupo_fk INT(11) NOT NULL
)

SELECT u.id_codUsuarios as id,u.nombre_usuario,u.apellido_usuarios,u.usuario_usuarios,u.password_usuarios,u.Estado_usuarios,g.nombre_grupo,g.id_grupo
FROM tbl_usuarios AS u, tbl_grupos AS g
WHERE u.id_grupo_fk = g.id_grupo;


CREATE TABLE tbl_tipo_cliente (
 id_tipoCliente INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
 descripcion VARCHAR(80) NOT NULL
);
CREATE TABLE tbl_Cliente (
 id_cliente INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
 nit VARCHAR(10),
 nombre VARCHAR(80) NOT NULL,
 apellido VARCHAR(80) NOT NULL,
 direccion VARCHAR(80) NOT NULL,
 telefono VARCHAR(80) NOT NULL,
 fecha_nacimiento VARCHAR(80) NOT NULL,
 id_tipoCliente INT(11) NOT NULL
);

CREATE TABLE tbl_venta (
id_venta INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
nofactura_venta VARCHAR(80) NOT NULL,
fecha_venta VARCHAR(80) NOT NULL,
total_venta VARCHAR(80) NOT NULL,
id_codUsuariosfk INT(11) NOT NULL,
id_clientefk INT(11) NOT NULL
);

ALTER TABLE tbl_venta AUTO_INCREMENT = 1;
INSERT INTO tbl_venta(nofactura_venta,fecha_venta,total_venta,id_codUsuariosfk,id_clientefk)VALUE (00001,'2022/04/11',200,3,6);

CREATE TABLE tbl_detalleventa (
id_detalle_venta INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
descripcion VARCHAR(80) NOT NULL,
monto VARCHAR(80) NOT NULL,
id_ventafk INT(11) NOT NULL
);

SELECT MAX(id_venta) from tbl_venta

INSERT INTO tbl_detalleventa(descripcion,monto,id_ventafk)VALUE ('pago1',300,13);

INSERT INTO tbl_Cliente(nit,nombre,apellido,direccion,telefono,fecha_nacimiento,id_tipoCliente)VALUE 
('123456-7','jona','hernan','zzzz','12354785','31/05/1999',1);

INSERT INTO tbl_tipo_cliente(id_tipoCliente,descripcion)VALUE 
(1,'Mayorista'),
(2,'Minorista');


ALTER TABLE tbl_gruposPermisos ADD CONSTRAINT fk1 FOREIGN KEY (id_grupo_fk) REFERENCES tbl_grupos (id_grupo); 
ALTER TABLE tbl_gruposPermisos ADD CONSTRAINT fk2 FOREIGN KEY (id_permiso_fk) REFERENCES tbl_permisos (id_permiso); 
ALTER TABLE tbl_usuarios ADD CONSTRAINT fk3 FOREIGN KEY (id_grupo_fk) REFERENCES tbl_grupos (id_grupo);
ALTER TABLE tbl_Cliente ADD CONSTRAINT fk4 FOREIGN KEY (id_tipoCliente) REFERENCES tbl_tipo_cliente (id_tipoCliente);
ALTER TABLE tbl_venta ADD CONSTRAINT fk5 FOREIGN KEY (id_codUsuariosfk) REFERENCES tbl_usuarios (id_codUsuarios);
ALTER TABLE tbl_venta ADD CONSTRAINT fk6 FOREIGN KEY (id_clientefk) REFERENCES tbl_Cliente (id_cliente);
ALTER TABLE tbl_detalleventa ADD CONSTRAINT fk7 FOREIGN KEY (id_ventafk) REFERENCES tbl_venta (id_venta);

INSERT INTO tbl_permisos(id_permiso,nombre_permiso)VALUE 
(1,'usuario'),
(2,'grupos'),
(3,'grupo permisos'),
(4,'permisos'),
(5,'clientes');
INSERT INTO tbl_grupos(id_grupo,nombre_grupo)VALUE 
(1,'administrador'),
(2,'ventas'),
(3,'inventario'),
(4,'compras');
INSERT INTO tbl_gruposPermisos(id_grupoPer,id_grupo_fk,id_permiso_fk)VALUE 
(1,1,1),
(2,1,2),
(3,1,3),
(4,1,4),
(5,1,5),
(6,2,1),
(7,2,2);
INSERT INTO tbl_usuarios(nombre_usuario,apellido_usuarios,usuario_usuarios,password_usuarios,Estado_usuarios,id_grupo_fk)VALUE 
('Jonathan','Herrera','admin','123',1,1),
('jorge','Lemus','jorge','123',1,2),
('john','Rodriguez','john','123',1,2);

INSERT INTO tbl_Cliente(nit,nombre,apellido,direccion,telefono,fecha_nacimiento,id_tipoCliente)VALUE 
('123456-7','jona','hernan','zzzz','12354785','31/05/1999',1);

INSERT INTO tbl_tipo_cliente(id_tipoCliente,descripcion)VALUE 
(1,'Mayorista'),
(2,'Minorista');


SELECT * FROM tbl_cliente;
SELECT * FROM tbl_tipo_cliente;

SELECT c.id_cliente as id,c.nit,c.nombre,c.apellido,c.direccion,c.telefono,c.fecha_nacimiento,tc.descripcion,tc.id_tipoCliente
FROM tbl_cliente AS c, tbl_tipo_cliente AS tc
WHERE c.id_tipoCliente=tc.id_tipoCliente AND id_cliente=1;

SELECT *
FROM tbl_permisos;
SELECT *
FROM tbl_grupos;
SELECT *
FROM tbl_gruposPermisos;
SELECT *
FROM tbl_usuarios;
SELECT *
FROM tbl_permisos;
SELECT * FROM tbl_venta;
SELECT * FROM tbl_detalleventa;
WHERE id_permiso=1;
UPDATE tbl_permisos SET nombre_permiso='esto es una'
WHERE id_permiso=9
INSERT INTO tbl_permisos(nombre_permiso)VALUE ('frmventas') ALTER TABLE tbl_permisos AUTO_INCREMENT = 8
UPDATE tbl_gruposPermisos SET id_grupo_fk=2,id_permiso_fk=2
WHERE id_grupoPer=10;
SELECT a.nombre_usuario, b.id_permiso_fk
FROM tbl_usuarios a
INNER JOIN tbl_gruposPermisos b ON a.id_grupo_fk = b.id_grupo_fk
WHERE a.nombre_usuario='Jonathan'

SELECT * FROM tbl_venta WHERE fecha_venta BETWEEN '2022-12-01' AND '2022-12-30'
SELECT v.id_venta as id,v.nofactura_venta AS NoFactura,v.fecha_venta,v.total_venta,c.nombre AS NombreCliente,c.apellido AS ApellidoCliente,u.nombre_usuario,u.apellido_usuarios
FROM tbl_cliente AS c, tbl_venta AS v, tbl_usuarios AS u
WHERE v.id_clientefk=c.id_cliente AND v.id_codUsuariosfk=u.id_codUsuarios 
AND v.fecha_venta BETWEEN '2022-12-01' AND '2022-12-30'

SELECT v.id_venta as id,v.nofactura_venta as NoFactura,v.fecha_venta,d.descripcion,d.monto,c.nombre AS nombreCliente,c.apellido AS ApellidoCliente,u.nombre_usuario,u.apellido_usuarios
FROM tbl_cliente AS c, tbl_venta AS v, tbl_usuarios AS u, tbl_detalleventa AS d
WHERE v.id_clientefk=c.id_cliente AND v.id_codUsuariosfk=u.id_codUsuarios AND d.id_ventafk=v.id_venta
AND v.fecha_venta BETWEEN '2022-11-01' AND '2022-11-30'