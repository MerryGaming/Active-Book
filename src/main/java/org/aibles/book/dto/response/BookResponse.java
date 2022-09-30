package org.aibles.book.dto.response;

import java.sql.Date;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.aibles.book.entity.Book;

@Data
public class BookResponse {

  private long id;

  @NotBlank
  private String name;

  @NotBlank
  private String description;

  private boolean isActive;

  private Date releaseAt;

  public static BookResponse from(Book book) {
    BookResponse response = new BookResponse();
    response.setId(book.getId());
    response.setName(book.getName());
    response.setDescription(book.getDescription());
    response.setReleaseAt(book.getReleaseAt());
    response.setActive(book.isActive());
    return response;
  }

}
