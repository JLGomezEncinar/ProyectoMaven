 # Miniproyecto: Integración de Maven, Git y Pruebas con JUnit 5 + Mockito
**1. Descripción del proyecto y funcionalidades.**

 Para el proyecto he realizado una pantalla de logueo utilizando JavaFX para el entorno gráfico y Maven para las dependencias. En la pantalla de logueo deberemos introducir un usuario y una contraseña (para la realización del proyecto he utilizado "user" y "password" para el usuario y la contraseña, respectivamente) y darle a un botón para validar. La pantalla da un aviso de error si el usuario o la contraseña está vacío cuando presionamos el botón, si el usuario y/o la contraseña avisará del error y del número de intentos que nos quedan (tendremos tres intentos iniciales, si agotamos los tres las veces siguientes que intentemos introducir datos la aplicación nos informará de que el sistema está bloqueado). Finalmente, si introducimos el usuario y la contraseña correctos la aplicación nos indicará que nos hemos logueado con éxito y cerrará la aplicación.

**2. Utilización de TDD en el proyecto.**

 Al diseñar la aplicación he tomado en cuenta los tests antes de implementar código. De esta forma primero cree las clases y las funciones sin código, y empecé a diseñar los test de esta manera:

a) Primero diseñé un test que, al introducir "user" como usuario y "password" como contraseña la función isLogin devolviese true, implementando el código necesario para poder pasar ese test.

b) En segundo lugar ideé un test que, al introducir tres veces unos datos incorrectos, la función isLogin devolviese siempre false aunque le introduzcamos los datos correctos después (la aplicación se "bloquea"), para eso agregué variables que controlasen el número de intentos y una función isBlocked que se activase cuando llegamos a los tres intentos fallidos.

c) Mi tercer test consistió en resetear el número de intentos si introducimos el usuario y la contraseña correctas antes de realizar tres intentos fallidos, en este caso bastó con devolver a 0 la variable de intentos cuando se introducen los datos correctos.

d) Por último, en esta parte de tests, consideré que era más correcto devolver una excepción a la hora de pulsar el botón cuando alguno de los campos estuviese vacío (en lugar de contar ese intento como fallido), para eso añadí código que lanzase una excepción cuando el usuario o la contraseña está vacía al pulsar el botón.

 Todos estos tests al principio no utilizaban una base de datos (simplemente validaba que el usuario fuese "user" y la contraseña "password"), sin embargo la lógica a la hora de comprobar los tests era la misma, simplemente cambia el hecho de que la conexión se realiza a una base de datos que tiene ese usuario y contraseña, así que aproveché también para refactorizar el código.
 Finalmente, como la función isLogin se ejecuta en la clase LoginScreen pero está en la clase UsuarioService decidí hacer un test con Mockito simulando la conexión desde LoginScreen a UsuarioService para comprobar que con los datos "user" (usuario) y "password" (contraseña) el test pasaba satisfactoriamente.

 Con todo esto los tests estaban completos, pero tuve que hacerles una ligera modificación puesto que, al añadir alertas en la aplicación, era necesario inicializar ciertos módulos en los tests para evitar fallos con estos elementos, de tal forma que elabore una clase JavaFXTestBase con métodos que permitiesen el correcto funcionamiento de los tests.

 En mi opinión, la idea de TDD es positiva en el aspecto de que se "obliga" al programador a intentar pasar los tests que se diseñan de la manera más "sencilla" posible, lo que evita que se escriba código innecesario y sea más fácil refactorizar posteriormente, aunque considero que puede alargar el proceso de desarrollo más de la cuenta si la aplicación no es excesivamente compleja (hay que pensar qué tests vamos a realizar primero cuando es posible que la aplicación no sea muy grande y los casos de prueba sean escasos).

**3. Instalación y compilación con Maven.**

 Para poder llegar a realizar la compilación e instalación he añadido las siguientes dependencias en Maven:

 a) Dependencias como javafx-fxml o javafx-controls, necesarias para el funcionamiento de la aplicación bajo el entorno JavaFX.

 b) Dependencia de mysql-connector-java, necesaria para poder realizar una conexión a la base de datos.

 c) Dependencias de junit-jupiter y mockito, necesarias para la realización de los tests.

 d) Por último añadí diferentes dependencias de apache que realizan las compilaciones correctas de JavaFX para poder crear el archivo .jar.
 Con estas dependencias es posible ejecutar desde consola el comando mvn install (o desde el menú de la parte izquierda del IntelliJ) y exportar el archivo .jar.

**4. Estructura del proyecto (y posibles futuras actualizaciones).**

 He estructurado en varias carpetas el proyecto intentando mantener una lógica de programación:

 a) Alerts: Una carpeta donde se incluyen las alertas que muestra la aplicación cuando sucede algún evento.

 b) Data: Aquí se encuentra la clase que realiza la conexión a la base de datos (el usuario, contraseña y localización de la base de datos se encuentran en un archivo aparte).

 c) Models: Aquí está la clase Usuario con sus variables de usuario y contraseña (podría albergar otras clases en el futuro al ampliar la aplicación).

 d) Navigation: Aquí indicamos la manera de movernos entre las diferentes escenas (en el proyecto sólo hay una, pero podrían añadirse más).

 e) Repositories: Aquí están las diferentes llamadas que realizamos a la base de datos (para poder hacer el proyecto sólo hay un select que busca el usuario y contraseña, aunque se podría añadir algún método más si queremos hacer una ventana de registro, por poner un ejemplo).

 f) Screens: Aquí se ubican las diferentes escenas del proyecto (sólo tiene una ahora, aunque se podrían añadir más).

 g) Services: Aquí está la clase que maneja los métodos que realizan la lógica (comprueba los datos que se reciben de la base de datos y autentifica si son válidos o no).

 h) Resources: Aquí está el archivo con la localización, usuario y contraseña de la base de datos.

 i) Test: Aquí están los diferentes tests creados, he añadido una clase JavaFXTestBase porque, al ejecutar los tests dentro de la compilación, al tener que cargar los dos tests el entorno de JavaFX se producían errores, de esta manera esa clase maneja correctamente la creación del entorno y los tests pasan correctamente cuando se compila la aplicación (o se hace un mvn test).

 j) Las otras clases como HelloApplication (que es la clase principal) o HelloController son las que se crean por defecto a la hora de elaborar un proyecto en JavaFX, me he visto sin embargo obligado a crear una clase Main que lo único que hace es cargar la clase HelloApplication puesto que, por incompatibilidades a la hora de compilar proyectos de JavaFX, no es posible utilizar como base una clase principal que extienda de otra (HelloApplication extiende la clase Application).
 
Por último, como posibles mejoras al proyecto se podría realizar una escena que permita el registro de usuarios (se pediría un usuario y contraseña que se añadiría a la base de datos existente, respetando que no se puedan repetir nombres de usuarios y que no se supere un número máximo de caracteres) y una pantalla de reserva (por ejemplo, la compra de entradas de un cine).
