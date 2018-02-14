package sugoispringboot.bookmark.port.secondary.persistence.memory;

import sugoispringboot.bookmark.domain.lifecycle.BookmarkRepository;
import sugoispringboot.bookmark.domain.model.Bookmark;

import java.util.List;

public class BookmarkRepositoryOnMemory implements BookmarkRepository {

  @Override
  public List<Bookmark> resolveAll() {
    // TODO 実装を書く
    return null;
  }

  @Override
  public Bookmark resolveByName(String name) {
    // TODO 実装を書く
    return null;
  }

  @Override
  public void save(Bookmark bookmark) {
    // TODO 実装を書く
  }

}
