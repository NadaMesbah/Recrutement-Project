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
						<li class="current">Mes postulations</li>
					</ol>
				</nav>
				<h1>Mes postulations</h1>
			</div>
		</div>
		<!-- End Page Title -->

		<section id="starter-section" class="starter-section section">
			<div class="container d-flex flex-column align-items-center">
				<c:choose>
					<c:when test="${empty postulations}">
						<div class="alert alert-info w-75 text-center" role="alert">
							<i class="bi bi-info-circle-fill"></i> Vous n'avez pas encore de
							postulations.
						</div>
					</c:when>
					<c:otherwise>
						<div class="col-lg-12">
							<div
								class="team-member d-flex justify-content-center align-items-center flex-column w-100">
								<c:forEach items='${postulations}' var="dm">
									<div class="card bg-white text-dark mb-4 w-75 shadow-sm"
										data-aos="fade-up" data-aos-delay="100">
										<div
											class="card-header d-flex justify-content-between align-items-center">
											<span>Date : ${dm.getDate()}</span> <span>Status: <c:choose>
													<c:when test="${dm.getStatus() == 'pending'}">
														<span class="badge bg-warning" style="color: #996515; height:1.8rem; border: none; border-radius: 100vmax; font-size: 1em; font-weight:600;">${dm.getStatus()}</span>
													</c:when>
													<c:when test="${dm.getStatus() == 'accepted'}">
														<span class="badge bg-success" style=" height:1.8rem; border: none; border-radius: 100vmax; font-size: 1em; font-weight:600;">${dm.getStatus()}</span>
													</c:when>
													<c:when test="${dm.getStatus() == 'canceled'}">
														<span class="badge bg-danger" style=" height:1.8rem; border: none; border-radius: 100vmax; font-size: 1em; font-weight:600;">${dm.getStatus()}</span>
													</c:when>
												</c:choose>
											</span>
										</div>
										<div class="card-body text-center">
											<h5 class="card-title">${dm.getOffre().getTitre()}</h5>
											<div class="d-flex justify-content-center">
												<a class="btn btn-outline-primary mx-2"
													href="${pageContext.request.contextPath}/uploads/${dm.getCv()}"
													download="${dm.getCv()}"> <img alt="CV"
													src="https://upload.wikimedia.org/wikipedia/commons/thumb/8/87/PDF_file_icon.svg/833px-PDF_file_icon.svg.png"
													width="24" height="24"> CV
												</a> <a class="btn btn-outline-secondary mx-2"
													href="${pageContext.request.contextPath}/uploads/${dm.getLm()}"
													download="${dm.getLm()}"> <img alt="LM"
													src="https://upload.wikimedia.org/wikipedia/commons/thumb/8/87/PDF_file_icon.svg/833px-PDF_file_icon.svg.png"
													width="24" height="24"> LM
												</a>
											</div>
											<div class="mt-3">
												<c:if test="${dm.getStatus() == 'pending'}">
													<a class="btn"
														href="<%=request.getContextPath()%>/editPostulation?id=${dm.getId()}" style="color:white; background-color: #496e4c; padding: 0.6rem; border: none; border-radius: 100vmax; font-size: 1em; cursor: pointer; font-weight:600;">Modifier</a>
												</c:if>
												<form
													action="<%=request.getContextPath()%>/deletePostulation"
													method="post" style="display: inline;">
													<input type="hidden" name="id" value="${dm.getId()}">
													<button type="submit" class="btn" style="color:white; background-color: #783b58;  padding: 0.6rem; border: none; border-radius: 100vmax; font-size: 1em; cursor: pointer; font-weight:600;">Supprimer</button>
												</form>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
							<!-- End Team Member -->
						</div>
					</c:otherwise>
				</c:choose>
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