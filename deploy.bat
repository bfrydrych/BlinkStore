rmdir /s /q %CATALINA_HOME%\webapps\shop
del /s /q %CATALINA_HOME%\webapps\shop.war
copy /Y target\shop.war %CATALINA_HOME%\webapps