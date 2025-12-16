package ba.sum.fpmoz.filmotekabaza.controller;

import ba.sum.fpmoz.filmotekabaza.models.Role;
import ba.sum.fpmoz.filmotekabaza.models.User;
import ba.sum.fpmoz.filmotekabaza.repositories.RoleRepository;
import ba.sum.fpmoz.filmotekabaza.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @PostMapping("/assign-role")
    public ResponseEntity<?> assignRole (@RequestParam String email, @RequestParam String role) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Role roleObj = roleRepository.findByName(role);

        user.getRoles().add(roleObj);
        userRepository.save(user);
        return ResponseEntity.ok("Uloga promjenjena");
    }
}
