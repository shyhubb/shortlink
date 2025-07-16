// ltd.tinyurl.shortlink.config.WebConfig.java
package ltd.tinyurl.shortlink.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Áp dụng CORS cho tất cả các đường dẫn API
                .allowedOrigins("*") // <-- Thay đổi ở đây: cho phép TẤT CẢ các nguồn gốc
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Cho phép các phương thức HTTP này
                .allowedHeaders("*") // Cho phép tất cả các header
        // .allowCredentials(true) // <-- LƯU Ý: Không thể dùng với .allowedOrigins("*")
        // Nếu bạn dùng .allowedOrigins("*"), bạn phải bỏ .allowCredentials(true)
        // hoặc thay thế "*" bằng một danh sách các nguồn gốc cụ thể.
        // Trình duyệt sẽ không cho phép allowCredentials(true) khi origin là "*"
        ;
    }
}