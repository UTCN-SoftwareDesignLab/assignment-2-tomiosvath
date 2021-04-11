package com.lab4.demo.frontoffice;

import com.lab4.demo.frontoffice.model.Book;
import com.lab4.demo.frontoffice.model.dto.BookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public List<BookDTO> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public Book findById(Long id){
        /*List<Long> list = new ArrayList<>();
        list.add(id);
        if (bookRepository.findAllById(list).size() > 0)
            return bookRepository.findAllById(list).get(0);

        return null;
         */
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No book with id: " + id));
    }

    public BookDTO create(BookDTO book){
        return bookMapper.toDto(bookRepository.save(bookMapper.fromDto(book)));
    }

    public BookDTO find(String author, String title, String genre){
        return bookMapper.toDto(bookRepository.findByAuthorLikeOrTitleLikeOrGenreLike(author, title, genre));
    }

    public void delete(Long id){
        Book book = findById(id);
        bookRepository.delete(book);
    }

    public void sell(Long id){
        Book book = findById(id);

        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);
    }

    public BookDTO edit(BookDTO book) {
        Book actBook = findById(book.getId());
        actBook.setQuantity(book.getQuantity());
        actBook.setAuthor(book.getAuthor());
        actBook.setGenre(book.getGenre());
        actBook.setPrice(book.getPrice());
        actBook.setTitle(book.getTitle());

        return bookMapper.toDto(
                bookRepository.save(actBook)
        );
    }

    public List<BookDTO> findAllByQuantity(int quantity){
        return bookRepository.findAllByQuantity(quantity).stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

}

