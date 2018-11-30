package com.testjpa;

import java.util.List;

public interface IUserService
{
    public List<User> findAll();

    public void saveUser(User book);
   
    public User findOne(long id);

    public void delete(long id);

    public List<User> findByName(String name);

}