package sugoispringboot.bookmark.port.primary.http;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import sugoispringboot.bookmark.application.BookmarkService;
import sugoispringboot.bookmark.domain.model.Bookmark;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/")
public class BookmarkController {

  private final BookmarkService service;

  public BookmarkController(BookmarkService service) {
    this.service = service;
  }

  @GetMapping
  List<Bookmark> getBookmarks() {
    return service.resolveAll();
  }

  @GetMapping(path = "{name}")
  Bookmark getBookmark(@PathVariable String name) {
    return service.resolveByName(name);
  }

  @PostMapping
  ResponseEntity<Bookmark> postBookmark(@RequestBody Bookmark bookmark, UriComponentsBuilder uriBuilder) {
    service.save(bookmark);
    URI location = uriBuilder.path("/{name}").buildAndExpand(bookmark.getName()).toUri();
    return ResponseEntity.created(location).body(bookmark);
  }

}
