package org.aibles.book.repository;

import java.util.Date;
import java.util.List;
import org.aibles.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>,
    JpaSpecificationExecutor<Book> {

  List<Book> findBookByReleaseAtAfter(Date time);

}
