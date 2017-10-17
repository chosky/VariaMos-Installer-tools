export SWI_HOME_DIR=/Applications/SWI_Prolog.app/Contents/swipl
export PATH=$PATH:$SWI_HOME_DIR/lib/:$SWI_HOME_DIR/lib/jpl.jar
export CLASSPATH=$SWI_HOME_DIR/lib/:$SWI_HOME_DIR/lib/jpl.jar

java -Djava.library.path=$SWI_HOME_DIR:$SWI_HOME_DIR/lib/x86_64-darwin14.3.0/ -jar $1
