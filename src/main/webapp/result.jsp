<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Subscription Result</title>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
    <div id="message" style="display:none;"><%= request.getAttribute("message") %></div>
    <div id="alertType" style="display:none;"><%= request.getAttribute("alertType") %></div>
    
    <script>
        // Get message and alert type from the server-side
        var message = document.getElementById('message').innerText;
        var alertType = document.getElementById('alertType').innerText;

        // Display the SweetAlert popup
        Swal.fire({
            icon: alertType,
            title: message
        }).then(function() {
            // Optionally, you can redirect the user to another page after closing the alert
          window.location.href = '/MyRecrutementApp/index.jsp'; // Adjust this to your actual home page path
        });
    </script>
</body>
</html>
