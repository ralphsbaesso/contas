package adaptergson;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class StringToCalendar implements JsonDeserializer<Calendar> {

	@Override
	public Calendar deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {

		String dateAsString = json.getAsString();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		
		try {
			date = sdf.parse(dateAsString);
			calendar.setTime(date);
		} catch (ParseException e) {
			
			calendar = null;
			e.printStackTrace();
		}
		return calendar;

	}

}
