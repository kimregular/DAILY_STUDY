@echo off
:loop
call ./gradlew clean build -i
if %errorlevel% neq 0 (
    echo Build did not exit successfully. Stopping the script.
    pause
    exit /b
)
echo Build succeeded. Retrying...
goto loop
