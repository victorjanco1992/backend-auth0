package org.example.controller;

import org.example.model.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final List<Image> images = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public ImageController() {
        // Imágenes de ejemplo
        images.add(new Image(counter.incrementAndGet(), "https://picsum.photos/id/237/300/200"));
        images.add(new Image(counter.incrementAndGet(), "https://picsum.photos/id/238/300/200"));
        images.add(new Image(counter.incrementAndGet(), "https://picsum.photos/id/239/300/200"));
        images.add(new Image(counter.incrementAndGet(), "https://picsum.photos/id/240/300/200"));
        images.add(new Image(counter.incrementAndGet(), "https://picsum.photos/id/241/300/200"));
        images.add(new Image(counter.incrementAndGet(), "https://picsum.photos/id/242/300/200"));
    }

    // Obtener todas las imágenes (incluidas eliminadas para admin)
    @GetMapping
    @PreAuthorize("hasAnyRole('user', 'admin')")
    public List<Image> getAllImages() {
        return images;
    }

    // Eliminar (simulado) una imagen
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<?> deleteImage(@PathVariable Long id) {
        for (Image img : images) {
            if (img.getId().equals(id)) {
                img.setDeleted(true);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    // Restaurar una imagen eliminada
    @PatchMapping("/{id}/restore")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Void> restoreImage(@PathVariable Long id) {
        Optional<Image> optional = images.stream()
                .filter(img -> img.getId().equals(id))
                .findFirst();

        if (optional.isPresent()) {
            optional.get().setDeleted(false);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
