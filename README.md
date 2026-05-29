# Available
![Status](https://img.shields.io/badge/Status-In%20Development-yellow)
![Kotlin](https://img.shields.io/badge/Kotlin-1.9-7F52FF?logo=kotlin&logoColor=white)
![Supabase](https://img.shields.io/badge/Supabase-Enabled-3ECF8E?logo=supabase&logoColor=white)

**Available** is a Kotlin application designed to help teachers schedule and manage laboratory reservations, streamlining the process of booking and organizing lab usage within educational institutions.

---

## Project Roadmap

- [x] **Repository Setup**
- [x] **Documentation**
- [x] **Database Schema (Supabase)**
- [ ] **Backend API**
- [ ] **User Interface**
- [ ] **Deployment**

---

## Tech Stack

### Mobile
- **Language:** Kotlin
- **Platform:** Android

### Backend
- **Runtime:** Node.js
- **Framework:** Express.js

### Database
- **Database:** PostgreSQL
- **BaaS:** Supabase

### Design
- **Tool:** Figma

### DevOps
- **CI/CD:** GitHub Actions

---

## How to Run

### Prerequisites
- Android Studio installed
- Node.js installed
- Git installed

### Clone the repository
```bash
git clone https://github.com/Rafael-Nunes18/Available.git
```

### Configure environment variables

Create a `.env` file inside the `api/` folder:

```env
SUPABASE_URL=your_supabase_url
SUPABASE_KEY=your_supabase_key
PORT=3000
```

> You can find your `SUPABASE_URL` and `SUPABASE_KEY` in your Supabase project settings under **API**.

### Start the backend

```bash
cd api
npm install
npm start
```

### Open the app in Android Studio
1. Open **Android Studio**
2. Select **Open an existing project**
3. Navigate to the cloned folder and open it
4. Connect a physical device or start an **Android Emulator** and click **Run ▶**

---

## Team

| Member | GitHub | Role |
|---|---|---|
| Rafael Toledo Nunes | [@Rafael-Nunes18](https://github.com/Rafael-Nunes18) | DevOps |
| Pedro Teles Prieto | [@PezTeles](https://github.com/PezTeles) | Mobile Frontend (Kotlin) |
| Marcelo Soares da Silva | [@MarceloDebug](https://github.com/MarceloDebug) | Mobile Frontend (Kotlin) |
| Paulo Henrique Santos Borges | [@EasterEggo](https://github.com/EasterEggo) | Backend (Express.js) |
| Fhelipe Alves Mantovan | [@FhelipeAM](https://github.com/FhelipeAM) | Database (PostgreSQL/Supabase) |
| Henry Secassi Tafuri | [@HenryST48](https://github.com/HenryST48) | UI/UX Design (Figma) |
