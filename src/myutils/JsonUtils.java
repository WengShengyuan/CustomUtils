package myutils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

import com.rails.common.core.frame.generic.dao.GenericDaoImpl;

/** * Class Name: JsonUtil.java * Function: * * Modifications: * * @author WengShengyuan DateTime 2015-1-23 下午3:59:21 * @version 1.0 * 
 * @param <T>
 */
public class JsonUtils<T> {

	/**
	 * 
	 *  Function:把实体存成json串
	 * 
	 *  @author WengShengyuan  DateTime 2015-1-28 下午7:09:47
	 *  @param filePath 包括文件名的全路径
	 *  @param entity 实体列表
	 *  @return
	 */
	public String saveEntityToFile(String filePath, List<T> entity) {
		try{
			File saveFile = new File(filePath);
			File locationFolder = new File(CustomFileUtils.getFileLocation(filePath));
			if(saveFile.exists())
				saveFile.delete();
			if(!locationFolder.exists())
				locationFolder.mkdirs();
				
			JsonGenerator jsonGenerator = null;
			ObjectMapper objectMapper = new ObjectMapper();
		    jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(saveFile,JsonEncoding.UTF8);
		    jsonGenerator.writeObject(entity);
		    //关掉流，不然删除不了文件
		    jsonGenerator.close();
		    return filePath;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}

	/**
	 * 
	 *  Function:把json转成实体列表
	 * 
	 *  @author WengShengyuan  DateTime 2015-1-28 下午7:10:30
	 *  @param filePath 文件全路径
	 *  @param entityClass  实体的class
	 *  @return
	 */
	public List<T> readFileToEntity(String filePath, Class entityClass) {
		try{
			List<T> returnObjs = null;
			File saveFile = new File(filePath);
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, entityClass);
			returnObjs = (List<T>)objectMapper.readValue(saveFile, javaType);
		    return returnObjs;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	
//	public List<T> InsertUpdateFromFile(String filePath, Class entityClass){
//		try{
//			List<T> lists = this.readFileToEntity(filePath, entityClass);
//			for(T item : lists){
//				
//				GenericDaoImpl<T>
//			}
//			
//			return lists;
//		}
//		catch(Exception e){
//			e.printStackTrace();
//			return null;
//		}
//		
//	}
	
	
}
