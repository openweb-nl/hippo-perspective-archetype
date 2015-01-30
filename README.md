Perspective Archetype
==========================
Please notice that the property "perspectiveName" is being used as a class name, therefore it is should follow the naming convention of a class

On Linux:

	mvn archetype:generate \
		-DarchetypeRepository=https://maven.open-web.nl/content/repositories/public/ \
		-DarchetypeGroupId=nl.openweb.archetype \
		-DarchetypeArtifactId=perspective-archetype \
		-DarchetypeVersion=1.0

On windows:

	mvn archetype:generate ^
		-DarchetypeRepository=https://maven.open-web.nl/content/repositories/public/ ^
		-DarchetypeGroupId=nl.openweb.archetype ^
		-DarchetypeArtifactId=perspective-archetype ^
		-DarchetypeVersion=1.0

