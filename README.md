# Instalación

## Instalar docker
1. Descargar [Docker](https://docs.docker.com/docker-for-windows/install/)
2. Descargar [Docker Compose](https://docs.docker.com/v17.09/compose/install/#install-compose)

## Git
1. Descargar [Git](https://git-scm.com/downloads)
2. Descargar [Tortoise Git](https://tortoisegit.org/)

## Editor de base de datos PGADMIN
Descargar [PGADMIN3](https://www.pgadmin.org/download/pgadmin-3-windows/) es un archivo zip, mi recomendacion es que instales la version 3, es mas rápida.

# Poniendo a funcionar nuestro entorno
Una vez terminado los pasos anteriores debemos ir al la raíz de nustro proyecto y clonar el archivo *.env.example*
Una vez clonado debemos ejecutar los siguientes comando de docker: 
1. ``docker-compose build`` Realiza las configuraciones al entorno de trabajo
2. ``docker-compose run java mvn build`` Permite instalar las dependencias de maven
3. ``docker-compose up --build`` Construye el resto de las configuraciones como migraciones de bases de datos y corridas procesos automáticos
4. Si has instalado el contenedor de docker en 
    - windows 10 home, entonces deberas entrar a la siguiente direccion: http://192.168.99.100:8080/
    - windows 10 pro hyper-v deberas ir a la direccion http://localhost:8080
    - linux en las direcciones ips que se encuentran en el archivo .env http://121.9.9.3 sin puerto o con el puerto 8080


Listo! ya tu entorno esta accesible desde la URL

[![Build Status][travis-badge]][travis-badge-url]

![](./img/postgres.png)


### Build
Ejecutar el siguiente comando en la raiz del directorio:
```
mvn clean install
```

### Iniciando el servicio

```

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.4.1.RELEASE)
...
2017-03-08 21:50:17.987  INFO 62548 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
2017-03-08 21:50:17.992  INFO 62548 --- [           main] c.b.e.p.spring.boot.BookApplication      : Started BookApplication in 7.152 seconds (JVM running for 7.566)

```
La aplicacion se inicia en el puerto `8080`.

### Accessing Swagger 
Puedes colocar en el navegador la siguiente ruta `http://localhost:8080/` para luego ver el Swagger. 
![](./img/book-swagger.png)

Click en el `Show/Hide` podras ver los endpoint ya configurados para hacer pruebas.

#### POST Example

Una vez expandido, cree una nueva entrada de libro haciendo clic en "POST" e ingresando el siguiente fragmento de código JSON en el campo `solicitud` y haga clic en` ¡test! `.![](./img/book-post-req.png)

Aquí está la respuesta que recibes. Tenga en cuenta el título del libro y el autor se cautiva antes de la inserción.
![](./img/book-post-rsp.png)

#### GET Example
Para ver todos los registros, haga clic en `GET` e ingrese ya sea` title`, `author`,` genre` o cualquier combinación o campo de ellos y haga clic en lick `¡Test!`.
Los parámetros `title` y` author` son insensibles a mayúsculas y minúsculas.
Aquí está la respuesta que recibes.
![](./img/book-get-rsp.png)

[travis-badge]: https://travis-ci.org/indrabasak/jpa-postgres-spring.svg?branch=master
[travis-badge-url]: https://travis-ci.org/indrabasak/jpa-postgres-spring/