# 🍔 Full Stack Multi-Tenant Food Ordering Platform

<p align="center">
  <img src="demo.gif" alt="Project Demo" width="850"/>
</p>

<p align="center">
  <b>🚀 Three-Tier Multi-Tenant Food Ordering System</b><br>
  <i>Spring Boot • Oracle DB • Flutter • Seller Portal • Dropbox API</i>
</p>

---

## 🎥 Full Demo Video

[▶️ Watch Complete Project Demo](https://github.com/SadeHarshaVardhan/FULL-STACK-E-COMMERCE-MULTI--TENANT-FOOD-ORDERING-PLATFORM/blob/main/FRONTED%20%26%20BACKEND%20DEVELOPMENT.mp4)

https://canva.link/e04jkwipkmb24gi
---

## 🚀 Overview

A scalable **multi-tenant food ordering platform** that enables multiple restaurants to operate independently with dedicated dashboards while providing customers a seamless ordering experience via mobile app.

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
- Tenant-specific dashboards  
- Data isolation for each seller  

### 📱 Customer Mobile App
- Browse food items  
- Add to cart  
- Place orders  

### 🖥️ Seller Web Portal
- CRUD operations for menu  
- Order management system  
- Image upload support  

### 🔐 Backend (Spring Boot)
- RESTful APIs  
- Secure and scalable architecture  

### ☁️ Cloud Integration
- Dropbox API for storing menu images  

### 🗄️ Database (Oracle)
- Structured schema  
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
2. Adds menu items (stored in Dropbox)  
3. Customer browses via mobile app  
4. Customer places order  
5. Order stored in database  
6. Seller manages orders  

---

## 📂 Project Structure


backend/ → Spring Boot APIs
frontend/ → Seller Web Portal
mobile-app/ → Flutter Application
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
