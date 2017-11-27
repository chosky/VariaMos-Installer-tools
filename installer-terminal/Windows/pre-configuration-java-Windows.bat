::
:: Created: 16/10/17
:: User: Jose David Henao Ocampo (Monitor of EDS - EAFIT)
::
:: 
:: Purpose: This program consist in an automatical instalation of VariaMos and SWI-Prolog
::          with its necesary dependecies and configuration of enviroment variables
::          for Windows.
::
:: Modifications: 1. 26/11/17: Change all the code, the new code only verify and configure Java
:: 
@ECHO off

reg Query "HKLM\Hardware\Description\System\CentralProcessor\0" | find /i "x86" > NUL && set OS=32BIT || set OS=64BIT

FOR /f %%j in ("java.exe") DO (
    SET JAVA_HOME=%%~dp$PATH:j
)

java -d64 -version >nul 2>&1
IF errorlevel 1 (
	WHERE java >nul 2>&1
	SET javaArchitecture=32BIT
) ELSE (
	SET javaArchitecture=64BIT
)

IF %JAVA_HOME%.==. (
	ECHO "Download and install Java"
	IF %OS%==32BIT (
		START download.oracle.com/otn-pub/java/jdk/8u151-b12/e758a0de34e24606bca991d704f6dcbf/jdk-8u151-windows-i586.exe
		PAUSE
	) ELSE (
		START download.oracle.com/otn-pub/java/jdk/8u151-b12/e758a0de34e24606bca991d704f6dcbf/jdk-8u151-windows-x64.exe
		PAUSE
	)
) ELSE (
	java -d64 -version >nul 2>&1
	IF errorlevel 1 (
		WHERE java >nul 2>&1
		SET javaArchitecture=32BIT
	) ELSE (
		SET javaArchitecture=64BIT
	)

	IF NOT %javaArchitecture%==%OS% (
	
		wmic product where "name like 'Java%%'" call uninstall /nointeractive
		
		ECHO "Download and install Java"
		IF %OS%==32BIT (
			START download.oracle.com/otn-pub/java/jdk/8u151-b12/e758a0de34e24606bca991d704f6dcbf/jdk-8u151-windows-i586.exe
			PAUSE
		) ELSE (
			START download.oracle.com/otn-pub/java/jdk/8u151-b12/e758a0de34e24606bca991d704f6dcbf/jdk-8u151-windows-x64.exe
			PAUSE
		)
	)
)
PAUSE

ECHO "The last step is to download VariaMos in this link (PLEASE DOWNLOAD THE LAST VERSION): "
ECHO "https://variamos.com/home/variamos/downloads/" 
ECHO "You can opened VariaMos every moment you want with only one click or executing: "
ECHO "    variamos-launcher-Windows.bat variamos-<<version of VariaMos>>.jar "
ECHO "ENJOY IT :) "
PAUSE
