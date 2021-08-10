package com.projet.epargne.exceptions;

public class SoldeNegatifException extends RuntimeException {

    public SoldeNegatifException(String message) {
        super(message);
    }

    public SoldeNegatifException(String message, Exception e) {
        super(message, e);
    }
}
