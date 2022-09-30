package org.aibles.book.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.aibles.book.component.SearchList;
import org.aibles.book.dto.request.CreateBookRequest;
import org.aibles.book.dto.request.UpdateBookRequest;
import org.aibles.book.dto.response.BookResponse;
import org.aibles.book.dto.response.MessageResponse;
import org.aibles.book.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
@Slf4j
public class BookController {

  private final BookService service;


  public BookController(BookService service) {
    this.service = service;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public BookResponse create(@RequestBody @Validated() CreateBookRequest request) {
    log.info("(create)create information book: {}", request);
    return service.create(request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public MessageResponse deleteById(@PathVariable("id") long id) {
    log.info("(deleteById)delete book by id: {}", id);
    return service.deleteById(id);
  }

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public BookResponse getById(@PathVariable("id") long id) {
    log.info("(getById)get book by id: {}", id);
    return service.getById(id);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<BookResponse> getAll() {
    log.info("(getAll)get all book on list book");
    return service.getAll();
  }

  @GetMapping("/search")
  @ResponseStatus(HttpStatus.OK)
  public List<BookResponse> search(@RequestBody SearchList searchList) {
    log.info("(search)search information book");
    return service.search(searchList);
  }

  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public BookResponse update(@PathVariable("id") long id,
      @RequestBody @Valid UpdateBookRequest request) {
    log.info("(update)update book by id: {} and the information: {}", id, request);
    return service.updateById(id, request);
  }

  @GetMapping("/check/{id}")
  @ResponseStatus(HttpStatus.OK)
  public MessageResponse jobCheckBook(@PathVariable("id") long id) {
    log.info("(jobCheckBook)check book is active");
    return service.jobCheckBook(id);
  }
}
