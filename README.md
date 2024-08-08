# Cucumber + Selenium + Spring Boot POC (multi-thread)

POC implemented with the latest versions of Cucumber + Selenium + Spring Boot + JDK (21). It allows to make automated
tests for a web application (in this case the target is my portfolio https://andre-i.eu).
See also the [single-thread](https://github.com/goto-eof/andre-i-test-selenium-cucumber-spring-boot-single-thread)
version.

# Configuration

Edit `config.properties` by choosing your OS and browser:

```
# could be linux or windows
com.andreidodu.test.os=linux

# could be firefox or chrome
com.andreidodu.test.browser=chrome
```

# Run tests

Run the following command from the root of the project:

 ```
  ./gradlew clean test
 ```

# Moreover

- this application was developed on Linux and tested on Linux Ubuntu 24.04 LTS and Windows 11
- in case of browser issues, make sure that firefox or chrome were not installed from snap (but with apt-get install)
