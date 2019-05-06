package com.football.manager.util;

import com.football.manager.exception.WrongIdException;

public class Validator {

    public static void validateId(Long id) {
        if (id <= 0) {
            throw new WrongIdException("Not valid id '" + id + "'");
        }
    }
}
