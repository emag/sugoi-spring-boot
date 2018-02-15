package sugoispringboot.cloud.bookmarkdecorator;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class BookmarkDecoratorController {

  @LoadBalanced
  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Autowired
  private RestTemplate restTemplate;

  @HystrixCommand(fallbackMethod = "defaultResponse")
  @GetMapping(path = "{name}")
  DecoratedBookmark get(@PathVariable String name) {
    DecoratedBookmark bookmark = restTemplate.getForObject("http://bookmark/" + name, DecoratedBookmark.class);
    bookmark.setNotes("This is notes.");
    return bookmark;
  }

  public DecoratedBookmark defaultResponse(String name) {
    return new DecoratedBookmark(
      name, "http://example.com", "Hmm, something wrong?"
    );
  }

}
