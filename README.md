Carrental: Example Using Angular2 with Angular Cli and Java EE 6 Deployed as an EAR
==============================================================================================
Author: Sven Loesekann
Level: Intermediate
Technologies: EAR, Rest, Angular 2, Angular Cli
Summary: Example Project for the integration of Maven, Angular, Java EE 6
Target Project: Java EE 6

What is it?
-----------

The project shows the integration of Angular 2 with Angular Cli as UI. The Rest Services are provided by JavaEE 6. In this Example with WildFly. The Build is done with Maven. 
The Development Setup is Eclipse with Typescript Plugin or Webstorm. JBoss is integrated with the JBoss Plugin to provide redeployments. Angular Cli provides the UI an redeploys the UI on change and makes the optimized production build.

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
Install Eclipse Typescript Plugin "http://eclipse-update.palantir.com/eclipse-typescript/"
