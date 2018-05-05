Carrental: Example Using Angular with Angular Cli and Java EE 6 Deployed as an EAR
==============================================================================================
Author: Sven Loesekann

![Build Status](https://travis-ci.org/Angular2Guy/Angular2AndJavaEE.svg?branch=master)


Technologies: EAR, Rest, Angular, Angular Cli

Summary: Example Project for the integration of Maven, Angular, Java EE 6

Target Project: Java EE 6

What is it?
-----------

The project shows the integration of Angular with Angular Cli as UI. The Rest services are provided by JavaEE 6, in this example is with WildFly. The build is done in Maven. 

The development setup is Eclipse with Typescript Plugin or Webstorm. JBoss is integrated with the JBoss Plugin to provide re-deployments. Angular Cli provides the UI, redeploys the UI on change and optimizes production build.

The Ear that is build can be tested in a Docker Image that Maven builds.

Development Requirements
-------------------

Java 8 JDK or newer. 

Eclipse Oxygen JEE or newer.

WildFly 10 or newer. (Any JavaEE 6 Appserver will do)

JBoss Plugin JBoss AS, WildFly & EAP Server Tools	4.5.0 or newer.

Plugin Typescript.Java 1.4.0 or newer.

Maven 3.3.3 or newer. 

Nodejs 8.11.x or newer 

Npm 5.6.x or newer

Angular Cli 1.7.0 or newer. 

Build Server Requirements
-------------------------
Java 8 JDK or newer. 

Maven 3.3.3 or newer. 

Nodejs 8.11.x  

Npm 5.6.x 

Setup
-----
JDK 8, Wildfly 10(or an other Java EE6 server), Nodejs, Eclipse, Maven must be installed.

Install Angular Cli "npm install -g angular-cli@latest".

Install Eclipse JBoss Plugin "http://download.jboss.org/jbosstools/oxygen/stable/updates/"

Add the server runtime environment for Wildfly and connect it to the WildFly installation.

Install Eclipse Plugin Typescript.Java "http://oss.opensagres.fr/typescript.ide/1.4.0-SNAPSHOT/"

Optionally install Webstorm for the UI.

Development Setup
-----------------
Check out the carrental project. 

Open a shell and cd into the directory carrental-web/src/main/angular2/carrental

Execute npm install for dependency resolution.

Execute npm start to start the UI.

Start Wildfly in Eclipse.

Build Setup
-----------
The project is a multi-module project. 
The ear project configures the Ear.
The ejb project builds the ejbs to provide the data for the rest service.
The war project builds the war. Jax-Rs provides the rest services to serve the data of the ejbs. Npm install provides the dependency resolution for Angular Cli. Angular Cli builds a tree shaken, uglified bundle. Angular Cli runs the tests with Karma on PhantomJS and breaks the build if they fail.
The ear is built.

Project Goals
------------
To provide a maven project that can be build on continous build server and provide an optimized ui bundle. 
To provide a development setup that provides fast feedback on the java ee server and on the UI. 
The fast feedback on the rest service is provided by the wildfly integration in eclipse.
The fast feedback on the ui is provided by the angular cli integration. Angular Cli is used to provide the ui with fast feedback on change. Angular Cli can generate components, pipes and more. It builds Typescript into Javascript. It provides test wrappers and can execute them. 

With such a setup Angular can be used on Java EE environment.

Testing the Ear
---------------
To provide a method to test the Ear of the Build a Docker Image can be build. The image uses Wildfly and deploys the Ear in the server. The server can then be started and the optimized build can be tested. That enables testing the Ear without changing the setup of the development environment. Docker Images of other Application Servers are availiable and provide the opportunity to test the Ear on different platforms. Images for Wildfly and Websphere Liberty can be build.
