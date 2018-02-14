package sugoispringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloWorldApplication {

  // TODO ここを埋める
  String home() {
    return null;
  }

  public static void main(String[] args) {
    SpringApplication.run(HelloWorldApplication.class, args);
  }

}
