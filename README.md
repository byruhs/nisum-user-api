# RestAPI Usuarios
Autenticacion JWT y Creación de usuarios.

***
# Tecnologías

- [Swagger ](https://swagger.io/docs/specification/about/)
- [JWT](https://github.com/jwtk/jjwt)
- [Spring Validation](https://docs.spring.io/spring-framework/docs/4.1.x/spring-framework-reference/html/validation.html#validation-beanvalidation)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [H2 Database](https://www.h2database.com/html/main.html)
- [Mockito](https://site.mockito.org/)

***
# Correr el proyecto
Ejecutar aplicación.

```shell
mvn spring-boot:run
```

URL del API Rest: 

http://localhost:8080/

***
# Endpoints

La documentacion del API Rest se encuentra en Swagger:

http://localhost:8080/swagger-ui/index.html

![image](https://github.com/byruhs/nisum-user-api/blob/main/imgs/swagger?raw=true)

### /authenticate
Obtiene Json Web Token (JWT) para realizar peticiones autenticadas.

**Credenciales para generar token:**

username: **admin**

password: **admin**

**Request:**

```
{
    "username": "admin",
    "password": "admin"
}
```

**Resultado**

Se genera un token con 1 hora de validez.

![image](https://github.com/byruhs/nisum-user-api/blob/main/imgs/postman-token?raw=true)

### /users/save
Crea un usuario en la base de datos.

*Requiere header Authorization Bearer token*

**Request de Ejemplo:**

```
{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "Hunter2*",
    "phones": [
        {
        "number": "1234567",
        "citycode": "1",
        "contrycode": "57"
        }
    ]
}
```

**Resultado Satisfactorio**

Si los datos enviados son correctos, se crea el usuario en la base de datos en memoria.

![image](https://github.com/byruhs/nisum-user-api/blob/main/imgs/postman-save-user?raw=true)

**Ejemplo de Response con Error**

Si los datos enviados tiene algun dato erroneo, se muestra mensaje de error.

![image](https://github.com/byruhs/nisum-user-api/blob/main/imgs/postman-save-user-error?raw=true)

***
## Diagrama de flujo

Diagrama que muestra el flujo que se debe seguir para consumir el API Rest.

![image](https://github.com/byruhs/nisum-user-api/blob/main/imgs/diagrama?raw=true)

