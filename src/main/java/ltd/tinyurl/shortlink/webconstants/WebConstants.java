package ltd.tinyurl.shortlink.webconstants;

public class WebConstants {
    private WebConstants() {
    }

    public static final String API_VERSION_V1 = "/v1";
    public static final String API_VERSION_V2 = "/v2";

    public static final String PUBLIC_SHORT_LINK_API_V1_PATH = API_VERSION_V1 + "/public/shortlink";
    public static final String PRIVATE_SHORT_LINK_API_V1_PATH = API_VERSION_V1 + "/link";

    public static final String BASE_SHORT_URL_DOMAIN = "http://localhost:8080/";

    public static final String URL_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static final int LENGTH_KEY_URL = 6;

    public static final int MAX_ATTEMPTS_GENERATE_KEY = 10;
}