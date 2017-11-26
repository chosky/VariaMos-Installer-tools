#!/bin/bash

# NOTE: Only do this if is your first time configuring Variamos
# Created: 12/10/17
# User: Jose David Henao Ocampo (Monitor of EDS - EAFIT)
#
# 
# Purpose: This program consist in an automatical instalation of VariaMos and SWI-Prolog
#          with its necesary dependecies and configuration of enviroment variables
#          for iOS.
#
# Modifications: 1. 13/10/17: Adding the instalation of Homebrew for the efective maintenance.
#                2. 26/11/17: Change all the code, the new code only verify and configure Java                

declare java;
declare javaVersion;
declare javaArchitecture;
declare pcArchitecture;

# Installing Homebrew to keep the aplications up-to-date
which -s brew
if [[ $? != 0 ]]; then
    echo "Installing Homebrew for keep Java and SWI-Proog actualiced"
    ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
else 
    brew update
fi

# Check if Java is installed or install
if [ -a /usr/bin/java ]; then 
    if java -version 2>&1 | grep -q open
    then
	java="openjdk"
    else
	java="java"
    fi

    if java -version 2>&1 | grep -q 1.8
    then
	if java -version 2>&1 | grep -q 32-Bit
	then
            javaArchitecture="32"
	else
    	    javaArchitecture="64"
	fi
	
	if [ $pcArchitecture != $javaArchitecture ]; then
	    if [$java == "openjdk"]; then
		echo FALTA PURGAR OPENJDK POR NO SER DE LA MISMA ARQUITECTURA
	    else
		echo FALTA PURGAR JAVA POR NO SER DE LA MISMA ARQUITECTURA
	    fi    
	    echo INSTALAR JAVA(ULTIMA VERSION CON ARQUITECTURA CORRECTA) CON RESPECTO A LA ARQUITECTURA
	fi
    else
	if [$java == "openjdk"]; then
	    echo FALTA PURGAR OPENJDK POR SER VERSION VIEJA
	else
	    echo FALTA PURGAR JAVA POR SER VERSION VIEJA
	fi    
	echo INSTALAR JAVA(ULTIMA VERSION) CON RESPECTO A LA ARQUITECTURA
    fi
else
    # no se si usar breo porque puede traer problemas o
    # descargarlo directamente con curl RECORDAR QUE ESTO DEPENDE DE LA ARQUITECTURA
    # cd ~/Downloads
    # curl -v -j -k -L -H "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u121-b13/e9e7ea248e2c4826b92b3f075a80e441/jdk-8u121-macosx-x64.dmg > jdk-8u121-macosx-x64.dmg
    # hdiutil attach jdk-8u121-macosx-x64.dmg
    # sudo installer -pkg /Volumes/JDK\ 8\ Update\ 121/JDK\ 8\ Update\ 121.pkg -target /
    # diskutil umount /Volumes/JDK\ 8\ Update\ 121
    # rm jdk-8u121-macosx-x64.dmg
    brew cask install java
fi

echo "The last step is to download VariaMos in this link (PLEASE DOWNLOAD THE LAST VERSION): "
echo "https://variamos.com/home/variamos/downloads/" 
echo "You can opened VariaMos every moment you want with only one click or executing: "
echo "    source variamos-launcher-iOS.sh variamos-<<version of VariaMos>>.jar "
echo "ENJOY IT :) "
