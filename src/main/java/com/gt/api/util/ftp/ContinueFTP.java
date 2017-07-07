package com.gt.api.util.ftp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

import com.gt.api.util.CharacterUtils;





/**
 * 支持断点续传的FTP实用类
 * 测试一下
 * @author lfx
 * @version 0.1 实现基本断点上传下载
 * @version 0.2 实现上传下载进度汇报
 * @version 0.3 实现中文目录创建及中文文件创建，添加对于中文的支持
 */
public class ContinueFTP
{
    private static final Logger logger = Logger.getLogger(ContinueFTP.class);

    public FTPClient ftpClient = new FTPClient();


    public ContinueFTP()
    {

        // 设置将过程中使用到的命令输出到控制台
        // this.ftpClient.addProtocolCommandListener(new
        // PrintCommandListener(new PrintWriter(System.out)));
    }

    /**
     * 连接到FTP服务器
     *
     * @param hostname 主机名
     * @param port 端口
     * @param username 用户名
     * @param password 密码
     * @return 是否连接成功
     * @throws IOException
     */
    public boolean connect(String hostname, int port, String username, String password)
            throws Exception
    {

        try
        {
            ftpClient.connect(hostname, port);
        }
        catch (Exception e)
        {
            throw new Exception("登陆异常，请检查主机端口");
        }
        ftpClient.setControlEncoding("GBK");
        if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode()))
        {
            if (ftpClient.login(username, password))
            {
                return true;
            }
        }
        return false;
    }

    public String[] getFileList(String filedir)
            throws IOException
    {
        ftpClient.enterLocalPassiveMode();

        FTPFile[] files = ftpClient.listFiles(filedir);

        String[] sfiles = null;
        if (files != null)
        {
            sfiles = new String[files.length];
            for (int i = 0; i < files.length; i++)
            {
                // System.out.println(files[i].getName());
                sfiles[i] = files[i].getName();
            }
        }
        return sfiles;
    }

    /**
     * 从FTP服务器上下载文件,支持断点续传，上传百分比汇报
     *
     * @param local 本地文件路径
     * @return 上传的状态
     * @throws IOException
     */
    public Map<String, Object> download(String local,String ftpIp,String FtpPort,String FtpUser,String FtpPwd)
            throws Exception
    {
        connect(ftpIp, CharacterUtils.toInteger(FtpPort), FtpUser,FtpPwd);
        Boolean is_connect=connect(ftpIp, CharacterUtils.toInteger(FtpPort), FtpUser,FtpPwd);
        Map<String, Object> resultMap=new HashMap<>();
        if(is_connect==false){
            resultMap.put("filesPath", "");
            resultMap.put("code",  DownloadStatus.Download_From_Break_Success);
            return resultMap;
        }

        // 设置被动模式
        ftpClient.enterLocalPassiveMode();

        // 设置以二进制方式传输
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.enterLocalActiveMode();
        // 检查远程文件是否存在
        FTPFile[] files = ftpClient.listFiles();
        List<String> filesPath=new ArrayList<String>();
        for (FTPFile ftpFile : files) {
            String localPath=local+"/"+ftpFile.getName();
            long lRemoteSize = files[0].getSize();
            File f = new File(localPath);
            // 本地存在文件，进行断点下载
            if (f.exists())
            {
                long localSize = f.length();
                // 判断本地文件大小是否大于远程文件大小
                if (localSize > lRemoteSize)
                {
                    logger.info("本地文件大于远程文件，下载中止");
                    resultMap.put("code",  DownloadStatus.Local_Bigger_Remote);
                    return resultMap;
                }
//
                // 进行断点续传，并记录状态
                FileOutputStream out = new FileOutputStream(f, true);
                // 找出本地已经接收了多少
                ftpClient.setRestartOffset(localSize);
                InputStream in = ftpClient.retrieveFileStream(new String(ftpFile.getName().getBytes("GBK"), "iso-8859-1"));
//                   InputStream in = ftpClient.retrieveFileStream(new String(remote.getBytes("GBK"), "iso-8859-1"));
                try
                {
                    byte[] bytes = new byte[1024];
                    // 总的进度
                    long step = lRemoteSize / 100;
                    long process = localSize / step;
                    int c;
                    while ((c = in.read(bytes)) != -1)
                    {
                        out.write(bytes, 0, c);
                        localSize += c;
                        long nowProcess = localSize / step;
                        if (nowProcess > process)
                        {
                            process = nowProcess;
                            if (process % 10 == 0)
                                logger.info("下载进度：" + process);
                            // TODO 更新文件下载进度,值存放在process变量中
                        }
                    }
                }
                catch (Exception e)
                {
                }
                finally
                {
                    if (in != null)
                        in.close();
                    if (out != null)
                        out.close();
                }

                // 确认是否全部下载完毕
                boolean isDo = ftpClient.completePendingCommand();
                if (isDo)
                {
                    resultMap.put("code",  DownloadStatus.Download_From_Break_Success);
                    filesPath.add(localPath);
                }
                else
                {
                    resultMap.put("code",  DownloadStatus.Download_From_Break_Failed);
                    return resultMap;
                }
            }
            else
            {
                OutputStream out = new FileOutputStream(f);
                InputStream in = ftpClient.retrieveFileStream(new String(ftpFile.getName().getBytes("GBK"), "iso-8859-1"));
                try
                {
                    byte[] bytes = new byte[1024];
                    long step = lRemoteSize / 100;
                    long process = 0;
                    long localSize = 0L;
                    int c;
                    while ((c = in.read(bytes)) != -1)
                    {
                        out.write(bytes, 0, c);
                        localSize += c;
                        long nowProcess = localSize / step;
                        if (nowProcess > process)
                        {
                            process = nowProcess;
                            if (process % 10 == 0)
                                logger.info("下载进度：" + process);
                            // TODO 更新文件下载进度,值存放在process变量中
                        }
                    }
                }
                catch (Exception e)
                {
                }
                finally
                {
                    if (in != null)
                        in.close();
                    if (out != null)
                        out.close();
                }
                boolean upNewStatus = ftpClient.completePendingCommand();
                if (upNewStatus)
                {
                    filesPath.add(localPath);
                    resultMap.put("code",  DownloadStatus.Download_New_Success);
                }
                else
                {
                    resultMap.put("code",  DownloadStatus.Download_New_Failed);
                    return resultMap;
                }
            }
        }
        resultMap.put("filesPath", filesPath);
        disconnect();
        return resultMap;
    }

    /**
     * 上传文件到FTP服务器，支持断点续传
     *
     * @param local 本地文件名称，绝对路径
     * @param remote 远程文件路径，使用/home/directory1/subdirectory/file.ext 按照Linux上的路径指定方式，支持多级目录嵌套，支持递归创建不存在的目录结构
     * @return 上传结果
     * @throws IOException
     */
    public UploadStatus upload(String local, String remote,String ftpIp,String FtpPort,String FtpUser,String FtpPwd)
            throws Exception
    {
        UploadStatus result;
        try {
            Boolean is_connect=connect(ftpIp, CharacterUtils.toInteger(FtpPort), FtpUser,FtpPwd);
            System.out.println("is_connect:"+is_connect);
            if(is_connect==false){
                return UploadStatus.Upload_From_Break_Success;
            }
            // 设置PassiveMode传输
            ftpClient.enterLocalPassiveMode();
            ftpClient.enterLocalActiveMode();
            // 设置以二进制流的方式传输
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.setControlEncoding("GBK");
            // 对远程目录的处理
            String remoteFileName = remote;
            if (remote.contains("/"))
            {
                remoteFileName = remote.substring(remote.lastIndexOf("/") + 1);
                // 创建服务器远程目录结构，创建失败直接返回
                if (CreateDirecroty(remote, ftpClient) == UploadStatus.Create_Directory_Fail)
                {
                    return UploadStatus.Create_Directory_Fail;
                }
            }

            // 检查远程是否存在文件
            FTPFile[] files = ftpClient.listFiles(new String(remoteFileName.getBytes("GBK"), "iso-8859-1"));
            if (files.length == 1)
            {
                long remoteSize = files[0].getSize();
                File f = new File(local);
                long localSize = f.length();
                if (remoteSize == localSize)
                {
                    return UploadStatus.File_Exits;
                }
                else if (remoteSize > localSize)
                {
                    return UploadStatus.Remote_Bigger_Local;
                }

                // 尝试移动文件内读取指针,实现断点续传
                result = uploadFile(remoteFileName, f, ftpClient, remoteSize);

                // 如果断点续传没有成功，则删除服务器上文件，重新上传
                if (result == UploadStatus.Upload_From_Break_Failed)
                {
                    if (!ftpClient.deleteFile(remoteFileName))
                    {
                        return UploadStatus.Delete_Remote_Faild;
                    }
                    result = uploadFile(remoteFileName, f, ftpClient, 0);
                }
            }
            else
            {
                result = uploadFile(remoteFileName, new File(local), ftpClient, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        disconnect();
        return result;
    }



    /**
     * 上传文件到FTP服务器，支持断点续传
     *
     * @param local 本地文件名称，绝对路径
     * @return 上传结果result:code:0、上传成功 -1:上传失败 msg:结果描述
     * @throws IOException
     */
    public Map<String, Object> upload(String local,String ftpIp,String FtpPort,String FtpUser,String FtpPwd)
            throws Exception
    {
        Map<String, Object> result=new HashMap<String, Object>();
        String remote=local.split("upload")[1];
        UploadStatus resultStr=upload(local, remote,ftpIp,FtpPort,FtpUser,FtpPwd);
        String msg="";
        result.put("code", -1);
        switch (resultStr) {
            case Create_Directory_Fail:
                msg="远程服务器相应目录创建失败";
                break;
            case Create_Directory_Success:
                msg="远程服务器创建目录成功";
                result.put("code", 0);
                break;
            case Upload_New_File_Success:
                msg="上传新文件成功";
                result.put("code", 0);
                break;
            case Upload_New_File_Failed:
                msg="上传新文件失败";
                break;
            case File_Exits:
                msg="文件已经存在";
                result.put("code", 0);
                break;
            case Remote_Bigger_Local:
                msg="远程文件大于本地文件";
                break;
            case Upload_From_Break_Success:
                msg="断点续传成功";
                result.put("code", 0);
                break;
            case Upload_From_Break_Failed:
                msg="断点续传失败";
                break;
            case Delete_Remote_Faild:
                msg="删除远程文件失败";
                break;
            default:
                msg="ftp上传异常，请联系管理员";
                break;
        }
        result.put("msg", msg);
        return result;
    }

    /**
     * 断开与远程服务器的连接
     *
     * @throws IOException
     */
    public void disconnect()
            throws IOException
    {
        if (ftpClient.isConnected())
        {
            ftpClient.disconnect();
        }
    }

    /**
     * 递归创建远程服务器目录
     *
     * @param remote 远程服务器文件绝对路径
     * @param ftpClient FTPClient对象
     * @return 目录创建是否成功
     * @throws IOException
     */
    public UploadStatus CreateDirecroty(String remote, FTPClient ftpClient)
            throws IOException
    {
        UploadStatus status = UploadStatus.Create_Directory_Success;
        String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
        if (!directory.equalsIgnoreCase("/")
                && !ftpClient.changeWorkingDirectory(new String(directory.getBytes("GBK"), "iso-8859-1")))
        {
            // 如果远程目录不存在，则递归创建远程服务器目录
            int start = 0;
            int end = 0;
            if (directory.startsWith("/"))
            {
                start = 1;
            }
            else
            {
                start = 0;
            }
            end = directory.indexOf("/", start);
            while (true)
            {
                String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), "iso-8859-1");
                if (!ftpClient.changeWorkingDirectory(subDirectory))
                {
                    if (ftpClient.makeDirectory(subDirectory))
                    {
                        ftpClient.changeWorkingDirectory(subDirectory);
                    }
//                    else
//                    {
//                        System.out.println("创建目录失败");
//                        return UploadStatus.Create_Directory_Fail;
//                    }
                }

                start = end + 1;
                end = directory.indexOf("/", start);

                // 检查所有目录是否创建完毕
                if (end <= start)
                {
                    break;
                }
            }
        }
        return status;
    }

    /**
     * 上传文件到服务器,新上传和断点续传
     *
     * @param remoteFile 远程文件名，在上传之前已经将服务器工作目录做了改变
     * @param localFile 本地文件File句柄，绝对路径
     * @param processStep 需要显示的处理进度步进值
     * @param ftpClient FTPClient引用
     * @return
     * @throws IOException
     */
    public UploadStatus uploadFile(String remoteFile, File localFile, FTPClient ftpClient, long remoteSize)
            throws IOException
    {
        UploadStatus status;
        // 显示进度的上传
        long step = localFile.length() / 100;
        long process = 0;
        long localreadbytes = 0L;
        RandomAccessFile raf = new RandomAccessFile(localFile, "r");
        OutputStream out = ftpClient.appendFileStream(new String(remoteFile.getBytes("GBK"), "iso-8859-1"));
        // 断点续传
        if (remoteSize > 0)
        {
            ftpClient.setRestartOffset(remoteSize);
            process = remoteSize / step;
            raf.seek(remoteSize);
            localreadbytes = remoteSize;
        }
        byte[] bytes = new byte[1024];
        int c;
        while ((c = raf.read(bytes)) != -1)
        {
            out.write(bytes, 0, c);
            localreadbytes += c;
            if (localreadbytes / step != process)
            {
                process = localreadbytes / step;
                System.out.println("上传进度:" + process);
                // TODO 汇报上传状态
            }
        }
        out.flush();
        raf.close();
        out.close();
        boolean result = ftpClient.completePendingCommand();
        if (remoteSize > 0)
        {
            status = result ? UploadStatus.Upload_From_Break_Success : UploadStatus.Upload_From_Break_Failed;
        }
        else
        {
            status = result ? UploadStatus.Upload_New_File_Success : UploadStatus.Upload_New_File_Failed;
        }

        return status;
    }

    /*public static void main(String[] args)
    {
        ContinueFTP myFtp = new ContinueFTP();
        try
        {
            myFtp.upload("E:/lfx/dev/materal/4.jpg", "4.jpg");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("连接FTP出错：" + e.getMessage());
        }
    }*/

    public enum DownloadStatus
    {
        Remote_File_Noexist, // 远程文件不存在
        Local_Bigger_Remote, // 本地文件大于远程文件
        Download_From_Break_Success, // 断点下载文件成功
        Download_From_Break_Failed, // 断点下载文件失败
        Download_New_Success, // 全新下载文件成功
        Download_New_Failed; // 全新下载文件失败
    }

    public enum UploadStatus
    {
        Create_Directory_Fail, // 远程服务器相应目录创建失败
        Create_Directory_Success, // 远程服务器闯将目录成功
        Upload_New_File_Success, // 上传新文件成功
        Upload_New_File_Failed, // 上传新文件失败
        File_Exits, // 文件已经存在
        Remote_Bigger_Local, // 远程文件大于本地文件
        Upload_From_Break_Success, // 断点续传成功
        Upload_From_Break_Failed, // 断点续传失败
        Delete_Remote_Faild; // 删除远程文件失败
    }
}
