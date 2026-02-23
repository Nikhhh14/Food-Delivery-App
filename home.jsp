<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.util.List" %>
        <%@ page import="com.food.model.Restaurant" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Home | Foodie</title>
                <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <link rel="stylesheet" href="css/styles.css">
            </head>

            <body class="bg-body">

                <jsp:include page="navbar.jsp" />

                <!-- Hero Section -->
                <section class="hero-section position-relative overflow-hidden py-5">
                    <div class="container position-relative z-1">
                        <div class="row align-items-center">
                            <div class="col-lg-6 text-center text-lg-start mb-5 mb-lg-0">
                                <span class="badge badge-custom bg-white text-primary shadow-sm mb-3">
                                    <i class="fa-solid fa-fire me-2"></i> #1 Food Delivery App
                                </span>
                                <h1 class="hero-title display-3 fw-bold mb-3 ls-tight">
                                    Craving Something <br> <span class="text-primary-gradient">Delicious?</span>
                                </h1>
                                <p class="lead text-secondary mb-5" style="max-width: 500px;">
                                    Order from the best restaurants in town and get it delivered to your doorstep in
                                    minutes. Fresh, hot, and tasty!
                                </p>

                                <div class="d-flex justify-content-center justify-content-lg-start gap-3">
                                    <a href="#restaurants" class="btn btn-primary btn-lg shadow-primary">Order Now</a>
                                    <a href="#how-it-works" class="btn btn-outline-primary btn-lg">How it works</a>
                                </div>
                            </div>
                            <div class="col-lg-6 text-center">
                                <div class="position-relative">
                                    <!-- decorative blob behind image -->
                                    <div class="position-absolute top-50 start-50 translate-middle bg-primary opacity-10 blur-3xl rounded-circle"
                                        style="width: 400px; height: 400px; z-index: -1;"></div>

                                    <img src="https://images.unsplash.com/photo-1504674900247-0877df9cc836?q=80&w=1000&auto=format&fit=crop"
                                        class="img-fluid rounded-4 shadow-lg rotate-hover" alt="Delicious Food Platter"
                                        style="transform: rotate(-5deg); transition: transform 0.5s ease;">
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Decorative Elements -->
                    <div class="position-absolute top-0 start-0 translate-middle rounded-circle bg-primary opacity-10 blur-3xl"
                        style="width: 300px; height: 300px; filter: blur(80px); opacity: 0.1;"></div>
                    <div class="position-absolute bottom-0 end-0 translate-middle rounded-circle bg-warning opacity-10 blur-3xl"
                        style="width: 400px; height: 400px; filter: blur(100px); opacity: 0.1;"></div>
                </section>

                <!-- Restaurants Section -->
                <div class="container mb-5" id="restaurants">

                    <div class="d-flex align-items-end justify-content-between mb-5">
                        <div>
                            <h5 class="text-primary fw-bold text-uppercase tracking-wider mb-2">Order Now</h5>
                            <h2 class="fw-bold display-6 mb-0">Top Restaurants</h2>
                        </div>
                        <a href="#" class="btn btn-outline-primary btn-sm rounded-pill px-4">See All <i
                                class="fa-solid fa-arrow-right ms-2"></i></a>
                    </div>

                    <div class="row g-4">
                        <% List<Restaurant> restaurantList = (List<Restaurant>) request.getAttribute("restaurantList");
                                if (restaurantList != null && !restaurantList.isEmpty()) {
                                for (Restaurant r : restaurantList) {
                                %>
                                <div class="col-md-6 col-lg-4">
                                    <a href="menu?restaurantId=<%= r.getId() %>" class="text-decoration-none">
                                        <div class="card h-100">
                                            <div class="position-relative">
                                                <img src="<%= r.getImagePath() %>" class="card-img-top"
                                                    alt="<%= r.getName() %>">
                                                <div class="position-absolute top-0 end-0 m-3">
                                                    <span class="badge badge-custom bg-white text-dark shadow-sm">
                                                        <i class="fa-solid fa-clock text-primary me-1"></i>
                                                        30-40 mins
                                                    </span>
                                                </div>
                                            </div>
                                            <div class="card-body p-4">
                                                <div class="d-flex justify-content-between align-items-center mb-2">
                                                    <h5 class="card-title fw-bold text-dark mb-0">
                                                        <%= r.getName() %>
                                                    </h5>
                                                    <span class="badge badge-rating">
                                                        <%= r.getRating() %> <i class="fa-solid fa-star ms-1"></i>
                                                    </span>
                                                </div>
                                                <p class="text-secondary small mb-3 text-truncate-2">
                                                    <%= r.getCuisine() %>
                                                </p>
                                                <div class="d-flex align-items-center text-muted small gap-3">
                                                    <span><i class="fa-solid fa-location-dot me-1"></i>
                                                        Main Street, City Center
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                                <% } } else { %>
                                    <div class="col-12 text-center py-5">
                                        <div class="py-5">
                                            <i class="fa-solid fa-store fa-4x text-muted mb-3 opacity-25"></i>
                                            <h4 class="text-muted">No restaurants available right now.</h4>
                                            <p class="text-muted small">Please check back later.</p>
                                        </div>
                                    </div>
                                    <% } %>
                    </div>
                </div>

                <!-- Features Section -->
                <section class="py-5 bg-white mb-5" id="how-it-works">
                    <div class="container py-5">
                        <div class="text-center mb-5">
                            <h2 class="fw-bold mb-3">Why choose us?</h2>
                            <p class="text-muted">We provide the best experience for our customers</p>
                        </div>

                        <div class="row g-4 text-center">
                            <div class="col-md-4">
                                <div class="p-4 rounded-xl bg-body shadow-hover transition-all">
                                    <div class="d-inline-flex align-items-center justify-content-center bg-white rounded-circle shadow-sm mb-4 text-primary"
                                        style="width: 80px; height: 80px; font-size: 2rem;">
                                        <i class="fa-solid fa-truck-fast"></i>
                                    </div>
                                    <h4 class="fw-bold mb-3">Fast Delivery</h4>
                                    <p class="text-muted">Super fast delivery to your door. We ensure your food is fresh
                                        and hot.</p>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="p-4 rounded-xl bg-body shadow-hover transition-all">
                                    <div class="d-inline-flex align-items-center justify-content-center bg-white rounded-circle shadow-sm mb-4 text-primary"
                                        style="width: 80px; height: 80px; font-size: 2rem;">
                                        <i class="fa-solid fa-utensils"></i>
                                    </div>
                                    <h4 class="fw-bold mb-3">Fresh Food</h4>
                                    <p class="text-muted">We partner with the best restaurants to ensure high quality
                                        food.</p>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="p-4 rounded-xl bg-body shadow-hover transition-all">
                                    <div class="d-inline-flex align-items-center justify-content-center bg-white rounded-circle shadow-sm mb-4 text-primary"
                                        style="width: 80px; height: 80px; font-size: 2rem;">
                                        <i class="fa-solid fa-headset"></i>
                                    </div>
                                    <h4 class="fw-bold mb-3">24/7 Support</h4>
                                    <p class="text-muted">We are here to help you with any issues or questions you might
                                        have.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

                <jsp:include page="footer.jsp" />
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
            </body>

            </html>