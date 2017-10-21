export SWI_HOME_DIR=/usr/lib/swi-prolog
export PATH=$PATH:$SWI_HOME_DIR/lib/:$SWI_HOME_DIR/lib/jpl.jar

java -jar $1
