package com.revature.foundations.services;

import com.revature.foundations.daos.CrudDAO;
import com.revature.foundations.models.Users;

public class UsersService {
    private CrudDAO<Users> usersDao;

    public UsersService(CrudDAO<Users> usersDao) {
        this.usersDao = usersDao;
    }
}
