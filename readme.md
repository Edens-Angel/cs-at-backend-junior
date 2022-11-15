# getting started

## Requirements

This application uses java 17. You should have a jdk with java 17 on your device to run this application.  
Besides java you will also need Maven to run this project. I am using Maven 3.8.6.  
As editor of choice I am using IntellJ.

### To start the application, please follow the next steps.

1. Clone the repository
2. Navigate in your terminal to the root directory of this project
3. Run the command `mvn spring-boot:run`
4. You can query the API using GraphQL in the following link `http://localhost:8080/graphiql?path=/graphql`

### API docs

The API have the following queries and mutations.

#### Queries

    - books
    - bookById
    - authors
    - authorById

#### Mutations

    - addBook(bookInput: BookInput): Book
    - updateBook(id: ID!, bookInput: BookUpdateInput): Book
    - deleteBook(id: ID!): Book
    - addAuthor(authorInput: AuthorInput): Author

More information about the schema of the API can be found [here](src/main/resources/graphql/schema.graphqls)
