
### 1. Setting up the Spring Boot Project:
1. Start by creating a new Maven project in Spring Tool Suite (STS).
2. Add the following Maven dependencies to your `pom.xml`:

```xml
<!-- Spring Boot Web Starter -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- Spring Boot Data MongoDB -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>

<!-- Spring Boot Starter Test (for testing) -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>

<!-- Java 17 -->
<properties>
    <java.version>17</java.version>
</properties>
```

### 2. Configure MongoDB:

Make sure you have MongoDB installed and running.

1. In `src/main/resources/application.properties` or `application.yml`, configure the MongoDB connection:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/urlshortenerdb
```

### 3. Create Model, Repository, and Service:

1. **Model**: Create a class called `ShortenedUrl` with fields `id`, `originalUrl`, and `shortenedHash`.

2. **Repository**: Create an interface called `ShortenedUrlRepository` extending `MongoRepository<ShortenedUrl, String>`.

3. **Service**: Create a service class (`UrlShortenerService`) to handle the URL shortening and retrieving the original URL using the hash.

### 4. URL Shortening Logic:

In `UrlShortenerService`,

1. Use Java's built-in `MessageDigest` to get an MD5 hash of the original URL.
2. Convert the MD5 byte array to a hexadecimal string.
3. Take the first 8 characters of this string as your short hash.
4. Store the original URL with the hash in the database.
5. Return the hash to the client.

### 5. Controller:

1. Create a `UrlShortenerController`.
2. Have two endpoints:
   - POST `/shorten` - Takes the full URL as input, returns the shortened hash.
   - GET `/{hash}` - Takes the hash as a path variable, looks up the original URL in the database, and redirects to the original URL.

### 6. Running the Application:

1. Create a main class annotated with `@SpringBootApplication` to run your application.
2. Use `SpringApplication.run(YourMainClass.class, args);` to start the Spring Boot application.

### 7. Testing:

After launching the application, you can:

1. Send a POST request to `http://localhost:8080/shorten` with the full URL to get a shortened hash.
2. Use a browser or HTTP client to navigate to `http://localhost:8080/{hash}` and it should redirect to the original URL.

Remember to handle edge cases such as:
- What happens if the same URL is shortened multiple times? (You could check if the URL already exists in the database and reuse the hash.)
- Ensure that the short hash is unique. (The likelihood of MD5 hash collision for 8 characters from different URLs is low, but it could still happen. Make sure to handle this case.)

Hope this helps you set up your URL shortener!