## A Framework for testing with Karate using a Java codebase
An example of Karate with an implemented common repository integrated as a git submodule for 
complex infrastructures with very similar testing objectives striving for common goals.

### Maven build commands
- A default maven build line would look like this:

`mvn clean test -Dkarate.env=<ENVIRONMENT_NAME>`

- Or when **ONLY** wanting to run load tests with Gatling:

`mvn clean -Pgatling gatling:test -Dkarate.env=<ENVIRONMENT_NAME>`

- Or when **ALSO** wanting to run load tests with Gatling:

`mvn clean test -Pgatling gatling:test -Dkarate.env=<ENVIRONMENT_NAME>`

- Run **ALL** tests **ignoring failures** (_for development purposes **ONLY**_)

`mvn clean test -Pgatling gatling:test -Dmaven.test.failure.ignore=true`