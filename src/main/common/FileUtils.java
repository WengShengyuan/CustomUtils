package main.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


 /**
 *  Class Name: FileUtils.java
 *  Function:
 *  
 *  Modifications:   
 *  
 *  @author "blueSummer"  DateTime 2015-2-4 上午10:26:27    
 *  @version 1.0 
 */
public class FileUtils {
	
	public static List<File> getPathAllFileExt(String path,String ext){
		List<File> list =new ArrayList<File>();
		
		getPathAllFileExt(list,path,ext);
		
		return list;
		
	}
	
	public  static  List<File> getPathAllFileExt(List<File> list,String path,String ext){
		
		File dir=new File(path);
		if(dir.isDirectory()==false)
			return list;
		
		File[] files=dir.listFiles();
		
	if(files==null||files.length<1)
		return list;
	for(File f:files){
		if(f.isDirectory()){
			String dirPath=f.getAbsolutePath();
			getPathAllFileExt(list,dirPath,ext);
		}else{
			if(f.getName().endsWith(ext)){
				
				list.add(f);
			}
		}
	}
		
		return list;
	}
	
	
	
	 /**
	 *  Function:
	 * 
	 *  @author "blueSummer"  DateTime 2015-2-4 上午10:26:31
	 *  @param srcFile 拷贝文件
	 *  @param targetFile
	 *  @throws IOException
	 */
	public static  void copyFile(File srcFile,File targetFile) throws IOException{
		 BufferedInputStream inBuff = null;
	        BufferedOutputStream outBuff = null;
	        try {
	            // 新建文件输入流并对它进行缓冲
	            inBuff = new BufferedInputStream(new FileInputStream(srcFile));

	            // 新建文件输出流并对它进行缓冲
	            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

	            // 缓冲数组
	            byte[] b = new byte[1024 * 5];
	            int len;
	            while ((len = inBuff.read(b)) != -1) {
	                outBuff.write(b, 0, len);
	            }
	            // 刷新此缓冲的输出流
	            outBuff.flush();
	        } finally {
	            // 关闭流
	            if (inBuff != null)
	                inBuff.close();
	            if (outBuff != null)
	                outBuff.close();
	        }
	}
	
	

}
