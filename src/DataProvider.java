import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;


public class DataProvider {

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public Document GetDocument() throws IOException, ParserConfigurationException, InterruptedException, SAXException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.nbp.pl/kursy/xml/lasta.xml"))
                .timeout(Duration.ofMinutes(1))
                .GET()
                .build();

        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        String XMLBody = response.body();

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource src = new InputSource();
        src.setCharacterStream(new StringReader(XMLBody));
        return builder.parse(src);
    }
}
