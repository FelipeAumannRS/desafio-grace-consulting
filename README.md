# 🚀 Grace Consulting Backend Challenge – Credit Card Registry API

## 📄 Overview

This project implements a REST API for securely registering and querying full credit card numbers.

It was developed as part of the Grace Consulting technical challenge and focuses on:

- Secure persistence of sensitive card data
- JWT protected endpoints
- Single and batch (CSV) card ingestion
- Clean and simple architecture using Java + Spring Boot

The system never stores full card numbers in plain text.  
Instead, it uses hashing and encryption to guarantee data safety.

---

## ⚙️ Features

### ✅ Credit Card Registration
- Register a single credit card via REST
- Prevent duplicates using hashed card number
- Returns a unique system UUID

### ✅ CSV Bulk Import
- Upload CSV file containing multiple credit cards
- Line-by-line ingestion (streamed)
- Returns number of processed records

### ✅ Secure Storage
- Full card number → one-way HMAC hash
- Card holder name + CVV → AES-GCM encryption
- Only `last4` is stored in plain text

### ✅ Authentication
- JWT based login
- Bearer token required for protected endpoints

---

## 🧱 Architecture

Lightweight Clean Architecture + DDD concepts:
