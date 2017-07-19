# Purpose

This is the basis-archetype for all Microservices developed at Synoa. 



# Release

## Stable 

* 1.0.1



# How to use the archetype

## Basic

* Clone this repository
* Go into "genisys.archetype"
* Execute `mvn clean install`
* Generate your Microservice by executing `mvn archetype:generate -DarchetypeGroupId=de.synoa.genisys -DarchetypeArtifactId=archetype`

## Extended

You can find an extended how to on our [internal confluence](https://synoagmbh.atlassian.net/wiki/spaces/GENISYS/pages/1971278/How+to+create+a+Synoa+Microservice).



# How to update the archetype

* Make your changes
* Change the version in `pom.xml` to the same version as your git tag`
* Tag your new version `git tag -a `
* If this is a stable release: Update the `README.md`
