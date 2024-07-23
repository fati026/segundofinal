CREATE TABLE producto (
	id int auto_increment PRIMARY KEY,
	nombre varchar(50),
	descripcion varchar(50),
	unidades int,
	costo decimal(10, 2),
	precio decimal (10, 2),
	categoria varchar(50)
);

CREATE TABLE usuario (
	id int auto_increment PRIMARY KEY,
	nombre varchar(50),
	contrasenia varchar(50),
	administrador tinyint(1)
);

CREATE TABLE auditoria (
	idAuditoria int auto_increment PRIMARY KEY,
	nombreProducto varchar(50),
	descripcionProducto varchar(50),
	unidadesProducto int,
	costoProducto decimal(10, 2),
	PrecioProducto decimal (10, 2),
	categoriaProducto varchar(50),
	idUsuario int,
	nombreUsuario varchar(50),
	descripcionAccion varchar(20)
);