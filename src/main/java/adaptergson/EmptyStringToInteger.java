package adaptergson;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class EmptyStringToInteger extends TypeAdapter<Number> {

	@Override
	public void write(JsonWriter jsonWriter, Number number) throws IOException {
		if (number == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.value(number);
		
	}

	@Override
	public Number read(JsonReader jsonReader) throws IOException {
		 if (jsonReader.peek() == JsonToken.NULL) {
             jsonReader.nextNull();
             return null;
         }

         try {
             String value = jsonReader.nextString();
             
             return Integer.valueOf(value);
         } catch (NumberFormatException e) {
        	 System.err.println("Formato não valido para Integer");
        	 return Integer.MIN_VALUE;
         }
     }
}
