package sugoispringboot.bookmark.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sugoispringboot.bookmark.domain.lifecycle.BookmarkRepository;
import sugoispringboot.bookmark.domain.model.Bookmark;

import java.util.List;

@Service
public class BookmarkService {

  private final BookmarkRepository repository;

  public BookmarkService(BookmarkRepository repository) {
    this.repository = repository;
  }

  public List<Bookmark> resolveAll() {
    return repository.resolveAll();
  }

  public Bookmark resolveByName(String name) {
    return repository.resolveByName(name);
  }

  @Transactional
  public void save(Bookmark bookmark) {
    repository.save(bookmark);
  }

}
