# DemoLab
Proyecto de laboratorio Electoral desarrollado en la asignatura de ISST. 

## Getting Started

Es recomendable usar el entorno de desarrollo integrado Eclipse, con el paquete Enterprise Java Developers - https://www.eclipse.org/downloads/packages/ 

### Prerequisites

Para poder desarrollar el caso de estudio es preciso disponer de estas aplicaciones o paquetes en el ordenador de desarrollo:

```
[Java JDK] (Java Development Kit) versi�n 8 o superior - https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html 
[Maven](https://maven.apache.org/) - Administrador de dependencias
[Apache Tomcat](https://tomcat.apache.org/download-90.cgi) - Servidor Web (Descargar core->zip)
```
[Maven]
En el proyecto que se proporciona ya se ha convertido el proyecto eclipse a un proyecto Maven, lo que puede comprobar viendo que el proyecto tenga el men� contextual Maven. Si no fuera as�, podr�a hacer la conversi�n seleccionando el proyecto y con la opci�n del men� contextual: Configure->Convert to maven project.

[Apache Tomcat]
Marcando su proyecto, en el men� contextual Build Path->Configure Build Path y en la pesta�a Libraries compruebe si est� Apache Tomcat v9.0. Si no estuviera, marque la opci�n Add Library->Server Runtime y all� elija Apache Tomcat v9.0. De esta forma se incorporan al proyecto las bibliotecas necesarias para compilar el c�digo de Java de servidor.

[Arranque del servicio H2 BBDD]
java -jar <rutacompleta>/h2-1.4.197.jar

[Arranque del proyecto]
Run as-> Run on server.

## Built With

* [Maven](https://maven.apache.org/) - Administrador de dependencias
* [Apache Tomcat](https://tomcat.apache.org/) - Servidor Web


## Authors

* **Equipo demolab-etsit** - *Initial work* - (https://github.com/demolab-etsit)


## License

This project is licensed under the GNU GENERAL PUBLIC LICENSE (Version 3, 29 June 2007)- see the [LICENSE.md](LICENSE.md) file for details
