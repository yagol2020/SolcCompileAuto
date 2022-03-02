import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yagol
 * @date 2022-02-26 上午10:51
 * @desc the description of this class
 **/
public class Main {
    public static void main(String[] args) {
        String dir = "/home/yagol/sols_dataset/smartbugs/reentrancy";
        List<File> solFilePaths = Utils.getSolFilePaths(dir);
        for (File solFilePath : solFilePaths) {
            SolcUtils.adaptVersion(solFilePath);
            SolcUtils.compile(solFilePath, "/home/yagol/IdeaProjects/AutoCompilSolForLargeScale/src/test/resources/sol_out");
        }
    }
}
