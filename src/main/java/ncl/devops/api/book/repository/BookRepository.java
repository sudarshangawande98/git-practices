package ncl.devops.api.book.repository;

import ncl.devops.api.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
