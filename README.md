# Dhaka International Trade Fair (DITF) Management System

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![JavaFX](https://img.shields.io/badge/JavaFX-21-blue.svg)](https://openjfx.io/)
[![Build](https://img.shields.io/badge/Build-Maven-green.svg)](https://maven.apache.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

A unified, full-featured desktop management system built with **JavaFX** and **Java 21** for the **Dhaka International Trade Fair (DITF)**. The platform streamlines vendor registrations, pavilion & stall allotments, coordination meetings, safety audits, customer care ticketing, and real-time executive analytics.

---

## 🌟 Key Features

### 🏢 Event Manager Operations
- **Vendor Onboarding & Allotment**: Review new applicant profiles, evaluate business lines, inspect special requests (such as high-voltage electricity or corner booth requirements), and approve/reject registrations.
- **Stall & Pavilion Allocation**: Create stalls with configurable dimensions (sq ft) and rental fees, view real-time availability, and allot stalls directly to approved vendors.
- **Stakeholder Coordination Meetings**: Schedule multi-departmental coordination meetings with date pickers, time slot allocators, role filters, and cancel capabilities.
- **Incident & Audit Reports**: Review daily operational logs, gate revenue reports, crowd metrics, and security audits submitted by field teams.
- **Stakeholder Reviews**: Browse authentic feedback and satisfaction ratings submitted by visitors, exhibitors, advertisers, and investors.

### 🎧 Customer Support Agent Hub
- **Customer Ticketing Desk**: Inspect attendee and exhibitor inquiries, lost-and-found reports, and ticketing issues. Update statuses between *Open*, *In Progress*, and *Resolved*.
- **Quick-Add Ticket Log**: Register walk-in attendee complaints with instant category classification.
- **Knowledge Base & FAQ**: Searchable repository of Standard Operating Procedures (SOPs), fair timings, gate rules, transport facilities, and official resolutions.
- **Contribute Guidelines**: Add new FAQ entries and guidelines to the central repository in real time.
- **Executive Analytics & KPIs**: Interactive dashboards featuring:
  - Total contracted stall rental revenue
  - Real-time stall occupancy breakdown (Pie Chart)
  - Ticket query volume distribution across categories (Bar Chart)
  - Approval and resolution rates

### 🎨 Modern Dark Design System
- Sleek slate & teal glassmorphic theme (`#0f172a` to `#134e4a`).
- Soft ambient drop shadows, frosted card panels, and high-contrast typography.
- Interactive focus glows, button hover transitions, and dark zebra-striped data tables.

---

## 🛠️ Technology Stack

- **Language**: Java 21 (LTS)
- **GUI Framework**: JavaFX 21 (Modularized with `module-info.java`)
- **Markup & Layout**: FXML 2.0 with custom CSS styling (`style.css`)
- **Build System**: Apache Maven / Maven Wrapper (`mvnw`)
- **Testing**: JUnit 5 (JUnit Jupiter) + Maven Surefire Plugin

---

## 🚀 Getting Started

### Prerequisites
- **JDK 21 or later** installed on your system.
- **Git** (optional, for cloning).

### Running the Application

1. **Clone or Open the Repository**:
   ```bash
   git clone https://github.com/ke961/InternationalTradeFair.git
   cd InternationalTradeFair
   ```

2. **Set `JAVA_HOME` (if not set in your system environment)**:
   - **PowerShell (Windows)**:
     ```powershell
     $env:JAVA_HOME = "C:\Path\To\Your\jdk-21"
     ```
   - **Command Prompt (CMD)**:
     ```cmd
     set JAVA_HOME=C:\Path\To\Your\jdk-21
     ```
   - **Linux / macOS (Bash/Zsh)**:
     ```bash
     export JAVA_HOME=/path/to/your/jdk-21
     ```

3. **Launch the JavaFX Application**:
   - **Windows**:
     ```powershell
     .\mvnw.cmd javafx:run
     ```
   - **Linux / macOS**:
     ```bash
     ./mvnw javafx:run
     ```

---

## 🔑 Default Demo Accounts

The application is pre-seeded with in-memory test data for immediate testing:

| Role | Username | Password | Access / Portal |
| :--- | :--- | :--- | :--- |
| **Event Manager** | `manager` | `pass123` | Event Manager Control Center |
| **Customer Support Agent** | `agent` | `pass123` | Support Hub & Analytics Dashboard |
| **Vendor** | `vendor1` | `pass123` | Exhibitor Application Portal |
| **New Users** | *Any (min 4 chars)* | *min 6 chars with number* | Dynamic instant access / Sign Up |

---

## 🧪 Running Automated Tests

Run the JUnit 5 test suite to verify data models, singleton integrity, authentication, and allotment logic:

```powershell
.\mvnw.cmd test
```

---

## 📦 Building a Distribution JAR

To build and package the application:

```powershell
.\mvnw.cmd clean package
```

The compiled JAR artifact will be generated in the `target/` folder.

---

## 📂 Project Architecture

```
InternationalTradeFair/
├── pom.xml                                      # Maven POM configuration
├── LICENSE                                      # MIT License
├── README.md                                    # Project documentation
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── module-info.java                 # Java module declarations
│   │   │   └── keya/internationaltradefairltd/
│   │   │       ├── HelloApplication.java        # Main Application entrypoint
│   │   │       ├── HomePageViewController.java  # Role selector & portal gateway
│   │   │       ├── LoginViewController.java     # Authentication controller
│   │   │       ├── SignUpController.java        # Registration controller
│   │   │       ├── User/                        # User, EventManager, CustomerCareAgent models
│   │   │       ├── HelperClass/                 # DataManager singleton, Stall, Vendor, Query, Report models
│   │   │       ├── EventManager/                # Event Manager controllers (Booths, Vendors, Meetings, Reports)
│   │   │       └── CustomerSupportAgent/        # Support controllers (Ticketing, FAQ, Knowledge Base, Summary)
│   │   └── resources/
│   │       └── keya/internationaltradefairltd/
│   │           ├── style.css                    # Master Dark Glassmorphic Design System
│   │           ├── *.fxml                       # FXML scene view definitions
│   │           ├── EventManager/                # Event Manager FXML views
│   │           └── CustomerSupportAgent/        # Customer Support FXML views
│   └── test/
│       └── java/keya/internationaltradefairltd/
│           └── TradeFairSystemTest.java         # JUnit 5 test suite
```

---

## 📄 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.
