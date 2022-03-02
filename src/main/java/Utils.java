import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

/**
 * @author yagol
 * @date 2022-02-26 上午10:52
 * @desc the description of this class
 **/
public class Utils {
    public static List<File> getSolFilePaths(String dirPath) {
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".sol");
            }
        };
        return FileUtil.loopFiles(dirPath, fileFilter);
    }
}
