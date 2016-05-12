package readinglist;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


/**
 * Created by pivotal on 5/11/16.
 */
public interface ReadingListRepository extends MongoRepository<Book, String> {
    List<Book> findByReader(String reader);
    List<Book> findByIsbn(String isbn);
}