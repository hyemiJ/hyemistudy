package org.example.model;

import java.util.Objects;

public class User {
    private String userId;
    private String userName;

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
    public boolean equalUser(User user) {
        return this.userId.equals(user.userId)
                && this.userName.equals(user.userName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(userName, user.userName);
    }
    @Override
    public int hashCode() {
        return Objects.hash(userId, userName);
    }
}
