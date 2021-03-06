package com.basaki.example.postgres.spring.jsonb.controller;

import com.basaki.example.postgres.spring.jsonb.model.Book;
import com.basaki.example.postgres.spring.jsonb.model.BookRequest;
import com.basaki.example.postgres.spring.jsonb.model.Genre;
import com.basaki.example.postgres.spring.jsonb.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@code BookController} is the spring REST controller for book API. Exposes
 * all CRUD operations on book.
 * <p/>
 *
 * @author Indra Basak
 * @since 3/13/17
 */
@RestController
@Slf4j
@Api(value = "Book API",
        description = "Book API",
        produces = "application/json", tags = {"API"})
public class BookController {

    public static final String BOOK_URL = "/books";

    public static final String BOOK_BY_ID_URL = BOOK_URL + "/{id}";

    public static final String PUBLISHER_URL = BOOK_URL + "/publishers";

    @Autowired
    private BookService service;

    @ApiOperation(
            value = "Creates a book.",
            notes = "Requires a book title, genre, publisher, star, and author.",
            response = Book.class)
    @ApiResponses({
            @ApiResponse(code = 201, response = Override.class,
                    message = "Override override created successfully")})
    @RequestMapping(method = RequestMethod.POST, value = BOOK_URL,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody BookRequest request) {
        return service.create(request);
    }

    @ApiOperation(
            value = "Retrieves a book by ID.",
            notes = "Requires a book identifier",
            response = Book.class)
    @RequestMapping(method = RequestMethod.GET, value = BOOK_BY_ID_URL,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Book getById(@PathVariable("id") UUID id) {
        return service.getById(id);
    }

    @ApiOperation(
            value = "Retrieves all books associated with a title, genre, publisher or combination of them.",
            notes = "In absence of any parameter, it will return all authors",
            response = Book.class, responseContainer = "List")
    @RequestMapping(method = RequestMethod.GET, value = BOOK_URL,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Book> get(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "genre", required = false) Genre genre,
            @RequestParam(value = "publisher", required = false) String publisher) {
        return service.get(title, genre, publisher, null);
    }

    @ApiOperation(value = "Deletes a book by ID.")
    @RequestMapping(method = RequestMethod.DELETE, value = BOOK_BY_ID_URL)
    @ResponseBody
    public void deleteById(@PathVariable("id") UUID id) {
        service.delete(id);
    }

    @ApiOperation(value = "Deletes all books.")
    @RequestMapping(method = RequestMethod.DELETE, value = BOOK_URL)
    @ResponseBody
    public void deleteAll() {
        service.deleteAll();
    }

    @ApiOperation(
            value = "Retrieves a list of distinct publisher based on partial publisher name search.",
            notes = "Requires a partial publisher name",
            response = String.class, responseContainer = "List")
    @RequestMapping(method = RequestMethod.GET, value = PUBLISHER_URL,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<String> getPublisher(
            @ApiParam(value = "partial publisher name", required = true)
            @RequestParam(value = "q") String publisher) {
        return service.getPublisher(publisher);
    }
}
