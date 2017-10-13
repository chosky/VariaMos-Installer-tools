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
# Modifications: 1. 13/10/17: Adding yhe instalation of Homebrew for the efective maintenance.
#                

function append_to_file {
    echo $1 >> $2
}

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
    echo "Java is installed"
else
    brew cask install java
fi

# Check if SWI-Prolog is installed or install
if [ -a /usr/bin/swipl ]; then
    echo "SWI-Prolog is installed"
else
    #curl http://www.swi-prolog.org/download/stable/bin/SWI-Prolog-7.2.3.dmg > SWI-Prolog-7\.2\.3.dmg
    brew tap homebrew/x11
    brew install swi-prolog
    sudo cp /Applications/SWI-Prolog.app/Contents/swipl/lib/x86_64-darwin14.3.0/* /usr/local/lib/
fi
 
echo "The last step is to download VariaMos in this link (PLEASE DOWNLOAD THE LAST VERSION): "
echo "https://variamos.com/home/variamos/downloads/" 
echo "You can opened VariaMos every moment you want with only one click or executing: "
echo "    source variamos-launcher-iOS.sh variamos-<<version of VariaMos>>.jar "
echo "ENJOY IT :) "