<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.util.Map" %>
        <%@ page import="com.food.model.CartItem" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Your Cart | Foodie</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
                <link rel="stylesheet" href="css/styles.css">
            </head>

            <body class="bg-body d-flex flex-column min-vh-100">

                <jsp:include page="navbar.jsp" />

                <div class="container my-5 pt-4 flex-grow-1">
                    <div class="row justify-content-center">
                        <div class="col-lg-10">
                            <div class="d-flex align-items-center gap-3 mb-4">
                                <div class="bg-white rounded-circle p-2 shadow-sm d-flex align-items-center justify-content-center"
                                    style="width: 48px; height: 48px;">
                                    <i class="fa-solid fa-cart-shopping text-primary fs-5"></i>
                                </div>
                                <div>
                                    <h4 class="fw-bold mb-0">Your Food Cart</h4>
                                    <p class="text-muted small mb-0">Review your items and checkout</p>
                                </div>
                            </div>

                            <% Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
                                    double total = 0;
                                    if (cart != null && !cart.isEmpty()) {
                                    %>

                                    <div class="card border-0 shadow-sm overflow-hidden mb-4 rounded-xl">
                                        <div class="table-responsive">
                                            <table class="table table-hover align-middle mb-0">
                                                <thead class="bg-light">
                                                    <tr>
                                                        <th scope="col"
                                                            class="py-3 px-4 border-0 text-secondary small text-uppercase tracking-wide">
                                                            Item</th>
                                                        <th scope="col"
                                                            class="py-3 border-0 text-secondary small text-uppercase tracking-wide">
                                                            Price</th>
                                                        <th scope="col"
                                                            class="py-3 border-0 text-secondary small text-uppercase tracking-wide">
                                                            Quantity</th>
                                                        <th scope="col"
                                                            class="py-3 px-4 border-0 text-end text-secondary small text-uppercase tracking-wide">
                                                            Total</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <% for (CartItem item : cart.values()) { total +=item.getSubTotal();
                                                        %>
                                                        <tr>
                                                            <td class="px-4 py-3">
                                                                <img src="<%= item.getImagePath() %>"
                                                                    class="rounded-3 shadow-sm object-fit-cover"
                                                                    style="width: 60px; height: 60px;"
                                                                    alt="<%= item.getName() %>">
                                                                <div>
                                                                    <span class="fw-bold text-dark d-block">
                                                                        <%= item.getName() %>
                                                                    </span>
                                                                </div>
                                        </div>
                                        </td>
                                        <td class="text-muted fw-medium">₹<%= item.getPrice() %>
                                        </td>
                                        <td>
                                            <div
                                                class="border rounded-pill px-3 py-1 d-inline-block bg-white small fw-bold shadow-sm">
                                                <%= item.getQuantity() %>
                                            </div>
                                        </td>
                                        <td class="px-4 text-end fw-bold text-primary">₹<%= item.getSubTotal() %>
                                        </td>
                                        </tr>
                                        <% } %>
                                            </tbody>
                                            </table>
                                    </div>
                        </div>

                        <div class="row g-4 justify-content-end">
                            <div class="col-md-6 col-lg-5">
                                <div class="card border-0 shadow-sm p-4 rounded-xl">
                                    <h5 class="fw-bold mb-4">Order Summary</h5>

                                    <div class="d-flex justify-content-between mb-2">
                                        <span class="text-muted">Subtotal</span>
                                        <span class="fw-bold">₹<%= total %></span>
                                    </div>
                                    <div class="d-flex justify-content-between mb-3 border-bottom pb-3">
                                        <span class="text-muted">Delivery Fee</span>
                                        <span class="text-success fw-bold">Free</span>
                                    </div>
                                    <div class="d-flex justify-content-between align-items-center mb-4">
                                        <span class="fw-bold fs-5">Total</span>
                                        <span class="fw-bold fs-4 text-primary">₹<%= total %></span>
                                    </div>

                                    <form action="order" method="post">
                                        <div class="mb-3">
                                            <label class="form-label small text-muted fw-bold text-uppercase">Payment
                                                Method</label>
                                            <select name="paymentMode" class="form-select border-2"
                                                style="padding: 0.8rem 1rem;">
                                                <option value="COD">Cash on Delivery</option>
                                                <option value="ONLINE">Online Payment (UPI/Card)</option>
                                            </select>
                                        </div>

                                        <button
                                            class="btn btn-primary w-100 py-3 fw-bold shadow-primary rounded-pill mt-2">
                                            Checkout Now <i class="fa-solid fa-arrow-right ms-2"></i>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>

                        <% } else { %>

                            <div class="text-center py-5">
                                <div class="mb-4">
                                    <div class="bg-white rounded-circle p-4 shadow-sm d-inline-block">
                                        <i class="fa-solid fa-cart-arrow-down text-muted opacity-25"
                                            style="font-size: 4rem;"></i>
                                    </div>
                                </div>
                                <h3 class="fw-bold text-secondary">Your cart is empty</h3>
                                <p class="text-muted mb-4">Looks like you haven't added anything to your
                                    cart yet.</p>
                                <a href="home" class="btn btn-primary rounded-pill px-5 py-3 fw-bold shadow-primary">
                                    Browse Restaurants
                                </a>
                            </div>

                            <% } %>
                    </div>
                </div>
                </div>

                <jsp:include page="footer.jsp" />

                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
            </body>

            </html>