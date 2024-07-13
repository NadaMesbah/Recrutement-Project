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
<body>
 <header id="header" class="header d-flex align-items-center sticky-top">
    <div class="container-fluid container-xl position-relative d-flex align-items-center">

      <a href="<%=request.getContextPath()%>/index.jsp" class="logo d-flex align-items-center me-auto">
        <!-- Uncomment the line below if you also wish to use an image logo -->
        <!-- <img src="assets/img/logo.png" alt=""> -->
        <h1 class="sitename">RecrutMaroc</h1>
      </a>
      <nav id="navmenu" class="navmenu">
        <ul>
          <li><a href="<%=request.getContextPath()%>/index.jsp#hero" class="">Home</a></li>
          <c:if test="${user.getRole().equals('candidat')}">
          	<li><a href="<%=request.getContextPath()%>/index.jsp#about">A propos de nous</a></li>
          </c:if>
          <c:if test="${not sessionScope.Authenticated}">
          		<li><a href="<%=request.getContextPath()%>/index.jsp#about">A propos de nous</a></li>
          		<li><a href="<%=request.getContextPath()%>/ServletOffreAfficher">Nos offres</a></li>
          </c:if>
          <c:if test="${sessionScope.Authenticated}">
    
          
          <c:if test="${user.getRole().equals('candidat')}">
          <li class="dropdown"><a href="<%=request.getContextPath()%>/ServletOffreAfficher"><span>Nos Offres</span> <i class="bi bi-chevron-down toggle-dropdown"></i></a>
            <ul>          	
              <li><a href="<%=request.getContextPath()%>/MesPostulations">Mes Postulations</a></li>	
            </ul>
          </li>
          </c:if>
          
          <c:if test="${user.getRole().equals('admin')}">
          <li class="dropdown"><a href="<%=request.getContextPath()%>/ServletOffreAfficher"><span>Offres</span> <i class="bi bi-chevron-down toggle-dropdown"></i></a>
            <ul>         	
				<li><a href="<%=request.getContextPath()%>/ServletOffreAjouter">Ajouter une offre</a></li>              	
            </ul>
          </li>
          </c:if>
          
          <li><a href="<%=request.getContextPath()%>/logout">Deconnexion</a></li>
          
         </c:if>
        </ul>
        <i class="mobile-nav-toggle d-xl-none bi bi-list"></i>
      </nav>

 	  <c:if test="${not sessionScope.Authenticated}">
      	<a class="btn-getstarted" href="<%=request.getContextPath()%>/register">S'inscrire</a>
      </c:if>

    </div>
  </header>

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