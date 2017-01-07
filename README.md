Carrental: Example Using Angular2 with Angular Cli and Java EE 6 Deployed as an EAR
==============================================================================================
Author: Sven Loesekann

Technologies: EAR, Rest, Angular 2, Angular Cli

Summary: Example Project for the integration of Maven, Angular, Java EE 6

Target Project: Java EE 6

What is it?
-----------

The project shows the integration of Angular 2 with Angular Cli as UI. The Rest Services are provided by JavaEE 6. In this Example with WildFly. The Build is done with Maven. 

The Development Setup is Eclipse with Typescript Plugin or Webstorm. JBoss is integrated with the JBoss Plugin to provide redeployments. Angular Cli provides the UI an redeploys the UI on change and makes the optimized production build.

The Ear that is build can be tested in a Docker Image that Maven can build.

Development requirements
-------------------

Java 8 Jdk or newer. 

Eclipse Neon or newer.

Wildfly 10 or newer. (Any JavaEE 6 Appserver will do)

JBoss Plugin JBoss AS, WildFly & EAP Server Tools	3.2.0 or newer.

Plugin TypeScript	1.8.0 or newer.

Maven 3.3.3 or newer. 

Nodejs 6.9.x  

Npm 3.10.x 

Angular Cli 1.0.0-beta.20 or newer. 

Build Server requirements
-------------------------
Java 8 Jdk or newer. 

Maven 3.3.3 or newer. 

Nodejs 6.9.x  

Npm 3.10.x 

Setup
-----
Jdk 8, Wildfly 10(or an other Java EE6 server), Nodejs, Eclipse, Maven must be installed.

Install Angular Cli "npm install -g angular-cli@latest"

Install Eclipse JBoss Plugin "http://download.jboss.org/jbosstools/neon/stable/updates/"

Add the Server Runtime Environment for Wildfly and connect it to the wildfly installation.

Install Eclipse Typescript Plugin "http://oss.opensagres.fr/typescript.ide/1.1.0/"

Optionally install Webstorm for the UI.

Development Setup
-----------------
Check out the carrental project. 

Open a shell and cd into the directory carrental-web/src/main/angular2/carrental

Execute npm install for dependency resolution

Execute ng serve to start the UI

Start Wildfly in Eclipse

Build Setup
-----------
It is a multi module project. 
The ear project configures the Ear.
The ejb project builds the ejbs to provide the data for the rest service.
The war project builds the war. Jax-Rs provides the rest services to serve the data of the ejbs. Npm install provides the dependency resolution for Angular Cli. Angular Cli builds a tree shaken, uglified bundle. 
The ear is build.

Project Goal
------------
To provide a maven project that can be build on continous build server and provide an optimized ui bundle. 
To provide a development setup that provides fast feedback on the java ee server and on the UI. 
The fast feedback on the rest service is provided by the wildfly integration in eclipse.
The fast feedback on the ui is provided by the angular cli integration. Angular Cli is used to provide the ui with fast feedback on change. Angular Cli can generate components, pipes and more. It builds Typescript into Javascript. It provides test wrappers and can execute them. 

With such a setup Angular 2 can be used in an Java EE environment.

Testing the Ear
---------------
To provide a method to test the Ear of the Build a Docker Image can be build. The image uses Wildfly and deploys the Ear in the server. The server can then be started and the optimized build can be tested. That enables testing the Ear without changing the setup of the development environment. Docker Images of other Application Servers a availiable and provide the opportunity to test the Ear on different platforms.
