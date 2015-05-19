import org.junit.Test;
import play.twirl.api.Content;

import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.contentType;


/**
 * Simple (JUnit) tests that can call all parts of a play app.
 * If you are interested in mocking a whole application, see the wiki for more details.
 */
public class ApplicationTest {
    @Test
    public void renderTemplate() {
        Map<Integer, String> commodities = new HashMap<>();
        commodities.put(5, "Disher");
        Content html = views.html.Commodities.commodity.render(commodities);
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("Disher");
    }
}
