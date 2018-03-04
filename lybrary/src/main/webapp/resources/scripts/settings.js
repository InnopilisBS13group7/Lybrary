$(document).ready(function(){
	$("#settings_type_profile").click(function(){
		$("#settings_profile").animate({"margin-left":"0"}, 300);
		$("#settings_type_line").animate({"margin-left":"0px"}, 300);
		$("#settings_users").animate({"margin-left":"800px"}, 300);
		$("#settings_orders").animate({"margin-left":"1600px"}, 300);
	});
	$("#settings_type_users").click(function(){
		$("#settings_profile").animate({"margin-left":"-800px"}, 300);
		$("#settings_type_line").animate({"margin-left":"266px"}, 300);
		$("#settings_users").animate({"margin-left":"0px"}, 300);
		$("#settings_orders").animate({"margin-left":"800px"}, 300);
	});
	$("#settings_type_orders").click(function(){
		$("#settings_profile").animate({"margin-left":"-1600px"}, 300);
		$("#settings_type_line").animate({"margin-left":"532px"}, 300);
		$("#settings_users").animate({"margin-left":"-800px"}, 300);
		$("#settings_orders").animate({"margin-left":"0px"}, 300);
	});
});