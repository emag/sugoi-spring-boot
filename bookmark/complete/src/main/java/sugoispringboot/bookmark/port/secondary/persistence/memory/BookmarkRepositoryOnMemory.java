package sugoispringboot.bookmark.port.secondary.persistence.memory;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import sugoispringboot.bookmark.domain.lifecycle.BookmarkRepository;
import sugoispringboot.bookmark.domain.model.Bookmark;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Profile("demo")
@Repository
public class BookmarkRepositoryOnMemory implements BookmarkRepository {

  private Map<String, Bookmark> storage = new ConcurrentHashMap<>();

  @Override
  public List<Bookmark> resolveAll() {
    List<Bookmark> bookmarks = new ArrayList<>(storage.values());
    return Collections.unmodifiableList(bookmarks);
  }

  @Override
  public Bookmark resolveByName(String name) {
    return storage.get(name);
  }

  @Override
  public void save(Bookmark bookmark) {
    storage.put(bookmark.getName(), bookmark);
  }

}
