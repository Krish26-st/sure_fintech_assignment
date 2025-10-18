# Bank Statement Parser

![Bank Statement Parser](https://img.shields.io/badge/Status-Completed-brightgreen)
![Java](https://img.shields.io/badge/Language-Java-red)
![Spring Boot](https://img.shields.io/badge/Framework-SpringBoot-blue)

---

## Overview

The **Bank Statement Parser** is a Java-based application built using **Spring Boot** that extracts key information from credit card statements across multiple banks. It allows users to upload **PDF statements**, parses important details, and generates a downloadable **CSV file** for easy record-keeping or analysis.  

This project supports multiple issuers, including **HDFC, SBI, Axis, ICICI, and American Express**.

---

## Features

- Extracts **key information** from credit card statements:
  - Issuer
  - Cardholder Name
  - Last 4 Digits of the card
  - Billing Period
  - Payment Due Date
  - Total Amount Due
- Supports **multiple PDF uploads** simultaneously.
- Generates a **CSV file** for download.
- **Web-based UI** built with Spring Boot and Thymeleaf.
- Clean and responsive interface with **Bootstrap** and custom CSS.
- Handles **real-world PDF statement formats**.

---

## Demo

1. Upload one or multiple PDF statements using the **web UI**.
2. View the extracted data in a clean **table view**.
3. Click the **Download CSV** button to export all parsed data.

---

## Technologies Used

- **Java 17**
- **Spring Boot**
- **Thymeleaf**
- **Apache PDFBox** (for PDF parsing)
- **Bootstrap 5** and **custom CSS** for UI
- **Maven** for dependency management

---

## Project Structure
```
bank-statement-parser-ui/
│
├─ src/
│ ├─ main/
│ │ ├─ java/parser/ # Core parsing logic
│ │ ├─ java/web/ # Spring Boot controllers
│ │ └─ resources/templates/ # Thymeleaf HTML templates
│ └─ test/
│
├─ sample/ # Sample PDF statements
├─ pom.xml # Maven configuration
├─ .gitignore
├─ .gitattributes
└─ README.md
```

---

## Installation & Running

1. Clone the repository:

`git clone https://github.com/Krish26-st/sure_fintech_assignment.git
cd sure_fintech_assignment`

2. Ensure you have Java 17 and Maven installed.
3. Build the project:
`mvn clean install`
4. Run the Spring Boot application:
`mvn spring-boot:run`
5. Open a browser and go to:
`http://localhost:8080/`
6. Upload PDF statements and download the parsed CSV.

---

Author

Krish26-st
Email: nisarkrish3@gmail.com

GitHub: https://github.com/Krish26-st
