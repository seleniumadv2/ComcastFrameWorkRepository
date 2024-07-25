package comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.util.Properties;

public class FileUtility {

	public String getDataFromPropertiesFiles(String Key) throws Exception {
		
		FileInputStream fis = new FileInputStream("./configAppData/commondata.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String data = pObj.getProperty(Key);
		
		
		return data;
		
	}
}
