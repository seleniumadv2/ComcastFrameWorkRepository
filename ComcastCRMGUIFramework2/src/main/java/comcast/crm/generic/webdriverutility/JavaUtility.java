package comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class JavaUtility {

	public int getRandomNumber() {
		Random random = new Random();
		int ranNUm = random.nextInt(5000);
		return ranNUm;
	}
	
	public String getSystemDateYYYYMMDD() {
		
		Date dateObj = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(dateObj);
		return date;
		
	}
	
	public String getCurrentTime() {
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		return time;
	}
	
		
	public String getRequiredDateYYYYMMDD(int days) {
		Date dateObj = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.format(dateObj);
		Calendar cal = sdf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH,days);
		String reqdate = sdf.format(cal.getTime());
		return reqdate;
		
	}
}
