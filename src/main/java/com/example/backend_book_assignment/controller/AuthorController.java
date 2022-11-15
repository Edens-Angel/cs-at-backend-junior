package com.example.backend_book_assignment.controller;

import com.example.backend_book_assignment.entities.Author;
import com.example.backend_book_assignment.entities.Book;
import com.example.backend_book_assignment.models.AuthorInput;
import com.example.backend_book_assignment.repositories.AuthorRepository;
import com.example.backend_book_assignment.repositories.BookRepository;
import com.example.backend_book_assignment.utilities.BookUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
public class AuthorController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @QueryMapping
    Iterable<Author> authors() {
        return authorRepository.findAll();
    }

    @QueryMapping
    Optional<Author> authorById(@Argument long id){
        return authorRepository.findById(id);
    }

    @MutationMapping
    Author addAuthor(@Argument AuthorInput authorInput) throws ParseException {
        Date formattedDate = new SimpleDateFormat("dd-MM-yyyy").parse(authorInput.dateOfBirth());
        Author author = new Author(authorInput.firstName(), authorInput.lastName(), formattedDate);

        for (long bookId: authorInput.bookIds()) {
            Book tempBook = bookRepository.findById(bookId).orElseThrow((() -> new IllegalArgumentException("Author not found!")));

            BookUtil.addBook(author, tempBook);
        }

        return authorRepository.save(author);
    }
}
