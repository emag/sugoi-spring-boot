package sugoispringboot.bookmark.port.secondary.persistence.rdb;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import sugoispringboot.bookmark.domain.lifecycle.BookmarkRepository;
import sugoispringboot.bookmark.domain.model.Bookmark;

import java.util.List;

@Profile("prod")
@Repository
public class BookmarkRepositoryOnJDBC implements BookmarkRepository {

  private static final RowMapper<Bookmark> bookmarkRowMapper = (rs, i) -> new Bookmark(
    rs.getString("name"),
    rs.getString("url")
  );

  private final NamedParameterJdbcTemplate jdbcTemplate;

  public BookmarkRepositoryOnJDBC(NamedParameterJdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Bookmark> resolveAll() {
    return jdbcTemplate.query("SELECT name, url FROM bookmarks", bookmarkRowMapper);
  }

  @Override
  public Bookmark resolveByName(String name) {
    SqlParameterSource param = new MapSqlParameterSource().addValue("name", name);
    return jdbcTemplate.queryForObject(
      "SELECT name, url FROM bookmarks WHERE name = :name",
      param,
      bookmarkRowMapper);
  }

  @Override
  public void save(Bookmark bookmark) {
    SqlParameterSource param = new BeanPropertySqlParameterSource(bookmark);

    jdbcTemplate.update("INSERT INTO bookmarks(name, url) VALUES(:name, :url)", param);
  }

}
