package com.basaki.example.postgres.spring.data.repository;

import com.basaki.example.postgres.spring.data.entity.BookRecord;
import com.basaki.example.postgres.spring.model.Genre;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

/**
 * {@code BookRepository} exposes all CRUD operations on a data of type
 * {@code Book}.
 * <p/>
 *
 * @author Indra Basak
 * @since 3/8/17
 */
public interface BookRepository extends CrudRepository<BookRecord, UUID> {

    List<BookRecord> findByTitleIgnoreCase(String title);

    List<BookRecord> findByTitleIgnoreCaseAndAuthorIgnoreCase(String title,
            String author);

    List<BookRecord> findByTitleIgnoreCaseAndAuthorIgnoreCaseAndGenre(
            String title,
            String author,
            Genre genre);

    List<BookRecord> findByTitleIgnoreCaseAndGenre(String title, Genre genre);

    List<BookRecord> findByAuthorIgnoreCase(String author);

    List<BookRecord> findByAuthorIgnoreCaseAndGenre(String author, Genre genre);

    List<BookRecord> findByGenre(Genre genre);
}
