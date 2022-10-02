package org.aibles.book.dto.request;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.aibles.book.entity.Book;

@Data
public class CreateBookRequest {

  @NotBlank
  private String name;

  @NotBlank
  private String description;

  private Date releaseAt;

  public Date getReleaseAt() {
    return releaseAt;
  }


  public Book toBook() {
    Book book = new Book();
    book.setName(this.getName());
    book.setDescription(this.getDescription());
    book.setReleaseAt(this.getReleaseAt());
    return book;
  }

}
