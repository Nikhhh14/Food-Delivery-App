<!DOCTYPE html>
<html>

<head>
    <title>Register | Foodie</title>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/styles.css">
</head>

<body class="bg-body d-flex align-items-center justify-content-center min-vh-100 position-relative overflow-hidden">

    <!-- Decorative Background -->
    <div class="position-absolute top-0 start-0 translate-middle rounded-circle bg-primary opacity-10 blur-3xl"
        style="width: 400px; height: 400px; filter: blur(100px); opacity: 0.1;"></div>
    <div class="position-absolute bottom-0 end-0 translate-middle rounded-circle bg-secondary opacity-10 blur-3xl"
        style="width: 300px; height: 300px; filter: blur(80px); opacity: 0.1;"></div>

    <div class="col-md-5 position-relative z-1 px-3">
        <div class="text-center mb-4">
            <a href="home" class="text-decoration-none">
                <h1 class="fw-bold text-dark display-5"><i class="fa-solid fa-burger text-primary"></i> Foodie<span
                        class="text-primary">.</span></h1>
            </a>
        </div>

        <div class="card shadow-lg border-0 p-4 p-md-5 rounded-xl">
            <div class="text-center mb-5">
                <h4 class="fw-bold mb-1">Create Account</h4>
                <p class="text-muted small">Join us to order delicious food</p>
            </div>

            <form action="register" method="post">
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" name="username" id="username" placeholder="Username"
                        required>
                    <label for="username">Full Name</label>
                </div>

                <div class="form-floating mb-3">
                    <input type="email" class="form-control" name="email" id="email" placeholder="name@example.com"
                        required>
                    <label for="email">Email address</label>
                </div>

                <div class="form-floating mb-3">
                    <input type="password" class="form-control" name="password" id="password" placeholder="Password"
                        required>
                    <label for="password">Password</label>
                </div>

                <div class="form-floating mb-4">
                    <input type="text" class="form-control" name="address" id="address" placeholder="Address">
                    <label for="address">Delivery Address</label>
                </div>

                <button type="submit" class="btn btn-primary w-100 py-3 mb-3 fw-bold shadow-primary">Sign Up</button>
            </form>

            <div class="text-center mt-3">
                <p class="text-muted small mb-0">Already have an account? <a href="login.jsp"
                        class="text-decoration-none fw-bold text-primary">Log in</a></p>
            </div>
        </div>

        <div class="text-center mt-4">
            <a href="home" class="text-decoration-none text-muted small hover-primary"><i
                    class="fa-solid fa-arrow-left me-1"></i> Back to Home</a>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>