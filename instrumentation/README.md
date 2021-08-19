# Java Instrumentation Demo

JDK 8

## static load
First change to the agent directory, then execute the following command

`cd xxx/agent`

`java -javaagent:target/agent-1.0.jar -jar ..\application\target\application-1.0.jar StartMyAtmApplication 100 1000`

## dynamic load
First change to the application directory, then execute the following command

Tips:  JAVA_HOME path
- Windows: %JAVA_HOME%
- Linux: $JAVA_HOME

`cd xxx/agent`

`java -jar ../application/target/application-1.0.jar StartMyAtmApplication 5000 5000`

-----------------------------

`cd xxx/application`

`java -Xbootclasspath/a:"%JAVA_HOME%\lib\tools.jar" -jar target\application-1.0.jar LoadAgent`
