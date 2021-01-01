	var Rfid = {};
 var a=null;
	
$(document).ready(function() {
	   
    
	
	     
	     
	  $.getJSON("/WarehouseManagementSystem/rest/rfid/getCompany",function(result){
	        $.each(result, function(i, Company){
	   var a=Company.name;
	   
	         	$("#Firmaadi").append('<option value="Firmaadi">'+a+'</option>');

	        });
	    });
 
		  
		  $.getJSON("/WarehouseManagementSystem/rest/rfid/getCategory",function(result){
		      $.each(result, function(i, getCategory){
		 var a=getCategory.Name;
		 console.log(a);
		       	$("#Katagori").append('<option value="Katagori">'+a+'</option>');

		      });
		  });
		  
		  
		  $.getJSON("/WarehouseManagementSystem/rest/rfid/getStok",function(result){
		      $.each(result, function(i, getstok){
		 var a=getstok.name+"  "+getstok.address;
		 console.log(getstok.address);
		       	$("#DepoSec").append('<option value="DepoSec" selected="selected">'+a+'</option>');

		      });
		  });
		  
		  
		  
 
		     
		  
	 
}); 
function getComboA(selectObject) {
    var value = selectObject.value;  
}
function numan(a) {
	var durum='Henüz İncelenmedi';
 var TDEKLE='</td><td>'
	$.ajax({
		type : "POST",
		url : '/WarehouseManagementSystem/rest/rfid/getStokProducts',
		contentType : "application/json",
		mimeType : "application/json",
		data : JSON.stringify(a),
		success : function(result) {
	$.each(result, function(i, getStokProducts){
	         
	        	$("#secılenDepoUrunleri").append('<tr><td>'+getStokProducts.Id+TDEKLE+getStokProducts.name+TDEKLE+getStokProducts.createDate+TDEKLE+'<button   type="button"  onclick="Update('+getStokProducts.id+')"   class="btn btn-info btn-sm" data-toggle="modal" data-target="#İzinBilgisi">CIKIS VER</button>'+'</td></tr>');
	        });
			
		},
		error : function() {
			alert("error : secılenDepoUrunleri");

		}
	});



}


function Update(rfIdNo) {
	console.log(rfIdNo);
	$.ajax({
		type : "POST",
		url : '/WarehouseManagementSystem/rest/rfid/updateStokProduct',
		contentType : "application/json",
		mimeType : "application/json",
		data : JSON.stringify(rfIdNo),
		success : function(result) {
			alert("SUCCESS : basarılı");
		},
		error : function() {
			alert("error : deniedPermissionSecondManager");

		}
	});	
}


function addRfid() {
	var Rfid = {};
	Rfid["tur"] = $("#departman").val();
	Rfid["id"] = $("#rfidad").val();

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/WarehouseManagementSystem/rest/rfid/addRfid",
		data : JSON.stringify(Rfid),
		dataType : 'json',
		cache : false,
		timeout : 100000,
		success : function(data) {
			alert("SUCCESS : ", data);

		},
		error : function(e) {
			alert("ERROR : ", e);
		}
	});
	}	
	
	
 



 