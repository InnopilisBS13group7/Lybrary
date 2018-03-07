$(document).ready(function(){
	$(".books").mouseenter(function(){
		$(this).find(".books_inside").animate({"opacity":"1"}, 150);
	});
	$(".return_book").click(function(){
		$.post("/return", {order:$(this).attr("id")}, function(result){
			alert(result);
		});
	});
});