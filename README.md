# Solc Compile Auto Project

This project will help you automatically distinguish between `solc` versions of SOL files. When you have a large number
of SOL files that need to be compiled (for example, during testing). You can use this project, which will automatically
switch `solc` versions and automatically compile to separate folders( using `solc-select`). You can also configure the
compilation options for `solc`.

## Usage

The entry to the project is in `Java/main.java`.

```java
public class Main {
    public static void main(String[] args) {
        String dir = "/home/yagol/sols_dataset/smartbugs/reentrancy";//Enter your folder address here
        List<File> solFilePaths = Utils.getSolFilePaths(dir);
        for (File solFilePath : solFilePaths) {
            SolcUtils.adaptVersion(solFilePath);
            SolcUtils.compile(solFilePath, "/home/yagol/IdeaProjects/AutoCompilSolForLargeScale/src/test/resources/sol_out");//Enter the address where you want to store the output file here
        }
    }
}
```