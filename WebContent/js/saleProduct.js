$(document).ready(
		function() {

			$.getJSON("/WarehouseManagementSystem/rest/rfid/getStok", function(
					result) {
				$.each(result, function(i, getstok) {
					var a = getstok.name + " " + getstok.address;
					console.log(getstok.address);
					$("#DepoSec").append(
							'<option value="DepoSec" selected="selected">' + a
									+ '</option>');

				});
			});
			$("select").change(function() {
				var str = "";

				$("select option:selected").each(function() {
					str += $(this).index() + 1 + " ";
				});
				console.log(str);
				numan(str);

			}).trigger("change");

		});

function numan(a) {

	$('#secılenDepoUrunleri').empty()
	$('#secılenDepoUrunleri')
			.append(
					"<tr><th>ÜRÜN NO</th><th>ÜRÜN ADI</th><th>URUN GIRIS TARIHI</th><th>cıkıs</th></tr>");

	var durum = 'Henüz İncelenmedi';
	var TDEKLE = '</td><td>'
	$
			.ajax({
				type : "POST",
				url : '/WarehouseManagementSystem/rest/rfid/getStokProducts',
				contentType : "application/json",
				mimeType : "application/json",
				data : JSON.stringify(a),
				success : function(result) {
					$
							.each(
									result,
									function(i, getStokProducts) {
										console.log(getStokProducts);
										$("#secılenDepoUrunleri")
												.append(
														'<tr><td>'
																+ parseInt(getStokProducts.id)
																+ TDEKLE
																+ getStokProducts.rfId
																+ TDEKLE
																+ getStokProducts.name
																+ TDEKLE
																+ getStokProducts.createDate
																+ TDEKLE
																+ '<button   type="button"  onclick="Update('
																+ getStokProducts.id
																+ ')"   class="btn btn-info btn-sm" data-toggle="modal" data-target="#İzinBilgisi">CIKIS VER</button>'
																+ '</td></tr>');
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
			alert("error : Update Rfid");

		}
	});
}
