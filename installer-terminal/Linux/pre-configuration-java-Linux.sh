#!/bin/bash

#
# Created: 11/10/17
# User: Jose David Henao Ocampo (Monitor of EDS - EAFIT)
#
# 
# Purpose: This program consist in an automatical instalation of VariaMos and SWI-Prolog
#          with its necesary dependecies and configuration of enviroment variables
#          for Linux.
#
# Modifications: 13/10/17: Make the file excutor.
#                26/11/17: Change all the code, the new code only verify and configure Java


function append_to_file {
    echo $1 >> $2
}

declare osInfo;
declare java;
declare javaVersion;
declare javaArchitecture;
declare pcArchitecture;

if [ -f /etc/redhat-release ]; then
    osInfo="yum"
elif [ -f /etc/arch-release ]; then
    osInfo="pacman"
elif [ -f /etc/debian_version ]; then
    osInfo="apt-get"
fi

if uname -a 2>&1 | grep -q x86_64
then
    pcArchitecture="64"
else
    pcArchitecture="32"
fi

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
	    if [ $osInfo = 'yum' ]; then
		echo "Not yet"
	    elif [ $osInfo = 'pacman' ]; then
		echo "Not yet"
	    elif [ $osInfo = 'apt-get' ]; then
		if [$java == "openjdk"]; then
		    sudo apt-get purge openjdk-*
		else
		    sudo apt-get purge oracle-java*
		fi    
		sudo add-apt-repository ppa:webupd8team/java
		sudo apt-get update
		sudo apt-get install oracle-java8-installer
	    fi
	fi
    else
	if [$java == "openjdk"]; then
	    sudo apt-get purge openjdk-*
	else
	    sudo apt-get purge oracle-java*
	fi    
	sudo add-apt-repository ppa:webupd8team/java
	sudo apt-get update
	sudo apt-get install oracle-java8-installer
    fi
else
    if [ $osInfo = 'yum' ]; then
	echo "Not yet"
    elif [ $osInfo = 'pacman' ]; then
	echo "Not yet"
    elif [ $osInfo = 'apt-get' ]; then
	sudo add-apt-repository ppa:webupd8team/java
	sudo apt-get update
	sudo apt-get install oracle-java8-installer
    fi
fi

#append_to_file "export SWI_HOME_DIR=/usr/lib/swi-prolog" $HOME/.bashrc
#append_to_file "export PATH=$PATH:$SWI_HOME_DIR/lib/:$SWI_HOME_DIR/lib/jpl.jar" $HOME/.bashrc

echo "The last step is to download VariaMos in this link (PLEASE DOWNLOAD THE LAST VERSION): "
echo "https://variamos.com/home/variamos/downloads/" 
echo "You can opened VariaMos every moment you want with only one click or executing: "
echo "    source variamos-launcher-Linux.sh variamos-<<version of VariaMos>>.jar "
echo "ENJOY IT :) "
