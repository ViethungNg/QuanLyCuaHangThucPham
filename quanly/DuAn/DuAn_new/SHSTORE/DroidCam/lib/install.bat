@echo off
IF "%~1"=="" GOTO :install

echo un-install
regsvr32 /s /u "DroidCamFilter32.ax"
regsvr32 /s /u "DroidCamFilter64.ax"
goto end

:install
echo install
regsvr32 /s "DroidCamFilter32.ax"
regsvr32 /s "DroidCamFilter64.ax"

:end
exit
