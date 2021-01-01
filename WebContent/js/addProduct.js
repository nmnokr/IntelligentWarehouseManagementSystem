var Rfid = {};
var a = null;

$(document).ready(
		
		function() {
			var b1 = $("#btn1");

			$.getJSON("/WarehouseManagementSystem/rest/rfid/getCompany",
					function(result) {
						$.each(result, function(i, Company) {
							var a = Company.name;

							$("#Firmaadi").append(
									'<option value="Firmaadi">' + a
											+ '</option>');

						});
					});

			$.getJSON("/WarehouseManagementSystem/rest/rfid/getCategory",
					function(result) {
						$.each(result, function(i, getCategory) {
							var a = getCategory.name;
							console.log("......." + a);
							$("#Katagori").append(
									'<option value="Katagori" selected="selected2">' + a+ '</option>');

						});
					});

			$.getJSON("/WarehouseManagementSystem/rest/rfid/getStok", function(
					result) {
				$.each(result, function(i, getstok) {
					var a = getstok.name + "  " + getstok.address;
					console.log(getstok.address);
					$("#DepoSec").append(
							'<option value="DepoSec" selected="selected">' + a	+ '</option>');

				});
			});

			  var span = document.getElementsByClassName("close")[0];
			  span.onclick = function() {
				  modal.style.display = "none";
				}
		 
			  // When the user clicks anywhere outside of the modal, close it
			  window.onclick = function(event) {
			    if (event.target == modal) {
			      modal.style.display = "none";
			    }
			  }
		});

function okut() {
	$.ajax({
		type : 'GET',
		url : "/WarehouseManagementSystem/rest/rfid/getReadRfid",
		dataType : 'json',
		success : function(data) {
			console.log((data));

			document.getElementById("rfidData").value = data;

		}
	});
}

function asd2() {
	var Katagori = $("#Katagori option:selected").text();
	var Firmaadi = $("#Firmaadi option:selected").text();
	var Name = $("#nameTxt").val();
	var Rfid = $("#rfidData").val();
	
	
	
	
 
}
