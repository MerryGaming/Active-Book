package org.aibles.book.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.aibles.book.entity.Book;

public class DateUtil {

  public static Date convertLocalDateToDate(LocalDate localDate) {
    return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
  }

}
