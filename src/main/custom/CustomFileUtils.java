package main.custom;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomFileUtils extends org.apache.commons.io.FileUtils {

	/**
	 * 
	 * Function:移动文件夹(文件也行),路径写到真正移动的文件夹为止(也可用作改名)
	 * 
	 * @author WengShengyuan DateTime 2015-1-29 下午4:58:04
	 * @param srcFile
	 *            原文件夹
	 * @param destPath
	 *            目标文件夹
	 * @return
	 */
	public static boolean moveFolder(String srcFile, String destPath) {
		try {
			org.apache.commons.io.FileUtils.copyDirectory(new File(srcFile),
					new File(destPath), false);
			deleteDir(srcFile);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 文件重命名，如 a.jpg -> a_tail.jpg
	 * @param file	源文件 a.jpg
	 * @param tail	重命名后缀"tail"
	 * @param deleteOriFile	是否删除源文件 a.jpg
	 * @return a_tail.jpg
	 */
	public static File renameFileAddTail(File file, String tail, boolean deleteOriFile) {
		try{
			File newFile = new File(String.format("%s%s%s_%s.%s", file.getParent(),File.separator,CustomFileUtils.getFileNameNoExt(file.getName()),tail,CustomFileUtils.getFileExt(file.getName())));
			org.apache.commons.io.FileUtils.copyFile(file, newFile);
			if(deleteOriFile){
				file.delete();
			}
			return newFile;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * Function:复制文件夹,路径写到真正复制的文件夹
	 * 
	 * @author WengShengyuan DateTime 2015-1-29 下午4:58:22
	 * @param srcfile
	 *            原文件夹
	 * @param destfile
	 *            目标文件夹
	 * @throws IOException
	 */
	public static boolean copyFolder(String srcfile, String destfile)
			throws IOException // 使用FileInputStream和FileOutStream
	{
		try {
			org.apache.commons.io.FileUtils.copyDirectory(new File(srcfile),
					new File(destfile), false);

			return true;
		} catch (IOException e) {
			throw e;
		}

	}

	/**
	 * 
	 * Function:获取当前文件夹下的后缀名匹配的文件
	 * 
	 * @author WengShengyuan DateTime 2015-1-29 下午4:06:15
	 * @param path	文件夹路径
	 * @param ext	查找的后缀名
	 * @return		文件列表
	 */
	public static List<File> getCurrentPathFileExt(String path, String ext) {
		List<File> list = new ArrayList<File>();
		File dir = new File(path);
		if (dir.isDirectory() == false)
			return null;
		File[] files = dir.listFiles();
		for (File f : files) {
			if (f.getName().endsWith(ext)) {
				list.add(f);
			}
		}
		return list;
	}
	
	
	/**
	 * 
	 * Function:获取当前文件夹下的文件名匹配的文件
	 * 
	 * @author WengShengyuan DateTime 2015-1-29 下午4:06:15
	 * @param path 文件名
	 * @param Name 匹配文件名
	 * @return		文件列表
	 */
	public static List<File> getCurrentPathFileName(String path, String Name) {
		List<File> list = new ArrayList<File>();
		File dir = new File(path);
		if (dir.isDirectory() == false)
			return null;
		File[] files = dir.listFiles();
		for (File f : files) {
			if (CustomFileUtils.getFileNameNoExt(f.getName()).equals(Name) && !f.isDirectory()) {
				list.add(f);
			}
		}
		return list;
	}

	/**
	 * 从指定目录中找出指定后缀、文件名的文件(文件名完全匹配)
	 * 
	 * @param path
	 *            文件夹路径
	 * @param ext
	 *            匹配的后缀，不限则""
	 * @param matchName
	 *            匹配的文件名
	 * @return	文件列表
	 */
	public static List<File> getPathAllFileExt(String path, String ext,
			String matchName) {
		List<File> returnList = new ArrayList();
		for (File f : main.common.FileUtils.getPathAllFileExt(path, "")) {
			if (f.isFile()) {
				if (getFileNameNoExt(f.getName()).equals(matchName)) {
					returnList.add(f);
				}
			}

		}
		return returnList;
	}
	
	
	/**
	 * 从指定目录中找出指定后缀、文件名的文件(文件名不完全匹配)
	 * 
	 * @param path
	 *            文件夹路径
	 * @param ext
	 *            匹配的后缀，不限则""
	 * @param matchName
	 *            匹配的文件名
	 * @return		文件列表
	 */
	public static List<File> getPathAllLikeFileExt(String path, String ext,
			String matchName) {
		List<File> returnList = new ArrayList();
		for (File f : main.common.FileUtils.getPathAllFileExt(path, "")) {
			if (f.isFile()) {
				if (getFileNameNoExt(f.getName()).contains(matchName)) {
					returnList.add(f);
				}
			}

		}
		return returnList;
	}

	/**
	 * 输入 文件名.后缀 输出时去掉后缀
	 * 
	 * @param fullName
	 *            文件全名  ABC.exe
	 * @return	   文件名 ABC
	 */
	public static String getFileNameNoExt(String fullName) {
		if (fullName.contains(".")) {
			try {

				return fullName.substring(0, fullName.lastIndexOf("."));
			} catch (Exception e) {
				return "";
			}
		} else {
			return fullName;
		}

	}

	/**
	 * 输入 文件名.后缀 输出后缀
	 * 
	 * @param fullName
	 *            文件全名 ABC.jpg
	 * @return	后缀	jpg
	 */
	public static String getFileExt(String fullName) {
		if (fullName.contains(".")) {
			try {
				return fullName.substring(fullName.lastIndexOf(".") + 1);
			} catch (Exception e) {
				return "";
			}
		} else {
			return "";
		}

	}

	/**
	 * 递归删除文件夹下所有文件
	 * 
	 * @param dirPath
	 *            文件夹路径
	 * @return	boolean 
	 */
	public static boolean deleteDir(String dirPath) {
		File dir = new File(dirPath);
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i])
						.getPath());
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return dir.delete();
	}

	/**
	 * 根据文件路径得到所在文件夹的路径，如果本身就是文件夹，则返回上一级
	 * 
	 * @param dirPath
	 *            文件或文件夹的路径
	 * @return	The pathname string of the parent directory named by this abstract pathname, or null if this pathname does not name a parent
	 */
	public static String getFileLocation(String dirPath) {
		File inFile = new File(dirPath);

		try {
			return inFile.getParent();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}