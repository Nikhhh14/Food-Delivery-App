<!DOCTYPE html>
<html>

<head>
    <title>Order Confirmed | Foodie</title>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/styles.css">
</head>

<body class="bg-body d-flex align-items-center justify-content-center min-vh-100 position-relative overflow-hidden">

    <!-- Decorative Background -->
    <div class="position-absolute top-0 start-0 translate-middle rounded-circle bg-success opacity-10 blur-3xl"
        style="width: 400px; height: 400px; filter: blur(100px); opacity: 0.1;"></div>
    <div class="position-absolute bottom-0 end-0 translate-middle rounded-circle bg-primary opacity-10 blur-3xl"
        style="width: 300px; height: 300px; filter: blur(80px); opacity: 0.1;"></div>

    <div class="col-md-5 text-center position-relative z-1 px-3">

        <div class="card shadow-lg border-0 p-5 rounded-xl">
            <div class="mb-4">
                <div class="bg-success bg-opacity-10 text-success rounded-circle d-inline-flex align-items-center justify-content-center pulse-animation"
                    style="width: 100px; height: 100px;">
                    <i class="fa-solid fa-check display-4"></i>
                </div>
            </div>

            <h2 class="fw-bold mb-2">Order Confirmed!</h2>
            <p class="text-muted mb-4">We've received your order and we'll start preparing it right away.</p>

            <% String orderId=request.getParameter("orderId"); %>

                <% if(orderId !=null) { %>
                    <div class="bg-light rounded-3 p-3 mb-4 border d-inline-block px-5">
                        <small class="text-muted text-uppercase fw-bold tracking-wide" style="font-size: 0.75rem;">Order
                            ID</small>
                        <h3 class="fw-bold m-0 text-dark mt-1">#<%= orderId %>
                        </h3>
                    </div>
                    <% } %>

                        <div>
                            <a href="home"
                                class="btn btn-primary rounded-pill px-5 py-3 fw-bold shadow-primary w-100">Continue
                                Ordering</a>
                        </div>
        </div>

    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        .pulse-animation {
            animation: pulse 2s infinite;
        }

        @keyframes pulse {
            0% {
                box-shadow: 0 0 0 0 rgba(46, 213, 115, 0.4);
            }

            70% {
                box-shadow: 0 0 0 20px rgba(46, 213, 115, 0);
            }

            100% {
                box-shadow: 0 0 0 0 rgba(46, 213, 115, 0);
            }
        }
    </style>
</body>

</html>