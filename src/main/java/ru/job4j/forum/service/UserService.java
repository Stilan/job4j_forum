package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserService {
    private static final AtomicInteger POST_ID = new AtomicInteger();

    private final Map<Integer, User> users = new HashMap<>();

    public UserService() {
        users.put(1, new User("w", "w"));
    }

    public List<User> getUsers() {
        return (List<User>) users.values();
    }


    public void save(User user) {
        if (user.getId() == 0) {
            user.setId(POST_ID.incrementAndGet());
            users.put(user.getId(), user);
        }
        users.replace(user.getId(), user);
    }

    public User getUserPasswordUsername(String password, String username) {
        User getUser = null;
        for (User user : users.values()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                getUser = user;
            }
        }
        return getUser;
    }


}
