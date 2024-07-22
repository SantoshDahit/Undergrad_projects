@echo off
color 0a

:username
cls
set /p input=Username:
if %input%==admin goto password
goto password
cls


:password
cls
set /p input=Password:
if %input%==neeta goto menu
goto password
cls

:menu
findstr /v "Neeta" Menu.txt
set /p input=/
if %input% ==1 date/time
if %input% ==2 ipconfig
if %input% ==3 calc
if %input% ==4 start.
if %input% ==5 "C:\Program Files\Google\Chrome\Application\chrome.exe"
if %input% ==6 msinfo32
if %input% ==7 notepad
if %input% ==8 regedit
if %input% ==9 ncpa.cpl
if %input% ==10 devmgmt.msc
if %input% ==11 control
if %input% ==0 exit
goto menu
cls
