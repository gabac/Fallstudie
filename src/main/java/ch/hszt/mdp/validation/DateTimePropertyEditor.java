package ch.hszt.mdp.validation;

import java.beans.PropertyEditorSupport;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 * Converts a String date into a Joda DateTime. And vice versa.
 */
public class DateTimePropertyEditor extends PropertyEditorSupport {

	private String pattern;

	/**
	 * @param pattern
	 *            Date pattern, see Joda documentation.
	 */
	public DateTimePropertyEditor(String pattern) {
		this.pattern = pattern;
	}

	@Override
	public String getAsText() {

		if (!(getValue() instanceof DateTime)) {
			return "";
		}

		DateTime value = (DateTime) getValue();

		return value.toString(pattern);
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {

		if (text == null) {

			throw new IllegalArgumentException("The specified DateTime string cannot be null");

		} else {
			setValue(DateTimeFormat.forPattern(pattern).parseDateTime(text));
		}
	}
}
