#!/bin/sh

case $1 in
	"serial")
		git checkout serial
		echo "compilando a resolucion 'serial'..."
		javac src/filosofos/Main.java
		jar cvfm bin/filosofos.jar src/mymanifest src/filosofos/*.class
		rm src/filosofos/*.class
		echo "... OK"
		;;
	"varios-turnos")
		git checkout varios-turnos
		echo "compilando a resolucion 'varios turnos'..."
		javac src/filosofos/Main.java
		jar cvfm bin/filosofos.jar src/mymanifest src/filosofos/*.class
		rm src/filosofos/*.class
		echo "... OK"
		;;
	"cola-de-tenedores")
		git checkout cola-de-tenedores
		echo "compilando a resolucion 'cola de tenedores'..."
		javac src/filosofos/Main.java
		jar cvfm bin/filosofos.jar src/mymanifest src/filosofos/*.class
		rm src/filosofos/*.class
		echo "... OK"
		;;
	*)
        echo "Usar:"
	    echo "	./build serial"
	    echo "	./build varios-turnos"
	    echo "	./build cola-de-tenedores" && echo
		;;
esac
