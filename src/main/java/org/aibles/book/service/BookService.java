package org.aibles.book.service;


import java.util.List;
import org.aibles.book.component.SearchList;
import org.aibles.book.dto.request.CreateBookRequest;
import org.aibles.book.dto.request.UpdateBookRequest;
import org.aibles.book.dto.response.BookResponse;
import org.aibles.book.dto.response.MessageResponse;

public interface BookService {

  /**
   * create information of books
   * @param request - information of books
   * @return - information of books
   */
  BookResponse create(CreateBookRequest request);

  /**
   * delete book by id
   * @param id - id of the book to delete
   * @return - successful delete
   */
  MessageResponse deleteById(long id);

  /**
   * get book by id
   * @param id - id of the book to get
   * @return - information of books
   */
  BookResponse getById(long id);

  /**
   * get all book on list
   * @return - list book
   */
  List<BookResponse> getAll();

  /**
   * search book
   * @param searchList - search book in the form of a list
   * @return - information of books
   */
  List<BookResponse> search(SearchList searchList);

  /**
   * update book by id
   * @param id - id of the book to update
   * @param request - information of books to update
   * @return - information of books new
   */
  BookResponse updateById(long id, UpdateBookRequest request);

}
