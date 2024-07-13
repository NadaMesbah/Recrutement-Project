<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<title>RecrutMaroc</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="assets/img/favicon.png" rel="icon">
<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Fonts -->
<link href="https://fonts.googleapis.com" rel="preconnect">
<link href="https://fonts.gstatic.com" rel="preconnect" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;1,300;1,400;1,500;1,600;1,700;1,800&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Jost:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="assets/vendor/aos/aos.css" rel="stylesheet">
<link href="assets/vendor/glightbox/css/glightbox.min.css"
	rel="stylesheet">
<link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">



<!-- Main CSS File -->
<link href="assets/css/main.css" rel="stylesheet">

<!-- =======================================================
  * Template Name: Arsha
  * Template URL: https://bootstrapmade.com/arsha-free-bootstrap-html-template-corporate/
  * Updated: May 18 2024 with Bootstrap v5.3.3
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<style>
.footer-newsletter {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	display: flex;
	justify-content: center;
	align-items: center;
	margin: 0;
}

.newsletter-container {
	background-color: #fff;
	padding: 2em;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	border-radius: 8px;
	text-align: center;
	max-width: 400px;
	width: 100%;
}

.newsletter-container h4 {
	margin-bottom: 1em;
	color: #333;
}

.newsletter-container p {
	margin-bottom: 2em;
	color: #666;
}

.newsletter-form {
	display: flex;
	flex-direction: row;
	align-items: center;
}

.newsletter-form input[type="email"] {
	padding: 1em;
	border: 1px solid #ddd;
	border-radius: 4px 0 0 4px;
	font-size: 1em;
	flex: 1;
}

.newsletter-form input[type="submit"] {
	padding: 5rem;
	background-color: white;
	color: #007BFF;
	border: none;
	border-radius: 0 4px 4px 0;
	font-size: 1em;
	cursor: pointer;
}

.newsletter-form input[type="submit"]:hover {
	background-color: #0056b3;
}
</style>


</head>

<body>
	<div id="message" style="display: none;"><%=request.getAttribute("message")%></div>
	<div id="alertType" style="display: none;"><%=request.getAttribute("alertType")%></div>

	<footer id="footer" class="footer"
		style="background-color: color-mix(in srgb, #37517e, transparent 95%);">
		<c:if test="${user.getRole() != 'admin'}">
			<div class="footer-newsletter" id="newsletter">
				<div class="newsletter-container">
					<h4>Abonnez-vous à notre Newsletter</h4>
					<p>En vous abonnant à notre newsletter, vous recevrez des
						notifications en temps réel dès qu'une nouvelle offre d'emploi est
						publiée sur notre plateforme !</p>
					<form id="newsletterForm"
						action="${pageContext.request.contextPath}/subscribe"
						method="post" class="newsletter-form">
						<input type="email" name="email" placeholder="Enter your email"
							>
						<button
							onclick="document.getElementById('newsletterForm').submit();"
							type="submit"
							style="padding: 0.6rem; color: white; background-color: #42b0f5; border: none; border-radius: 100vmax; font-size: 1em; cursor: pointer;">S'abboner</button>
					</form>
					<div id="response-message" class="alert"></div>
				</div>
			</div>
		</c:if>


		<div class="container footer-top">
			<div class="row gy-4">
				<div class="col-lg-4 col-md-6 footer-about">
					<a href="index.html" class="d-flex align-items-center"> <span
						class="sitename">RecrutMaroc</span>
					</a>
					<div class="footer-contact pt-3">
						<p>Faculté des sciences FSM</p>
						<p>Meknes, Maroc</p>
						<p class="mt-3">
							<strong>Phone:</strong> <span>+212 702875466</span>
						</p>
						<p>
							<strong>Email:</strong> <span>doyen@fsm.ma</span>
						</p>
					</div>
				</div>

				<div class="col-lg-2 col-md-3 footer-links">
					<h4>Consulter</h4>
					<ul>
						<li><i class="bi bi-chevron-right"></i> <a href="#">Home</a></li>
						<li><i class="bi bi-chevron-right"></i> <a href="#">A
								propos de nous</a></li>
						<li><i class="bi bi-chevron-right"></i> <a href="#">Nos
								Offres</a></li>
						<li><i class="bi bi-chevron-right"></i> <a href="#">FAQs</a></li>
					</ul>
				</div>

				<div class="col-lg-2 col-md-3 footer-links">
					<h4>Nos Offres</h4>
					<ul>
						<li><i class="bi bi-chevron-right"></i> <a href="#">Full Stack Developer</a></li>
						<li><i class="bi bi-chevron-right"></i> <a href="#">Analyste Financier</a></li>
						<li><i class="bi bi-chevron-right"></i> <a href="#">Data Analyst</a></li>
						<li><i class="bi bi-chevron-right"></i> <a href="#">Marketing Specialist</a></li>
						<li><i class="bi bi-chevron-right"></i> <a href="#">Software Developer</a></li>
					</ul>
				</div>

				<div class="col-lg-4 col-md-12">
					<h4>Suivez-nous</h4>
					<p>Pour plus d'informations, n'hesitez pas de nous suivre sur
						les réseaux sociaux.</p>
					<div class="social-links d-flex">
						<a href=""><i class="bi bi-twitter-x"></i></a> <a href=""><i
							class="bi bi-facebook"></i></a> <a href=""><i
							class="bi bi-instagram"></i></a> <a href=""><i
							class="bi bi-linkedin"></i></a>
					</div>
				</div>

			</div>
		</div>



		<script>
    document.addEventListener('DOMContentLoaded', function() {
        document.getElementById('newsletterForm').addEventListener('submit', function(event) {
            event.preventDefault();
            var form = event.target;
            var formData = new FormData(form);
            var email = formData.get('email');

            // Check subscription status before showing the confirmation pop-up
            fetch('${pageContext.request.contextPath}/checkSubscription', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: new URLSearchParams({
                    'email': email
                })
            }).then(response => response.text())
              .then(data => {
                  var parser = new DOMParser();
                  var doc = parser.parseFromString(data, 'text/html');
                  var isSubscribed = doc.getElementById('isSubscribed').value === 'true';
                  var message = doc.getElementById('subscriptionMessage').value;

                  if (isSubscribed) {
                      // Redirect to result.jsp if the user is already subscribed
                      window.location.href = '${pageContext.request.contextPath}/result.jsp';
                  } else {
                      // Show SweetAlert confirmation pop-up if the user is not subscribed
                      Swal.fire({
                          title: 'Confirmation d\'abonnement',
                          text: "Voulez-vous vous abonner à notre newsletter?",
                          icon: 'question',
                          showCancelButton: false,
                          confirmButtonColor: '#3085d6',
                          cancelButtonColor: '#d33',
                          confirmButtonText: 'Oui, abonne moi!'
                      }).then((result) => {
                          if (result.isConfirmed) {
                              fetch('${pageContext.request.contextPath}/subscribe', {
                                  method: 'POST',
                                  headers: {
                                      'Content-Type': 'application/x-www-form-urlencoded'
                                  },
                                  body: new URLSearchParams({
                                      'email': email
                                  })
                              }).then(response => response.text())
                                .then(data => {
                                    // Use a hidden div to pass the message and alertType from the server to the client-side JavaScript
                                    var parser = new DOMParser();
                                    var doc = parser.parseFromString(data, 'text/html');
                                    var message = doc.getElementById('message').innerText;
                                    var alertType = doc.getElementById('alertType').innerText;

                                    Swal.fire({
                                        icon: alertType,
                                        title: message
                                    });
                                }).catch(error => {
                                    Swal.fire(
                                        'Error!',
                                        'Une erreur est survenue lors de l\'abonnement.',
                                        'error'
                                    );
                                });
                          }
                      });
                  }
              }).catch(error => {
                  Swal.fire(
                      'Error!',
                      'Une erreur est survenue lors de la vérification de l\'abonnement.',
                      'error'
                  );
              });
        });
    });
</script>


	</footer>

</body>
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="assets/vendor/php-email-form/validate.js"></script>
<script src="assets/vendor/aos/aos.js"></script>
<script src="assets/vendor/glightbox/js/glightbox.min.js"></script>
<script src="assets/vendor/swiper/swiper-bundle.min.js"></script>
<script src="assets/vendor/waypoints/noframework.waypoints.js"></script>
<script src="assets/vendor/imagesloaded/imagesloaded.pkgd.min.js"></script>
<script src="assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>

<!-- Main JS File -->
<script src="assets/js/main.js"></script>
</html>