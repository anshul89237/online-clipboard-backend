package net.engineeringdigest.journalApp;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClipRepository extends MongoRepository<Clip, String> {
    Clip findByToken(String token);
}
