-- Creación de las secuencias para las claves primarias
CREATE SEQUENCE seq_permisos START WITH 1;
CREATE SEQUENCE seq_grupos START WITH 1;
CREATE SEQUENCE seq_gruposPermisos START WITH 1;
CREATE SEQUENCE seq_usuarios START WITH 1;
CREATE SEQUENCE seq_tipo_cliente START WITH 1;
CREATE SEQUENCE seq_cliente START WITH 1;
CREATE SEQUENCE seq_venta START WITH 1;
CREATE SEQUENCE seq_detalleventa START WITH 1;

-- Creación de la tabla tbl_permisos
CREATE TABLE tbl_permisos (
    id_permiso NUMBER(11) PRIMARY KEY,
    nombre_permiso VARCHAR2(80) NOT NULL
);

-- Creación de la tabla tbl_grupos
CREATE TABLE tbl_grupos (
    id_grupo NUMBER(11) PRIMARY KEY,
    nombre_grupo VARCHAR2(80) NOT NULL
);

-- Creación de la tabla tbl_gruposPermisos (relación muchos a muchos)
CREATE TABLE tbl_gruposPermisos (
    id_grupoPer NUMBER(11) PRIMARY KEY,
    id_grupo_fk NUMBER(11) NOT NULL,
    id_permiso_fk NUMBER(11) NOT NULL,
    CONSTRAINT fk_grupos FOREIGN KEY (id_grupo_fk) REFERENCES tbl_grupos(id_grupo),
    CONSTRAINT fk_permisos FOREIGN KEY (id_permiso_fk) REFERENCES tbl_permisos(id_permiso)
);

-- Creación de la tabla tbl_usuarios
CREATE TABLE tbl_usuarios (
    id_codUsuarios NUMBER(11) PRIMARY KEY,
    nombre_usuario VARCHAR2(80) NOT NULL,
    apellido_usuarios VARCHAR2(80) NOT NULL,
    usuario_usuarios VARCHAR2(80) NOT NULL,
    password_usuarios VARCHAR2(80) NOT NULL,
    estado_usuarios NUMBER(1) NOT NULL, -- Usamos NUMBER(1) para representar un booleano (1 o 0)
    id_grupo_fk NUMBER(11) NOT NULL,
    CONSTRAINT fk_grupos_usuarios FOREIGN KEY (id_grupo_fk) REFERENCES tbl_grupos(id_grupo)
);

-- Creación de la tabla tbl_tipo_cliente
CREATE TABLE tbl_tipo_cliente (
    id_tipoCliente NUMBER(11) PRIMARY KEY,
    descripcion VARCHAR2(80) NOT NULL
);

-- Creación de la tabla tbl_Cliente (incluye dirección y teléfono directamente)
CREATE TABLE tbl_Cliente (
    id_cliente NUMBER(11) PRIMARY KEY,
    nit VARCHAR2(10),
    nombre VARCHAR2(80) NOT NULL,
    apellido VARCHAR2(80) NOT NULL,
    direccion VARCHAR2(255) NOT NULL,
    telefono VARCHAR2(15) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    id_tipoCliente NUMBER(11) NOT NULL,
    CONSTRAINT fk_tipoCliente FOREIGN KEY (id_tipoCliente) REFERENCES tbl_tipo_cliente(id_tipoCliente)
);

-- Creación de la tabla tbl_venta
CREATE TABLE tbl_venta (
    id_venta NUMBER(11) PRIMARY KEY,
    nofactura_venta VARCHAR2(80) NOT NULL,
    fecha_venta DATE NOT NULL,
    total_venta NUMBER(10, 2) NOT NULL,
    id_codUsuariosfk NUMBER(11) NOT NULL,
    id_clientefk NUMBER(11) NOT NULL,
    CONSTRAINT fk_usuarios_venta FOREIGN KEY (id_codUsuariosfk) REFERENCES tbl_usuarios(id_codUsuarios),
    CONSTRAINT fk_cliente_venta FOREIGN KEY (id_clientefk) REFERENCES tbl_Cliente(id_cliente)
);

-- Creación de la tabla tbl_detalleventa
CREATE TABLE tbl_detalleventa (
    id_detalle_venta NUMBER(11) PRIMARY KEY,
    descripcion VARCHAR2(80) NOT NULL,
    monto NUMBER(10, 2) NOT NULL,
    id_ventafk NUMBER(11) NOT NULL,
    CONSTRAINT fk_venta_detalle FOREIGN KEY (id_ventafk) REFERENCES tbl_venta(id_venta)
);

-- Inserciones en la tabla tbl_tipo_cliente
INSERT INTO tbl_tipo_cliente (id_tipoCliente, descripcion)
VALUES (seq_tipo_cliente.NEXTVAL, 'Mayorista');
INSERT INTO tbl_tipo_cliente (id_tipoCliente, descripcion)
VALUES (seq_tipo_cliente.NEXTVAL, 'Minorista');

-- Inserciones en la tabla tbl_permisos
INSERT INTO tbl_permisos (id_permiso, nombre_permiso)
VALUES (seq_permisos.NEXTVAL, 'usuario');
INSERT INTO tbl_permisos (id_permiso, nombre_permiso)
VALUES (seq_permisos.NEXTVAL, 'grupos');
INSERT INTO tbl_permisos (id_permiso, nombre_permiso)
VALUES (seq_permisos.NEXTVAL, 'grupo permisos');
INSERT INTO tbl_permisos (id_permiso, nombre_permiso)
VALUES (seq_permisos.NEXTVAL, 'permisos');
INSERT INTO tbl_permisos (id_permiso, nombre_permiso)
VALUES (seq_permisos.NEXTVAL, 'clientes');

-- Inserciones en la tabla tbl_grupos
INSERT INTO tbl_grupos (id_grupo, nombre_grupo)
VALUES (seq_grupos.NEXTVAL, 'administrador');
INSERT INTO tbl_grupos (id_grupo, nombre_grupo)
VALUES (seq_grupos.NEXTVAL, 'ventas');
INSERT INTO tbl_grupos (id_grupo, nombre_grupo)
VALUES (seq_grupos.NEXTVAL, 'inventario');
INSERT INTO tbl_grupos (id_grupo, nombre_grupo)
VALUES (seq_grupos.NEXTVAL, 'compras');

-- Inserciones en la tabla tbl_gruposPermisos
INSERT INTO tbl_gruposPermisos (id_grupoPer, id_grupo_fk, id_permiso_fk)
VALUES (seq_gruposPermisos.NEXTVAL, 1, 1);
INSERT INTO tbl_gruposPermisos (id_grupoPer, id_grupo_fk, id_permiso_fk)
VALUES (seq_gruposPermisos.NEXTVAL, 1, 2);
INSERT INTO tbl_gruposPermisos (id_grupoPer, id_grupo_fk, id_permiso_fk)
VALUES (seq_gruposPermisos.NEXTVAL, 1, 3);
INSERT INTO tbl_gruposPermisos (id_grupoPer, id_grupo_fk, id_permiso_fk)
VALUES (seq_gruposPermisos.NEXTVAL, 1, 4);
INSERT INTO tbl_gruposPermisos (id_grupoPer, id_grupo_fk, id_permiso_fk)
VALUES (seq_gruposPermisos.NEXTVAL, 1, 5);
INSERT INTO tbl_gruposPermisos (id_grupoPer, id_grupo_fk, id_permiso_fk)
VALUES (seq_gruposPermisos.NEXTVAL, 2, 1);
INSERT INTO tbl_gruposPermisos (id_grupoPer, id_grupo_fk, id_permiso_fk)
VALUES (seq_gruposPermisos.NEXTVAL, 2, 2);

-- Inserciones en la tabla tbl_usuarios
INSERT INTO tbl_usuarios (id_codUsuarios, nombre_usuario, apellido_usuarios, usuario_usuarios, password_usuarios, estado_usuarios, id_grupo_fk)
VALUES (seq_usuarios.NEXTVAL, 'Jonathan', 'Herrera', 'admin', '123', 1, 1);
INSERT INTO tbl_usuarios (id_codUsuarios, nombre_usuario, apellido_usuarios, usuario_usuarios, password_usuarios, estado_usuarios, id_grupo_fk)
VALUES (seq_usuarios.NEXTVAL, 'Jorge', 'Lemus', 'jorge', '123', 1, 2);
INSERT INTO tbl_usuarios (id_codUsuarios, nombre_usuario, apellido_usuarios, usuario_usuarios, password_usuarios, estado_usuarios, id_grupo_fk)
VALUES (seq_usuarios.NEXTVAL, 'John', 'Rodriguez', 'john', '123', 1, 2);

-- Inserciones en la tabla tbl_Cliente
INSERT INTO tbl_Cliente (id_cliente, nit, nombre, apellido, direccion, telefono, fecha_nacimiento, id_tipoCliente)
VALUES (seq_cliente.NEXTVAL, '123456-7', 'Jonathan', 'Herrera', 'Guatemala', '12345678', TO_DATE('1999-05-31', 'YYYY-MM-DD'), 1);

-- Inserciones en la tabla tbl_venta
INSERT INTO tbl_venta (id_venta, nofactura_venta, fecha_venta, total_venta, id_codUsuariosfk, id_clientefk)
VALUES (seq_venta.NEXTVAL, '00001', TO_DATE('2022-04-11', 'YYYY-MM-DD'), 200.00, 3, 1);

-- Inserciones en la tabla tbl_detalleventa
INSERT INTO tbl_detalleventa (id_detalle_venta, descripcion, monto, id_ventafk)
VALUES (seq_detalleventa.NEXTVAL, 'pago1', 300.00, 1);


