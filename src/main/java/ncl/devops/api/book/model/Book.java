package ncl.devops.api.book.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
@Builder
public class Book {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long bookId;

    @NonNull
    private String bookName;

    @NonNull
    private String bookSummary;

    @NonNull
    private String bookRating;
}
