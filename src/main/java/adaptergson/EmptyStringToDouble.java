package adaptergson;

import java.io.IOException;

import org.apache.commons.lang3.math.NumberUtils;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class EmptyStringToDouble extends TypeAdapter<Number> {

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
             
             value = value.replace(",", ".");
             double d = Double.valueOf(value);
             return d;
         } catch (NumberFormatException e) {
        	 
        	 System.err.println("Formato não válido para Double");
        	 return Double.MIN_VALUE;
         }
     }
}
