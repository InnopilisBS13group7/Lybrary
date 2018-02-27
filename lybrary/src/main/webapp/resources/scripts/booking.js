$(document).ready(function(){
	$(".bookit").click(function(){
		$.post("/takeItem", {documentId:$(this).attr("id")}, function(result){
			alert(result);
		});
	});
});