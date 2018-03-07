$(document).ready(function(){
	var hwindow = $(window).height();
	$("#main").animate({"margin-top":(-hwindow + 74) + "px"}, 400);
	$("#topic").css({"margin-top":((hwindow / 2) - 67) + "px"}).animate({"margin-top":(hwindow - 74) + "px"}, 400);
	$("#avatar").css({"border":"2px solid white"}).delay(100).animate({"width":"28px", "height":"28px", "margin-top":"19px", "margin-left":"-=14px"}, 400);
	$("#first_menu_block").delay(200).animate({"width":"30px"}, 150);
	$("#second_menu_block").delay(200).animate({"width":"30px"}, 250);
	$("#third_menu_block").delay(200).animate({"width":"30px"}, 350);
	$.getScript('../resources/scripts/profile.js');
});