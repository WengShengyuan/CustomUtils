package main.custom;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ThumbnailUtils {

	

	public void imgResize(String oriPath, int[] imgSize, String newPath,
			boolean keepScale, boolean deleteOri) throws Exception {
		try{
			File oriFile = new File(oriPath);
			File newFile = new File(newPath);
			Thumbnails.of(oriFile).size(imgSize[0], imgSize[1])
					.keepAspectRatio(keepScale).toFile(newFile);
			if(deleteOri){
				oriFile.delete();
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	
	/**
	 * 智能压缩 算法： 1、找到长或宽的最长的边，使其只有一个维度超出长度 2、保持比例压缩到这个大小 3、裁取超出长度的中间区段
	 * 
	 * @param newFile
	 * @param imgSize
	 * @param oriFile
	 * @throws Exception
	 */
	private void smartCompressNoAspctRatio(File newFile, int[] imgSize,
			File oriFile) throws Exception {
		try{
			// 原图尺寸
			int[] oriInt = getImgSize(newFile);
			// 如果规定高度 < 缩放后的原图高度
			if (imgSize[1] < oriInt[1] * (int)((double)imgSize[0] / (double)oriInt[0])) {
				// 裁去上下边:截取原图，再等比例缩放
				// 缩放后的规定高度
				int scaled_imgSize_height = (int)((double)(imgSize[1] * oriInt[0]) / (double)(imgSize[0]));
				Thumbnails
						.of(newFile)
						.sourceRegion(Positions.CENTER, oriInt[0],
								scaled_imgSize_height).size(imgSize[0], imgSize[1])
						.keepAspectRatio(false).toFile(oriFile);
			} else {
				// 裁去左右边
				int scaled_imgSize_width = (int)((double)(imgSize[0] * oriInt[1]) / (double)(imgSize[1]));
				Thumbnails
						.of(newFile)
						.sourceRegion(Positions.CENTER, scaled_imgSize_width,
								oriInt[1]).size(imgSize[0], imgSize[1])
						.keepAspectRatio(false).toFile(oriFile);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}

	/**
	 * 简单粗暴的压缩
	 * 
	 * @param newFile
	 * @param imgSize
	 * @param keepAspctRatio
	 * @param oriFile
	 * @throws Exception
	 */
	private void dumpCompressNoAspctRatio(File newFile, int[] imgSize,
			boolean keepAspctRatio, File oriFile) throws Exception {
		try{
			Thumbnails.of(newFile).size(imgSize[0], imgSize[1])
			.keepAspectRatio(keepAspctRatio).toFile(oriFile);
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}

	/**
	 * 获取图片大小
	 * 
	 * @param f
	 * @return
	 * @throws Exception
	 */
	private int[] getImgSize(File f) throws Exception {
		BufferedImage sourceImg = ImageIO.read(new FileInputStream(f));
		int[] r = new int[2];
		r[0] = sourceImg.getWidth();
		r[1] = sourceImg.getHeight();
		return r;
	}
	

}
