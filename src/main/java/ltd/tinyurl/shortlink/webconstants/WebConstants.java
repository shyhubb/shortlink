package ltd.tinyurl.shortlink.webconstants;

public class WebConstants {
    private WebConstants() {
    }

    public static final String API_VERSION_V1 = "/v1";
    public static final String API_VERSION_V2 = "/v2";
    public static final String PUBLIC_SHORT_LINK_API_V1_PATH = API_VERSION_V1 + "/public/shortlink";
    public static final String PRIVATE_SHORT_LINK_API_V1_PATH = API_VERSION_V1 + "/user/link";
    public static final String AUTH_API_V1_PATH = API_VERSION_V1 + "/auth";
    public static final String USER_API_V1_PATH = API_VERSION_V1 + "/user";
    public static final String BASE_SHORT_URL_DOMAIN = "https://linkz.onrender.com";
    public static final String URL_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static final int LENGTH_KEY_URL = 6;
    public static final int MAX_ATTEMPTS_GENERATE_KEY = 10;
    public static final int CREATE_LINK_LIMIT = 100;
    public static final int TIME_CREATE_LINK_LIMIT = 24;
    public static final String LIMIT_CREATE_LINK_ERROR = "Bạn đã đạt giới hạn tạo Shortlink.";
    public static final String BASE_SUCCESS = "Thành công.";
    public static final String BASE_FAIL = "Thất bại.";
    public static final String ERROR_SHORTLINK_START_WITH_BASE_URL = "Không được phép rút gọn liên kết gốc server.";
    // for User service
    public static final String PASSWORD_OR_ACCOUNT_NOT_EXACTLY = "Tài khoản hoặc mật khẩu không đúng.";
    public static final String CONFIRM_PASSWORD_NOT_MACTH = "Mật khẩu xác nhận không khớp.";
    public static final String ACCOUNT_EXITS = "Tài khoản này đã tồn tại.";
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";
    public static final String SYSTEM_ERROR_FIND_ROLE = "Lỗi không tìm thấy quyền tài khoản.";
    public static final String SHORT_CODE_EXISTS_ERROR = "Mã rút gọn đã tồn tại.";
    public static final String SHORT_CODE_LENGTH_ERROR = "Mã rút gọn phải có ít nhất " + LENGTH_KEY_URL + " ký tự.";
    public static final String SHORT_CODE_INVALID_CHARACTERS_ERROR = "Mã custom shortlink không hợp lệ, vui lòng thử lại.";
    public static final String DONT_HAVE_SHORTLINK_IN_SYSTEM = "Bạn chưa tạo shortlink nào.";
    // error
    public static final String SHORT_LINK_NOT_FOUND_MESSAGE = "Không tìm thấy short link";
}