package ncl.devops.api.book.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import ncl.devops.api.book.model.Book;
import ncl.devops.api.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/books")
//@Tag(name = "Z-Book Management", description = "Book Management APIs")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping
    public List<Book> getAllBookDetails(){
        return bookRepository.findAll();
    }

    @Operation(summary = "Retrieve a Book by Id", description = "Get a Book object by specifying its id.")
    @GetMapping(value = "{bookId}")
    public Book getBookById(@PathVariable("bookId") Long bookId){
        return bookRepository.findById(bookId).get();
    }

    @Operation(summary = "Create Book", description = "Create Book object.")
    @PostMapping
    public Book createBook(@Valid @RequestBody Book book){
        return bookRepository.save(book);
    }

    @Operation(summary = "Update Book", description = "Update Book object.")
    @PutMapping
    public Book updateBook(@Valid @RequestBody Book book) throws ChangeSetPersister.NotFoundException {
        if(book == null || book.getBookId() == null) {
            throw new ChangeSetPersister.NotFoundException();
        }

        Optional<Book> optionalBook = bookRepository.findById(book.getBookId());
        if(optionalBook.isEmpty()){
            throw new ChangeSetPersister.NotFoundException();
        }

        Book existingBook = optionalBook.get();
        existingBook.setBookName(book.getBookName());
        existingBook.setBookSummary(book.getBookSummary());
        existingBook.setBookRating(book.getBookRating());

        return bookRepository.save(book);
    }
}