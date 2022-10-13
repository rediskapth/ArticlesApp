package ua.my.exception;

import java.util.UUID;

public class ArticleNotFoundException extends RuntimeException {

    public ArticleNotFoundException(String message, UUID id) {
        super(message);
    }
}
