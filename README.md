Este proyecto es una aplicación en **Java** que simula el manejo de citas en un consultorio médico.  
Se desarrolló como práctica de programación orientada a objetos y manejo de archivos CSV para guardar la información.

**Instalación y ejecución**

Abrir NeatBeans y buscar proyecto CitasConsultorio

Ejecutar el archivo MainAppGUI.java.

La aplicación iniciará mostrando la pantalla de login.

Usuario por defecto: admin
Contraseña: admin

**Estructura de datos**
Toda la información se guarda en la carpeta db/ en formato CSV (archivos de texto con comas).

doctores.csv → guarda los doctores del consultorio.

pacientes.csv → guarda los pacientes registrados.

citas.csv → guarda todas las citas creadas, con doctor y paciente asignados.

admins.csv → guarda los usuarios que pueden entrar al sistema.

Estos archivos no se suben al repositorio, cada usuario los genera automáticamente cuando corre el programa por primera vez.

**Funcionalidades principales**
Control de acceso (Login de administrador)
Solo los usuarios registrados en admins.csv pueden acceder.

Si no existe el archivo, se crea un usuario administrador por defecto (admin/admin).

**Alta de doctores**

Se pueden dar de alta doctores nuevos.
Cada doctor tiene:
ID único (número autogenerado)
Nombre completo
Especialidad

**Alta de pacientes**

Se pueden registrar pacientes.
Cada paciente tiene:
ID único
Nombre completo

**Creación de citas**

Una cita siempre debe estar relacionada con un doctor y un paciente ya registrados.
Cada cita guarda:
ID único
Fecha y hora (formato yyyy-MM-dd HH:mm)
Motivo de la cita
Doctor asignado
Paciente asignado

**Listados**

Listar doctores disponibles.
Listar pacientes registrados.
Listar citas programadas.

**Lógica de funcionamiento**
El usuario entra al sistema con usuario y contraseña.

El administrador puede:

Registrar doctores → se guardan en doctores.csv.

Registrar pacientes → se guardan en pacientes.csv.

Crear citas → se verifica que exista el doctor y paciente, luego se guarda en citas.csv.

Consultar listados → se leen los archivos CSV y se muestran en pantalla.

Todos los datos se guardan en archivos CSV, lo que hace que el sistema sea simple y portátil.

**Créditos**
Autor: Francisco Lule

Proyecto realizado como práctica de programación en Java con NetBeans y GitHub.

**Licencia**
Este proyecto es de uso académico y puede ser reutilizado con fines educativos.
