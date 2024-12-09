package ncl.devops.api.book.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import ncl.devops.api.book.model.Book;
import ncl.devops.api.book.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    Book book1;
    Book book2;

    List<Book> bookList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        book1 = new Book(1L, "Book1", "Summary1", "Rating1");
        book2 = new Book(2L, "Book2", "Summary2", "Rating2");

        bookList.add(book1);
        bookList.add(book2);
    }

    @AfterEach
    void tearDown() {
        book1 = book2 = null;
        bookList = null;
    }

    @Test
    void getAllBookDetails() throws Exception {
        when(bookRepository.findAll()).thenReturn(bookList);

        this.mockMvc.perform(get("/api/v1/books"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getBookById() throws Exception {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book1));
        this.mockMvc.perform(get("/api/v1/books/1"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void createBook() throws Exception {
        String requestJson = objectWriter.writeValueAsString(book1);

        when(bookRepository.save(book1)).thenReturn(book1);

        this.mockMvc.perform(post("/api/v1/books")
                .contentType("application/json")
                .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void updateBook() {
    }
}