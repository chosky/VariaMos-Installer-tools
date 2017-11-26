::
:: Created: 16/10/17
:: User: Jose David Henao Ocampo (Monitor of EDS - EAFIT)
::
:: 
:: Purpose: This program consist in an automatical instalation of VariaMos and SWI-Prolog
::          with its necesary dependecies and configuration of enviroment variables
::          for Windows.
::
:: Modifications:
:: 
@ECHO off

reg Query "HKLM\Hardware\Description\System\CentralProcessor\0" | find /i "x86" > NUL && set OS=32BIT || set OS=64BIT

FOR /f %%j in ("java.exe") DO (
    SET JAVA_HOME=%%~dp$PATH:j
)

IF %JAVA_HOME%.==. (
	ECHO "Download and install Java"
	IF %OS%==32BIT (
		START download.oracle.com/otn-pub/java/jdk/8u144-b01/090f390dda5b47b9b721c7dfaa008135/jdk-8u144-windows-i586.exe
		PAUSE
	) ELSE (
		START download.oracle.com/otn-pub/java/jdk/8u144-b01/090f390dda5b47b9b721c7dfaa008135/jdk-8u144-windows-x64.exe
		PAUSE
	)
) ELSE (
	ECHO "Java is installed"
)
PAUSE

IF EXIST "C:\Program Files\swipl" (
	ECHO "SWI-Prolog is installed"
) ELSE (
ECHO "Download and install SWI-Prolog"
	IF %OS%==32BIT (
		START www.swi-prolog.org/download/stable/bin/swipl-w32-723.exe
		PAUSE
	) ELSE (
		START www.swi-prolog.org/download/stable/bin/swipl-w64-723.exe
		PAUSE
	)
)
PAUSE

ECHO "The last step is to download VariaMos in this link (PLEASE DOWNLOAD THE LAST VERSION): "
ECHO "https://variamos.com/home/variamos/downloads/" 
ECHO "You can opened VariaMos every moment you want with only one click or executing: "
ECHO "    variamos-launcher-Windows.bat variamos-<<version of VariaMos>>.jar "
ECHO "ENJOY IT :) "
PAUSE