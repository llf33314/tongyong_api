package com.gt.api.util;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * 命令工具类
 * @author psr
 * @since 2017/7/7
 * @version v1.0
 */
public class CmdUtils {

    private static Logger logger = Logger.getLogger(CmdUtils.class);

    /**
     * single
     */
    private CmdUtils(){}

    /**
     * 执行cmd命令
     * @param cmd
     * @return
     */
    public static String exec(String cmd){
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            LineNumberReader br = new LineNumberReader(new InputStreamReader(process.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null){
                logger.debug("read result line : --------------------> " + line);
                sb.append(line).append("\n");
            }
            return sb.toString();
        }catch (Exception e){
            logger.error(ExceptionUtils.getFullStackTrace(e));
        }
        return null;
    }

}
