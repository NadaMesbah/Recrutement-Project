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
   <script>
    function addResponsibilityField() {
        var container = document.getElementById("responsibilitiesContainer");
        var textarea = document.createElement("textarea");
        textarea.className = "form-control"; // Add the class "form-control"
        textarea.placeholder = "ajouter une responsabilité"; // Add placeholder
        textarea.rows = "3"; // Add rows
        textarea.name = "responsabilites";
        container.appendChild(textarea);
        container.appendChild(document.createElement("br"));
    }

    function addQualificationField() {
        var container = document.getElementById("qualificationsContainer");
        var textarea = document.createElement("textarea");
        textarea.className = "form-control"; // Add the class "form-control"
        textarea.placeholder = "ajouter une qualification"; // Add placeholder
        textarea.rows = "3"; // Add rows
        textarea.name = "qualifications";
        container.appendChild(textarea);
        container.appendChild(document.createElement("br"));
    }

    function addExpectationField() {
        var container = document.getElementById("expectationsContainer");
        var textarea = document.createElement("textarea");
        textarea.className = "form-control"; // Add the class "form-control"
        textarea.placeholder = "ajouter une attente"; // Add placeholder
        textarea.rows = "3"; // Add rows
        textarea.name = "attentes";
        container.appendChild(textarea);
        container.appendChild(document.createElement("br"));
    }
    
    // Vérifie si la liste des responsabilités est nulle avant d'ajouter un champ de saisie
    function checkAndAddResponsibilityField() {
        var responsabilites = document.getElementsByName("responsabilites");
        if (responsabilites.length > 0) {
            addResponsibilityField();
        } else {
            // Gérer le cas où la liste des responsabilités est nulle
            console.error("La liste des responsabilités est nulle.");
        }
    }
</script>
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
            <li class="current">Ajouter une offre</li>
          </ol>
        </nav>
        <h1>Ajouter une offre</h1>
      </div>
    </div><!-- End Page Title -->

    <!-- Starter Section Section -->
    <section id="starter-section" class="starter-section section">

      <div class="container section-title pb-lg-2">
        <h2>Ajout d'offre</h2>
      </div> 

      <div class="container">
        <div class="col">

          <div class="col-lg-12">
            <div class="team-member d-flex justify-content-center align-items-center">
              <div class="member-info w-50">
                <form
							action="<%=request.getContextPath()%>/ServletOffreAjouter"
							method="post">

							<div class="form-group mb-3">
								<label for="titre">Titre:</label> <input type="text"
									class="form-control" id="titre" placeholder="Titre de l'offre"
									name="titre" required>
							</div>

							<div class="form-group mb-3">
								<label for="description">description:</label>
								<textarea class="form-control" id="description"
									placeholder="description de l' offre" rows="3"
									name="description" required></textarea>
							</div>
	
							<div class="form-group col-md-4 mb-3">
								<label for="inputType">Type</label> <select id="inputType"
									name="inputType" class="form-select">
									<option value="Full-Time">Full-Time</option>
									<option value="Part-Time">Part-Time</option>
									<option value="Stage">Stage</option>
									<option value="Freelance">Freelance</option>
									<option value="Temporary">Temporary</option>
								</select>
							</div>
							
							<div class="form-group col-lg-12 mb-3">
							
							<label for="responsibilitiesContainer" class="mb-3">Responsablités:</label>
        						<div class="mb-3" id="responsibilitiesContainer"></div>
        						<button class="btn" style="color: white; background-color: #589da3; padding: 0.6rem; border: none; border-radius: 100vmax; font-size: 1em; cursor: pointer; font-weight: 600;" type="button" onclick="addResponsibilityField()">Ajouter Responsablité</button><br><br>
        
       						<label for="qualificationsContainer" class="mb-3">Qualifications:</label>
        						<div class="mb-3" id="qualificationsContainer"></div>
        						<button class="btn" style="color: white; background-color: #589da3; padding: 0.6rem; border: none; border-radius: 100vmax; font-size: 1em; cursor: pointer; font-weight: 600;" type="button" onclick="addQualificationField()">Ajouter Qualification</button><br><br>
        
        					<label for="expectationsContainer" class="mb-3">Attentes:</label>
        						<div class="mb-3" id="expectationsContainer"></div>
        						<button class="btn" style="color: white; background-color:#589da3; padding: 0.6rem; border: none; border-radius: 100vmax; font-size: 1em; cursor: pointer; font-weight: 600;"  type="button" onclick="addExpectationField()">Ajouter Attente</button><br><br>
 							</div>
 							
							<button type="submit" class="btn" style="color:white; background-color: #9663ba; padding: 0.6rem; border: none; border-radius: 100vmax; font-size: 1em; cursor: pointer; font-weight:600;">Ajouter</button>

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
