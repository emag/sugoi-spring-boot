package sugoispringboot.bookmark.domain.model;

import java.util.Objects;

public class Bookmark {

  private String name;
  private String url;

  public Bookmark(String name, String url) {
    this.name = name;
    this.url = url;
  }

  public Bookmark() {}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Bookmark bookmark = (Bookmark) o;
    return Objects.equals(name, bookmark.name) &&
      Objects.equals(url, bookmark.url);
  }

  @Override
  public int hashCode() {

    return Objects.hash(name, url);
  }

  @Override
  public String toString() {
    return "Bookmark{" +
      "name='" + name + '\'' +
      ", url='" + url + '\'' +
      '}';
  }

}
