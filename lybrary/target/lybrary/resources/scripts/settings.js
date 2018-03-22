$(document).ready(function(){
	$("#settings_block").height($("#settings_type_menu").height() + $("#settings_profile").height());
	$("#settings_type_profile").click(function(){
		$("#settings_profile").animate({"margin-left":"0"}, 300);
		$("#settings_type_line").animate({"margin-left":"0px"}, 300);
		$("#settings_users").animate({"margin-left":"800px"}, 300);
		$("#settings_orders").animate({"margin-left":"1600px"}, 300);
		$("#settings_block").animate({"height":$("#settings_type_menu").height() + $("#settings_profile").height()}, 300);
	});
	$("#settings_type_users").click(function(){
		$("#settings_profile").animate({"margin-left":"-800px"}, 300);
		$("#settings_type_line").animate({"margin-left":"266px"}, 300);
		$("#settings_users").animate({"margin-left":"0px"}, 300);
		$("#settings_orders").animate({"margin-left":"800px"}, 300);
		$("#settings_block").animate({"height":$("#settings_type_menu").height() + $("#settings_users").height()}, 300);
	});
	$("#settings_type_orders").click(function(){
		$("#settings_profile").animate({"margin-left":"-1600px"}, 300);
		$("#settings_type_line").animate({"margin-left":"532px"}, 300);
		$("#settings_users").animate({"margin-left":"-800px"}, 300);
		$("#settings_orders").animate({"margin-left":"0px"}, 300);
		$("#settings_block").animate({"height":$("#settings_type_menu").height() + $("#settings_orders").height()}, 300);
	});
	$("#new_user_bottom").click(function(){
		if($(this).text() == "+ Add a new user"){
			$(this).text("Close").css({"color":"#ff4751"});
			$("#new_user").animate({"height":"238px"}, 150);
			$("#settings_block").animate({"height":"+=218px"}, 150);
		}
		else if($(this).text() == "Close"){
			$(this).text("+ Add a new user").css({"color":"black"});
			$("#new_user").animate({"height":"20px"}, 150);
			$("#settings_block").animate({"height":"-=218px"}, 150);
		}
	});
	$("#new_user_save").click(function(){
		$.post("/addNewUser", {name:$("#new_user_inputs_name").val(), surname:$("#new_user_inputs_surname").val(), email:$("#new_user_inputs_email").val(), password:$("#new_user_inputs_password").val(), status:$("#new_user_inputs_type").val()}, function(result){
			alert(result)
		});
	});
	$("#settings_profile_save").click(function(){
		$.post("/profileSettings", {name:$("#settings_name").val(), surname:$("#settings_surname").val(), currentPassword:$("#settings_current_password").val(), newPassword:$("#settings_new_password").val(), address:$("#settings_adress").val(), phone:$("#settings_phone").val()}, function(result){
			$("#settings_alert").animate({"width":"256px", "padding-left":"8px", "padding-right":"8px"}, 150).delay(1000).animate({"width":"0px", "padding-left":"0px", "padding-right":"0px"}, 150);
		});
	});
	$(".settings_users_list_modify").click(function(){
		// alert("work")
		var object = $(this).parent();
		var id = $(this).attr("id");
		$.post("/modifyUser", {id:id, name:object.find(".settings_inputs_users_name").val(), address:object.find(".settings_inputs_users_adress").val(), phone:object.find(".settings_inputs_users_phone").val(), type:object.find(".settings_inputs_users_type").val()}, function(result){
			alert(result)
		});
	});
	$(".settings_orders_list_modify").click(function(){
		var id = $(this).attr("id");
		$.post("/closeOrder", {orderId:id}, function(result){
			alert(result)
		});
	});
});