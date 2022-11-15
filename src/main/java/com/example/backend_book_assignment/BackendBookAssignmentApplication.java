package com.example.backend_book_assignment;

import com.example.backend_book_assignment.entities.Author;
import com.example.backend_book_assignment.entities.Book;
import com.example.backend_book_assignment.entities.Genre;
import com.example.backend_book_assignment.repositories.AuthorRepository;
import com.example.backend_book_assignment.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SpringBootApplication
public class BackendBookAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendBookAssignmentApplication.class, args);
    }

    /**
     * This function inserts test data in the database
      * @param bookRepository
     * @param authorRepository
     * @return
     */
    @Bean
    CommandLineRunner commandLineRunner(BookRepository bookRepository, AuthorRepository authorRepository) {
        return args -> {
            ArrayList<Author> authorList = new ArrayList<>();
            String[] names = {"Paul Aldabagh", "Napoleon hill", "Tony Robbins"};

            for (String name: names) {
                Author tempAuthor = new Author();
                tempAuthor.setFirstName(name.split(" ")[0]);
                tempAuthor.setLastName(name.split(" ")[1]);
                tempAuthor.setDateOfBirth(new Date());
                authorList.add(tempAuthor);
            }

            Book book1 = new Book();
            book1.setTitle("How to get a job");
            book1.setGenre(Genre.NONFICTION);

            Book book2 = new Book();
            book2.setTitle("Think and grow rich");
            book2.setGenre(Genre.NONFICTION);

            Book book3 = new Book();
            book3.setTitle("Lord of the rings");
            book3.setGenre(Genre.FICTION);

            book1.setAuthors(List.of(authorList.get(0)));
            book2.setAuthors(List.of(authorList.get(0), authorList.get(1)));
            book3.setAuthors(List.of(authorList.get(0), authorList.get(2)));

            bookRepository.saveAll(List.of(
                    book1, book2, book3
            ));

            authorRepository.saveAll(authorList);

        };
    }

}
