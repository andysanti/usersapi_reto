# smartjob_reto
api de invocacion de usuario previa autorizacion con token jwt.
Descripcion tecnica de implementacion:
se usa spring boot ,jdk 8, spring security con JWT y base de datos en memoria H2.
hay dos end points.1 : POST relacionado al registro de Usuario y guardado 
junto con el token generado,se retorna el usuario creado junto con el token.
2 : GET donde se obtiene el listado de usuarios.en la invocacion se debe agregar 
en la variable Header Authorization el token generado en el paso 1.
la visualizacion del contrato swagger se encuentra en la siguiente url: http://localhost:8080/swagger-ui/index.html
