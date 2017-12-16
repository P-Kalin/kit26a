package ua.khpi.oop.lytvyn16.util;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * *Adapter (for JAXB) to convert between the LocalDate and the ISO 8601 String
 * representation of the date such as '2012-12-03'.
 * 
 * @author student Lytvyn I.I. KIT-26A
 */

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

	@Override
	public String marshal(LocalDate v) throws Exception {
		return v.toString();
	}

	@Override
	public LocalDate unmarshal(String v) throws Exception {
		return LocalDate.parse(v);
	}
}
