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

![Screenshot 2025-05-19 143351](https://github.com/user-attachments/assets/3e34f54c-c986-42ac-96a4-ed7ad18035a6)
![Screenshot 2025-05-19 143427](https://github.com/user-attachments/assets/a4046d4e-8c3d-4629-8913-5543d709e80e)
![Screenshot 2025-05-19 143441](https://github.com/user-attachments/assets/09c92348-ec06-4607-9ae4-88b28cc1e0ec)
![Screenshot 2025-05-19 143452](https://github.com/user-attachments/assets/0e73aece-a28c-413d-9363-245358a0e439)
![Screenshot 2025-05-19 143504](https://github.com/user-attachments/assets/ac26bd92-0fcd-473d-a175-9bc45fdb12ba)


![image](https://github.com/user-attachments/assets/3d407958-efbe-449f-b4e1-5b08b9f8a28b)


![Screenshot 2024-08-24 194958](https://github.com/user-attachments/assets/c11a4710-69f8-42fd-b9d7-2b5278b2c8a3) 


![Screenshot 2024-08-24 220831](https://github.com/user-attachments/assets/d3cd3cdd-cda5-460a-a253-24e45cf600b0)  
![Screenshot 2024-08-24 195054](https://github.com/user-attachments/assets/733afb94-a251-4a6d-86a4-3a124e8c3469)
![Screenshot 2024-08-24 195106](https://github.com/user-attachments/assets/ef09886f-7936-4ef7-b01e-4da0008fd047) 
![Screenshot 2024-08-24 195121](https://github.com/user-attachments/assets/219272b8-3938-4b7f-ba78-817e507beee9)
![Screenshot 2024-10-03 093051](https://github.com/user-attachments/assets/d4da51a4-dfe8-4cc1-ae49-745f6e1ae17a) 
![Screenshot 2024-10-03 093106](https://github.com/user-attachments/assets/09c31cf7-e960-4ea5-a858-8807317486b7)
![Screenshot 2024-10-03 093133](https://github.com/user-attachments/assets/b1da1faa-7e71-49be-972a-432029a1e7c3) 
![Screenshot 2024-10-03 093424](https://github.com/user-attachments/assets/b0a9a1e2-6018-475a-95fc-d64980677ee7) 
![Screenshot 2024-10-03 093437](https://github.com/user-attachments/assets/b860f905-2c35-4af3-9df8-38cda70b4406)

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
