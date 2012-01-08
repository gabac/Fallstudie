package ch.hszt.mdp.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class ISODateSerializer extends JsonSerializer<DateTime> {

	private static DateTimeFormatter formatter = ISODateTimeFormat.dateTime();

	@Override
	public void serialize(DateTime value, JsonGenerator generator, SerializerProvider provider) throws IOException, JsonProcessingException {

		generator.writeString(formatter.print(value));
	}
}
