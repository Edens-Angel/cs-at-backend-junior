package com.example.backend_book_assignment.utilities;

import com.example.backend_book_assignment.entities.Author;
import com.example.backend_book_assignment.entities.Book;
import com.example.backend_book_assignment.models.BookInput;
import com.example.backend_book_assignment.repositories.AuthorRepository;

import java.util.ArrayList;

public class BookUtil {

    private BookUtil () {}

    public static Book updateChangedBook(Book book, BookInput bookUpdate, AuthorRepository authorRepository) {
        boolean isTitleUpdated = bookUpdate.title() != null && !bookUpdate.title().equals(book.getTitle());
        boolean isGenreUpdated = bookUpdate.genre() != null && !bookUpdate.genre().equals(book.getGenre());
        boolean isAuthorUpdated = bookUpdate.authorIds() != null;

        if (isTitleUpdated) {
            book.setTitle(bookUpdate.title());
        }

        if (isGenreUpdated ) {
            book.setGenre(bookUpdate.genre());
        }

        if (isAuthorUpdated) {
            ArrayList<Author> updatedAuthors = new ArrayList<>();

            for (long authorId: bookUpdate.authorIds()){
                Author tempAuthor = authorRepository.findById(authorId).orElseThrow(() -> new IllegalArgumentException("An book with this ID does not exist!"));
                updatedAuthors.add(tempAuthor);
            }
            book.setAuthors(updatedAuthors);
        }

        return book;
    }

    /**
     * adds a single author for a book
     * @param book
     * @param author
     */
    public static void addAuthor(Book book, Author author) {
        if (book.getAuthors() == null) {
            book.setAuthors(new ArrayList<>());
        }

        book.getAuthors().add(author);
    }

    /**
     * adds a single book for an author
     * @param author
     * @param books
     */
    public static void addBook(Author author, Book books) {
        if (author.getBooks() == null) {
            author.setBooks(new ArrayList<>());
        }

        author.getBooks().add(books);
    }
}
