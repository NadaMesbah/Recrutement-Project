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
            <li class="current">Inscription</li>
          </ol>
        </nav>
        <h1>Inscription</h1>
      </div>
    </div><!-- End Page Title -->

    <!-- Starter Section Section -->
    <section id="starter-section" class="starter-section section">

      <div class="container section-title pb-lg-2">
        <h2>Inscrivez-vous</h2>
      </div> 

      <div class="container">
        <div class="col">

          <div class="col-lg-12">
            <div class="team-member d-flex justify-content-center align-items-center">
              <div class="member-info w-50">
                 <form id="contactForm" method="post" action="<%=request.getContextPath()%>/register">
                 <!-- Name input-->
           <div class="form-floating mb-3">
               <input class="form-control" id="nom" name="nom" type="text" placeholder="Entrez votre nom..." required data-sb-validations="required" />
               <label for="nom">Nom</label>
               <div class="invalid-feedback" data-sb-feedback="nom:required">Last name is required.</div>
           </div>
           
            <div class="form-floating mb-3">
               <input class="form-control" id="prenom" name="prenom" type="text" placeholder="Entrez votre prenom..." required data-sb-validations="required" />
               <label for="prenom">Prenom</label>
               <div class="invalid-feedback" data-sb-feedback="prenom:required">First name is required.</div>
           </div>
           
           <!-- Email address input-->
           <div class="form-floating mb-3">
               <input class="form-control" id="email" name="email" type="email" placeholder="name@example.com" required data-sb-validations="required,email" />
               <label for="email">Email</label>
               <div class="invalid-feedback" data-sb-feedback="email:required">An email is required.</div>
               <div class="invalid-feedback" data-sb-feedback="email:email">Email is not valid.</div>
           </div>
           
            <div class="form-floating mb-3">
               <input class="form-control" id="username" name="username" type="text" placeholder="Entrez un username..." required data-sb-validations="required" />
               <label for="username">Username</label>
               <div class="invalid-feedback" data-sb-feedback="username:required">Username is required.</div>
           </div>
           <!-- Phone number input-->
           <div class="form-floating mb-3">
               <input class="form-control" id="pwd1" name="pwd1" type="password" placeholder="********" required data-sb-validations="required" />
               <label for="pwd1">Mot de passe</label>
               <div class="invalid-feedback" data-sb-feedback="pwd1:required">A password is required.</div>
           </div>
           <!-- Message input-->
           <div class="form-floating mb-3">
               <input class="form-control" id="pwd2" name="pwd2" type="password" placeholder="Confirmez le mot de passe..." required data-sb-validations="required"></input>
               <label for="pwd2">Confirmez le mot de passe</label>
               <div class="invalid-feedback" data-sb-feedback="pwd2:required">Password confirmation is required.</div>
           </div>
           
           <div class="d-none" id="submitErrorMessage"><div class="text-center text-danger mb-3">Error sending message!</div></div>
           <!-- Submit Button-->
           <div class="d-flex justify-content-center align-items-center"><button class="btn btn-primary mb-3" id="submitButton" type="submit">Sign up</button></div>
       </form>
		<% Object msgObj = request.getAttribute("validation"); 
              if(msgObj != null){
           	   String msg = (String) msgObj;
              
              %>
               <div>message: <%= msg %></div>
              <%
              }
              %>   
	  </div>
	</div>
	<div class="d-flex justify-content-center align-items-start">
       
           <p class="fw-bold">Already have an account ?</p>
           <a href="<%=request.getContextPath()%>/login" class="nav-item ms-2 fw-bold">Login</a>
     
    </div>
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