package org.aibles.book.service.impl;

import java.time.LocalDate;
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
import org.aibles.book.exception.NotFoundBaseException;
import org.aibles.book.repository.BookRepository;
import org.aibles.book.service.BookService;
import org.aibles.book.util.DateUtil;
import org.aibles.book.util.SearchSpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;

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
        request.getReleaseAt().before(DateUtil.convertLocalDateToDate(LocalDate.now()))
    );
    return BookResponse.from(repository.save(book));
  }

  @Override
  @Transactional
  public MessageResponse deleteById(long id) {
    log.info("(deleteById)delete book by id: {}", id);
    Book book = repository
        .findById(id)
        .orElseThrow(() -> {
          throw new NotFoundBaseException(id);
        });
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
    return BookResponse.from(repository.save(book));
  }

  @Override
  @Scheduled(cron = "0 0 8 * * ?")
  public void checkJob() {
    log.info("(checkJob)check job is active book every day");
    List<Book> books = repository.findBookByReleaseAtAfter(DateUtil.convertLocalDateToDate(
        LocalDate.now()));
    books.forEach((book -> {
      book.setActive(true);
      repository.save(book);
    }));
    log.info("Check is active book successful!!!");

  }


}
