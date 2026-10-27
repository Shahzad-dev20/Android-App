# CampusNest 🎓

**CampusNest** is a modern, elegant Android mobile application built with **Java** and **Material Design Components**. Designed with a warm cream (`#F3F2EF`) and near-black (`#0E0D0B`) aesthetic with gold (`#E9B949`) accents, custom typography (**Instrument Serif** & **Poppins**), pill buttons, interactive shop-shutter opening animations, and cascading slide-up lists, CampusNest serves as a complete campus companion for students.

---

## 🌟 Key Features

### 🎨 Editorial UI & Premium Aesthetics
- **Custom Typography**: Serif headlines rendered in **Instrument Serif** paired with clean **Poppins** body font (`regular`, `medium`, `semibold`).
- **Warm Cream & Near-Black Palette**: Custom color palette supporting automatic Light (`#F3F2EF`) and Dark (`#0E0D0B`) themes.
- **Pill Buttons & Sand Chips**: Rounded Material 3 buttons (`28dp` corner radius) and sand-tinted metadata chips.
- **Vector Logo**: Hand-drawn flashlight and gold-tile pyramid logo (`logo.xml`).

### 🎬 Fluid Animations
- **Rolling Shop-Shutter Opening**: Custom `ShutterAnimator` overlay that rolls a multi-slat shop-shutter upwards when entering the Login screen.
- **Cascading Dashboard Rise**: The **Up next** card slides up with decelerated physics, followed sequentially by all 6 module cards.
- **List Item Slide-Up**: `RecyclerView` layouts use `layout_slide_up` animations when rendering module items.

### 🔐 Auth & Session Persistence
- **Splash & Shutter Login**: Animated splash screen routing to Login or directly to Dashboard if already authenticated.
- **Persistent User Accounts**: Multi-user registration, login validation, and password recovery using JSON-serialized `SharedPreferences` (`UserStore`).

### 📊 Dashboard & "Up Next" Lecture Card
- **Personalized Greeting**: Dynamic time-of-day greeting (*Good morning*, *Good afternoon*, *Good evening*).
- **Up Next Lecture Card**: Displays the upcoming lecture (*Machine Learning Lecture*), room tag, time remaining, and instant schedule launch.
- **Live Counters**: Dynamic item count indicators on all module cards.

### 📚 5 Fully Interactive Campus Modules
Modules for **Courses**, **Schedule**, **Assignments**, **Messages**, and **Notices** feature:
- **Interactive List View**: Custom `ItemAdapter` with Instrument Serif titles and chip tags.
- **Pre-seeded & Custom Data**: JSON-backed local storage (`DataStore`) in `SharedPreferences`.
- **Add Items**: Floating Action Button (**+**) launching a validated `MaterialAlertDialog`.
- **Swipe-to-Delete with Undo**: `ItemTouchHelper` left/right swipe with a `Snackbar` **Undo** action.

### 🌐 Bilingual Localization (English & Urdu)
- Top action bar buttons for one-tap Dark Mode toggling (Sun/Moon) and Language selection (Globe icon with English and Urdu `values-ur` strings) with RTL layout mirroring.

---

## 🛠 Tech Stack & Dependencies

- **Language**: Java 11
- **UI Framework**: Android XML Views & Material Design (`com.google.android.material:material:1.14.0`)
- **Typography**: Instrument Serif, Poppins (`regular`, `medium`, `semibold`)
- **Animations**: Custom `ShutterAnimator`, Layout Animations, ValueAnimators & Decelerate Interpolators
- **Layout Managers**: ConstraintLayout, CoordinatorLayout, LinearLayout, ScrollView, GridLayout
- **Components**: RecyclerView, MaterialCardView, FloatingActionButton, TextInputLayout, TextInputEditText, MaterialAlertDialog, Snackbar, ItemTouchHelper
- **Persistence**: SharedPreferences (JSON Serialization via `JSONArray` / `JSONObject`)
- **Localization**: Android App Locales API (`AppCompatDelegate`, `values/strings.xml`, `values-ur/strings.xml`)
- **Build System**: Gradle 9.4+ / Android Gradle Plugin 9.4.1

---

## 🔑 Demo Credentials

- **Email**: `student@campusnest.com`
- **Password**: `123456`

---

## 🚀 Getting Started & Building

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Shahzad-dev20/Android-App.git
   ```
2. **Open in Android Studio**:
   Open Android Studio and select the `CampusNest` directory.
3. **Build & Run**:
   ```bash
   ./gradlew assembleDebug
   ```

---

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).
