# CampusNest 🎓

**CampusNest** is a modern, feature-rich Android mobile application built with **Java** and **XML**. It serves as a unified campus management platform for students, providing instant access to course schedules, assignments, campus messages, notices, and profile management with full offline persistence, real-time dynamic updates, dark/light theme switching, and English/Urdu bilingual localization.

---

## 🌟 Key Features

### 🔐 Authentication & Session Persistence
- **Splash Screen**: Animated logo transition on startup; auto-restores saved theme preferences and checks active user session.
- **Login & Registration**: Secure registration with full validation (email regex pattern, minimum password length, confirmation matching, and duplicate email prevention).
- **Persistent User Accounts**: Uses JSON-based `SharedPreferences` storage (`UserStore`) to persist registered accounts and active login sessions across app restarts.
- **Password Recovery**: Simulated reset flow with email verification and password updates.

### 📊 Interactive Dashboard
- **Dynamic Greeting**: Time-aware greeting (*Good morning*, *Good afternoon*, *Good evening*) personalized with the student's name.
- **Live Module Counters**: Real-time item counters for Courses, Schedule, Assignments, Messages, and Notices that automatically refresh on screen resume.
- **Navigation Cards**: Clean Material 3 card layout leading directly to module screens.

### 📚 5 Fully Interactive Campus Modules
Each module (Courses, Schedule, Assignments, Messages, Notices) includes:
- **Interactive List View**: Built with `RecyclerView` and custom `ItemAdapter`.
- **Pre-seeded Sample Data**: Instant mock data for a realistic campus experience.
- **Data Persistence**: Local JSON storage (`DataStore`) in `SharedPreferences` ensures all additions and deletions persist on the device.
- **Add Items**: Floating Action Button (**+**) launching a validated `MaterialAlertDialog`.
- **Swipe-to-Delete with Undo**: Swipe left or right via `ItemTouchHelper` to delete items, with a `Snackbar` **Undo** action to restore deleted entries.

### 🎨 Modern UI, Themes & Bilingual Support
- **Teal & Amber Design System**: Modern color scheme (`#0F766E` Teal primary, `#F59E0B` Amber accent) with Material 3 styling.
- **Dark & Light Mode**: Top-action bar toggle allowing one-tap theme switching with sun/moon icons.
- **Bilingual Localization (English & Urdu)**: Top-action bar globe icon providing instant language switching (`en` / `ur`) using `AppCompatDelegate.setApplicationLocales` and complete Right-To-Left (RTL) support.

---

## 🛠 Tech Stack & Dependencies

- **Language**: Java 11
- **UI Framework**: Android XML Views & Material Design Components (`com.google.android.material:material:1.14.0`)
- **Layout Managers**: ConstraintLayout, CoordinatorLayout, LinearLayout, ScrollView, GridLayout
- **Components**: RecyclerView, MaterialCardView, FloatingActionButton, TextInputLayout, TextInputEditText, MaterialAlertDialog, Snackbar, ItemTouchHelper
- **Persistence**: SharedPreferences (JSON Serialization via `JSONArray` / `JSONObject`)
- **Localization**: Android App Locales API (`AppCompatDelegate`, `values/strings.xml`, `values-ur/strings.xml`)
- **Build System**: Gradle 9.4+ / Android Gradle Plugin 9.4.1

---

## 📱 Navigation & App Flow

```
Splash Screen (MainActivity)
      │
      ├──> Already Logged In? ──> DashboardActivity
      │                                │
      │                                ├──> ModuleActivity (Courses, Schedule, Assignments, Messages, Notices)
      │                                └──> ProfileActivity (Logout ──> LoginActivity)
      │
      └──> Not Logged In ─────> LoginActivity
                                       │
                                       ├──> RegisterActivity ──> LoginActivity
                                       └──> ForgotPasswordActivity ──> ResetPasswordActivity ──> LoginActivity
```

---

## 🔑 Demo Credentials

To quickly test the application without registering:
- **Email**: `student@campusnest.com`
- **Password**: `123456`

*(You can also register a new custom account directly inside the app!)*

---

## 🚀 Getting Started & Building

1. **Clone the repository**:
   ```bash
   git clone https.github.com/Shahzad-dev20/Android-App.git
   ```
2. **Open in Android Studio**:
   Open Android Studio (Ladybug or newer) and choose **Open**, then select the `CampusNest` directory.
3. **Build the Project**:
   Run Gradle sync and execute:
   ```bash
   ./gradlew assembleDebug
   ```
4. **Run on Device / Emulator**:
   Select an Android emulator or connected device (API 24+) and click **Run ▶** in Android Studio.

---

## 📁 Project Structure

```
app/src/main/
├── java/com/example/campusnest/
│   ├── AppSettings.java          # Theme (Dark/Light) & Language (EN/UR) manager
│   ├── CampusApp.java            # Application entry point & initialization
│   ├── DataStore.java            # Module data seeding & JSON SharedPreferences persistence
│   ├── UserStore.java            # User account persistence & session management
│   ├── Item.java                 # Data model for module items
│   ├── ItemAdapter.java          # RecyclerView adapter for module list items
│   ├── MainActivity.java         # Splash screen with logo animation & auth routing
│   ├── LoginActivity.java        # User sign-in screen
│   ├── RegisterActivity.java     # User account creation screen
│   ├── ForgotPasswordActivity.java# Account recovery request screen
│   ├── ResetPasswordActivity.java # Password reset screen
│   ├── DashboardActivity.java    # Dynamic greeting & campus module overview
│   ├── ProfileActivity.java      # User profile details & logout
│   └── ModuleActivity.java       # Generic interactive screen for all 5 campus modules
├── res/
│   ├── drawable/                 # Vector icons, circle backgrounds, and app logo
│   ├── layout/                   # Activity XML layouts & dialog templates
│   ├── values/                   # Light colors, dimensions, themes, & English strings
│   ├── values-night/             # Dark mode colors
│   ├── values-ur/                # Urdu translation strings
│   └── mipmap-*/                 # Adaptive launcher icons
└── AndroidManifest.xml           # Manifest declarations & locale service registration
```

---

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).
