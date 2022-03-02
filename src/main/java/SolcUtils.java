import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;


/**
 * @author yagol
 * @date 2022-02-26 上午10:57
 * @desc solc compiler utils
 **/
public class SolcUtils {
    private static final String DEFAULT_SOLC_VERSION = "0.4.24";
    private static final String SOLC_BIN_PATH = "/home/yagol/anaconda3/envs/solc_commons/bin/solc";
    private static final String SOLC_SELECT_BIN_PATH = "/home/yagol/anaconda3/envs/solc_commons/bin/solc-select";


    public static String getCurrentVersion() {
        String outputStr = RuntimeUtil.execForStr(SOLC_BIN_PATH, "--version");
        /* example: solc, the solidity compiler commandline interface\nVersion: 0.5.1+commit.c8a2cb62.Linux.g++ */
        return StrUtil.splitTrim(StrUtil.splitTrim(outputStr, "Version:").get(1), "+").get(0);
    }

    private static void selectVersion(String version) {
        String outputStr = RuntimeUtil.execForStr(SOLC_SELECT_BIN_PATH, "use", version);
    }

    public static void adaptVersion(File solFile) {
        String version = DEFAULT_SOLC_VERSION;
        List<String> outputList = RuntimeUtil.execForLines(SOLC_BIN_PATH, solFile.getAbsolutePath());
        for (int i = 0; i < outputList.size(); i++) {
            if (StrUtil.contains(outputList.get(i), "Source file requires different compiler version")) {
                version = StringUtils.split(outputList.get(1).replace("pragma solidity", "").replace(";", "").trim(), "^>=<")[0];
                version = version.trim();
                break;
            }
        }
        if (!StrUtil.equals(version, DEFAULT_SOLC_VERSION)) {
            selectVersion(version);
        }
    }

    public static void compile(File solFile, String outputDir) {
        String outputStr = RuntimeUtil.execForStr(SOLC_BIN_PATH,
                "--output-dir", outputDir + File.separator + solFile.getName().replace(".sol", ""),
                "--bin",
                "--bin-runtime",
                "--abi",
                "--ast-json",
                "--opcodes",
                "--asm",
                "--overwrite",
                solFile.getAbsolutePath());
        if (StrUtil.contains(outputStr, "Error")) {
            Log.get().error("find compile error in [" + solFile.getAbsolutePath() + "]");
        }
    }
}