package org.htk.bot.util;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MyDateTime {
	public static String getDate() {
		Date date = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
		return formatador.format(date);
	}
}
