package org.aibles.book.util;

import java.sql.Date;
import java.time.LocalDate;
import org.aibles.book.entity.Book;

public class JobCheck {

  public static boolean checkBookIsActive(Book book) {

    LocalDate convertToEntityAttribute = LocalDate.now();
    Date date = Date.valueOf(convertToEntityAttribute);
    Date convertToDatabaseColumn = book.getReleaseAt();

    int check = date.compareTo(convertToDatabaseColumn);
    if (check == 0) {
      return true;
    } else if (check < 0) {
      return false;
    } else {
      return true;
    }

  }

}
