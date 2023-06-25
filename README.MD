Pattern Finder Worker - Sample project

Seperate worker app for horizontal scaling. It has configurable thread count to use multiple core vertical scaling as well. <br />
For making scanning artificially longer it has Thread.sleep(3000L) line in TextScannerImpl class. In this small sample project <br />
there is no crash recovery mechanism but every task is marked with worker name to know which tasks to revert in case of worker crash.  <br />
There is no endpoints except actuator for health checks.

It's a Spring Boot application build with Java 17 and Maven.
To build use:

```
mvn clean install
```
<br />

To run application you can use Spring Boot Plugin:

```
mvn spring-boot:run
```