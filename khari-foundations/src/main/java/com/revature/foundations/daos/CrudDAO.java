package com.revature.foundations.daos;

import com.revature.foundations.models.User;

import java.util.List;

public interface CrudDAO<T> {
    /*T[] getAll();

    T getById(String id);

    void save(T newObj);

    void update(T updatedObj);

    void deleteById(String id);*/

    void save(T newObject);
    T getById(String id);

    static List<User> getAll() {
        return null;
    }

    void update(T updatedObject);
    void deleteById(String id);
}
