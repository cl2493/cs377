# cs377 - QuoteMaker Android App

## 📱 Overview

**QuoteMaker** is a simple and elegant Android app that delivers motivational and inspirational quotes at the tap of a button. It integrates with the **[ZenQuotes API](https://zenquotes.io/)** to fetch real-time quotes and allows users to save their favorites locally.

The app is built using **Kotlin**, follows the **MVVM architecture**, and uses **Retrofit** for API communication and **Room** for local data persistence.

---

## ⚙️ Features

- 🎲 **Random Quote Generator**: Tap the “New Quote!” button to fetch a new quote from ZenQuotes.
- ❤️ **Favorite Quotes**: Tap the heart button to save your current quote to a favorites list.
- 💾 **Share Quotes**: Share any given quote to anyone you want.
- 🧾 **Favorites Screen**: View a list of saved quotes on a separate screen.
- 📡 **ZenQuotes API Integration**: Uses Retrofit to pull quotes dynamically from the internet.
- 🎨 **Modern UI**: Clean, responsive, and intuitive interface built with Material Design components.

---

## 🚀 How It Works

1. **MainActivity** launches and displays the quote generator screen.
2. Tapping **“Give Me a Quote!”** sends a request to the **ZenQuotes API**.
3. The app displays a new quote on the screen.
4. Tapping the ♡ button saves the quote to the **Room database**.
5. Tapping the ↗ button will allow you to share your quote.
6. Users can view their saved favorites by clicking the **“View Favorites”** button.

---

## 🧱 Tech Stack

- **Language:** Kotlin
- **Architecture:** MVVM
- **UI:** ConstraintLayout, RecyclerView
- **API:** [ZenQuotes API](https://zenquotes.io/)
- **Networking:** Retrofit
- **Database:** Room (SQLite)
- **Lifecycle:** ViewModel, LiveData
