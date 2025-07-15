package ltd.tinyurl.shortlink.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.http.HttpHeaders; // Thêm import này

@RestController
@RequestMapping // Xóa "/url" ở đây để có thể dùng đường dẫn gốc
public class ShortlinkController {

    // logic chuyen huong url goc
    @GetMapping("/youtube")
    public ResponseEntity<Void> redirectToYoutube() throws URISyntaxException {
        System.out.println("Đang chuyển hướng đến YouTube bằng ResponseEntity...");
        URI youtubeUri = new URI("https://www.youtube.com");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(youtubeUri);
        // Trả về ResponseEntity với mã 302 Found và header Location
        return new ResponseEntity<>(httpHeaders, HttpStatus.FOUND);
    }

}