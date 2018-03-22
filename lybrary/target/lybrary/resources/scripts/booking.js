$(document).ready(function(){
	$(".bookit").click(function(){
		$.post("/takeItem", {documentId:$(this).attr("id")}, function(result){
			alert(result);
		});
	});
	$(".modifyit").click(function(){
		var object = $(this).parent();
		var id = $(this).attr("id");
		$.post("/modifyDocument", {documentId:id, title:object.find(".books_inputs_title").val(), author:object.find(".books_inputs_author").val(), publisher:object.find(".books_inputs_publisher").val(), year:object.find(".books_inputs_year").val(), edition:object.find(".books_inputs_edition").val(), note:object.find(".books_inputs_note").val()}, function(result){
			alert(result);
		});
	});
	$("#add_save_book").click(function(){
		$.post("/addDocument", {title:$("#add_book_title").val(), author:$("#add_book_author").val(), publisher:$("#add_book_publisher").val(), year:$("#add_book_year").val(), edition:$("#add_book_edition").val(), note:$("#add_book_note").val()}, function(result){
			alert(result);
		});
	});
	$("#add_save_av").click(function(){
		$.post("/addAV", {title:$("#add_av_title").val(), author:$("#add_av_author").val()}, function(result){
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