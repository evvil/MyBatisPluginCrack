package com.rover12421.crack.mybatis.plugin;

/**
 * Created by rover12421 on 3/18/16.
 */
public class Usage {
    public static void main(String[] args) {
        System.out.println(Util.getVersionInfo());
        System.out.println("Usage:\n" +
                "1. Copy the jar file to bin directory.\n" +
                "2. Modify \"bin/idea[64].vmoptions\" file.\n" +
                "\tAppend \"-javaagent:MyBatisPluginCrack.jar\" to end line.\n" +
                "3. Run idea.[sh|bat].\n" +
                "4. If show error msg : \"Error opening zip file or JAR manifest missing : MyBatisPluginCrack.jar\"\n" +
                "please modify the jar file path to absolute path in \"bin/idea[64].vmoptions\" file.");
    }
}
