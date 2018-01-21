package adaptergson;

import java.util.Calendar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FactoryGson {
	
	private static Gson gson;
	
	public static Gson getGson() {
		
		 gson = new GsonBuilder()
					.registerTypeAdapter(Calendar.class, new StringToCalendar())
					.registerTypeAdapter(double.class, new EmptyStringToDouble())
					.registerTypeAdapter(Double.class, new EmptyStringToDouble())
					.registerTypeAdapter(int.class, new EmptyStringToInteger())
					.registerTypeAdapter(Integer.class, new EmptyStringToInteger())
					.create();
		 return gson;
	}

}
