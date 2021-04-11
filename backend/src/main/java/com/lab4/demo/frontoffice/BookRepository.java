package com.lab4.demo.frontoffice;

import com.lab4.demo.frontoffice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByAuthorLikeOrTitleLikeOrGenreLike(String author, String title, String genre);

    @Query("SELECT b from Book b where b.quantity = ?1")
    List<Book> findAllByQuantity(@Param("quantity") int quantity);
}
