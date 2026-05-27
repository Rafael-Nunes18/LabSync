# LabSync

![Status](https://img.shields.io/badge/Status-In%20Development-yellow)
![Kotlin](https://img.shields.io/badge/Kotlin-1.9-7F52FF?logo=kotlin&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?logo=docker&logoColor=white)

**LabSync** is a Kotlin application designed to help teachers schedule and manage laboratory reservations, streamlining the process of booking and organizing lab usage within educational institutions.

---

##  Project Roadmap

- [x] **Repository Setup**
- [x] **Documentation**
- [ ] **Database Script**
- [ ] **Backend API**
- [ ] **Authentication & Authorization**
- [ ] **Deployment**
- [ ] **User Interface**
- [ ] **Unit Testing**

---

##  Tech Stack

### Mobile
- **Language:** Kotlin
- **Platform:** Android

### Backend
- **Runtime:** Node.js
- **Framework:** Express.js

### Database
- **Database:** PostgreSQL

### Design
- **Tool:** Figma

### DevOps
- **Containerization:** Docker & Docker Compose
- **CI/CD:** GitHub Actions

---

##  How to Run

### Prerequisites
- Android Studio installed
- Docker Desktop installed
- Git installed

### Clone the repository
```bash
git clone https://github.com/Rafael-Nunes18/LabSync.git
```

### Configure environment variables
Create a `.env` file in the root of the project:
```env
POSTGRES_USER=your_user
POSTGRES_PASSWORD=your_password
POSTGRES_DB=labsync
PORT=3000
```

### Start backend and database
```bash
docker compose up --build
```

### Open the app in Android Studio
1. Open **Android Studio**
2. Select **Open an existing project**
3. Navigate to the cloned folder and open it
4. Connect a physical device or start an **Android Emulator** and click **Run ▶**

---

##  Team

| Member | GitHub | Role |
|---|---|---|
| Rafael Toledo Nunes | [@Rafael-Nunes18](https://github.com/Rafael-Nunes18) | DevOps |
| Pedro Teles Prieto | [@PezTeles](https://github.com/PezTeles) | Mobile Frontend (Kotlin) |
| Marcelo Soares da Silva | [@MarceloDebug](https://github.com/MarceloDebug) | Mobile Frontend (Kotlin) |
| Paulo Henrique Santos Borges | [@EasterEggo](https://github.com/EasterEggo) | Backend (Express.js) |
| Fhelipe Alves Mantovan | [@FhelipeAM](https://github.com/FhelipeAM) | Database (PostgreSQL) |
| Henry Secassi Tafuri | [@HenryST48](https://github.com/HenryST48)  | UI/UX Design (Figma) |
