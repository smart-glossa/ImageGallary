$(document).ready(function() {

});
$(document).on('click', '#signup', function() {
      var name = $('#name').val();
      var uName = $('#uname').val();
      var pass = $('#pass').val();
      var add = $('#address').val();
      var age = $('#age').val();
      var mobileNo = $('#moblileNumber').val();
      if (name === "") {
        $("#name").focus().css("outline-color", "#ff0000");
        return;
      }
      if (uName === "") {
        $("#uName").focus().css("outline-color", "#ff0000");
        return;
      }
      if (pass === "") {
        $("#pass").focus().css("outline-color", "#ff0000");
        return;
      }
      if (add === "") {
        $("#address").focus().css("outline-color", "#ff0000");
        return;
      }
      if (age === "") {
        $("#age").focus().css("outline-color", "#ff0000");
        return;
      }
      if (mobileNo === "") {
        $("#mobileNumber").focus().css("outline-color", "#ff0000");
        return;
      }
      var url = "/gallary/gallery?operation=addUser&name=" + name + "&uName=" + uName + "&pass=" + pass + "&add=" + add + "&age=" + age + "&moblileNumber=" + mobileNo;
      var request = new FormData();
      request.append('file', $('#profile')[0].files[0]);
      $.ajax({
            url: url,
            type: 'POST',
            data: request,
            processData: false,
            contentType: false
          }.done(function(result) {
            var res = JSON.parse(result);
            if (res.status == "1") {
              alert("Added Successfully").fadeout(5000);
            }
          }).fail(function(result) {
            var res = JSON.parse(result);
            if (res.status == "0") {
              alert("Error occurs").fadeout(5000);
            }

          }); $(document).on('click', '#login', function() {
            var user = $('#user').val();
            var passw = $('#passw').val();
            if (user == "") {
              $("#user").focus().css("outline-color", "#ff0000");
              return;
            }
            if (passw == "") {
              $("#passw").focus().css("outline-color", "ff0000");
              return;
            }
            var url = "/bill/bill?operation=login&user=" +
              user + "&passw=" + passw;
            $.ajax({
              url: url,
              type: 'POST'
            }).done(function(result) {
              var res = JSON.parse(result);
              if (res.status == "1") {
                document.cookie = "uname=" + user;
              }

            }).fail(function(result) {

            })
          });

          function getCookie(cname) {
            var name = cname + "=";
            var ca = document.cookie.split(';');
            for (var i = 0; i < ca.length; i++) {
              var c = ca[i];
              while (c.charAt(0) == ' ') {
                c = c.substring(1);
              }
              if (c.indexOf(name) == 0) {
                return c.substring(name.length, c.length);
              }
            }
            return undefined;
          }
