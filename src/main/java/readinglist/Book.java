package readinglist;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Created by pivotal on 5/11/16.
 */
@Data
public class Book {
    //@GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private String id;
    private String reader;
    private String isbn;
    private String title;
    private String author;
    private String description;

    public Book() {
    }

    protected boolean canEqual(Object other) {
        return other instanceof Book;
    }

}
