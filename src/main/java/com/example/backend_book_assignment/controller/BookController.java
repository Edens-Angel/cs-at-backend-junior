package com.example.backend_book_assignment.controller;

import com.example.backend_book_assignment.entities.Author;
import com.example.backend_book_assignment.entities.Book;
import com.example.backend_book_assignment.models.BookInput;
import com.example.backend_book_assignment.repositories.AuthorRepository;
import com.example.backend_book_assignment.repositories.BookRepository;
import com.example.backend_book_assignment.utilities.BookUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;


@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @QueryMapping
    Iterable<Book> books() {
        return bookRepository.findAll();
    }

    @QueryMapping
    Optional<Book> bookById(@Argument long id) {
        return bookRepository.findById(id);
    }

    @MutationMapping
    Book addBook(@Argument BookInput bookInput) {
        Book book = new Book(bookInput.title(), bookInput.genre());

        for (long authorId: bookInput.authorIds()) {
            Author tempAuthor = authorRepository.findById(authorId).orElseThrow(() -> new IllegalArgumentException("Author with this ID not found!"));

            BookUtil.addAuthor(book, tempAuthor);
        }

        return bookRepository.save(book);
    }

    @MutationMapping
    Book updateBook(@Argument long id, @Argument BookInput bookInput) throws Exception {
        if (bookInput == null) throw new Exception("Updated body of book is empty!");

        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("A book with this ID does not exist!"));

        Book updatedBook = BookUtil.updateChangedBook(book, bookInput, authorRepository);

        return bookRepository.save(updatedBook);
    }

    @MutationMapping
    Book deleteBook(@Argument long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("A book with this ID does not exist!"));

        // removes association with the foreign key which makes it safe to delete
        book.setAuthors(null);

        bookRepository.delete(book);

        return book;
    }

}
