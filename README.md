# Spleet
Aplikacja do Zarządzania Wydatkami Grupowymi

Aplikacja służy do zarządzania wydatkami grupowymi, umożliwiając użytkownikom śledzenie wspólnych wydatków w różnych grupach.
Jest w sam raz do rozliczania się między znajomymi, współlokatorami czy podczas wspólnych wyjazdów.

Oferowane funkcjonalności:
- Zarządzanie użytkownikami: dodawanie i usuwanie ich, kontrola salda,
- Zarządzanie grupami: tworzenie nowych grup, dodawanie użytkowników do grup, śledzenie wydatków grupowych,
- Zarządzanie wydatkami: dodawanie wydatków z kategoryzacją, automatyczny podział kosztów między członków grupy, śledzenie historii wydatków,
- Wizualizacja danych: wykresy słupkowe pokazujące wydatki w czasie, wykresy kołowe pokazujące podział wydatków na kategorie, rzejrzyste tabele z historią transakcji,

Zastosowane technologie:
- Java,
- JavaFX - interfejs graficzny,
- FXML - struktura interfejsu użytkownika,
- ObservableList - reaktywne zarządzanie danymi,
- Scene Builder - projektowanie interfejsu,

Instalacja:

- sklonuj repozytorium,
- upewnij się, że masz zainstalowane:
  - Java JDK 11 lub nowszy,
  - JavaFX SDK,
  - Maven (opcjonalnie),
- skonfiguruj projekt w swoim IDE:
- dodaj JavaFX do ścieżki projektu,
- zaimportuj projekt jako projekt Maven lub Java,
- uruchom aplikację poprzez klasę główną - Hello Application zawierającą metodę main.

Struktura projektu: 

project/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── Aplikacja/
│   │   │   │   ├── Expense.java          # Klasa modelu wydatków
│   │   │   │   ├── Group.java            # Klasa modelu grup
│   │   │   │   └── User.java             # Klasa modelu użytkowników
│   │   │   │
│   │   │   └── com/example/demo2/
│   │   │       ├── MainController.java         # Główny kontroler aplikacji
│   │   │       ├── AddUserController.java      # Kontroler dodawania użytkowników
│   │   │       ├── AddGroupController.java     # Kontroler dodawania grup
│   │   │       └── AddExpenseController.java   # Kontroler dodawania wydatków
│   │   │
│   │   └── resources/
│   │       ├── com/example/demo2/
│   │       │   ├── MainView.fxml              # Główny widok aplikacji
│   │       │   ├── AddUserStage.fxml          # Widok dodawania użytkownika
│   │       │   ├── AddGroupStage.fxml         # Widok dodawania grupy
│   │       │   └── AddExpenseStage.fxml       # Widok dodawania wydatku
│   │       │
│   │       ├── styles/
│   │       │   └── styles.css                 # Style CSS aplikacji
│   │       │
│   │       └── images/                        # Katalog na obrazy i ikony
│   │
│   └── pom.xml                                # Konfiguracja Maven (jeśli używany)
│
└── README.md                                  # Dokumentacja projektu
