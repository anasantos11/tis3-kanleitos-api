package Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Metodos {
	private static final Metodos INSTANCE = new Metodos();
	
	public static Metodos getInstance() {
		return INSTANCE;
	}
	
	public Calendar stringParseCalendar(String data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(data));
		} catch (ParseException e) {
			System.out.println("Erro metodo stringParseCalendar na classe Metodo" + e.getMessage());
		}
		return cal;
	}
	public String dateParseString(Date date) {
		String sdf = new SimpleDateFormat("dd/MM/yyyy").format(date);
		return sdf;
	}
}
