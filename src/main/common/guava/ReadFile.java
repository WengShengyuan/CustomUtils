package main.common.guava;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class ReadFile {
	
	
	/**
	 * Read File using guava API
	 * @param args
	 */
	public static void main(String[] args) {
		String filePath = "resources/httpderr.log";
		File file = new File(filePath);
		List<String> readed = null;
		try {
			readed = Files.readLines(file, Charsets.UTF_8);
			for(String s : readed){
				System.out.println(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
