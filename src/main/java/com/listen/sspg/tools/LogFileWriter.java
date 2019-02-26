package com.listen.sspg.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.github.pagehelper.util.StringUtil;
import org.slf4j.Logger;

public class LogFileWriter {
	private static final Logger log = LogUtil.get(LogFileWriter.class);

	// 文件输出流
	private PrintWriter writer;

	private long FILE_MAX_SIZE=100*1024*1024;

	/**
	 * 默认构造函数
	 */

	private LogFileWriter(String logFilePathName, String content) throws Exception {
		if(StringUtil.isEmpty(logFilePathName)){
        	//目录为空，设置默认目录
        	logFilePathName=System.getProperty("user.home")+File.separator+"log.log";
        }
		init(logFilePathName,content);
	}

	/**
	 * 获取LogFileWriter的唯一实例。
	 *
	 * @return
	 * @throws Exception
	 */


	public synchronized static void getLogFileWriter(String logFilePathName,String content) throws Exception {
		new LogFileWriter(logFilePathName,content);
	}

	public static String getNowDateStr(  ) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(new Date());
	}

	/**
	 * 往日志文件中写一条日志信息 为了防止多线程同时操作(写)日志文件，造成文件”死锁”。使用synchronized关键字
	 *
	 * @param logMsg
	 *            日志消息
	 */
	public synchronized void log(String logMsg) {
		this.writer.println(getNowDateStr() + ": " + logMsg);
	}

	/**
	 * 初始化LogFileWriter
	 *
	 * @throws Exception
	 */
	private void init(String logFilePathName,String content) throws Exception {
		// 如果用户没有在参数中指定日志文件名，则从配置文件中获取。
//		if (!StringUtil.isEmptyStrs(logFilePathName, content) ) {
		if (!(StringUtil.isEmpty(logFilePathName)&&StringUtil.isEmpty(content))) {
			File logFile = new File(logFilePathName);
			try {
	            if(!logFile.exists()){
	            	//不存在创建文件
	            	File fileParent = logFile.getParentFile();
	            	if(!fileParent.exists()){
	            	    fileParent.mkdirs();
	            	}
	            	logFile.createNewFile();
	            }else{
	            	//判断文件的大小
	            	 if (logFile.isFile()){
	                     @SuppressWarnings("resource")
						FileInputStream fis= new FileInputStream(logFile);
	                     FileChannel  fc= fis.getChannel();
	                     if(fc.size()>=FILE_MAX_SIZE){
	                    	 //把文件备份好，然后进行清空
	                    	 //System.out.println("把文件备份好，然后进行清空");
	                         long time = logFile.lastModified();
	                         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	                         String timeStr=formatter.format(new Date(time));
	                         if(logFilePathName.indexOf(File.separator)<0){
	                        	 if("/".equals(File.separator)){
	                        		 logFilePathName=logFilePathName.replace("\\", File.separator);
	                        	 }else if("\\".equals(File.separator)){
	                        		 logFilePathName=logFilePathName.replace("/", File.separator);
	                        	 }

	                         }
	                         //文件路径
	                         String logFile_path=logFilePathName.substring(0,logFilePathName.lastIndexOf(File.separator));
	                         //文件名称
	                         String logFile_name=logFilePathName.substring(logFilePathName.lastIndexOf(File.separator)+1,logFilePathName.lastIndexOf("."));
	                         //文件后缀
	                         String logFile_suffix=logFilePathName.substring(logFilePathName.lastIndexOf("."));
	                         //获取文件夹下的所有文件
	                         File filePath = new File(logFile_path);
	                         File files[] = filePath.listFiles();
	                         List<Integer> filenum=new ArrayList<Integer>();
	                         for (int i = 0; i < files.length; i++) {
	                             File fs = files[i];
	                             //非目录的文件
	                             if (!fs.isDirectory()) {
	                            	 if(fs.getName().indexOf(logFile_name+"-"+timeStr)>=0){
	                            		String nums= fs.getName().replace(logFile_name, "").replace(timeStr, "").replace("-", "").replace(logFile_suffix, "");
	                            		if(Integer.valueOf(nums)!=null){
	                            			filenum.add(Integer.valueOf(nums));
	                            		}
	                            	 }
	                             }
	                           }
	                         int maxnum=0;
	                         if(filenum.size()==1){
	                        	 maxnum=filenum.get(0);
	                         }else{
	                        	  for(int i = 0; i < filenum.size()-1; i++){
	 	                        	 if(filenum.get(i)!=null){
	 	                        		 maxnum=filenum.get(i);
	 	                        		 if(filenum.get(i+1)!=null){
	 			                        	if(filenum.get(i)<filenum.get(i+1)){
	 			                        		maxnum=filenum.get(i+1);
	 			                        	}
	 	                        		 }
	 		                         }
	 	                         }
	                         }
	                         FileWriter fileWriter =null;
	                         try{
		                         //System.out.println("maxnum:"+maxnum);
		                         String logFilePathNameBak=logFile_path+File.separator+logFile_name+"-"+timeStr+"-"+(maxnum+1)+logFile_suffix;

		                    	 File logFile_bak = new File(logFilePathNameBak);
		                    	 copyFileUsingFileStreams(logFile, logFile_bak);
		                    	 fileWriter =new FileWriter(logFile);
		                         fileWriter.write("");
	                         }catch (Exception ex) {
	                        	 String errmsg = "复制日志文件异常:" + logFile.getAbsolutePath();
	             				// System.out.println(errmsg);
	             				throw new Exception(errmsg, ex);
							}finally{
								if(fileWriter!=null){
									fileWriter.flush();
			                        fileWriter.close();
								}
							}
	                     }
	                     //System.out.println(fc.size()+"/"+FILE_MAX_SIZE);
	                 }
	            }

				// 其中的FileWriter()中的第二个参数的含义是:是否在文件中追加内容
				// PrintWriter()中的第二个参数的含义是：自动将数据flush到文件中
				writer = new PrintWriter(new FileWriter(logFile, true), true);
				//System.out.println("日志文件的位置：" + logFile.getAbsolutePath());

				/**
				 * 写日志
				 */

				log(content);

			} catch (IOException ex) {
				String errmsg = "无法打开日志文件:" + logFile.getAbsolutePath();
				// System.out.println(errmsg);
				throw new Exception(errmsg, ex);
			}finally{
				close();
			}
		}else{
			LogUtil.warn(log, "日志文件路径名称不能为空");
		}
	}

	private static synchronized void copyFileUsingFileStreams(File source, File dest)
	        throws IOException {
	    InputStream input = null;
	    OutputStream output = null;
	    try {

	    	if(!dest.exists()){
            	//不存在创建文件
            	File fileParent = dest.getParentFile();
            	if(!fileParent.exists()){
            	    fileParent.mkdirs();
            	}
            	dest.createNewFile();
            }
	           input = new FileInputStream(source);
	           output = new FileOutputStream(dest);
	           byte[] buf = new byte[1024];
	           int bytesRead;
	           while ((bytesRead = input.read(buf)) != -1) {
	               output.write(buf, 0, bytesRead);
	           }
	    } finally {
	        input.close();
	        output.close();
	    }
	}

	// 关闭LogFileWriter
	public void close() {
		if (writer != null) {
			writer.close();
		}
	}

	public static void main(String[] args) {
		try {
			String fileName = "d:/temp/log/a/logger.log";

			String content = "tableaA|device_number|13701010";
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < 1000000; i++) {
				sb.append(content).append(i).append(";\n");
			}
			content = sb.toString();

			LogFileWriter.getLogFileWriter(fileName,content);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}