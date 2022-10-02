package org.aibles.book.configuration;

import org.aibles.book.repository.BookRepository;
import org.aibles.book.service.BookService;
import org.aibles.book.service.impl.BookServiceImpl;
import org.aibles.book.util.HeaderLocalResolver;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
@EnableJpaRepositories(basePackages = {"org.aibles.book.repository"})
@ComponentScan(basePackages = {"org.aibles.book.repository"})
public class BookConfiguration implements WebMvcConfigurer {

  @Bean
  public BookService bookService (BookRepository repository) {
    return new BookServiceImpl(repository);
  }

  @Bean
  public LocaleResolver localeResolver() {
    return new HeaderLocalResolver();
  }

  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource
        = new ReloadableResourceBundleMessageSource();
    messageSource.setBasename(
        "classpath:/i18n/messages"
    );
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }
}
