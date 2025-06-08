package net.engineeringdigest.journalApp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clips")
public class Clip {

    @Id
    private String id;       // MongoDB ObjectId

    private String token;    // unique token string to retrieve clip
    private String content;  // the clipboard text content

    public Clip() {}

    public Clip(String token, String content) {
        this.token = token;
        this.content = content;
    }

    // getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
