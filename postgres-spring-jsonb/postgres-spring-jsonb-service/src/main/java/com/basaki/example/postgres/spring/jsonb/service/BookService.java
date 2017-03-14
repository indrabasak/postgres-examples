package com.basaki.example.postgres.spring.jsonb.service;

import com.basaki.example.postgres.spring.jsonb.data.entity.BookEntity;
import com.basaki.example.postgres.spring.jsonb.data.repository.BookRepository;
import com.basaki.example.postgres.spring.jsonb.error.DataNotFoundException;
import com.basaki.example.postgres.spring.jsonb.model.Author;
import com.basaki.example.postgres.spring.jsonb.model.Book;
import com.basaki.example.postgres.spring.jsonb.model.BookRequest;
import com.basaki.example.postgres.spring.jsonb.model.Genre;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

/**
 * {@code BookService} service provides data access service for {@code Book}.
 * <p/>
 *
 * @author Indra Basak
 * @sice 3/13/17
 */
@Service
@Slf4j
public class BookService {

    @Autowired
    private BookRepository repo;

    private Mapper mapper;

    @Autowired
    public BookService(BookRepository repo, Mapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }


    public Book create(BookRequest request) {
        Assert.notNull(request.getTitle(), "Title should not be null.");
        Assert.notNull(request.getGenre(), "Genre should not be null.");
        Assert.notNull(request.getPublisher(), "Publisher should not be null.");
        Assert.notNull(request.getAuthor(), "Author should not be null.");
        Assert.state((request.getStar() > 0 && request.getStar() <= 5),
                "Star should be between 1 and 5");

        BookEntity record = mapper.map(request, BookEntity.class);
        record = repo.save(record);
        Book book = mapper.map(record, Book.class);

        return book;
    }

    public Book getById(UUID id) {
        BookEntity record = repo.findOne(id);
        Book book = mapper.map(record, Book.class);

        return book;
    }

    public List<Book> get(String title, Genre genre, String publisher,
            Author author) {

        if (title == null && genre == null && publisher == null && author == null) {
            return map(repo.findAll());
        }

        BookEntity entity = new BookEntity();
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths(
                "star");

        if (title != null) {
            entity.setTitle(title);
            matcher = matcher.withMatcher("title", startsWith().ignoreCase());
        }

        if (genre != null) {
            entity.setGenre(genre);
        }

        if (publisher != null) {
            entity.setPublisher(publisher);
            matcher =
                    matcher.withMatcher("publisher", startsWith().ignoreCase());
        }

        Example<BookEntity> example = Example.of(entity, matcher);

        return map(repo.findAll(example));
    }

    public void delete(UUID id) {
        repo.delete(id);
    }

    public void deleteAll() {
        repo.deleteAll();
    }

    public List<String> getPublisher(String publisher) {
        return repo.findDistinctPublisher(publisher);
    }

    private List<Book> map(List<BookEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            throw new DataNotFoundException(
                    "No books found with the search criteria!");
        }

        return entities.stream().map(
                r -> mapper.map(r, Book.class)).collect(
                Collectors.toList());
    }
}
