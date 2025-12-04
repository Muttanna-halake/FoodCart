# FoodCart
Foodcart is a full-stack food ordering and management system designed to handle products, customers, and orders efficiently. It includes secure user authentication, session handling, and dynamic content rendering with Thymeleaf. The backend is built using Spring Boot with MySQL integration, while the frontend is created using HTML, CSS, and Thymeleaf templates. Admin users can add, update, and manage food items, while customers can browse products, place orders, view their order history, and track their account activity. The system ensures a smooth workflow with clear separation between frontend UI and backend logic.

<img width="1920" height="2277" alt="Home page" src="https://github.com/user-attachments/assets/4dec73c3-32d1-4b08-beca-19290af7576a" />


## Features
- **User Management**: Users can register, log in, and manage their food orders easily.
- **Admin Management**: Admins can log in from a separate admin page and manage products, users, other admins, and orders.
- **Product Management**: Add, update, delete, and display food products with images, descriptions, and prices.
- **Order Management**: Users can place orders, view order history, cancel orders with reason, and track order status.
- **Order Tracking**: Flipkart-style tracking system showing status stages — Ordered, Packed, Shipped, Delivered.
- **Order Cancellation**: Users can cancel orders (before shipping) with a popup reason box, and cancelled orders store reason & cancel date.
- **Admin Order Control**: Admin can update order status from the admin panel (Ordered → Packed → Shipped → Delivered).
- **Inventory Display**: Products are displayed beautifully with images, prices, and detail pages.
- **User Authentication**: Secure login system for users and admins with session management.
- **Role-Based Access Control**: User and Admin panels separated with different permissions.
- **Reusable Navbar & Footer**: Every page uses a common navigation bar and footer (Thymeleaf fragments).
- **Responsive Navigation**: Mobile-friendly hamburger menu for small devices.
- **Search Functionality**: Users can search for any product directly.
-**Cart-like Ordering**: Users can select quantity before ordering.
- **Dynamic Thymeleaf Templates**: All pages dynamically updated using Spring Boot + Thymeleaf.
- **Database Integration**: Fully connected to MySQL to store users, products, orders, admins, and tracking details.

## Technology Stack

- **Backend**: Spring Boot, Java 8, Spring MVC, Spring Data JPA (Hibernate)
- **Frontend**: Thymeleaf, HTML, CSS, JavaScript
- **Database**: MySQL
- **IDE**: Eclipse, Spring Tool Suite (STS)

## Prerequisites

Before running this project, ensure you have the following installed:

- Java 8
- MySQL
- Maven
- Vs Code or Spring Tool Suite (STS)
## Website Screenshot

Here is a preview of the FoodCart interface:
<img width="1920" height="2028" alt="Foods" src="https://github.com/user-attachments/assets/94969acc-f499-4e0f-b5b5-7aedd6937ee3" />

<img width="1920" height="2467" alt="Admin-dashboard" src="https://github.com/user-attachments/assets/38f17b8f-0e69-4348-96cd-ab29163f78aa" />

<img width="1919" height="872" alt="Add-Admin" src="https://github.com/user-attachments/assets/0c2f9a1d-b33c-4958-9cf9-c612e5730b25" />

<img width="1919" height="870" alt="Add-user" src="https://github.com/user-attachments/assets/7d058c9c-ee04-406c-84f8-28a293c33dc6" />

<img width="1901" height="871" alt="Add-product" src="https://github.com/user-attachments/assets/82b12baa-df6c-43fe-87bd-a882369f81fd" />

<img width="1910" height="759" alt="Login-User" src="https://github.com/user-attachments/assets/ecc9397f-b540-4b81-b61c-4514232533b1" />

<img width="1920" height="1688" alt="Single-Product-page" src="https://github.com/user-attachments/assets/d21d03ec-391e-4d77-830b-ea80abdd907e" />

<img width="1903" height="870" alt="Checkout-Page" src="https://github.com/user-attachments/assets/0d2ad9ac-299e-492f-9ea7-834a75b0ad45" />

<img width="1918" height="860" alt="Order-Scucess" src="https://github.com/user-attachments/assets/a61d6ff4-fe97-4917-8ada-2e01317a6da2" />

<img width="1919" height="869" alt="Orders-page" src="https://github.com/user-attachments/assets/132fd37f-9b42-4748-a037-32e866adc70d" />

<img width="1917" height="865" alt="Order-track-page" src="https://github.com/user-attachments/assets/7cf0bdb9-a04b-403e-b0dc-efee337de83a" />

<img width="1898" height="863" alt="Order-cancelation-reson" src="https://github.com/user-attachments/assets/9029c7c5-4558-4079-8bd1-86f4b12cd993" />

<img width="1913" height="785" alt="Update-product" src="https://github.com/user-attachments/assets/0c976e72-3e5d-447b-80d3-43f6892b69d6" />

<img width="1883" height="578" alt="Status-update" src="https://github.com/user-attachments/assets/11655c1f-3418-4c01-82ae-47b65f68cbe3" />
## Project Structure

```bash
src/
├── main/
│   ├── java/
│   │   └── com.example.foodfrenzy/
│   │       ├── controller/      # Contains all controllers
│   │       ├── model/           # Contains entity classes
│   │       ├── repository/      # Repository interfaces for database interaction
│   │       └── service/         # Service layer with business logic
│   ├── resources/
│   │   ├── templates/           # Thymeleaf templates for views
│   │   ├── static/              # Static assets (CSS, JavaScript)
│   │   └── application.properties  # Project configuration
│   └── webapp/
│       └── WEB-INF/
│           └── views/           # Additional view files
└── test/                        # Test cases for unit testing
