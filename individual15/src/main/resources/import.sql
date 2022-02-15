INSERT INTO departamentos(nombre, ubicacion) VALUES ("Comunicacion", "Sevilla")
INSERT INTO departamentos(nombre, ubicacion) VALUES ("RRHH", "Sevilla")
INSERT INTO departamentos(nombre, ubicacion) VALUES ("IT", "Sevilla")
INSERT INTO departamentos(nombre, ubicacion) VALUES ("Desarrollo", "Sevilla")

INSERT INTO empleados(DNI, nombre, Salario, telefono, departamento_id) VALUES ("123A", "David", 1500, 657878834, 1)
INSERT INTO empleados(DNI, nombre, Salario, telefono, departamento_id) VALUES ("123B", "David", 1500, 657878876, 2)
INSERT INTO empleados(DNI, nombre, Salario, telefono, departamento_id) VALUES ("123C", "David", 1500, 657878809, 3)
INSERT INTO empleados(DNI, nombre, Salario, telefono, departamento_id) VALUES ("123D", "David", 1500, 657878800, 2)

INSERT INTO jefes(DNI, nombre, Salario, telefono, departamento_id) VALUES ("124D", "David", 2340, 657878820, 2)
INSERT INTO jefes(DNI, nombre, Salario, telefono, departamento_id) VALUES ("1123AD", "David", 2340, 657872220, 3)
INSERT INTO jefes(DNI, nombre, Salario, telefono, departamento_id) VALUES ("11232D", "David", 2340, 2345423, 1)

INSERT INTO login(usuario, password) VALUES ("1234", "1234")