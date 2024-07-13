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

<body class="index-page">

  <header id="header" class="header d-flex align-items-center fixed-top">
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


  <main class="main">

    <!-- Hero Section -->
    <section id="hero" class="hero section">

      <div class="container">
        <div class="row gy-4">
          <div class="col-lg-6 order-2 order-lg-1 d-flex flex-column justify-content-center" data-aos="zoom-out">
            <h1 class="">Nous Recrutons, Vas-y postulez pour les postes qui vous conviennent !</h1>
            <p class="">Tentez votre chance pour obtenir un job de qualité et qvec un très bon salaire.</p>
            <div class="d-flex">
              <a href="<%=request.getContextPath()%>/register" class="btn-get-started">Commencer !</a>
             <!--  <a href="https://www.youtube.com/watch?v=LXb3EKWsInQ" class="glightbox btn-watch-video d-flex align-items-center"><i class="bi bi-play-circle"></i><span>Watch Video</span></a>  -->
            </div>
          </div>
          <div class="col-lg-6 order-1 order-lg-2 hero-img" data-aos="zoom-out" data-aos-delay="200">
            <img src="assets/img/rec.png" class="img-fluid animated" alt="">
          </div>
        </div>
      </div>

    </section><!-- /Hero Section -->

  
    <!-- About Section -->
    <section id="about" class="about section">

      <!-- Section Title -->
      <div class="container section-title" data-aos="fade-up">
        <h2 class="">A propos de nous</h2>
      </div><!-- End Section Title -->

      <div class="container">

        <div class="row gy-4">

          <div class="col-lg-6 content" data-aos="fade-up" data-aos-delay="100">
            <p>
              RecrutMaroc est une application de gestion de recrutement innovante dédiée aux entreprises marocaines et aux chercheurs d'emploi. 
              Notre mission est de simplifier et d'optimiser le processus de recrutement, en créant un pont efficace entre les recruteurs et les candidats qualifiés.
            </p>
            <h4 class="">Nos valeurs</h4>
            <ul>
              <li><i class="bi bi-check2-circle"></i> <span><strong>Satisfaction :</strong> Nous nous engageons à fournir un service de haute qualité qui satisfait toutes les parties prenantes.</span></li>
              <li><i class="bi bi-check2-circle"></i> <span><strong>Efficacité : </strong>Nous visons à rendre le processus de recrutement rapide.</span></li>
              <li><i class="bi bi-check2-circle"></i> <span><strong>Innovation : </strong>Nous utilisons les dernières technologies pour offrir des fonctionnalités avancées et intuitives.</span></li>
            </ul>
          </div>

          <div class="col-lg-6" data-aos="fade-up" data-aos-delay="200">
            <h4 class=""> Comment s'abonner ?</h4>
            <ul>
              <li><i class="bi bi-check2-circle"></i> <span>Rendez-vous sur notre page d'accueil et entrez votre adresse e-mail dans la section dédiée à la newsletter.</span></li>
              <li><i class="bi bi-check2-circle"></i> <span>Une fois abonné, vous recevrez automatiquement des alertes pour chaque nouvelle offre d'emploi ajoutée !</span></li>
            </ul>
            <p>Nous sommes ravis de vous accompagner dans votre parcours de recrutement ou de recherche d'emploi. Merci de faire confiance à RecrutMaroc ! </p>
            <a href="#footer" class="read-more"><span>S'abonner</span><i class="bi bi-arrow-down"></i></a>
          </div>

        </div>

      </div>

    </section><!-- /About Section -->

    <!-- Why Us Section -->
    <section id="why-us" class="section why-us" data-builder="section">

      <div class="container-fluid">

        <div class="row gy-4">

          <div class="col-lg-7 d-flex flex-column justify-content-center order-2 order-lg-1">

            <div class="content px-xl-5" data-aos="fade-up" data-aos-delay="100">
              <h3 class=""><span class=""><strong>Procédure de Recrutement sur RecrutMaroc</strong></span></h3>
            </div>

            <div class="faq-container px-xl-5" data-aos="fade-up" data-aos-delay="200">

              <div class="faq-item faq-active">

                <h3><span>01</span>Consultation des Candidatures</h3>
                <div class="faq-content">
                	<p> Dès qu'une offre d'emploi est publiée, les candidats peuvent postuler en soumettant leur CV et leur lettre de motivation (LM). 
                	Ensuite, les recruteurs peuvent consulter les CV et les LM des candidats, et chaque candidature est affichée avec des informations clés pour faciliter la lecture. </p>
                </div>
                <i class="faq-toggle bi bi-chevron-right"></i>
              </div><!-- End Faq item-->

              <div class="faq-item">
                <h3><span>02</span>Discussion et Évaluation des Profils</h3>
                <div class="faq-content">
                	<p>Les recruteurs évaluent chaque candidature en fonction des qualifications, de l'expérience et des compétences mentionnées dans les CV et LM.
                	Ils discutent des profils des candidats en interne pour identifier ceux qui correspondent le mieux aux exigences du poste.</p>
                </div>
                <i class="faq-toggle bi bi-chevron-right"></i>
              </div><!-- End Faq item-->

              <div class="faq-item">
                <h3><span>03</span>Sélection Basée sur les Critères du Poste</h3>
                <div class="faq-content">
                 	<p>En se basant sur les critères établis, les recruteurs sélectionnent les candidats les plus qualifiés pour la prochaine étape du processus de recrutement.
                 	Enfin, les recruteurs envoient des e-mails aux candidats présélectionnés pour les informer de leur sélection et pour fixer une date et une heure pour l'entretien.</p>
                </div>
                <i class="faq-toggle bi bi-chevron-right"></i>
              </div><!-- End Faq item-->

            </div>

          </div>

          <div class="col-lg-5 order-1 order-lg-2 why-us-img">
            <img src="assets/img/rec2.png" class="img-fluid" alt="" data-aos="zoom-in" data-aos-delay="100">
          </div>
        </div>

      </div>

    </section><!-- /Why Us Section -->

    <!-- Skills Section -->
    <section id="skills" class="skills section">

      <div class="container" data-aos="fade-up" data-aos-delay="100">

        <div class="row">

          <div class="col-lg-6 d-flex align-items-center">
            <img src="assets/img/skills.png" class="img-fluid" alt="">
          </div>

          <div class="col-lg-6 pt-4 pt-lg-0 content">

            <h3>Les compétences les plus demandées sur le marché</h3>
            <p class="fst-italic">
              Sur le marché du travail en 2024, certaines compétences sont particulièrement recherchées par les employeurs :
            </p>

            <div class="skills-content skills-animation">

              <div class="progress">
                <span class="skill"><span>Compétences Techniques en Informatique</span> <i class="val">50%</i></span>
                <div class="progress-bar-wrap">
                  <div class="progress-bar" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                </div>
              </div><!-- End Skills Item -->

              <div class="progress">
                <span class="skill"><span>Compétences en Gestion de Projets </span> <i class="val">20%</i></span>
                <div class="progress-bar-wrap">
                  <div class="progress-bar" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"></div>
                </div>
              </div><!-- End Skills Item -->

              <div class="progress">
                <span class="skill"><span>Compétences en Marketing Digital </span> <i class="val">15%</i></span>
                <div class="progress-bar-wrap">
                  <div class="progress-bar" role="progressbar" aria-valuenow="15" aria-valuemin="0" aria-valuemax="100"></div>
                </div>
              </div><!-- End Skills Item -->

              <div class="progress">
                <span class="skill"><span>Compétences en Communication et Soft Skills</span> <i class="val">15%</i></span>
                <div class="progress-bar-wrap">
                  <div class="progress-bar" role="progressbar" aria-valuenow="15" aria-valuemin="0" aria-valuemax="100"></div>
                </div>
              </div><!-- End Skills Item -->

            </div>

          </div>
        </div>

      </div>

    </section><!-- /Skills Section -->
    
    <!-- Call To Action Section -->
    <section id="call-to-action" class="call-to-action section">

      <img src="assets/img/cta-bg.jpg" alt="">

      <div class="container">

        <div class="row" data-aos="zoom-in" data-aos-delay="100">
          <div class="col-xl-9 text-center text-xl-start">
            <h3>Appel à l'action</h3>
            <p>Découvrez nos opportunités de carrière, explorez les postes disponibles et postulez facilement et rapidement</p>
          </div>
          <div class="col-xl-3 cta-btn-container text-center">
            <a class="cta-btn align-middle" href="<%=request.getContextPath()%>/ServletOffreAfficher">Explorer les offres</a>
          </div>
        </div>

      </div>

    </section><!-- /Call To Action Section -->

    <!-- Faq 2 Section -->
    <section id="faq-2" class="faq-2 section">

      <!-- Section Title -->
      <div class="container section-title" data-aos="fade-up">
        <h2>Frequently Asked Questions</h2>
      </div><!-- End Section Title -->

      <div class="container">

        <div class="row justify-content-center">

          <div class="col-lg-10">

            <div class="faq-container">

              <div class="faq-item faq-active" data-aos="fade-up" data-aos-delay="200">
                <i class="faq-icon bi bi-question-circle"></i>
                <h3>Qu'est-ce que RecrutMaroc ?</h3>
                <div class="faq-content">
                	<p>RecrutMaroc est une application de gestion de recrutement conçue pour faciliter et optimiser le processus de recrutement pour les entreprises marocaines et les chercheurs d'emploi. Elle offre une plateforme intuitive pour publier des offres d'emploi, gérer des candidatures et suivre l'évolution du recrutement.</p>
                </div>
                <i class="faq-toggle bi bi-chevron-right"></i>
              </div><!-- End Faq item-->

              <div class="faq-item" data-aos="fade-up" data-aos-delay="300">
                <i class="faq-icon bi bi-question-circle"></i>
                <h3>Comment puis-je m'inscrire sur RecrutMaroc ?</h3>
                <div class="faq-content">
                	<p>Pour vous inscrire, cliquez sur le bouton "S'inscrire" sur la page d'accueil et remplissez le formulaire d'inscription. Ensuite vous pouvez faire le login et activer votre compte.</p>
                </div>
                <i class="faq-toggle bi bi-chevron-right"></i>
              </div><!-- End Faq item-->

              <div class="faq-item" data-aos="fade-up" data-aos-delay="400">
                <i class="faq-icon bi bi-question-circle"></i>
                <h3>Quels types d'utilisateurs peuvent utiliser RecrutMaroc ?</h3>
                <div class="faq-content">
                	<p>RecrutMaroc est conçu pour deux principaux types d'utilisateurs : les recruteurs et les candidats à la recherche d'emploi. Les recruteurs peuvent publier des offres et gérer les candidatures, tandis que les candidats peuvent postuler aux offres et suivre leur candidature.</p>
                </div>
                <i class="faq-toggle bi bi-chevron-right"></i>
              </div><!-- End Faq item-->
            </div>

          </div>

        </div>

      </div>

    </section><!-- /Faq 2 Section -->

  

  </main>
  <!--
 <div class="footer-newsletter">
      <div class="container">
        <div class="row justify-content-center text-center">
          <div class="col-lg-6">
            <h4>Join Our Newsletter</h4>
            <p>Subscribe to our newsletter and receive the latest news about our products and services!</p>
            <form action="forms/newsletter.php" method="post" class="php-email-form">
              <div class="newsletter-form"><input type="email" name="email"><input type="submit" value="Subscribe"></div>
              <div class="loading">Loading</div>
              <div class="error-message"></div>
              <div class="sent-message">Your subscription request has been sent. Thank you!</div>
            </form>
          </div>
        </div>
      </div>
    </div>
     -->
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