# Dự án Rút Gọn Liên Kết (Shortlink Project)

Dự án này là một ứng dụng web để rút gọn liên kết, quản lý người dùng, tỷ lệ CPM và ví tiền. Nó cung cấp các API để tạo liên kết rút gọn, quản lý người dùng, và xử lý các giao dịch liên quan đến ví.

## Tính năng chính

* **Rút gọn liên kết:** Tạo các liên kết ngắn từ các URL dài.
* **Liên kết tùy chỉnh:** Cho phép người dùng tạo các liên kết rút gọn với tên tùy chỉnh.
* **Quản lý người dùng:** Đăng ký, đăng nhập, quản lý hồ sơ và thay đổi mật khẩu người dùng.
* **Quản lý quản trị (Admin):** Các chức năng quản lý người dùng và các thiết lập hệ thống cho quản trị viên.
* **Tỷ lệ CPM:** Quản lý và tính toán tỷ lệ CPM (Cost Per Mille) cho các liên kết.
* **Quản lý ví:** Theo dõi số dư, giao dịch của người dùng.
* **API công khai và bảo mật:** Cung cấp các API cho cả truy cập công khai và yêu cầu xác thực.

## Công nghệ sử dụng

* **Ngôn ngữ lập trình:** Java
* **Framework:** Spring Boot
* **Kiến trúc:** RESTful API
* **Cơ sở dữ liệu:** (Giả định: MySQL/PostgreSQL hoặc tương tự, cần cấu hình cụ thể)
* **Công cụ xây dựng:** (Giả định: Maven hoặc Gradle, cần file `pom.xml` hoặc `build.gradle`)

## Cấu trúc dự án (Tổng quan)

Dự án được tổ chức theo các thành phần chính:

* **Controllers:** Xử lý các yêu cầu HTTP đến, định tuyến và gọi các dịch vụ phù hợp.
    * `AdminController`, `AuthController`, `CpmRateController`, `GetLinkController`, `PublicShortlinkController`, `ShortlinkController`, `UserController`.
* **DTOs (Data Transfer Objects):** Các lớp dùng để truyền dữ liệu giữa client và server (Request/Response).
    * `ChangePasswordRequest`, `CustomLinkRequest`, `LinkRequest`, `LoginRequest`, `ProfileRequest`, `UserRequest`.
    * `BaseResponse`, `CpmRateResponse`, `CreateLinkResponse`, `LoginResponse`.
* **Entities:** Đại diện cho các bảng trong cơ sở dữ liệu.
    * `CpmRate`, `GetLink`, `Link`, `Role`, `User`, `Wallet`.
* **Services:** Chứa logic nghiệp vụ chính của ứng dụng.
    * `AuthService`, `CpmRateService`, `GetLinkService`, `PublicShortLinkService`, `ShortlinkService`, `UserService`, `WalletService`.
* **Constants:** Chứa các hằng số được sử dụng trong ứng dụng.
    * `WebConstants`.

## Cài đặt và Chạy dự án

Để chạy dự án này trên môi trường phát triển của bạn, hãy làm theo các bước sau:

1.  **Yêu cầu:**
    * Java Development Kit (JDK) 11 trở lên.
    * Maven hoặc Gradle (tùy thuộc vào công cụ xây dựng dự án).
    * Một hệ quản trị cơ sở dữ liệu (ví dụ: MySQL, PostgreSQL) và một công cụ quản lý cơ sở dữ liệu.

2.  **Clone repository:**
    ```bash
    git clone <URL_DỰ_ÁN_CỦA_BẠN>
    cd <TÊN_THƯ_MỤC_DỰ_ÁN>
    ```

3.  **Cấu hình cơ sở dữ liệu:**
    * Tạo một cơ sở dữ liệu mới cho dự án.
    * Cập nhật tệp `application.properties` hoặc `application.yml` (trong `src/main/resources`) với thông tin kết nối cơ sở dữ liệu của bạn (URL, username, password).
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name?useSSL=false&serverTimezone=UTC
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update # hoặc none, validate, create
    spring.jpa.show-sql=true
    ```

4.  **Xây dựng dự án:**
    * Sử dụng Maven:
        ```bash
        mvn clean install
        ```
    * Sử dụng Gradle:
        ```bash
        gradle clean build
        ```

5.  **Chạy ứng dụng:**
    * Sau khi xây dựng thành công, bạn có thể chạy file JAR được tạo ra:
        ```bash
        java -jar target/your-project-name.jar # Đối với Maven
        java -jar build/libs/your-project-name.jar # Đối với Gradle
        ```
    * Hoặc chạy trực tiếp từ IDE của bạn (IntelliJ IDEA, Eclipse).

Ứng dụng sẽ chạy trên cổng mặc định 8080 (hoặc cổng bạn cấu hình).

## API Endpoints (Tổng quan)

Dưới đây là một số ví dụ về các nhóm API có thể có dựa trên tên controllers:

* `/api/auth`: Đăng ký, đăng nhập người dùng.
* `/api/users`: Quản lý hồ sơ người dùng.
* `/api/admin`: Các API dành cho quản trị viên (yêu cầu quyền admin).
* `/api/links`: Tạo, quản lý các liên kết rút gọn.
* `/api/public/shortlinks`: Rút gọn liên kết công khai.
* `/api/cpm-rates`: Quản lý tỷ lệ CPM.
* `/api/get-link`: Truy xuất liên kết gốc từ liên kết rút gọn.

## Đóng góp

Để đóng góp cho dự án này, vui lòng fork repository và tạo một pull request.
