# Spring Security Database Authentication

A complete Spring Boot project demonstrating database authentication using Spring Security with MySQL.

---

# 🚀 Features

* Spring Security Authentication
* Database Authentication using MySQL
* Custom UserDetailsService
* DaoAuthenticationProvider
* BCrypt Password Encryption
* Role-Based Authorization
* Secure REST APIs
* Modern SecurityFilterChain Configuration

---

# 🛠️ Technologies Used

* Java 17
* Spring Boot 3
* Spring Security 6
* Spring Data JPA
* MySQL
* Maven

---

# 📁 Project Structure

```text
src/main/java

├── config
│   └── SecurityConfig.java

├── controller
│   └── UserController.java

├── entity
│   └── User.java

├── repository
│   └── UserRepository.java

├── service
│   └── CustomUserDetailsService.java

└── SpringSecurityApplication.java
```

---

# 📦 Dependencies

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
</dependency>
```

---

# ⚙️ Database Configuration

## application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/security_db

spring.datasource.username=root

spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true
```

---

# 👤 User Entity

```java
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String role;
}
```

---

# 📂 Repository Layer

```java
public interface UserRepository
extends JpaRepository<User, Long> {

    Optional<User>
    findByUsername(String username);
}
```

---

# 🔐 Custom UserDetailsService

```java
@Service
public class CustomUserDetailsService
implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(
            String username)
            throws UsernameNotFoundException {

        User user =
                userRepository
                .findByUsername(username)

                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User Not Found"
                        )
                );

        return org.springframework.security
                .core.userdetails.User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
```

---

# 🛡️ Security Configuration

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService
            customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider
    authenticationProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();

        provider.setUserDetailsService(
                customUserDetailsService
        );

        provider.setPasswordEncoder(
                passwordEncoder()
        );

        return provider;
    }

    @Bean
    public SecurityFilterChain
    securityFilterChain(HttpSecurity http)
            throws Exception {

        http

            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                    .requestMatchers("/admin/**")
                    .hasRole("ADMIN")

                    .requestMatchers("/user/**")
                    .hasAnyRole("USER","ADMIN")

                    .anyRequest()
                    .authenticated()
            )

            .formLogin(withDefaults());

        return http.build();
    }
}
```

---

# 🔄 Authentication Flow

```text
Client Request
      ↓
SecurityFilterChain
      ↓
Authentication Filter
      ↓
DaoAuthenticationProvider
      ↓
CustomUserDetailsService
      ↓
Database
      ↓
PasswordEncoder
      ↓
Authentication Success
      ↓
Authorization
      ↓
Controller
```

---

# 🔑 Password Encryption

Passwords are encrypted using:

```java
BCryptPasswordEncoder
```

Example:

```java
passwordEncoder.encode("admin123");
```

---

# 🌐 API Endpoints

| Endpoint  | Access      |
| --------- | ----------- |
| /admin/** | ADMIN       |
| /user/**  | USER, ADMIN |

---

# ▶️ How to Run the Project

## Clone Repository

```bash
git clone <repository-url>
```

## Build Project

```bash
mvn clean install
```

## Run Application

```bash
mvn spring-boot:run
```

---

# 🧪 Test Credentials

| Username | Password | Role  |
| -------- | -------- | ----- |
| admin    | admin123 | ADMIN |

---

# 📚 Concepts Covered

* Spring Security Architecture
* SecurityFilterChain
* Authentication & Authorization
* Custom UserDetailsService
* DaoAuthenticationProvider
* PasswordEncoder
* BCrypt Encryption
* Role-Based Authentication

---

# 🚀 Future Enhancements

* JWT Authentication
* Refresh Token
* OAuth2 Login
* Role & Permission Management
* Exception Handling
* Swagger Documentation
* Docker Deployment

---

# 👨‍💻 Author

Sharad Yadav
