package com.rails.vehicle.core.common.Utils;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import com.rails.common.logger.WifiLogUtil;
import com.rails.common.utils.FileUtils;

public class zip4jUtils {

	//分卷压缩的包大小，5M
	private static int partitionSizeInBytes = 5120000; /* N * 1024 */
	

	/**
	 * 
	 *  Function: 分卷压缩**注意!原文件不能与目的文件在同一目录
	 * 
	 *  @author WengShengyuan  DateTime 2015-1-28 下午7:13:22
	 *  @param src 需要压缩的文件夹 或 文件
	 *  @param dest 需要保存zip的全路径
	 *  @param isCreateDir 是否需要分卷
	 *  @param passwd 密码(不需要则写"")
	 *  @return 压缩包的地址列表，分卷则有多个.
	 */
	public static List splitZip(String src, String dest, Boolean isCreateDir,
			String passwd) {

		try {
			String destFolder = CustomFileUtils.getFileLocation(dest);
			//暂时先不删，用版本号控制不重复
//			if(!(destFolder==null)){
//				System.out.println("deleting Folder :"+ destFolder + "--> " + CustomFileUtils.deleteDir(CustomFileUtils.getFileLocation(dest)));
//			}
				
				
			ZipFile zipFile = new ZipFile(dest);
			File srcFile = new File(src);
			dest = buildDestinationZipFilePath(srcFile, dest);
			ArrayList filesToAdd = new ArrayList();
			ZipParameters parameters = new ZipParameters();
			if (!passwd.isEmpty()) {
				parameters.setEncryptFiles(true);
				parameters
						.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD); // 加密方式
				parameters.setPassword(passwd.toCharArray());
			}
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_ULTRA);
			try {
				if (srcFile.isDirectory()) {
					// 如果不创建目录的话,将直接把给定目录下的文件压缩到压缩文件,即没有目录结构
					if (!isCreateDir) {
						File[] subFiles = srcFile.listFiles();
						ArrayList<File> temp = new ArrayList<File>();
						Collections.addAll(temp, subFiles);
						zipFile.addFiles(temp, parameters);
						zipFile.createZipFile(temp, parameters, true,
								partitionSizeInBytes);
						return findFiles(dest);
					}
					zipFile.createZipFileFromFolder(srcFile, parameters, true,
							partitionSizeInBytes);
				} else {
					zipFile.createZipFile(srcFile, parameters, true,
							partitionSizeInBytes);
				}
				return findFiles(dest);
			} catch (ZipException e) {
				return null;
			}

		} catch (ZipException e) {
			return null;
		}
	}

	
	private static List findFiles(String dest){
		String parentFile = new File(dest).getParentFile().getPath();
		String fileNameFull = new File(dest).getName();
		String fileName = fileNameFull.substring(0, fileNameFull.lastIndexOf("."));
		
		return CustomFileUtils.getCurrentPathFileName(parentFile, fileName);
	}
	
	
	/**
	 * 使用给定密码解压指定的ZIP压缩文件到指定目录
	 * <p>
	 * 如果指定目录不存在,可以自动创建,不合法的路径将导致异常被抛出
	 * 
	 * @param zip
	 *            指定的ZIP压缩文件
	 * @param dest
	 *            解压目录
	 * @param passwd
	 *            ZIP文件的密码
	 * @return 解压后文件数组
	 * @throws ZipException
	 *             压缩文件有损坏或者解压缩失败抛出
	 */
	public static File[] unzip(String zip, String dest, String passwd)
			throws ZipException {
		File zipFile = new File(zip);
		return unzip(zipFile, dest, passwd);
	}

	/**
	 * 使用给定密码解压指定的ZIP压缩文件到当前目录
	 * 
	 * @param zip
	 *            指定的ZIP压缩文件
	 * @param passwd
	 *            ZIP文件的密码
	 * @return 解压后文件数组
	 * @throws ZipException
	 *             压缩文件有损坏或者解压缩失败抛出
	 */
	public static File[] unzip(String zip, String passwd) throws ZipException {
		File zipFile = new File(zip);
		File parentDir = zipFile.getParentFile();
		return unzip(zipFile, parentDir.getAbsolutePath(), passwd);
	}

	/**
	 * 使用给定密码解压指定的ZIP压缩文件到指定目录
	 * <p>
	 * 如果指定目录不存在,可以自动创建,不合法的路径将导致异常被抛出
	 * 
	 * @param zip
	 *            指定的ZIP压缩文件
	 * @param dest
	 *            解压目录
	 * @param passwd
	 *            ZIP文件的密码
	 * @return 解压后文件数组
	 * @throws ZipException
	 *             压缩文件有损坏或者解压缩失败抛出
	 */
	public static File[] unzip(File zipFile, String dest, String passwd)
			throws ZipException {
		ZipFile zFile = new ZipFile(zipFile);
		zFile.setFileNameCharset("UTF8");
		if (!zFile.isValidZipFile()) {
			WifiLogUtil.wifiLogInfo("zip4jUtils.class unzip",new Object[] { "压缩文件不合法,可能被损坏." });
			throw new ZipException("压缩文件不合法,可能被损坏.");
		}
		File destDir = new File(dest);
		if (destDir.isDirectory() && !destDir.exists()) {
			destDir.mkdir();
		}
		if (zFile.isEncrypted()) {
			zFile.setPassword(passwd.toCharArray());
		}
		zFile.extractAll(dest);

		List<FileHeader> headerList = zFile.getFileHeaders();
		List<File> extractedFileList = new ArrayList<File>();
		for (FileHeader fileHeader : headerList) {
			if (!fileHeader.isDirectory()) {
				extractedFileList.add(new File(destDir, fileHeader
						.getFileName()));
			}
		}
		File[] extractedFiles = new File[extractedFileList.size()];
		extractedFileList.toArray(extractedFiles);
		return extractedFiles;
	}

	/**
	 * 压缩指定文件到当前文件夹
	 * 
	 * @param src
	 *            要压缩的指定文件
	 * @return 最终的压缩文件存放的绝对路径,如果为null则说明压缩失败.
	 */
	public static String zip(String src) {
		return zip(src, null);
	}

	/**
	 * 使用给定密码压缩指定文件或文件夹到当前目录
	 * 
	 * @param src
	 *            要压缩的文件
	 * @param passwd
	 *            压缩使用的密码
	 * @return 最终的压缩文件存放的绝对路径,如果为null则说明压缩失败.
	 */
	public static String zip(String src, String passwd) {
		return zip(src, null, passwd);
	}

	/**
	 * 使用给定密码压缩指定文件或文件夹到当前目录
	 * 
	 * @param src
	 *            要压缩的文件
	 * @param dest
	 *            压缩文件存放路径
	 * @param passwd
	 *            压缩使用的密码
	 * @return 最终的压缩文件存放的绝对路径,如果为null则说明压缩失败.
	 */
	public static String zip(String src, String dest, String passwd) {
		return zip(src, dest, true, passwd);
	}

	/**
	 * 使用给定密码压缩指定文件或文件夹到指定位置.
	 * <p>
	 * dest可传最终压缩文件存放的绝对路径,也可以传存放目录,也可以传null或者"".<br />
	 * 如果传null或者""则将压缩文件存放在当前目录,即跟源文件同目录,压缩文件名取源文件名,以.zip为后缀;<br />
	 * 如果以路径分隔符(File.separator)结尾,则视为目录,压缩文件名取源文件名,以.zip为后缀,否则视为文件名.
	 * 
	 * @param src
	 *            要压缩的文件或文件夹路径
	 * @param dest
	 *            压缩文件存放路径
	 * @param isCreateDir
	 *            是否在压缩文件里创建目录,仅在压缩文件为目录时有效.<br />
	 *            如果为false,将直接压缩目录下文件到压缩文件.
	 * @param passwd
	 *            压缩使用的密码
	 * @return 最终的压缩文件存放的绝对路径,如果为null则说明压缩失败.
	 */
	public static String zip(String src, String dest, boolean isCreateDir,
			String passwd) {
		File srcFile = new File(src);
		dest = buildDestinationZipFilePath(srcFile, dest);
		ZipParameters parameters = new ZipParameters();
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE); // 压缩方式
		parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_ULTRA); // 压缩级别
		if (!passwd.isEmpty()) {
			parameters.setEncryptFiles(true);
			parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD); // 加密方式
			parameters.setPassword(passwd.toCharArray());
		}
		try {
			ZipFile zipFile = new ZipFile(dest);
			if (srcFile.isDirectory()) {
				// 如果不创建目录的话,将直接把给定目录下的文件压缩到压缩文件,即没有目录结构
				if (!isCreateDir) {
					File[] subFiles = srcFile.listFiles();
					ArrayList<File> temp = new ArrayList<File>();
					Collections.addAll(temp, subFiles);
					zipFile.addFiles(temp, parameters);
					return dest;
				}
				zipFile.addFolder(srcFile, parameters);
			} else {
				zipFile.addFile(srcFile, parameters);
			}
			return dest;
		} catch (ZipException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 构建压缩文件存放路径,如果不存在将会创建 传入的可能是文件名或者目录,也可能不传,此方法用以转换最终压缩文件的存放路径
	 * 
	 * @param srcFile
	 *            源文件
	 * @param destParam
	 *            压缩目标路径
	 * @return 正确的压缩文件存放路径
	 */
	private static String buildDestinationZipFilePath(File srcFile,
			String destParam) {
		if (destParam.isEmpty()) {
			if (srcFile.isDirectory()) {
				destParam = srcFile.getParent() + File.separator
						+ srcFile.getName() + ".zip";
			} else {
				String fileName = srcFile.getName().substring(0,
						srcFile.getName().lastIndexOf("."));
				destParam = srcFile.getParent() + File.separator + fileName
						+ ".zip";
			}
		} else {
			createDestDirectoryIfNecessary(destParam); // 在指定路径不存在的情况下将其创建出来
			if (destParam.endsWith(File.separator)) {
				String fileName = "";
				if (srcFile.isDirectory()) {
					fileName = srcFile.getName();
				} else {
					fileName = srcFile.getName().substring(0,
							srcFile.getName().lastIndexOf("."));
				}
				destParam += fileName + ".zip";
			}
		}
		return destParam;
	}

	/**
	 * 在必要的情况下创建压缩文件存放目录,比如指定的存放路径并没有被创建
	 * 
	 * @param destParam
	 *            指定的存放路径,有可能该路径并没有被创建
	 */
	private static void createDestDirectoryIfNecessary(String destParam) {
		File destDir = null;
		if (destParam.endsWith(File.separator)) {
			destDir = new File(destParam);
		} else {
			destDir = new File(destParam.substring(0,
					destParam.lastIndexOf(File.separator)));
		}
		if (!destDir.exists()) {
			destDir.mkdirs();
		}
	}
	

}