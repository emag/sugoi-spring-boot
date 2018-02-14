package sugoispringboot.bookmark;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import sugoispringboot.bookmark.domain.lifecycle.BookmarkRepository;
import sugoispringboot.bookmark.domain.model.Bookmark;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
  properties = {
    "spring.profiles.active: prod",
    "spring.datasource.driver-class-name: net.sf.log4jdbc.DriverSpy",
    "spring.datasource.url: jdbc:log4jdbc:h2:mem:test-bookmark;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
    "logging.level.jdbc: off",
    "logging.level.jdbc.sqltiming: debug"
  }
)
public class BookmarkApplicationTest {

  @Autowired
  BookmarkRepository bookmarkRepository;

  @Autowired
  TestRestTemplate restTemplate;

  private Bookmark google;

  @Before
  public void setUp() {
    google = new Bookmark("Google", "https://google.com");
    bookmarkRepository.save(google);
  }

  @Test
  public void testGetBookmarks() throws Exception {
    ResponseEntity<List<Bookmark>> response = restTemplate.exchange(
      "/",
      HttpMethod.GET,
      null,
      new ParameterizedTypeReference<List<Bookmark>>() {
      });

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody().size()).isEqualTo(1);

    Bookmark bookmark = response.getBody().get(0);
    assertThat(bookmark.getName()).isEqualTo(google.getName());
    assertThat(bookmark.getUrl()).isEqualTo(google.getUrl());
  }

}