package com.example.backend_book_assignment;

import com.example.backend_book_assignment.entities.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

@GraphQlTest
public class BookControllerTests {

    @MockBean
    private GraphQlTester graphQlTester;


    private String fetchAllBooksQuery = """
            query {
                books {
                    id
                    title
                    genre                
                }
            }
            """;

    @Test
    void testFindAllBook() {
        graphQlTester.document(fetchAllBooksQuery).execute().path("book ").entityList(Book.class).hasSize(3);
    }


    @Test
    void testFindBookById() {
        String query = """
                query {
                    bookById(id: 2) {
                        id
                        title
                    }
                }""";

        graphQlTester.document(query).execute().path("bookById").entityList(Book.class).hasSize(1);
    }

    @Test
    void testAddBook() {
        String query = """
                 mutation {
                   addBook(bookInput: {title: "newBook", authorIds: [2], genre: FICTION}) {
                     id
                     title
                   }
                 }
                """;

        graphQlTester.document(query).execute().path("addBook");
        graphQlTester.document(fetchAllBooksQuery).execute().path("Book").entityList(Book.class).hasSize(4);
    }

    @Test
    void testUpdateBook () {

        String query = """
                 mutation {
                   updateBook(id: 3, bookInput: {title: "How to get hired at cs-at"}) {
                     title
                   }
                 }
                """;
        Book updatedBook = graphQlTester.document(query).execute().path("updateBook").entity(Book.class).get();
        Assertions.assertEquals("How to get hired at cs-at", updatedBook.getTitle());
    }

    @Test
    void testDeleteBook () {
        String query = """
                 mutation{
                   deleteBook(id:3){
                     title
                   }
                 }
                """;

        graphQlTester.document(query).execute();
        graphQlTester.document(fetchAllBooksQuery).execute().path("deleteBook").entityList(Book.class).hasSize(2);
    }
}
