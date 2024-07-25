package comcast.crm.orgtest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JavaBasics {

	public static void main(String[] args) {
		
		Date dateobj = new Date();
		
		//System.out.println(dateobj.toString());
		
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String actdt  = sim.format(dateobj);
		System.out.println(actdt);

		
		Calendar cal =sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH,30);
		String datereq = sim.format(cal.getTime());
		System.out.println(datereq);
	}
}
