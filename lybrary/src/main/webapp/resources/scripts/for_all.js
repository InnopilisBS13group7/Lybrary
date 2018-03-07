$(document).ready(function(){
	function rotate(object, time, angle, actual){
		var deg = actual;
		var step = (angle - actual) / (time / 10)
		var interval = setInterval(function(){
			deg += step;
			object.css({"transform":"rotate(" + deg + "deg)"});
		}, 10);
		setTimeout(function(){
			clearInterval(interval);
		}, time);
	}
	var menu_open = false;
	$("#main_menu").click(function(){
		if(!menu_open){
			$("#first_menu_block, #third_menu_block").animate({"margin-top":"12px"}, 100);
			rotate($("#first_menu_block"), 100, 45, 0);
			rotate($("#second_menu_block"), 100, 45, 0);
			rotate($("#third_menu_block"), 300, 135, 0);
			menu_open = true;
			setTimeout(function(){
				$("#first_menu_block, #second_menu_block").css({"transform":"rotate(45deg)"}, 100);
				$("#third_menu_block").css({"transform":"rotate(135deg)"}, 100);
			}, 300);
			$("#main").animate({"margin-top":"+=146px"}, 300);
			$("#topic").animate({"margin-top":"-=146px"}, 300);
			$(".menu_points").slideDown(0).animate({"opacity":"1"}, 300);
		}
		else{
			$("#first_menu_block").delay(300).animate({"margin-top":"0px"}, 100);
			$("#third_menu_block").delay(300).animate({"margin-top":"24px"}, 100);
			setTimeout(function(){
				rotate($("#first_menu_block"), 100, 0, 45);
				rotate($("#second_menu_block"), 100, 0, 45);
			}, 200);
			rotate($("#third_menu_block"), 300, 0, 135);
			menu_open = false;
			setTimeout(function(){
				$("#first_menu_block, #second_menu_block, #third_menu_block").css({"transform":"rotate(0deg)"}, 100);
			}, 300)
			$("#main").animate({"margin-top":"-=146px"}, 300);
			$("#topic").animate({"margin-top":"+=146px"}, 300);
			$(".menu_points").animate({"opacity":"0"}, 300).slideUp(0);
		}
	});
	$("#booking_system").click(function(){
		$.post("/listItems", function(result){
			$("#more_box20").animate({"opacity":"0"}, 300).animate({"opacity":"1"}, 300);
			//link = '../resources/more.jsp?name=' + name + ' #usercard';
			setTimeout(function(){
				$("#more_box20").html(result);
				$("#style20").load('../resources/style/booking.css');
				$.getScript('../resources/scripts/booking.js');
			}, 300);
			$("#first_menu_block").delay(300).animate({"margin-top":"0px"}, 100);
			$("#third_menu_block").delay(300).animate({"margin-top":"24px"}, 100);
			setTimeout(function(){
				rotate($("#first_menu_block"), 100, 0, 45);
				rotate($("#second_menu_block"), 100, 0, 45);
			}, 200);
			rotate($("#third_menu_block"), 300, 0, 135);
			menu_open = false;
			setTimeout(function(){
				$("#first_menu_block, #second_menu_block, #third_menu_block").css({"transform":"rotate(0deg)"}, 100);
			}, 300)
			$("#main").animate({"margin-top":"-=146px"}, 300);
			$("#topic").animate({"margin-top":"+=146px"}, 300);
			$(".menu_points").animate({"opacity":"0"}, 300).slideUp(0);
		});
	});
	$("#avatar").click(function(){
		$.post("/toProfile", function(page){
			$("#more_box20").animate({"opacity":"0"}, 300).animate({"opacity":"1"}, 300);
			//link = '../resources/more.jsp?name=' + name + ' #usercard';
			setTimeout(function(){
				$("#more_box20").html(page);
				$("#style20").load('../resources/style/more.css');
				$.getScript('../resources/scripts/profile.js');
			}, 300);
		});
	});
	$("#exit").click(function(){
		$.post("/exit", function(result){
			alert(result)
		});
	});
	$("#settings").click(function(){
		$.post("/settings", function(result){
			$("#more_box20").animate({"opacity":"0"}, 300).animate({"opacity":"1"}, 300);
			//link = '../resources/more.jsp?name=' + name + ' #usercard';
			setTimeout(function(){
				$("#more_box20").html(result);
				$("#style20").load('../resources/style/settings.css');
				$.getScript('../resources/scripts/settings.js');
			}, 300);
			$("#first_menu_block").delay(300).animate({"margin-top":"0px"}, 100);
			$("#third_menu_block").delay(300).animate({"margin-top":"24px"}, 100);
			setTimeout(function(){
				rotate($("#first_menu_block"), 100, 0, 45);
				rotate($("#second_menu_block"), 100, 0, 45);
			}, 200);
			rotate($("#third_menu_block"), 300, 0, 135);
			menu_open = false;
			setTimeout(function(){
				$("#first_menu_block, #second_menu_block, #third_menu_block").css({"transform":"rotate(0deg)"}, 100);
			}, 300)
			$("#main").animate({"margin-top":"-=146px"}, 300);
			$("#topic").animate({"margin-top":"+=146px"}, 300);
			$(".menu_points").animate({"opacity":"0"}, 300).slideUp(0);
		});
	});
});