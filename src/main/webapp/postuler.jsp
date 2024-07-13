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
<style>
.file-link {
    margin-bottom: 10px;
    font-size: 1rem;
    color: #007bff;
    text-decoration: underline;
    cursor: pointer;
    display: none;
}

.custom-file-input {
    position: relative;
    display: inline-block;
}

.custom-file-input input[type="file"] {
    position: relative;
    cursor: pointer;
}

.custom-file-input input[type="file"]::file-selector-button {
    display: none;
}

.custom-file-input input[type="file"]::before {
    content: 'Choisir fichier';
    display: inline-block;
    background: #007bff;
    color: white;
    padding: 0.5rem 1rem;
    border: none;
    border-radius: 0.25rem;
    cursor: pointer;
}

.custom-file-input input[type="file"]:hover::before {
    background: #0056b3;
}

</style>
<!-- =======================================================
  * Template Name: Arsha
  * Template URL: https://bootstrapmade.com/arsha-free-bootstrap-html-template-corporate/
  * Updated: May 18 2024 with Bootstrap v5.3.3
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body class="starter-page-page">

	<jsp:include page="/Shared/header.jsp"></jsp:include>

	<main class="main">

		<!-- Page Title -->
		<div class="page-title" data-aos="fade">
			<div class="container">
				<nav class="breadcrumbs">
					<ol>
						<li><a href="<%=request.getContextPath()%>/index.jsp">Home</a></li>
						<li class="current">Postuler</li>
					</ol>
				</nav>
				<h1>Postuler</h1>
			</div>
		</div>
		<!-- End Page Title -->

		<!-- Starter Section Section -->
		<section id="starter-section" class="starter-section section">

			<div class="container section-title pb-lg-2">
				<h2>Postuler pour l'offre : ${offre.getTitre()}</h2>
			</div>

			<div class="container">
				<div class="col">

					<div class="col-lg-12">
						<div
							class="team-member d-flex justify-content-center align-items-center">
							<div class="member-info w-50">
								<form method="post"
									action="<%=request.getContextPath()%>/postuler"
									enctype="multipart/form-data">

									<!-- <a href="${pageContext.request.contextPath}/PostulationDetails?id=${postulation.id}">View Details</a>
			
				<input type="hidden" name="id" value="${param.id}">
			 -->
									<input type="hidden" name="id" value="${id_offre}" readonly>

									<p class="fw-normal mt-3 mb-3">Ajoutez votre CV :</p>
									<div class="input-group mb-3">
										<input class="form-control" type="file" id="cv" name="cv"
											accept=".pdf,.doc,.docx" required />
									</div>

									<p class="fw-normal mb-3">Ajoutez votre Lettre de
										motivation :</p>
									<div class="input-group mb-3">
										<input class="form-control" type="file" id="lm" name="lm"
											accept=".pdf,.doc,.docx" required />
									</div>


									<input
										style="color: white; padding: 0.6rem; border: none; border-radius: 100vmax; font-size: 1em; cursor: pointer; font-weight: 600;"
										type="submit" class="btn btn-success" value="Envoyer" />
								</form>
							</div>
						</div>
					</div>
					<!-- End Team Member -->

				</div>
			</div>

		</section>
		<!-- /Starter Section Section -->

	</main>
	<jsp:include page="/Shared/footer.jsp"></jsp:include>
	<!-- Scroll Top -->
	<a href="#" id="scroll-top"
		class="scroll-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- Preloader -->
	<div id="preloader"></div>

	<!-- Vendor JS Files -->
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
	<script>
	document.addEventListener('DOMContentLoaded', function() {
	    var cvInput = document.getElementById('cv');
	    var lmInput = document.getElementById('lm');

	    function handleFileSelect(event, linkId) {
	        var input = event.target;
	        var link = document.getElementById(linkId);
	        var fileName = input.files[0].name;

	        if (fileName) {
	            link.textContent = fileName;
	            link.href = URL.createObjectURL(input.files[0]);
	            link.style.display = 'inline';
	        } else {
	            link.style.display = 'none';
	        }
	    }

	    cvInput.addEventListener('change', function(event) {
	        handleFileSelect(event, 'cv-link');
	    });

	    lmInput.addEventListener('change', function(event) {
	        handleFileSelect(event, 'lm-link');
	    });
	});

	</script>

</body>

</html>