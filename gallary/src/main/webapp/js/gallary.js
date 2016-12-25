$(document).ready(function() {
      $(document).on('click', '#signup', function() {
          var name = $('#name').val();
          var uName = $('#uname').val();
          var pass = $('#pass').val();
          var add = $('#address').val();
          var age = $('#age').val();
          var mobileNo = $('#moblileNumber').val();
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
            })
          })
      });
