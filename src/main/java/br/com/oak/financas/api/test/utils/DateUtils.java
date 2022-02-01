package br.com.oak.financas.api.test.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtils {

  public static String convertLocalDateToString(final LocalDate date, final String pattern) {

    String formattedReadingTime = null;
    if (date != null) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.US);
      formattedReadingTime = date.format(formatter);
    }
    return formattedReadingTime;
  }
}
