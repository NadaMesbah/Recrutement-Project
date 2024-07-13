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
<link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;500;600;700;800&family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">

<!-- Vendor CSS Files -->
<link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
<link href="assets/vendor/aos/aos.css" rel="stylesheet">
<link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
<link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

<!-- Main CSS File -->
<link href="assets/css/main.css" rel="stylesheet">
</head>
<body class="starter-page-page">

<jsp:include page="/Shared/header.jsp"></jsp:include>

<main class="main">

    <!-- Page Title -->
    <div class="page-title" >
        <div class="container">
            <nav class="breadcrumbs">
                <ol>
                    <li><a href="<%=request.getContextPath()%>/index.jsp">Home</a></li>
                    <li class="current">Modifier l'offre</li>
                </ol>
            </nav>
            <h1>Modifier l'offre</h1>
        </div>
    </div>
    <!-- End Page Title -->

    <!-- Starter Section Section -->
    <section id="starter-section" class="starter-section section">

        <div class="container section-title pb-lg-2">
            <h2>Modification de l'offre</h2>
        </div>

        <div class="container">
            <div class="col">

                <div class="col-lg-12">
                    <div class="team-member d-flex justify-content-center align-items-center">
                        <div class="member-info w-50">
                            <form action="${pageContext.request.contextPath}/ServletOffreModifier" method="post">
                                <input type="hidden" name="id" value="${offre.id}">
                                <div class="form-group mb-3">
                                    <label for="titre">Titre:</label>
                                    <input type="text" class="form-control" id="titre" placeholder="Titre de l'offre" name="titre" value="${offre.titre}" required>
                                </div>
                                <div class="form-group mb-3">
                                    <label for="description">Description:</label>
                                    <input type="text" class="form-control" id="description" placeholder="Description de l'offre" name="description" value="${offre.description}" required>
                                </div>
                                <div class="form-group mb-3">
                                    <label for="inputType">Type</label>
                                    <select id="inputType" name="inputType" class="form-control">
                                        <option value="Full-Time" ${offre.type.libelle == 'Full-Time' ? 'selected' : ''}>Full-Time</option>
                                        <option value="Part-Time" ${offre.type.libelle == 'Part-Time' ? 'selected' : ''}>Part-Time</option>
                                        <option value="Stage" ${offre.type.libelle == 'Stage' ? 'selected' : ''}>Stage</option>
                                        <option value="Freelance" ${offre.type.libelle == 'Freelance' ? 'selected' : ''}>Freelance</option>
                                        <option value="Temporary" ${offre.type.libelle == 'Temporary' ? 'selected' : ''}>Temporary</option>
                                    </select>
                                </div>
                                <div class="form-group mb-3">
                                    <label for="responsabilites">Responsabilités</label>
                                    <c:forEach var="res" items="${offre.responsabilites}">
                                        <div class="input-group mb-3">
                                            <input type="text" class="form-control" name="responsabilites" value="${res.getDescription()}">
                                            <div class="input-group-append">
                                                <button class="ms-3 btn delete-responsabilite" style="color:white; background-color: #783b58; padding: 0.6rem; border: none; border-radius: 100vmax; font-size: 1em; cursor: pointer; font-weight:600;" type="button" data-id="${res.id}" data-offre="${offre.id}">Delete</button>
                                            </div>
                                        </div>
                                    </c:forEach>
										<div class="mb-3" id="responsibilitiesContainer"></div>
        								<button class="btn mb-3" style="color: white; background-color:#589da3; padding: 0.6rem; border: none; border-radius: 100vmax; font-size: 1em; cursor: pointer; font-weight: 600;" type="button" onclick="addResponsibilityField()">Ajouter Responsablité</button>                                </div>
                                <div class="form-group mb-3">
                                    <label for="qualifications">Qualifications</label>
                                    <c:forEach var="qual" items="${offre.qualifications}">
                                        <div class="input-group mb-3">
                                            <input type="text" class="form-control" name="qualifications" value="${qual.getDescription()}">
                                            <div class="input-group-append">
                                                <button class="ms-3 btn delete-qualification" style="color:white; background-color: #783b58; padding: 0.6rem; border: none; border-radius: 100vmax; font-size: 1em; cursor: pointer; font-weight:600;" type="button" data-id="${qual.id}" data-offre="${offre.id}">Delete</button>
                                            </div>
                                        </div>
                                    </c:forEach>
										<div class="mb-3" id="qualificationsContainer"></div>
        								<button class="btn" type="button" style="color: white; background-color: #589da3; padding: 0.6rem; border: none; border-radius: 100vmax; font-size: 1em; cursor: pointer; font-weight: 600;" onclick="addQualificationField()">Ajouter Qualification</button>                                </div>
                                <div class="form-group mb-3">
                                    <label for="attentes">Attentes</label>
                                    <c:forEach var="att" items="${offre.attentes}">
                                        <div class="input-group mb-3">
                                            <input type="text" class="form-control" name="attentes" value="${att.getDescription()}">
                                            <div class="input-group-append">
                                                <button class="ms-3 btn delete-attente" style="color:white; background-color: #783b58; padding: 0.6rem; border: none; border-radius: 100vmax; font-size: 1em; cursor: pointer; font-weight:600;" type="button" data-id="${att.id}" data-offre="${offre.id}">Delete</button>
                                            </div>
                                        </div>
                                    </c:forEach>
										<div class="mb-3" id="expectationsContainer"></div>
        								<button class="btn mb-3" style="color: white; background-color: #589da3; padding: 0.6rem; border: none; border-radius: 100vmax; font-size: 1em; cursor: pointer; font-weight: 600;" type="button" onclick="addExpectationField()">Ajouter Attente</button> 
        								                               </div>
                                <button type="submit" class="btn mt-3" style="color:white; background-color: #9663ba; padding: 0.6rem; border: none; border-radius: 100vmax; font-size: 1em; cursor: pointer; font-weight:600;">Enregistrer</button>
                            </form>

                            <c:if test="${not empty message}">
                                <div class="alert alert-danger mt-3">${message}</div>
                            </c:if>

                        </div>
                    </div>
                    <!-- End Team Member -->
                </div>
            </div>
        </div>
    </section>
    <!-- /Starter Section Section -->

</main>
<jsp:include page="/Shared/footer.jsp"></jsp:include>
<!-- Scroll Top -->
<a href="#" id="scroll-top" class="scroll-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

<!-- Vendor JS Files -->
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="assets/vendor/php-email-form/validate.js"></script>
<script src="assets/vendor/aos/aos.js"></script>
<script src="assets/vendor/glightbox/js/glightbox.min.js"></script>
<script src="assets/vendor/swiper/swiper-bundle.min.js"></script>
<script src="assets/vendor/waypoints/noframework.waypoints.js"></script>
<script src="assets/vendor/imagesloaded/imagesloaded.pkgd.min.js"></script>
<script src="assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<!-- Main JS File -->
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
const contextPath = '<%= request.getContextPath() %>';

document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('.delete-responsabilite').forEach(button => {
        button.addEventListener('click', function() {
            const id = this.dataset.id;
            const offreId = this.dataset.offre;
            const elementToRemove = this.closest('.input-group');
            Swal.fire({
                title: 'Are you sure?',
                text: "You won't be able to revert this!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, delete it!'
            }).then((result) => {
                if (result.isConfirmed) {
                    fetch(`${contextPath}/ServletResponsabiliteSupprimer?id=${id}&offre=${offreId}`, {
                        method: 'POST'
                    }).then(response => response.text())
                    .then(data => {
                        Swal.fire(
                            'Deleted!',
                            'Responsibility has been deleted.',
                            'success'
                        ).then(() => {
                            elementToRemove.remove();
                        });
                    }).catch(error => {
                        Swal.fire(
                            'Error!',
                            'An error occurred while deleting.',
                            'error'
                        );
                    });
                }
            });
        });
    });

    document.querySelectorAll('.delete-qualification').forEach(button => {
        button.addEventListener('click', function() {
            const id = this.dataset.id;
            const offreId = this.dataset.offre;
            const elementToRemove = this.closest('.input-group');
            Swal.fire({
                title: 'Are you sure?',
                text: "You won't be able to revert this!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, delete it!'
            }).then((result) => {
                if (result.isConfirmed) {
                    fetch(`${contextPath}/ServletQualificationSupprimer?id=${id}&offre=${offreId}`, {
                        method: 'POST'
                    }).then(response => response.text())
                    .then(data => {
                        Swal.fire(
                            'Deleted!',
                            'Qualification has been deleted.',
                            'success'
                        ).then(() => {
                            elementToRemove.remove();
                        });
                    }).catch(error => {
                        Swal.fire(
                            'Error!',
                            'An error occurred while deleting.',
                            'error'
                        );
                    });
                }
            });
        });
    });

    document.querySelectorAll('.delete-attente').forEach(button => {
        button.addEventListener('click', function() {
            const id = this.dataset.id;
            const offreId = this.dataset.offre;
            const elementToRemove = this.closest('.input-group');
            Swal.fire({
                title: 'Are you sure?',
                text: "You won't be able to revert this!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, delete it!'
            }).then((result) => {
                if (result.isConfirmed) {
                    fetch(`${contextPath}/ServletAttenteSupprimer?id=${id}&offre=${offreId}`, {
                        method: 'POST'
                    }).then(response => response.text())
                    .then(data => {
                        Swal.fire(
                            'Deleted!',
                            'Expectation has been deleted.',
                            'success'
                        ).then(() => {
                            elementToRemove.remove();
                        });
                    }).catch(error => {
                        Swal.fire(
                            'Error!',
                            'An error occurred while deleting.',
                            'error'
                        );
                    });
                }
            });
        });
    });
});

</script>
</body>
</html>
