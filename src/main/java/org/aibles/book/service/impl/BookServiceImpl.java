package org.aibles.book.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.aibles.book.component.SearchList;
import org.aibles.book.dto.request.CreateBookRequest;
import org.aibles.book.dto.request.UpdateBookRequest;
import org.aibles.book.dto.response.BookResponse;
import org.aibles.book.dto.response.MessageResponse;
import org.aibles.book.entity.Book;
import org.aibles.book.exception.BadRequestBaseException;
import org.aibles.book.exception.NotFoundBaseException;
import org.aibles.book.repository.BookRepository;
import org.aibles.book.service.BookService;
import org.aibles.book.util.JobCheck;
import org.aibles.book.util.SearchSpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;

@Slf4j
public class BookServiceImpl implements BookService {

  public final BookRepository repository;

  public BookServiceImpl(BookRepository repository) {
    this.repository = repository;
  }

  @Override
  @Transactional
  public BookResponse create(CreateBookRequest request) {
    log.info("(create)create information book name: {}", request.getName());
    Book book = request.toBook();
    book.validateClient();
    book.setActive(
        JobCheck.checkBookIsActive(book)
    );
    return BookResponse.from(repository.save(book));
  }

  @Override
  @Transactional
  public MessageResponse deleteById(long id) {
    log.info("(deleteById)delete book by id: {}", id);
    repository.deleteById(id);
    return new MessageResponse("Successful delete!!!");
  }

  @Override
  @Transactional
  public BookResponse getById(long id) {
    log.info("(findById)get book by id: {}", id);
    Book book = repository
        .findById(id)
        .orElseThrow(() -> {
          throw new NotFoundBaseException(id);
        });
    return BookResponse.from(book);
  }

  @Override
  @Transactional
  public List<BookResponse> getAll() {
    log.info("(List)student list");
    List<Book> books = repository.findAll();
    return books.stream().map(BookResponse::from).collect(Collectors.toList());
  }

  @Override
  @Transactional
  public List<BookResponse> search(SearchList searchList) {
    log.info("(search)book add filter: {}", searchList);
    SearchSpecificationBuilder<Book> builder = new SearchSpecificationBuilder<>(
        searchList.getSearch());
    Specification<Book> specification = builder.build();
    List<Book> books = repository.findAll(specification);
    return books.stream().map(BookResponse::from).collect(Collectors.toList());
  }

  @Override
  @Transactional
  public BookResponse updateById(long id, UpdateBookRequest request) {
    log.info("(update)update book by id");
    Book bookCheck = repository
        .findById(id)
        .orElseThrow(() -> {
          throw new NotFoundBaseException(id);
        });
    Book book = request.toBook();
    book.setId(bookCheck.getId());
    book.setActive(
        JobCheck.checkBookIsActive(book)
    );
    return BookResponse.from(repository.save(book));
  }

  @Override
  @Transactional
  public MessageResponse jobCheckBook(long id) {
    log.info("(jobCheckBook)check book is active");
    Book book = repository
        .findById(id)
        .orElseThrow(() -> {
          throw new NotFoundBaseException(id);
        });
    book.setActive(
        JobCheck.checkBookIsActive(book)
    );
    return new MessageResponse("Check book is active successful!!!");
  }


}
