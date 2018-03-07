$(document).ready(function(){
	$(".bookit").click(function(){
		$.post("/takeItem", {documentId:$(this).attr("id")}, function(result){
			alert(result);
		});
	});
	$("#new_book").click(function(){
		if($(this).text() == "+ Add a new book"){
			$(this).text("Close").css({"color":"#ff4751"});
			$("#add_block_book").animate({"height":"236px"}, 150);
			$("#new_av").text("+ Add a new audio/video").css({"color":"black"});
			$("#add_block_av").animate({"height":"0px"}, 150);
		}
		else if($(this).text() == "Close"){
			$(this).text("+ Add a new book").css({"color":"black"});
			$("#add_block_book").animate({"height":"0px"}, 150);
		}
	});
	$("#new_av").click(function(){
		if($(this).text() == "+ Add a new audio/video"){
			$(this).text("Close").css({"color":"#ff4751"});
			$("#add_block_av").animate({"height":"109px"}, 150);
			$("#new_book").text("+ Add a new book").css({"color":"black"});
			$("#add_block_book").animate({"height":"0px"}, 150);
		}
		else if($(this).text() == "Close"){
			$(this).text("+ Add a new audio/video").css({"color":"black"});
			$("#add_block_av").animate({"height":"0px"}, 150);
		}
	});
});