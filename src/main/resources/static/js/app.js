function toggleResetPswd(e){
    e.preventDefault();
    $('#logreg-forms .form-signin').toggle() // display:block or none
    $('#logreg-forms .form-reset').toggle() // display:block or none
}

function toggleSignUp(e){
    e.preventDefault();
    $('#logreg-forms .form-signin').toggle(); // display:block or none
    $('#logreg-forms .form-signup').toggle(); // display:block or none
}

$(document).ready(function () {

    // Login Register Form
    $('#logreg-forms #forgot_pswd').click(toggleResetPswd);
    $('#logreg-forms #cancel_reset').click(toggleResetPswd);
    $('#logreg-forms #btn-signup').click(toggleSignUp);
    $('#logreg-forms #cancel_signup').click(toggleSignUp);
    $('#sidebarCollapse').on('click', function () {
        $('#sidebar').toggleClass('active');
    });
    $('#confirmModal').on('show.bs.modal', function (event) {
  	  var button = $(event.relatedTarget) // Button that triggered the modal
  	  var actionurl = button.data('actionurl') // Extract info from data-* attributes
  	  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
  	  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
  	  var confirmTitle = button.data('confirmtitle');
  	  var confirmBody = button.data('confirmbody');
  	  var modal = $(this)
  	  modal.find('#yes').attr("href", actionurl);
  	  console.log(confirmTitle);
  	  console.log(confirmBody);
  	  modal.find('#confirm-title').replaceWith(confirmTitle);
  	  modal.find('#confirm-body').replaceWith(confirmBody);
  	})
});

