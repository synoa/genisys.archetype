# Purpose

This is the basis-archetype for all Microservices developed at Synoa.



# Release

## Stable

* 1.2.2

## Old

You can find old releases [here](https://github.com/synoa/genisys.archetype/releases).


# How to use the archetype

## Basic

* Clone this repository
* Go into "genisys.archetype"
* Execute `mvn clean install`
* Generate your Microservice by executing `mvn archetype:generate -DarchetypeGroupId=de.synoa.genisys -DarchetypeArtifactId=archetype -DarchetypeVersion=1.2.2`

## Extended

You can find an extended how to on our [internal confluence](https://synoagmbh.atlassian.net/wiki/spaces/GENISYS/pages/1971278/How+to+create+a+Synoa+Microservice).



# How to update the archetype

Make your changes

## Testing

Execute `mvn clean install`, which builds the archetype and executes the archetype integration tests:

* Create a new service based on the settings defined in `src/test/resources/projects.basic/archetype.properties`
* Execute all maven goals as defined in `src/test/resources/projects.basic/goal.txt`

When all of that was successful you can find the generated `de.synoa.genisys.archetype.test` in `target/test-classes/projects/basic/project/test`.
There you can execute `docker-compose up` to start all docker containers and then `mvn spring-boot:run` to start the service.

## Versioning

* Change the version when you are done
    * `pom.xml` in attribute `<version>`
    * `README.md` under "Stable"
    * `README.md` under "Basic / Generate your Microservice by executing" in "-DarchetypeVersion="
    * `src/test/resources/projects.basic/archetype.properties` in key `version`
* Create a new tag with the version: `git tag -a`
