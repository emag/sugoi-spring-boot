package sugoispringboot.bookmark.domain.lifecycle;

import sugoispringboot.bookmark.domain.model.Bookmark;

import java.util.List;

public interface BookmarkRepository {

  List<Bookmark> resolveAll();

  Bookmark resolveByName(String name);

  void save(Bookmark bookmark);

}
