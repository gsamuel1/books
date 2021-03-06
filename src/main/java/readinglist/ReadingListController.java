package readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by pivotal on 5/11/16.
 */
@Controller
@RequestMapping("/readinglist")
public class ReadingListController {
    private ReadingListRepository readingListRepository;

    @Autowired
    public ReadingListController(ReadingListRepository readingListRepository) {
        this.readingListRepository = readingListRepository;
    }

    @RequestMapping(value="", method=RequestMethod.GET)
    public String homePage(Model model) {
        return readersBooks( "", model);
    }

    @RequestMapping(value="/{reader}", method= RequestMethod.GET)
    public String readersBooks(@PathVariable("reader") String reader, Model model) {

        List<Book> readingList = readingListRepository.findByReader(reader);
        if(readingList != null){
            model.addAttribute("books", readingList);
        }

        return "readingList";
    }

    @RequestMapping(value="/isbn/{isbn}", method= RequestMethod.GET)
    public String readersBooksByIsbn(@PathVariable("isbn") String isbn, Model model) {

        List<Book> readingList = readingListRepository.findByIsbn(isbn);
        if(readingList != null){
            model.addAttribute("books", readingList);
        }

        return "readingList";
    }

    @RequestMapping(value="/{reader}", method=RequestMethod.POST)
    public String addToReadingList(@PathVariable("reader") String reader, Book book) {
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/readinglist/{reader}";
    }
}
