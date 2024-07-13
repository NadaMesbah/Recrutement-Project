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
<style>
/* General modal styling */
.modal {
	display: none; /* Hidden by default */
	position: fixed;
	top: 0;
	left: 0;
	width: 20%;
	height: 50%;
	overflow: auto;
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
	z-index: 1000; /* Ensure it sits on top of other content */
	font-family: Arial, sans-serif;
	align-items: center;
	justify-content: center;
	display: flex;
}

/* Modal content */
.modal-content {
	background-color: #fff;
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
	border-radius: 8px;
	width: 300px; /* Smaller width */
	padding: 20px;
	position: relative;
}

/* Close button */
.close {
	position: absolute;
	top: 10px;
	right: 10px;
	color: #aaa;
	font-size: 24px;
	font-weight: bold;
	cursor: pointer;
}

.close:hover, .close:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}

/* Form styling */
#scheduleInterviewForm {
	display: flex;
	flex-direction: column;
}

#scheduleInterviewForm label {
	margin-top: 10px;
	margin-bottom: 5px;
	font-weight: bold;
}

#scheduleInterviewForm input, #scheduleInterviewForm button {
	margin-bottom: 10px;
	padding: 8px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

#scheduleInterviewForm button {
	background-color: #28a745;
	color: white;
	border: none;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

#scheduleInterviewForm button:hover {
	background-color: #218838;
}

/* Form styling */
#setOutcomeForm {
	display: flex;
	flex-direction: column;
}

#setOutcomeForm label {
	margin-top: 10px;
	margin-bottom: 5px;
	font-weight: bold;
}

#setOutcomeForm select, #setOutcomeForm button {
	margin-bottom: 10px;
	padding: 8px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

#setOutcomeForm button {
	background-color: #28a745;
	color: white;
	border: none;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

#setOutcomeForm button:hover {
	background-color: #218838;
}
/* Badge styling */
.badge {
	display: inline-block;
	padding: 0.6em 0.9em;
	font-size: 3rem;
	font-weight: 600;
	border-radius: 0.35rem;
	color: white;
	text-align: center;
	vertical-align: middle;
}

.badge-hired {
	background-color: #28a745; /* Green */
}

.badge-rejected {
	background-color: #dc3545; /* Red */
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
						<li class="current">Postulations des candidats</li>
					</ol>
				</nav>
				<h1>Postulations des candidats</h1>
			</div>
		</div>
		<!-- End Page Title -->

		<!-- Starter Section Section -->
		<section id="starter-section" class="starter-section section">
			<div class="container d-flex flex-column align-items-center">
				<c:choose>
					<c:when test="${empty postulations}">
						<div class="alert alert-info w-75 text-center" role="alert">
							<i class="bi bi-info-circle-fill"></i> Il n'y a aucune
							postulation pour cette offre.
						</div>
					</c:when>
					<c:otherwise>
						<c:forEach items='${postulations}' var="dm">
							<div class="card bg-white text-dark mb-4 w-75 shadow-sm"
								data-aos="fade-up" data-aos-delay="100">
								<div
									class="card-header d-flex justify-content-between align-items-center">
									<span>Date : ${dm.getDate()}</span> <span>Status: <c:choose>
											<c:when test="${dm.getStatus() == 'pending'}">
												<span class="badge bg-warning"
													style="color: #996515; height: 1.8rem; border: none; border-radius: 100vmax; font-size: 1em; font-weight: 600;">${dm.getStatus()}</span>
											</c:when>
											<c:when test="${dm.getStatus() == 'accepted'}">
												<span class="badge bg-success"
													style="height: 1.8rem; border: none; border-radius: 100vmax; font-size: 1em; font-weight: 600;">${dm.getStatus()}</span>
											</c:when>
											<c:when test="${dm.getStatus() == 'canceled'}">
												<span class="badge bg-danger"
													style="height: 1.8rem; border: none; border-radius: 100vmax; font-size: 1em; font-weight: 600;">${dm.getStatus()}</span>
											</c:when>
										</c:choose>
									</span>
								</div>
								<div class="card-body text-center">
									<h5 class="card-title">${dm.getDemandeur().getNom()}
										${dm.getDemandeur().getPrenom()}</h5>
									<p class="card-text">Submitted Files:</p>
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
									<div class="text-center mt-3">
										<c:if test="${not empty dm.getInterviewDate()}">
											<p>
												<strong>Date de l'interview :</strong>
												${dm.getInterviewDate()}
											</p>
										</c:if>
										<c:if test="${not empty dm.getInterviewTime()}">
											<p>
												<strong>Heure de l'interview :</strong>
												${dm.getInterviewTime()}
											</p>
										</c:if>
										<c:if test="${not empty dm.getInterviewLocation()}">
											<p>
												<strong>Lieu de l'interview :</strong>
												${dm.getInterviewLocation()}
											</p>
										</c:if>
										<c:if test="${not empty dm.getInterviewOutcome()}">
											<p>
												<strong>Status du candidat:</strong>
												${dm.getInterviewOutcome()}
											</p>
										</c:if>
										<c:if test="${not empty dm.getCandidateStatus()}">
											<p>
												<c:choose>
													<c:when test="${dm.getCandidateStatus() == 'hired'}">
														<span class="badge"
															style="background-color: #69a832; color: white; height: 1.8rem; border: none; border-radius: 100vmax; font-size: 1em; font-weight: 600;">
															Hired </span>
													</c:when>
													<c:when test="${dm.getCandidateStatus() == 'rejected'}">
														<span class="badge"
															style="background-color: #610a14; color: white; height: 1.8rem; border: none; border-radius: 100vmax; font-size: 1em; font-weight: 600;">
															Rejected </span>
													</c:when>
												</c:choose>
											</p>
										</c:if>
									</div>
								</div>
								<c:if test="${dm.getStatus() == 'pending'}">
									<div class="card-footer text-center">
										<button class="btn mx-2"
											style="color: white; background-color: #496e4c; padding: 0.6rem; border: none; border-radius: 100vmax; font-size: 1em; cursor: pointer; font-weight: 600;"
											onclick="confirmAction('${pageContext.request.contextPath}/AdminPostulation?action=accept&id=${dm.getId()}', 'Accepter')">Accepter</button>
										<button class="btn mx-2"
											style="color: white; background-color: #783b58; padding: 0.6rem; border: none; border-radius: 100vmax; font-size: 1em; cursor: pointer; font-weight: 600;"
											onclick="confirmAction('${pageContext.request.contextPath}/AdminPostulation?action=cancel&id=${dm.getId()}', 'Annuler')">Annuler</button>
									</div>
								</c:if>
								<c:if
									test="${dm.getStatus() == 'accepted' && empty dm.getInterviewDate() }">
									<div class="card-footer text-center">
										<button
											style="color: white; background-color: #9663ba; padding: 0.6rem; border: none; border-radius: 100vmax; font-size: 1em; cursor: pointer; font-weight: 600;"
											class="btn scheduleInterviewBtn mx-2"
											data-postulation-id="${dm.getId()}">Plannifier
											l'interview</button>
									</div>
									<div id="scheduleInterviewModal" class="modal">
										<div class="modal-content w-50">
											<span class="close">&times;</span>
											<h2>Plannifier l'interview</h2>
											<form id="scheduleInterviewForm"
												action="${pageContext.request.contextPath}/AdminPostulation?action=scheduleInterview&id=${dm.getId()}"
												method="POST">
												<input type="hidden" id="postulationId" name="postulationId">
												<label for="interviewDate">Date de l'interview :</label> <input
													type="date" id="interviewDate" name="interviewDate"
													required><br> <label for="interviewTime">Heure
													de l'interview :</label> <input type="time" id="interviewTime"
													name="interviewTime" required><br> <label
													for="interviewLocation">Lieu de l'interview :</label> <input
													type="text" id="interviewLocation" name="interviewLocation"
													required><br>
												<button
													style="color: white; background-color: #9663ba; padding: 0.6rem; border: none; border-radius: 100vmax; font-size: 1em; cursor: pointer; font-weight: 600;"
													class="align-self-center w-25" type="submit">Plannifier</button>
											</form>
										</div>
									</div>
								</c:if>
								<c:if
									test="${dm.getStatus() == 'accepted' && not empty dm.getInterviewDate() && empty dm.getInterviewOutcome()}">
									<div class="card-footer text-center">
										<button
											style="color: white; background-color: #9663ba; padding: 0.6rem; border: none; border-radius: 100vmax; font-size: 1em; cursor: pointer; font-weight: 600;"
											class="btn setOutcomeBtn mx-2"
											data-postulation-id="${dm.getId()}">Resultat de
											l'interview</button>
									</div>
									<div id="setOutcomeModal" class="modal">
										<div class="modal-content w-50">
											<span class="close">&times;</span>
											<h2>Definir le resultat de l'interview</h2>
											<form id="setOutcomeForm"
												action="${pageContext.request.contextPath}/AdminPostulation?action=setInterviewOutcome&id=${dm.getId()}"
												method="POST">
												<input type="hidden" id="outcomePostulationId"
													name="postulationId"> <label for="interviewOutcome">Resultat:</label>
												<select id="interviewOutcome" name="interviewOutcome"
													required>
													<option value="" disabled selected>Selectionner le
														resultat</option>
													<option value="hired">Hired</option>
													<option value="rejected">Rejected</option>
												</select>
												<button
													style="color: white; background-color: #28a745; padding: 0.6rem; border: none; border-radius: 100vmax; font-size: 1em; cursor: pointer; font-weight: 600;"
													class="align-self-center w-25" type="submit">Enregistrer</button>
											</form>
										</div>
									</div>
								</c:if>
							</div>
						</c:forEach>
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
	<!-- Custom JS for SweetAlert -->
	<script>
    document.addEventListener('DOMContentLoaded', function() {
        const scheduleForm = document.getElementById('scheduleInterviewForm');
        const scheduleModal = document.getElementById('scheduleInterviewModal');
        const closeScheduleModal = document.querySelector('.close');

        if (scheduleForm && scheduleModal && closeScheduleModal) {
            document.querySelectorAll('.scheduleInterviewBtn').forEach(btn => {
                btn.addEventListener('click', function() {
                    const postulationId = this.dataset.postulationId;
                    document.getElementById('postulationId').value = postulationId;
                    scheduleModal.style.display = 'flex';
                });
            });

            closeScheduleModal.onclick = function() {
                scheduleModal.style.display = 'none';
            };
        }

        const outcomeForm = document.getElementById('setOutcomeForm');
        const outcomeModal = document.getElementById('setOutcomeModal');
        const closeOutcomeModal = document.querySelector('#setOutcomeModal .close');

        if (outcomeForm && outcomeModal && closeOutcomeModal) {
            document.querySelectorAll('.setOutcomeBtn').forEach(btn => {
                btn.addEventListener('click', function() {
                    const postulationId = this.dataset.postulationId;
                    document.getElementById('outcomePostulationId').value = postulationId;
                    outcomeModal.style.display = 'flex';
                });
            });

            closeOutcomeModal.onclick = function() {
                outcomeModal.style.display = 'none';
            };
        }

        window.onclick = function(event) {
            if (event.target.classList.contains('modal')) {
                event.target.style.display = 'none';
            }
        };
    });

    function closeModal(modalId) {
        const modal = document.getElementById(modalId);
        if (modal) {
            modal.style.display = 'none';
        }
    }
</script>




	<script>
    function confirmAction(url, action) {
      Swal.fire({
        title: 'Vous etes sure?',
        text: `You are about to ${action.toLowerCase()} this postulation.`,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: `Yes, ${action.toLowerCase()} it!`
      }).then((result) => {
        if (result.isConfirmed) {
          window.location.href = url;
        }
      })
    }
  </script>

</body>

</html>
