package com.lab4.demo.frontoffice;

import com.lab4.demo.frontoffice.model.Book;
import com.lab4.demo.frontoffice.model.dto.BookDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b from Book b where b.author = ?1 and b.title = ?2 and b.genre = ?3")
    public Book findBook(@Param("author") String author, @Param("title") String title, @Param("genre") String genre);

    @Query("SELECT b from Book b where b.quantity = 0")
    public List<Book> findOutOfStock();
}
