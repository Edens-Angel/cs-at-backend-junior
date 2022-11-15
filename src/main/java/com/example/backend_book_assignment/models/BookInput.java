package com.example.backend_book_assignment.models;

import com.example.backend_book_assignment.entities.Genre;

import java.util.List;

public record BookInput(String title, Genre genre, List<Long> authorIds) {
}
