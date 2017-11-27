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
# which -s brew
# if [[ $? != 0 ]]; then
#     echo "Installing Homebrew for keep Java and SWI-Proog actualiced"
#     ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
# else 
#     brew update
# fi


if uname -a 2>&1 | grep -q x86_64
then
    pcArchitecture="64"
else
    pcArchitecture="32"
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
		sudo rm -rf /Library/Java/JavaVirtualMachines/jdk*
		sudo rm -fr /Library/Internet\ Plug-Ins/JavaAppletPlugin.plugin
		sudo rm -fr /Library/PreferencesPanes/JavaControlPanel.prefPane
		sudo rm -fr ~/Library/Application\ Support/Java
	    fi    
	    
	    cd ~/Downloads
	    curl -v -j -k -L -H "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u121-b13/e9e7ea248e2c4826b92b3f075a80e441/jdk-8u121-macosx-x64.dmg > jdk-8u121-macosx-x64.dmg
	    hdiutil attach jdk-8u121-macosx-x64.dmg
	    sudo installer -pkg /Volumes/JDK\ 8\ Update\ 121/JDK\ 8\ Update\ 121.pkg -target /
	    diskutil umount /Volumes/JDK\ 8\ Update\ 121
	    rm jdk-8u121-macosx-x64.dmg	    

	fi
    else
	if [$java == "openjdk"]; then
	    echo FALTA PURGAR OPENJDK POR SER VERSION VIEJA
	else
	    sudo rm -rf /Library/Java/JavaVirtualMachines/jdk*
	    sudo rm -fr /Library/Internet\ Plug-Ins/JavaAppletPlugin.plugin
	    sudo rm -fr /Library/PreferencesPanes/JavaControlPanel.prefPane
	    sudo rm -fr ~/Library/Application\ Support/Java
	fi
	# brew cask install java
	cd ~/Downloads
	curl -v -j -k -L -H "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u121-b13/e9e7ea248e2c4826b92b3f075a80e441/jdk-8u121-macosx-x64.dmg > jdk-8u121-macosx-x64.dmg
	hdiutil attach jdk-8u121-macosx-x64.dmg
	sudo installer -pkg /Volumes/JDK\ 8\ Update\ 121/JDK\ 8\ Update\ 121.pkg -target /
	diskutil umount /Volumes/JDK\ 8\ Update\ 121
	rm jdk-8u121-macosx-x64.dmg	    

fi
else
    # brew cask install java
    cd ~/Downloads
    curl -v -j -k -L -H "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u121-b13/e9e7ea248e2c4826b92b3f075a80e441/jdk-8u121-macosx-x64.dmg > jdk-8u121-macosx-x64.dmg
    hdiutil attach jdk-8u121-macosx-x64.dmg
    sudo installer -pkg /Volumes/JDK\ 8\ Update\ 121/JDK\ 8\ Update\ 121.pkg -target /
    diskutil umount /Volumes/JDK\ 8\ Update\ 121
    rm jdk-8u121-macosx-x64.dmg	    
fi

echo "The last step is to download VariaMos in this link (PLEASE DOWNLOAD THE LAST VERSION): "
echo "https://variamos.com/home/variamos/downloads/" 
echo "You can opened VariaMos every moment you want with only one click or executing: "
echo "    source variamos-launcher-iOS.sh variamos-<<version of VariaMos>>.jar "
echo "ENJOY IT :) "
