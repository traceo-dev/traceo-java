# Traceo SDK for Java

Library for integration with the [Traceo Platform](https://github.com/traceo-dev/traceo).

### Installation
To install this SDK add this package to your `pom.xml` like below:

```java
<dependency>
    <groupId>org.traceo</groupId>
    <artifactId>traceo-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

or to `build.gradle`

```java
dependencies {
    implementation 'org.traceo.sdk:traceo-sdk:1.0.0'
}
```

### Usage
To init SDK in your project use internal `TraceoClientBuilder` to create client configuration and pass configurations to static `init()` method from `TraceoClient` class.
```java
TraceoClientConfiguration clientConfiguration = TraceoClientBuilder
           .standard()
           .withApiKey("tr_408917b2-42fb-43d9-8602-861879c1a273")
           .withHost("http://localhost:3000")
           .build();

TraceoClient.init(clientConfiguration);
```

Table of available options in `TraceoClientBuilder`:

|   Method   |                                                                   Description                                                                   | Default | Required |
|:----------:|:-----------------------------------------------------------------------------------------------------------------------------------------------:|:-------:|:--------:|
| withApiKey |                                                  Project api key generated in Traceo Platform                                                   |         |    ✔     |
|  withHost  |                           Host on which Traceo Platform is running provided in format `[protocol]://[domain]:[port]`                            |         |    ✔     |
| withDebug  |                                             Set to true if you want to check internal logs for SDK.                                             |  false  |    ❌     |
| withEnabled |             If false then client is not initialized. None of the captured exceptions, spans or metrics are send to Traceo Platform.             |  true   |     ❌     |
| withCatchUncaughtException |                                           Set to true if you want to catch every uncaught exception.                                            |  false  |     ❌     |
| withPackages | List of packages where SDK is used. Based on this values, SDK can check wheter incoming exception is inside client code or in external library. |         |     ❌     |
| withExportIntervalMs |             Set custom value for export default metrics interval.          |  5000   |     ❌     |

### Incidents handling

Incidents are all the exceptions and other problems that occur in your application. After each exception occurs, the Traceo SDK catches the exception and sends it to the Traceo Platform. To catch an exception and send it to the Traceo platform, use the static `catchException()` method from the `TraceoClient` class. 

```java
try {
    // your code
} catch (ArrayIndexOutOfBoundsException exception) {
    TraceoClient.catchException(exception);
}
```

### Logger

The Traceo SDK can be used also as a logger. Each log is saved on the Traceo Platform, thanks to which it is possible to later easily access the recorded information. Logs are sent to Traceo in every 180 seconds.
Example of using logger:
```java
public class HttpClient {
    public static final TraceoLogger LOGGER = TraceoClient.getLogger(HttpClient.class);

    // your code

    public void makeRequest() {
        // your code

        LOGGER.log("OK.");
    }
}
```

Available logger methods:

| Method  |                                                                                                                                                                                                                                                       Description                                                                                                                                                                                                                                                       |
|:-------:|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
|   log   |                                                                        [The INFO level designates informational messages that highlight the progress of the application at coarse-grained level.](https://www.slf4j.org/api/org/apache/log4j/Level.html#:~:text=INFO-,The%20INFO%20level%20designates%20informational%20messages%20that%20highlight%20the%20progress%20of%20the%20application%20at%20coarse%2Dgrained%20level.,-static%20Level)                                                                         |
|  debug  |                                                                                       [The DEBUG Level designates fine-grained informational events that are most useful to debug an application.](https://www.slf4j.org/api/org/apache/log4j/Level.html#:~:text=DEBUG-,The%20DEBUG%20Level%20designates%20fine%2Dgrained%20informational%20events%20that%20are%20most%20useful%20to%20debug%20an%20application.,-static%20Level)                                                                                       |
|  error  |                                                                                               [The ERROR level designates error events that might still allow the application to continue running.](https://www.slf4j.org/api/org/apache/log4j/Level.html#:~:text=ERROR-,The%20ERROR%20level%20designates%20error%20events%20that%20might%20still%20allow%20the%20application%20to%20continue%20running.,-static%20Level)                                                                                               |
| warning |                                                                            [The WARN level designates potentially harmful situations.](https://www.slf4j.org/api/org/apache/log4j/Level.html#:~:text=WARN-,The%20WARN%20level%20designates%20potentially%20harmful%20situations.,-static%20int)                                                                                                                                                                                                                         |

**TIP:** Remember to init `TraceoClient` before using `TraceoLogger`.
### More
This package is also required to integration with `OpenTelemetry for Java`. Full implementation guide can be found [here](https://github.com/traceo-dev/traceo-java/blob/master/traceo-sdk-opentelemetry/README.md).

## Support
Feel free to create Issues, Pull Request and Discussion. If you want to contact with the developer working on this package click [here](mailto:piotr.szewczyk.software@gmail.com).
