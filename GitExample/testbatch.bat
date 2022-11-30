set projectLocation=C:\Users\sakha\eclipse-workspace\testing
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\lib\*;
java org.testng.TestNG TestFrames.xml
pause
