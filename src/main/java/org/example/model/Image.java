package org.example.model;

public class Image {
    private Long id;
    private String url;
    private boolean deleted;

    public Image(Long id, String url) {
        this.id = id;
        this.url = url;
        this.deleted = false;
    }

    // Getters y setters
    public Long getId() { return id; }
    public String getUrl() { return url; }
    public boolean isDeleted() { return deleted; }

    public void setId(Long id) { this.id = id; }
    public void setUrl(String url) { this.url = url; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }
}
