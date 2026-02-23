<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.util.List" %>
        <%@ page import="com.food.model.Menu" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Menu | Foodie</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
                <link rel="stylesheet" href="css/styles.css">
            </head>

            <body class="bg-body d-flex flex-column min-vh-100">

                <jsp:include page="navbar.jsp" />

                <div class="container my-5 pt-4 flex-grow-1">

                    <div class="mb-5 d-flex align-items-end justify-content-between border-bottom pb-4">
                        <div>
                            <h2 class="fw-bold display-6 mb-1 text-dark">Menu Items</h2>
                            <p class="text-muted mb-0">Select your favorite dishes from this restaurant</p>
                        </div>
                        <div>
                            <span class="badge bg-white text-dark border px-3 py-2 rounded-pill shadow-sm"><i
                                    class="fa-solid fa-filter me-2 text-primary"></i>Veg / Non-Veg</span>
                        </div>
                    </div>

                    <div class="row g-4">
                        <% List<Menu> list = (List<Menu>) request.getAttribute("menuList");
                                Integer restaurantId = (Integer) request.getAttribute("restaurantId");

                                if (list != null && !list.isEmpty()) {
                                for (Menu m : list) {
                                %>
                                <div class="col-lg-6">
                                    <div class="card p-3 h-100 border-0 shadow-sm transition-hover">
                                        <div class="row g-0 align-items-center h-100">
                                            <!-- Image Column -->
                                            <div class="col-4">
                                                <div class="position-relative h-100" style="min-height: 140px;">
                                                    <img src="<%= m.getImagePath() %>"
                                                        class="img-fluid rounded-4 w-100 h-100 object-fit-cover shadow-sm"
                                                        alt="<%= m.getName() %>">
                                                    <span
                                                        class="position-absolute top-0 start-0 m-2 bg-white rounded-circle p-1 shadow-sm lh-1"
                                                        style="width: 24px; height: 24px; display: flex; align-items: center; justify-content: center;">
                                                        <i class="fa-regular fa-square-caret-up text-success small"></i>
                                                    </span>
                                                </div>
                                            </div>

                                            <!-- Content Column -->
                                            <div class="col-8">
                                                <div
                                                    class="card-body py-0 pe-0 ps-3 d-flex flex-column h-100 justify-content-between">
                                                    <div>
                                                        <div
                                                            class="d-flex justify-content-between align-items-start mb-1">
                                                            <h5 class="fw-bold text-dark mb-0 text-truncate">
                                                                <%= m.getName() %>
                                                            </h5>
                                                            <h5 class="text-primary fw-bold mb-0">₹<%= m.getPrice() %>
                                                            </h5>
                                                        </div>
                                                        <p class="text-secondary small mb-3 text-truncate-2"
                                                            style="font-size: 0.85rem;">
                                                            <%= m.getDescription() %>
                                                        </p>
                                                    </div>

                                                    <div>
                                                        <form action="cart" method="post"
                                                            class="d-flex align-items-center justify-content-end gap-2">
                                                            <input type="hidden" name="menuId" value="<%= m.getId() %>">
                                                            <input type="hidden" name="restaurantId"
                                                                value="<%= restaurantId %>">

                                                            <div class="input-group input-group-sm"
                                                                style="width: 100px;">
                                                                <button type="button" class="btn btn-light border"
                                                                    onclick="this.parentNode.querySelector('input[type=number]').stepDown()">-</button>
                                                                <input type="number" name="quantity" value="1" min="1"
                                                                    max="10"
                                                                    class="form-control text-center border-top border-bottom border-0 bg-white"
                                                                    readonly>
                                                                <button type="button" class="btn btn-light border"
                                                                    onclick="this.parentNode.querySelector('input[type=number]').stepUp()">+</button>
                                                            </div>

                                                            <button
                                                                class="btn btn-primary btn-sm rounded-pill px-3 shadow-sm fw-semibold">
                                                                ADD <i class="fa-solid fa-plus ms-1 small"></i>
                                                            </button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <% } } else { %>
                                    <div class="col-12 text-center py-5">
                                        <p class="text-muted">No menu items available for this restaurant.</p>
                                    </div>
                                    <% } %>
                    </div>
                </div>

                <jsp:include page="footer.jsp" />

                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
            </body>

            </html>