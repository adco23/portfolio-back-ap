package com.portfolio.AC.services;

import com.portfolio.AC.models.User;
import com.portfolio.AC.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    public List<User> userList() throws Exception {
        try {
            return userRepository.findAll();
        } catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public User saveUser(User user) throws Exception {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Optional<User> getUserById(Long id) throws Exception {
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public boolean existUserById(Long id) throws  Exception {
        try {
            return userRepository.existsById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void deleteUser(Long id) throws Exception {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Optional<User> findByEmail(String email) throws Exception {
        try {
            return userRepository.findByEmail(email);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
