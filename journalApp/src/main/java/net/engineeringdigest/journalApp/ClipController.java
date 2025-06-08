package net.engineeringdigest.journalApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Base64;

@RestController
@RequestMapping("/api/clip")
@CrossOrigin(origins = "*")
public class ClipController {

    @Autowired
    private ClipRepository clipRepository;

    private static final SecureRandom random = new SecureRandom();

    private String generateUniqueToken() {
        String token;
        do {
            token = String.format("%04d", random.nextInt(10000)); // generates 0000 to 9999
        } while (clipRepository.findByToken(token) != null); // check uniqueness
        return token;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveClip(@RequestBody Clip clip) {
        String token = generateUniqueToken();
        clip.setToken(token);
        clipRepository.save(clip);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/get/{token}")
    public ResponseEntity<?> getClip(@PathVariable String token) {
        Clip clip = clipRepository.findByToken(token);
        if (clip == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clip.getContent());
    }
}
