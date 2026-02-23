<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Add Restaurant | Foodie</title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/styles.css">
    </head>

    <body class="bg-body d-flex flex-column min-vh-100">

        <jsp:include page="navbar.jsp" />

        <div class="container my-5 flex-grow-1">
            <div class="row justify-content-center">
                <div class="col-md-8 col-lg-6">

                    <!-- Card Container -->
                    <div class="card border-0 shadow-lg rounded-xl overflow-hidden glass-effect">
                        <div class="card-body p-5">

                            <div class="text-center mb-4">
                                <div class="d-inline-flex align-items-center justify-content-center bg-primary text-white rounded-circle mb-3 shadow-md"
                                    style="width: 60px; height: 60px; font-size: 1.5rem;">
                                    <i class="fa-solid fa-shop"></i>
                                </div>
                                <h3 class="fw-bold text-dark">Add New Restaurant</h3>
                                <p class="text-muted small">Expand your network with new partners</p>
                            </div>

                            <form action="addRestaurant" method="post">
                                <div class="mb-3">
                                    <label class="form-label fw-bold small text-uppercase text-secondary">Restaurant
                                        Name</label>
                                    <div class="input-group">
                                        <span class="input-group-text bg-light border-end-0"><i
                                                class="fa-solid fa-utensils text-muted"></i></span>
                                        <input type="text" name="name" class="form-control bg-light border-start-0 ps-0"
                                            placeholder="e.g. Burger King" required>
                                    </div>
                                </div>

                                <div class="mb-3">
                                    <label class="form-label fw-bold small text-uppercase text-secondary">Cuisine
                                        Type</label>
                                    <div class="input-group">
                                        <span class="input-group-text bg-light border-end-0"><i
                                                class="fa-solid fa-bowl-food text-muted"></i></span>
                                        <input type="text" name="cuisine"
                                            class="form-control bg-light border-start-0 ps-0"
                                            placeholder="e.g. American, Italian" required>
                                    </div>
                                </div>

                                <div class="mb-3">
                                    <label class="form-label fw-bold small text-uppercase text-secondary">Rating</label>
                                    <div class="input-group">
                                        <span class="input-group-text bg-light border-end-0"><i
                                                class="fa-solid fa-star text-muted"></i></span>
                                        <input type="number" step="0.1" name="rating"
                                            class="form-control bg-light border-start-0 ps-0" placeholder="e.g. 4.5"
                                            required>
                                    </div>
                                </div>

                                <div class="mb-4">
                                    <label class="form-label fw-bold small text-uppercase text-secondary">Image
                                        URL</label>
                                    <div class="input-group">
                                        <span class="input-group-text bg-light border-end-0"><i
                                                class="fa-solid fa-image text-muted"></i></span>
                                        <input type="url" name="imagePath"
                                            class="form-control bg-light border-start-0 ps-0" placeholder="https://..."
                                            required>
                                    </div>
                                    <div class="form-text small">Provide a high-quality image URL for the restaurant
                                        card.</div>
                                </div>

                                <button type="submit"
                                    class="btn btn-primary w-100 py-3 rounded-pill fw-bold shadow-primary text-uppercase tracking-wide">
                                    <i class="fa-solid fa-plus me-2"></i> Add Restaurant
                                </button>
                            </form>

                        </div>
                        <div class="card-footer bg-light py-3 text-center border-0">
                            <small class="text-muted">Want to go back? <a href="home"
                                    class="text-primary fw-bold text-decoration-none">Back to Home</a></small>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <!-- Decorative Background -->
        <div class="position-fixed top-0 start-0 w-100 h-100 pe-none" style="z-index: -1;">
            <div class="position-absolute top-0 start-0 translate-middle rounded-circle bg-primary opacity-10 blur-3xl"
                style="width: 600px; height: 600px;"></div>
            <div class="position-absolute bottom-0 end-0 translate-middle rounded-circle bg-warning opacity-10 blur-3xl"
                style="width: 500px; height: 500px;"></div>
        </div>

        <jsp:include page="footer.jsp" />
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>

    </html>