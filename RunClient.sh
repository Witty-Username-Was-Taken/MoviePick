#/bin/sh
#

CLASSPATH=.:$HOME/classes/lib/gson-2.8.0.jar:'lib/*'

echo $CLASSPATH

/usr/bin/java -classpath $CLASSPATH Client
