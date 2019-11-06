package com.uifuture.chapter12.entity;


import com.uifuture.chapter12.base.BaseEntity;

/**
 * table name: users
 *
 * @author chenhaoxiang 2018-09-15
 */
public class Users extends BaseEntity {
    /**
     * fields name: users.username
     */
    private String username;

    /**
     * fields name: users.password
     */
    private String password;

    /**
     * fields name: users.age
     */
    private Integer age;

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Users{");
        sb.append(super.toString());
        sb.append(",");
        sb.append("username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }
}