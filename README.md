# MagicFridge AI

MagicFridge AI is a smart application that suggests recipes based on the items you have in your fridge.  
It leverages **Spring Boot (WebFlux)** for the backend, **H2 Database** for item storage, **Flyway** for database migrations, and the **Gemini API** to generate recipe suggestions using AI.

---

## 🚀 Features
- Add fridge items through a simple web interface.
- Store items in an **H2 in-memory database**.
- Automatic database migrations handled by **Flyway**.
- Generate recipe suggestions using **Gemini API** (LLM-powered).
- Reactive backend with **Spring WebFlux**.

---

## 🛠️ Tech Stack
- **Java 21+**
- **Spring Boot**
- **Spring WebFlux**
- **H2 Database**
- **Flyway** (for database versioning)
- **Thymeleaf** (for minimal frontend)
- **Gemini API** (LLM for recipe generation)

---

## ⚙️ Setup

### 1. Clone the repository
```bash
git clone https://github.com/phgoncalves2603/MagicFridge-AI.git
cd magicfridge-ai
```

### 2. Configure environment variables
Create a `.env` file or configure your environment with:
```
DATABASE_URL=jdbc:h2:mem:magicfridge
DATABASE_USERNAME=sa
DATABASE_PASSWORD=
GEMINI_API_KEY=your_api_key_here
```

### 3. Flyway migrations
Place all migration scripts in:
```
src/main/resources/db/migration/
```
- Example: `V1__create_items_table.sql`  
Flyway will run them automatically at startup.

### 4. Run the application
```bash
./mvnw spring-boot:run
```

### 5. Access the app
- Frontend: [http://localhost:8080](http://localhost:8080)  
- H2 Console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

---

## 📂 Project Structure
```
magicfridge-ai/
 ├── src/main/java/dev/magicfridge/   # Java source code
 │   ├── controller/                  # Web controllers
 │   ├── service/                     # Business logic
 │   ├── repository/                  # Database repositories
 │   └── model/                       # Entities
 ├── src/main/resources/
 │   ├── templates/                   # Thymeleaf templates
 │   ├── db/migration/                # Flyway migration scripts
 │   └── application.properties       # Spring config
 └── README.md
```


