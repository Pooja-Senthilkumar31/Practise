package javautility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	public int getRandomNumber() {
		Random random = new Random();
		int ranint = random.nextInt(3000);
		return ranint;

	}

	public String getRequiredDate(int days) {
		// Getting the date after 30 days for expected close date field
		Date date = new Date(); // to get the current date
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy"); // to set the format and MM should always be caps
		sim.format(date); // to set the current date in mentioned format
		Calendar cal = sim.getCalendar(); // getting the calender to set the date
		cal.add(Calendar.DAY_OF_MONTH, 30); // in days of month add 30 days, to get after 30 days
		String reqdate = sim.format(cal.getTime()); // gettime is used to get the above calculated date

		return reqdate;

	}
	
	public String getCurrentDate()
	{
		Date date=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("dd-mm-yyyy");
		String currentdate = sim.format(date);
		return currentdate;
		
	}
}
