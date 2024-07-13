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

<!-- Fonts -->
<link href="https://fonts.googleapis.com" rel="preconnect">
<link href="https://fonts.gstatic.com" rel="preconnect" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;1,300;1,400;1,500;1,600;1,700;1,800&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800&display=swap"
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
  <style>
    ul {
        list-style-type: none;
        padding-left: 0;
    }

    ul li {
        display: flex;
        align-items: flex-start; /* Aligns icon and text at the top */
        margin-bottom: 10px; /* Adds space between items */
    }

    ul li i.bi-check-circle {
        color: #00bfff; /* couleur bleu ciel */
        margin-right: 8px; /* espace entre l'icône et le texte */
        flex-shrink: 0; /* Prevents icon from shrinking */
       
    }

    ul li span {
        flex: 1; /* Allows text to wrap properly */
    }
</style>

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
						<li class="current">Liste des offres</li>
					</ol>
				</nav>
				<h1>Liste des offres</h1>
			</div>
		</div>
		<!-- End Page Title -->

		<!-- Starter Section Section -->
		<section id="starter-section" class="starter-section section">

			<div class="container section-title pb-lg-2">
				<h2>Offres actuelles</h2>
			</div>

			<div class="container">
				<div class="col">

					<div class="col-lg-12">
						<div
							class="team-member d-flex justify-content-center align-items-center">
							<div class="member-info w-50">
								<c:forEach items="${offres}" var="item">
									<div class="card mt-3">
										<div
											class="card-header d-flex justify-content-between align-items-center">
											<div class="card-title fw-bold">
												<c:out value='${item.getTitre()}' />
											</div>
											<p class="badge bg-warning text-center fw-bold fs-6 mt-2"
												style="color: #996515; height: 1.8rem; border: none; border-radius: 100vmax; font-size: 1em; cursor: pointer; font-weight: 600;">
												<c:out value='${item.getType().getLibelle()}' />
											</p>
										</div>
										<div class="card-body">
											<p class="card-text">
												<c:out value='${item.getDescription()}' />
											</p>
											<div>
												<h5>Attentes :</h5>
												<ul>
													<c:forEach items="${item.getAttentes()}" var="attente">
														<li><i class="bi bi-check-circle"></i> <span><c:out
																	value='${attente.getDescription()}' /></span></li>
													</c:forEach>
												</ul>
											</div>
											<div>
												<h5>Responsabilités :</h5>
												<ul>
													<c:forEach items="${item.getResponsabilites()}"
														var="responsabilite">
														<li><i class="bi bi-check-circle"></i> <span><c:out
																	value='${responsabilite.getDescription()}' /></span></li>
													</c:forEach>
												</ul>
											</div>
											<div>
												<h5>Qualifications :</h5>
												<ul>
													<c:forEach items="${item.getQualifications()}"
														var="qualification">
														<li><i class="bi bi-check-circle"></i> <span><c:out
																	value='${qualification.getDescription()}' /></span></li>
													</c:forEach>
												</ul>
											</div>
											<c:if test="${user.getRole() == 'admin'}">
												<a class="btn"
													href="<%=request.getContextPath()%>/ServletOffreModifier?id=${item.getId()}"
													class="card-link"
													style="color: white; background-color: #496e4c; padding: 0.6rem; border: none; border-radius: 100vmax; font-size: 1em; cursor: pointer; font-weight: 600;">Modifier</a>
												<a class="btn"
													href="<%=request.getContextPath()%>/ServletOffreSupprimer?id=${item.getId()}"
													class="card-link"
													style="color: white; background-color: #783b58; padding: 0.6rem; border: none; border-radius: 100vmax; font-size: 1em; cursor: pointer; font-weight: 600;">Supprimer</a>
												<a class="btn btn-info"
													href="<%=request.getContextPath()%>/postulations?id=${item.getId()}"
													class="card-link"
													style="color: white; background-color: #589da3; padding: 0.6rem; border: none; border-radius: 100vmax; font-size: 1em; cursor: pointer; color: white; font-weight: 600;">Voir
													postulations</a>
											</c:if>

											<c:if test="${user.getRole() == 'candidat'}">
												<a class="btn"
													href="<%=request.getContextPath()%>/postuler?id=${item.getId()}"
													class="card-link"
													style="color: white; background-color: #9663ba; padding: 0.6rem; border: none; border-radius: 100vmax; font-size: 1em; cursor: pointer; font-weight: 600;">Postuler</a>
											</c:if>
										</div>
									</div>
								</c:forEach>
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

</body>

</html>
