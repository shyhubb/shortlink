# Link3s Shortlink Service Backend

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.3-green)
![Maven](https://img.shields.io/badge/Maven-3.x-red)
![MySQL](https://img.shields.io/badge/MySQL-8.0-orange)
![JWT](https://img.shields.io/badge/JWT-Authentication-yellow)
![Deployed On Render](https://img.shields.io/badge/Deployed%20on-Render-darkgreen)

Dự án Link3s Shortlink Service là một dịch vụ rút gọn URL mạnh mẽ và linh hoạt, được xây dựng bằng **Spring Boot**. Nó cung cấp các API để tạo, quản lý và chuyển hướng các liên kết rút gọn, hỗ trợ cả người dùng công khai và người dùng đã đăng ký.

---

## 🚀 Tính năng nổi bật

* **Rút gọn URL ngẫu nhiên:** Tạo các mã ngắn ngẫu nhiên cho URL dài.
* **Rút gọn URL tùy chỉnh:** Cho phép người dùng chọn mã ngắn riêng của họ (yêu cầu đăng nhập).
* **Giới hạn tần suất:** Giới hạn số lượng link tạo theo địa chỉ IP để ngăn chặn lạm dụng.
* **Xác thực mã ngắn:** Đảm bảo mã tùy chỉnh hợp lệ (ký tự, độ dài) và không xung đột với các đường dẫn nội bộ của hệ thống.
* **Quản lý link cá nhân:** Người dùng đã đăng ký có thể xem và quản lý các link họ đã tạo.
* **Xác thực người dùng:** Hệ thống đăng ký/đăng nhập an toàn với **JWT** (JSON Web Tokens).
* **Chuyển hướng hiệu quả:** Chuyển hướng nhanh chóng từ short link đến original link.

---

## 🛠️ Công nghệ sử dụng

### Backend

* **Ngôn ngữ:** Java 17
* **Framework:** Spring Boot 3.5.3
* **Quản lý Dependency:** Apache Maven
* **Cơ sở dữ liệu:** PostgreSQL (có thể cấu hình với MySQL)
* **ORM:** Spring Data JPA
* **Bảo mật:** Spring Security, JWT (jjwt-api, jjwt-impl, jjwt-jackson)
* **Utility:** Project Lombok, Apache Commons Validator

### Frontend (Giao diện người dùng)

**Lưu ý:** Phần frontend được gợi ý để xây dựng bằng **HTML, CSS và JavaScript thuần**, sử dụng **Bootstrap 5** cho giao diện.

---

## 📦 Cấu trúc Dự án

Dự án được tổ chức theo cấu trúc chuẩn của Spring Boot, với các package chính:

* `ltd.tinyurl.shortlink.controller`: Chứa các REST API endpoint.
* `ltd.tinyurl.shortlink.service`: Chứa logic nghiệp vụ chính của ứng dụng.
* `ltd.tinyurl.shortlink.repository`: Cung cấp các giao diện truy cập dữ liệu (Spring Data JPA).
* `ltd.tinyurl.shortlink.entity`: Định nghĩa các lớp Entity ánh xạ tới bảng trong cơ sở dữ liệu.
* `ltd.tinyurl.shortlink.dto`: Chứa các Data Transfer Object (DTO) cho Request và Response.
* `ltd.tinyurl.shortlink.webconstants`: Định nghĩa các hằng số được sử dụng trong toàn bộ ứng dụng (đường dẫn API, thông báo lỗi, giới hạn...).

---

## 🚀 Bắt đầu (Local Development)

### Yêu cầu

* Java 17 JDK
* Apache Maven
* Cơ sở dữ liệu PostgreSQL hoặc MySQL (đảm bảo dịch vụ DB đang chạy)

### Cài đặt

1.  **Clone repository:**

    ```bash
    git clone [URL_CỦA_REPOSITORY_CỦA_BẠN]
    cd shortlink
    ```

2.  **Cấu hình cơ sở dữ liệu:**
    * Mở file `src/main/resources/application.properties` (hoặc `application.yml`).
    * Cập nhật thông tin kết nối cơ sở dữ liệu của bạn. Dưới đây là ví dụ cho MySQL:

        ```properties
        # MySQL example
        spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name?useSSL=false&serverTimezone=UTC
        spring.datasource.username=your_username
        spring.datasource.password=your_password
        spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
        spring.jpa.hibernate.ddl-auto=update # hoặc create để tạo schema tự động
        spring.jpa.show-sql=true
        ```

3.  **Build dự án:**

    ```bash
    mvn clean install
    ```

4.  **Chạy ứng dụng:**

    ```bash
    mvn spring-boot:run
    ```

    Hoặc chạy file JAR sau khi build:

    ```bash
    java -jar target/shortlink-0.0.1-SNAPSHOT.jar
    ```

    Ứng dụng sẽ chạy trên cổng mặc định `8080` (hoặc cổng đã cấu hình trong `application.properties`).

---



## 🤝 Đóng góp

Đóng góp luôn được hoan nghênh! Vui lòng fork repository, tạo một nhánh mới cho tính năng của bạn và gửi Pull Request.
