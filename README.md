Carrental: Example Using Angular with Angular Cli and Java/Jakarta EE 8 Deployed as an EAR
==============================================================================================
Author: Sven Loesekann

![Build Status](https://travis-ci.org/Angular2Guy/Angular2AndJavaEE.svg?branch=master)
[![CodeQL](https://github.com/Angular2Guy/Angular2AndJavaEE/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/Angular2Guy/Angular2AndJavaEE/actions/workflows/codeql-analysis.yml)


Technologies: EAR, Rest, Angular, Angular Cli

Summary: Example Project for the integration of Maven, Angular, Java/Jakarta EE 8

Target Project: Java/Jakarta EE 8

## Articles
* [The Recipe for Angular 2 in a Java EE Environment](https://dzone.com/articles/recipe-for-angular-2-in-a-java-ee-environment)
* [The Recipe for Angular 2 in a Java EE Environment: Rest Service](https://dzone.com/articles/the-recipe-for-angular-2-in-a-java-ee-environment)
* [The Recipe for Angular 2 in a Java EE Environment: Frontend 1](https://dzone.com/articles/the-recipe-for-angular-2-in-a-java-ee-environment-1)
* [The Recipe for Angular 2 in a Java EE Environment: Frontend 2](https://dzone.com/articles/the-recipe-for-angular-2-in-a-java-ee-environment-2)
* [The Recipe for Angular 2 in a Java EE Environment: Maven Build](https://dzone.com/articles/the-recipe-for-angular-2-in-a-java-ee-environment-3)
* [The Recipe for Angular in a Java EE Environment: Aot Build](https://dzone.com/articles/the-recipe-for-angular-in-a-java-ee-environment-ao)
* [The Recipe for Angular in a Java EE Environment: Two Improvements](https://dzone.com/articles/the-recipe-for-angular-in-a-java-ee-environment-2)
* [Angular Interceptor for a BaseHref Path in Services](https://angular2guy.wordpress.com/2021/07/31/angular-interceptor-for-a-basehref-path-in-services/)

Retirement announcement
-----------
This project is now 9 years old and the technology has moved on. Jakarta EE projects are now legacy they have been moved to Angular or will never be moved. The purpose of this project no longer exists. I will try to update to Angular 21 and after that the project will be archived. If anybody still needs it, I suggest to fork it.


Update
-----------
The project has been updated to **Jakarta EE 8** and **Java 17 minimum** on **Wildfly 26**. 


What is it?
-----------

The project shows the integration of Angular with Angular Cli as UI. The Rest services are provided by Jakarta EE 8, in this example is with WildFly. The build is done in Maven. 

Wildfly is integrated with the JBoss Plugin to provide re-deployments. Angular Cli provides the UI, redeploys the UI on change and optimizes production build.

The Ear that is build can be tested in a Docker Image that Maven builds.

Development Requirements
-------------------

Java 17 JDK or newer. 

Eclipse JEE.

WildFly 26 or newer. (Any JavaEE 8 Appserver should do)

JBoss Plugin JBoss AS, WildFly & EAP Server Tools.

Install Eclipse Plugin Eclipse Wild Web Developer of the Eclipse Marketplace.

Maven 3.5.4 or newer. 

Nodejs 18.19.x or newer 

Npm 22.x.x or newer

Angular Cli 20 or newer. 

Build Server Requirements
-------------------------
Java 17 JDK or newer. 

Maven 3.8.x or newer. 

Nodejs 22.x.x 

Npm 10.2.x 

Setup
-----
JDK 17, Wildfly 23(or an other Jakarta EE8 server), Nodejs, Eclipse, Maven must be installed.

Install Angular Cli "npm install -g angular-cli@latest".

Install Eclipse JBoss Plugin "http://download.jboss.org/jbosstools/oxygen/stable/updates/"

Add the server runtime environment for Wildfly and connect it to the WildFly installation.

Install Eclipse Plugin Eclipse Wild Web Developer of the Eclipse Marketplace.

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
The war project builds the war. Jax-Rs provides the rest services to serve the data of the ejbs. Npm install provides the dependency resolution for Angular Cli. Angular Cli builds a tree shaken, uglified bundle. Angular Cli runs the tests with Karma on Chrome and breaks the build if they fail.
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
To provide a method to test the Ear of the Build a Docker Image can be build. The image uses Wildfly and deploys the Ear in the server. The server can then be started and the optimized build can be tested. That enables testing the Ear without changing the setup of the development environment. Docker Images of other Application Servers are available at [Docker Hub](https://hub.docker.com/u/angular2guy/) and provide the opportunity to test the Ear on different platforms. 

Build the Docker Image
----------------
For building the Docker Image the Docker needs to be installed and started. To build the Docker image the lines in buildDocker.sh can be used. First the ear needs to be build with 'mvnw' **Jdk 17 required** due to the wildfly base image. Then the docker command creates the image with the ear and the Docker image can be run locally.
