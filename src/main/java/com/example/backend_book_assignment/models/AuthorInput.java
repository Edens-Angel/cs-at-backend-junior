package com.example.backend_book_assignment.models;

import java.util.List;

public record AuthorInput(String firstName, String lastName, String dateOfBirth, List<Long> bookIds) {
}
