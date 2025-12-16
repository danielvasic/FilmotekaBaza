package ba.sum.fpmoz.filmotekabaza.controller;

import ba.sum.fpmoz.filmotekabaza.auth.JWTUtil;
import ba.sum.fpmoz.filmotekabaza.models.User;
import ba.sum.fpmoz.filmotekabaza.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam String email, @RequestParam String password) {
        // tražimo korisnika po email adresi
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // provjera lozinke: uspoređujemo plain-text s hashiranom lozinkom iz baze
        if (passwordEncoder.matches(password, user.getPassword())) {
            // Preuzimamo uloge korisnika i pretvaramo ih u listu stringova
            System.out.println("User roles from DB: " + user.getRoles());
            List<String> roles = user.getRoles().stream()
                    .map(role -> role.getName())
                    .collect(Collectors.toList());
            System.out.println("Roles being added to JWT: " + roles);

            String accessToken = jwtUtil.generateToken(user.getEmail(), roles);
            String refreshToken = jwtUtil.generateRefreshToken();
            user.setRefreshToken(refreshToken);
            userRepository.save(user);
            // ako je lozinka ispravna, generiramo token
            return ResponseEntity.ok(
                    Map.of("accessToken", accessToken,
                            "refreshToken", refreshToken)
            );
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken (@RequestParam String refreshToken) {
        User user = userRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));
        
        // Preuzimamo uloge korisnika za novi token
        List<String> roles = user.getRoles().stream()
                .map(role -> role.getName())
                .collect(Collectors.toList());
        
        String newAccessToken = jwtUtil.generateToken(user.getEmail(), roles);
        return ResponseEntity.ok(
                Map.of("accessToken", newAccessToken)
        );
    }

}

