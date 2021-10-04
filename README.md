# BestMovies

La rama 'master' contiene el proyecto a ejecutar. Es suficiente con clonar y ejecutar el proyecto.



Estructura de proyecto

Paquetes

- entities: 
Contiene los modelos según las respuestas del api, están definidos modelos para el manejo de tokens, carruseles, peliculas y una entidad resultante de la relación de uno a muchos entre carrusel y peliculas.

- local: 
Contiene las clases para instanciar la base de datos y los DAO para realizar las consultas

- remote: 
Contiene las interfaces para las peticiones al api, además contiene clases auxiliares para el manejo de respuestas de las peticiones, también un 'Authenticator' para la autenticación contra el api y un 'Interceptor' para proveer el accessToken obtenido a las demás peticiones.

- repository: 
Contiene los repositorios, las clases que reúnen el accesso a los datos a través del api y la bd.

- di: 
Contiene los módulos definidos para inyección de dependencias con Hilt.

- ui: 
Contiene los adaptadores para los Recycler, fragmentos para los carruseles, detalles de película, reproducción de video con Exoplayer y una actividad principal.

- utils: 
Contiene clases y métodos de utilería para facilitar el acceso a los datos, realizar peticiones al api y almacenar datos.
