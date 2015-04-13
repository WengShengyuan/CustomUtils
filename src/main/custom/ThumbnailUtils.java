package main.custom;

import java.io.File;

import net.coobird.thumbnailator.Thumbnails;

public class ThumbnailUtils {

	// public static void main(String[] args) {
	//
	// }

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

}
