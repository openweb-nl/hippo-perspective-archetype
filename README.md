Perspective Archetype
==========================
Please notice that the property "perspectiveName" is being used as a class name, therefore it is should follow the naming convention of a class

On Linux:
```BASH
	mvn archetype:generate \
		-DarchetypeRepository=https://maven.open-web.nl/content/repositories/public/ \
		-DarchetypeGroupId=nl.openweb.archetype \
		-DarchetypeArtifactId=perspective-archetype \
		-DarchetypeVersion=10.2.01
```
On windows:
```BASH
	mvn archetype:generate ^
		-DarchetypeRepository=https://maven.open-web.nl/content/repositories/public/ ^
		-DarchetypeGroupId=nl.openweb.archetype ^
		-DarchetypeArtifactId=perspective-archetype ^
		-DarchetypeVersion=10.2.01
```
