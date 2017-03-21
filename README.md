Perspective Archetype
==========================
Please notice that the property "perspectiveName" is being used as a class name, therefore it is should follow the naming convention of a class

On Linux:
```BASH
	mvn org.apache.maven.plugins:maven-archetype-plugin:2.4 \
		-DarchetypeRepository=https://maven.open-web.nl/content/repositories/public/ \
		-DarchetypeGroupId=nl.openweb.archetype \
		-DarchetypeArtifactId=perspective-archetype \
		-DarchetypeVersion=10.2.01
```
On windows:
```BASH
	mvn org.apache.maven.plugins:maven-archetype-plugin:2.4 ^
		-DarchetypeRepository=https://maven.open-web.nl/content/repositories/public/ ^
		-DarchetypeGroupId=nl.openweb.archetype ^
		-DarchetypeArtifactId=perspective-archetype ^
		-DarchetypeVersion=10.2.01
```
