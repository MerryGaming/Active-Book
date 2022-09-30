package org.aibles.book.configuration;

import org.aibles.book.repository.BookRepository;
import org.aibles.book.service.BookService;
import org.aibles.book.service.impl.BookServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"org.aibles.book.repository"})
@ComponentScan(basePackages = {"org.aibles.book.repository"})
public class BookConfiguration {

  @Bean
  public BookService bookService (BookRepository repository) {
    return new BookServiceImpl(repository);
  }
}
