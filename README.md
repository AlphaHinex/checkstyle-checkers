Checkstyle Checkers
===================

[![Build Status](https://travis-ci.org/AlphaHinex/checkstyle-checkers.svg?branch=master)](https://travis-ci.org/AlphaHinex/checkstyle-checkers)
[![Coverage Status](https://codecov.io/gh/AlphaHinex/checkstyle-checkers/branch/master/graph/badge.svg)](https://codecov.io/gh/AlphaHinex/checkstyle-checkers)

Customize Checkstyle checkers, inspired by https://github.com/blundell/CreateYourOwnCheckStyleCheck

Prepare
-------

Configure GitHub Packages Registry follow [GitHub Help](https://help.github.com/en/github/managing-packages-with-github-packages/using-github-packages-with-your-projects-ecosystem) or [GitHub Packages in Action](https://alphahinex.github.io/2020/01/17/github-packages-in-action/)

Install
-------

### Maven

````
<dependency>
     <groupId>io.github.alphahinex</groupId>
     <artifactId>checkstyle-checkers</artifactId>
     <version>1.0.0.RELEASE</version>
</dependency>
````

### Gradle

````
'io.github.alphahinex:checkstyle-checkers:1.0.0.RELEASE'
````

How to use
----------

### Method Limit Checker

Default limit is 30, and you can change the default value as below:

````
<module name="MethodLimit">
   <property name="max" value="50"/>
</module>
````

### Swagger Annotation Checker

Only check the swagger annotation on the controller which registered by annotation, not by xml.

* Rule #1: Check `@Api` existed on the class which has `@Controller` or `@RestController` annotation
* Rule #2: Base on Rule #1 (Has `@Api`), check `@ApiOperation` existed on the method which has annotation ending with `RequestMapping` or `Mapping`

Enable this check by:

````
<module name="SwaggerAnnotation"/>
````
