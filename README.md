# 🍔 Full Stack Multi-Tenant Food Ordering Platform

A scalable **three-tier food ordering system** that supports multiple restaurants (tenants), enabling independent menu management, order handling, and customer interaction via web and mobile platforms.

---

## 🎥 Demo Video

> 🔗 Add your demo video link here  
> Example: https://github.com/your-username/your-repo/assets/demo.mp4

---

## 🚀 Overview

This project is designed to provide:
- A **Seller Web Portal** for restaurant owners
- A **Flutter Mobile App** for customers
- A **Spring Boot Backend** for business logic
- An **Oracle Database** for secure data storage

---

## 🏗️ Architecture


Client Layer
├── Flutter Mobile App (Customers)
└── Seller Web Portal (HTML, CSS, JS)

Application Layer
└── Spring Boot REST APIs

Data Layer
└── Oracle Database


---

## ✨ Features

### 👨‍🍳 Multi-Tenant Support
- Multiple restaurants supported  
- Isolated data per tenant  
- Independent dashboards  

### 📱 Customer Mobile App (Flutter)
- Browse food items  
- Add to cart  
- Place orders  

### 🖥️ Seller Web Portal
- Add / Update / Delete menu items (CRUD)  
- Manage orders  
- Upload product images  

### 🔐 Backend (Spring Boot)
- REST APIs  
- Secure data handling  
- Scalable architecture  

### ☁️ Cloud Integration
- Dropbox API used for storing menu images  

### 🗄️ Database (Oracle)
- Structured relational schema  
- Optimized queries  

---

## 🛠️ Tech Stack

- **Backend:** Spring Boot (Java)  
- **Frontend:** HTML, CSS, JavaScript  
- **Mobile App:** Flutter  
- **Database:** Oracle DB  
- **Cloud:** Dropbox API  

---

## 🔄 Workflow

1. Seller logs into portal  
2. Adds menu items (images stored in Dropbox)  
3. Customer browses menu via mobile app  
4. Customer places order  
5. Order stored in database  
6. Seller manages orders  

---

## 📂 Project Structure


backend/ → Spring Boot APIs
frontend/ → Seller Web Portal
mobile-app/ → Flutter App
database/ → SQL Scripts


---

## ⚙️ Setup Instructions

### Backend

cd backend
mvn clean install
mvn spring-boot:run


### Database
- Create Oracle schema  
- Run scripts from `/database`  

### Seller Portal

Open index.html in browser


### Mobile App

cd mobile-app
flutter pub get
flutter run


---

## 🔐 Security

- Input validation  
- Secure API communication  
- Role-based access  

---

## 📈 Future Improvements

- Payment gateway integration 💳  
- Real-time notifications 🔔  
- AI-based recommendations 🤖  
- Advanced analytics 📊  

---

## 🤝 Contributing

Pull requests are welcome. For major changes, open an issue first.

---

## 📜 License

For educational purposes only.

---

## 👨‍💻 Author

**Harsha Vardhan Sade**
