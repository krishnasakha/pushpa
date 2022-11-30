set projectLocation=C:\Users\sakha\eclipse-workspace\SeleniumExample
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\lib\*;
java org.testng.TestNG testng.xml
pause
