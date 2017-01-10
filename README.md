# maven-java9-jigsaw

## Install

### Step 1: Create a toolchains.xml in you Maven local repository folder .m2
Default location:
- Linux (Ubuntu): ~/.m2
- Windows: C:\Documents and Settings\{your-username}\.m2

toolchains.xml example:
    
```
<toolchains>
  <!-- JDK toolchains -->
  <toolchain>
    <type>jdk</type>
    <provides>
      <version>1.9</version>
      <vendor>oracle</vendor>
    </provides>
    <configuration>
      <jdkHome>/path/to/jdk-9</jdkHome>
    </configuration>
  </toolchain>
  <toolchain>
    <type>jdk</type>
    <provides>
      <version>1.8</version>
      <vendor>oracle</vendor>
    </provides>
    <configuration>
      <jdkHome>/path/to/jdk1.8.0_112</jdkHome>
    </configuration>
  </toolchain>
</toolchains>
```

### Step 2: Build the application
```
mvn clean package
```

### Step 3: Run the GUI or CLI
Your JAVA_HOME should be pointing to jdk9 for this
##### Run GUI
```
java --module-path libs:lib -m calculator.gui/nl.frisodobber.java9.jigsaw.calculator.gui.Main
```
##### Run CLI
``` 
java --module-path libs:lib -m calculator.cli/nl.frisodobber.java9.jigsaw.calculator.cli.Main
```


