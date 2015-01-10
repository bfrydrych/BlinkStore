#!/bin/bash
export CATALINA_HOME=/var/lib/tomcat8
export APP_NAME=BlinkStore
export WEB_APP_NAME=$APP_NAME.war
export WEB_APP_SOURCE_FOLDER=build/libs

sudo chown -R bart $CATALINA_HOME/webapps
rm -rf $CATALINA_HOME/webapps/$APP_NAME
rm -f $CATALINA_HOME/webapps/$WEB_APP_NAME
echo $CATALINA_HOME/webapps/$WEB_APP_NAME
cp $WEB_APP_SOURCE_FOLDER/$WEB_APP_NAME $CATALINA_HOME/webapps/$WEB_APP_NAME
