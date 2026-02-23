<%@ page import="com.food.model.User" %>
        <% User loggedUser=(User) session.getAttribute("loggedInUser"); %>

                <!-- FontAwesome & Custom CSS -->
                <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
                <link rel="stylesheet" href="css/styles.css">

                <nav class="navbar navbar-expand-lg sticky-top glass-effect">
                        <div class="container">

                                <a class="navbar-brand d-flex align-items-center gap-2" href="home">
                                        <i class="fa-solid fa-burger text-primary-custom"></i> Foodie<span
                                                class="text-primary-custom">.</span>
                                </a>

                                <button class="navbar-toggler border-0" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#navbarNav">
                                        <span class="navbar-toggler-icon"></span>
                                </button>

                                <div class="collapse navbar-collapse" id="navbarNav">

                                        <!-- Search Bar -->
                                        <form class="d-none d-lg-flex ms-4 w-50 position-relative">
                                                <div class="input-group">
                                                        <span class="input-group-text border-end-0 bg-transparent ps-3">
                                                                <i class="fa-solid fa-magnifying-glass text-muted"></i>
                                                        </span>
                                                        <input class="form-control border-start-0 bg-light ps-0"
                                                                type="search"
                                                                placeholder="Search for restaurants, cuisine or a dish..."
                                                                style="box-shadow: none;">
                                                </div>
                                        </form>

                                        <ul class="navbar-nav ms-auto align-items-center gap-3">

                                                <% if(loggedUser !=null){ %>

                                                        <li class="nav-item">
                                                                <span
                                                                        class="nav-link fw-bold d-flex align-items-center gap-2">
                                                                        <i class="fa-regular fa-user"></i>
                                                                        <%= loggedUser.getName() %>
                                                                </span>
                                                        </li>

                                                        <li class="nav-item">
                                                                <a class="nav-link position-relative btn-icon"
                                                                        href="cart.jsp">
                                                                        <i class="fa-solid fa-cart-shopping fs-5"></i>
                                                                        <% java.util.Map cartMap=(java.util.Map)
                                                                                session.getAttribute("cart"); int
                                                                                count=(cartMap !=null) ? cartMap.size()
                                                                                : 0; if(count> 0) {
                                                                                %>
                                                                                <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger shadow-sm"
                                                                                        style="font-size: 0.6rem; border: 2px solid white;">
                                                                                        <%= count %>
                                                                                </span>
                                                                                <% } %>
                                                                </a>
                                                        </li>

                                                        <li class="nav-item">
                                                                <a class="btn btn-outline-primary btn-sm rounded-pill px-4"
                                                                        href="logout">Logout</a>
                                                        </li>

                                                        <% } else { %>

                                                                <li class="nav-item">
                                                                        <a class="nav-link fw-semibold"
                                                                                href="login.jsp">Log in</a>
                                                                </li>

                                                                <li class="nav-item">
                                                                        <a class="btn btn-primary text-white hover-shadow px-4 rounded-pill"
                                                                                href="register.jsp">Sign up</a>
                                                                </li>

                                                                <% } %>
                                        </ul>

                                </div>
                        </div>
                </nav>