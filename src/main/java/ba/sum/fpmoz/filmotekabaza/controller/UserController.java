package ba.sum.fpmoz.filmotekabaza.controller;

import ba.sum.fpmoz.filmotekabaza.models.User;
import ba.sum.fpmoz.filmotekabaza.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/user/create")
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping("/user/{id}")
    public Optional<User> getUserById(@PathVariable Long id){
        return userRepository.findById(id);
    }

    @DeleteMapping("/user/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
    }

    @PutMapping("/user/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        Optional<User> dbUser = userRepository.findById(id);
        if (dbUser.isPresent()){
            User existingUser = dbUser.get();
            existingUser.setName(user.getName());
            existingUser.setLastname(user.getLastname());
            existingUser.setEmail(user.getEmail());
            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }

    @GetMapping("/user/all")
    public Iterable<User> getAllUsers () {
        return userRepository.findAll();
    }
}
