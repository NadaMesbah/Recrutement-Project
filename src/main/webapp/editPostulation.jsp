<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
  <link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;1,300;1,400;1,500;1,600;1,700;1,800&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Jost:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/aos/aos.css" rel="stylesheet">
  <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
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
            <li class="current">Modifier ma postulation</li>
          </ol>
        </nav>
        <h1>Modifier ma postulation</h1>
      </div>
    </div><!-- End Page Title -->

    <!-- Starter Section Section -->
    <section id="starter-section" class="starter-section section">

      <div class="container section-title pb-lg-2">
        <h2>Modification de la postulation</h2>
      </div> 

      <div class="container">
        <div class="col">

          <div class="col-lg-12">
            <div class="team-member d-flex justify-content-center align-items-center">
              <div class="member-info w-50">
                <form method="post" action="<%=request.getContextPath()%>/editPostulation" enctype="multipart/form-data">
            		<input type="hidden" name="id" value="${postulation.id}">
            		<div class="form-group mt-5">
                		<label for="cv">CV:</label>
                		<input type="file" class="form-control" id="cv" name="cv" accept=".pdf,.doc,.docx">
                        <c:if test="${not empty postulation.cv}">
                            <a href="${pageContext.request.contextPath}/uploads/${postulation.cv}" target="_blank">Voir CV actuel</a>
                            <input type="hidden" name="existingCv" value="${postulation.cv}">
                        </c:if>
            		</div>
            		<div class="form-group mt-5">
                		<label for="lm">Lettre de motivation:</label>
                		<input type="file" class="form-control" id="lm" name="lm" accept=".pdf,.doc,.docx">
                        <c:if test="${not empty postulation.lm}">
                            <a href="${pageContext.request.contextPath}/uploads/${postulation.lm}" target="_blank">Voir Lettre de motivation actuelle</a>
                            <input type="hidden" name="existingLm" value="${postulation.lm}">
                        </c:if>
            		</div>
            		<button type="submit" style="color:white; background-color: #496e4c; padding: 0.6rem; border: none; border-radius: 100vmax; font-size: 1em; cursor: pointer; font-weight:600;" class="btn mt-5">Modifier</button>
        		</form>
              </div>
            </div>
          </div><!-- End Team Member -->

        </div>
		</div>

    </section><!-- /Starter Section Section -->

  </main>
   <jsp:include page="/Shared/footer.jsp"></jsp:include>
  <!-- Scroll Top -->
  <a href="#" id="scroll-top" class="scroll-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

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