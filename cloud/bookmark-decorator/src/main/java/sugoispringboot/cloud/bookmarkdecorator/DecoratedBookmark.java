package sugoispringboot.cloud.bookmarkdecorator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
class DecoratedBookmark {
  private String name;
  private String url;
  private String notes;
}
