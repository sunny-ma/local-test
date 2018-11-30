package com.springcloudtest.dao.entity;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Data
@Entity
@NamedQuery(name = "User.findByName", query = "select name,address from User u where u.name=?1")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    long id;
    @Column(name = "name")
    String name;
    @Column(name = "address")
    String address;

}