$(document).ready(function(){
	var hwindow = $(window).height();
	$("#topic").css({"margin-top":((hwindow / 2) - 67) + "px"}).delay(300).animate({"margin-top":((hwindow / 2) - 127) + "px", "opacity":"1"}, 600);
	$("#main_button_box").css({"margin-top":((hwindow / 2) - 53) + "px"}).delay(300).animate({"width":"335px"}, 600);
	$("#more_box").css({"margin-top":(hwindow - 86) + "px"});
	$("#plus_box").css({"margin-top":(hwindow + 8) + "px"});
	var scroll_testTop = false;
	var scroll_testBottom = true;
	var need_scroll = true;
	if($(window).scrollTop() >= (hwindow - 74)){
		setTimeout(function(){
			$("#main_button_box").css({"width":"0px"}, 200).slideUp(0);
			$("#topic").css({"margin-top":(hwindow - 74) + "px", "opacity":"0"}).animate({"opacity":"1"}, 200);
		}, 650);
		$("#more_box").css({"opacity":"0"});
		$("#main").css({"margin-top":(-hwindow + 74) + "px"});
		scroll_testTop = true;
		scroll_testBottom = false;
	}
	function enter(page){
		$("#enter_error").animate({"opacity":"0", "padding-top":"0px", "padding-bottom":"0px", "height":"0px", "margin-top":"0px"}, 200).css({"border-top":"none"}).slideUp(0);
		$("#main_button_box").animate({"width":"0px"}, 200).slideUp(0);
		$("#more_box").animate({"opacity":"0"}, 500).slideUp(0);
		$("#main").delay(200).animate({"margin-top":(-hwindow + 74) + "px"}, 400);
		$("#topic").delay(200).animate({"margin-top":(hwindow - 74) + "px"}, 400);
		$("#plus_box").slideUp(0);
		$("#main_menu").delay(400).slideDown(0);
		$("#first_menu_block").delay(400).animate({"width":"30px"}, 150);
		$("#second_menu_block").delay(400).animate({"width":"30px"}, 250);
		$("#third_menu_block").delay(400).animate({"width":"30px"}, 350);
		$("#avatar").delay(200).css({"border":"2px solid white"}).animate({"width":"28px", "height":"28px", "margin-top":"19px", "margin-left":"-=14px"}, 400);
		$("#more_box20").html(page);
		$("#style20").load('../resources/style/more.css');
		$.getScript('../resources/scripts/more.js');
		need_scroll = false;
		$.getScript('../resources/scripts/profile.js');
	}
	function scrollingDown(){
		$("#enter_error").animate({"opacity":"0", "padding-top":"0px", "padding-bottom":"0px", "height":"0px", "margin-top":"0px"}, 200).css({"border-top":"none"}).slideUp(0);
		$("#main_button_box").animate({"width":"0px"}, 200).slideUp(0);
		$("#more_box").animate({"opacity":"0"}, 500).slideUp(0);
		$("#main").delay(200).animate({"margin-top":(-hwindow + 74) + "px"}, 400);
		$('body,html').delay(200).animate({scrollTop:hwindow - 74}, 400);
		$("#topic").delay(200).animate({"margin-top":(hwindow - 74) + "px"}, 400);
		scroll_testTop = true;
		setTimeout(function(){
			scroll_testBottom = false;
		}, 650)
	}
	function scrollingUp(){
		$("#main_button_box").delay(400).slideDown(0).animate({"width":"335px"}, 200);
		$("#main").animate({"margin-top":"-8px"}, 400);
		$('body,html').animate({scrollTop:0}, 400);
		$("#more_box").delay(100).animate({"opacity":"1"}, 500).slideDown(0);
		$("#topic").animate({"margin-top":((hwindow / 2) - 127) + "px"}, 400);
		scroll_testBottom = true;
		setTimeout(function(){
			scroll_testTop = false;
		}, 650)
	}
	function error_open(object){
		if(object.css("width") == "0px")
			object.animate({"margin-top":"-=10px", "margin-left":"-=10px", "height":"20px", "width":"20px"}, 200);
	}
	function error_close(object){
		if(object.css("width") == "20px")
			object.animate({"margin-top":"+=10px", "margin-left":"+=10px", "height":"0px", "width":"0px"}, 200);
	}
	function registration_val_test(test, val){
		return test.test(val);
	}
	$(window).scroll(function(){
		var scroll = $(this).scrollTop();
        if(scroll >= 0 && scroll < (hwindow - 74) && need_scroll){
        	//$("#more_box").css({"opacity":(hwindow - scroll - 82) / (hwindow - 82)});
        	//$("#main").css({"margin-top":-(scroll + 8) + "px"});
        	if(scroll_testTop == false)
        		scrollingDown();
        	if(scroll_testBottom == false)
        		scrollingUp();
        }
    });
    $("#more_box").click(function(){
    	scrollingDown();
    });
	setInterval(function(){
		$("#more_arrow").animate({"margin-top":"+=10px"}, 750).animate({"margin-top":"-=10px"}, 750);
	}, 2000);
	$("#enter").click(function(){
		if($(this).text() == "Sign in"){
			$(this).css({"background-color":"white", "color":"black", "border":"none"}).text("Continue").animate({"margin-top":"+=82px"}, 200);
			$("#reg").css({"margin-top":"-=2px"});
			$("#enter_inputs_box").slideDown(0).animate({"height":"82px"}, 200);
			$(".enter_inputs").css({"opacity":"1"});
			if($("#reg").text() == "Sign up")
				$("#reg").animate({"margin-top":"+=82px"}, 200);
			$("#reg_error").animate({"opacity":"0", "padding-top":"0px", "padding-bottom":"0px", "height":"0px"}, 200).css({"border-bottom":"none"}).slideUp(0);
		}
		else{
			$.post("/enter", {email:$("#enter_email").val(), password:$("#enter_password").val()}, function(result){
				if(String(result) == "false"){
					$("#enter_error").slideDown(0).css({"border-top":"2px solid"}).animate({"opacity":"1", "padding-top":"8px", "padding-bottom":"8px", "height":"22px", "margin-top":"-41px"}, 200);
					$("#topic").animate({"margin-top":"-=41px"}, 200);
				}
				else
					enter(result);
			});
		}
		if($("#reg").text() == "Continue"){
			$("#reg").css({"background-color":"none", "color":"white"}).text("Sign up").animate({"margin-top":"-=123px"}, 200);;
			$("#reg_inputs_box").animate({"height":"0px"}, 200).slideUp(0);
			$(".reg_inputs").css({"opacity":"0"});
		}
		else if($(this).text() == "Sign in")
			$("#reg").animate({"margin-top":"+=82px"}, 200);
	});
	$("#reg").click(function(){
		if($(this).text() == "Sign up"){
			$(this).css({"background-color":"white", "color":"black"}).text("Continue");
			$("#reg_inputs_box").slideDown(0).animate({"height":"205px"}, 200);
			$(".reg_inputs").css({"opacity":"1"});
			if($("#enter").text() == "Sign in")
				$("#reg").animate({"margin-top":"+=205px"}, 200);
			$("#enter_error").animate({"opacity":"0", "padding-top":"0px", "padding-bottom":"0px", "height":"0px", "margin-top":"0px"}, 200).css({"border-top":"none"}).slideUp(0);
			if($("#enter_error").css("opacity") == "1")
				$("#topic").animate({"margin-top":"+=41px"}, 200);
		}
		else{
			error_test = true;
			if($("#reg_name").val().length < 2){
				error_open($("#reg_name_error"));
				error_test = false;
			}
			else{
				test = /^[a-zA-Z]{2,20}$/;
				result = registration_val_test(test, $("#reg_name").val());
				if(result == true)
					error_close($("#reg_name_error"));
				else{
					error_open($("#reg_name_error"));
					error_test = false;
				}
			}
			if($("#reg_surname").val().length < 2){
				error_open($("#reg_surname_error"));
				error_test = false;
			}
			else{
				test = /^[a-zA-Z]{2,20}$/;
				result = registration_val_test(test, $("#reg_surname").val());
				if(result == true)
					error_close($("#reg_surname_error"));
				else{
					error_open($("#reg_surname_error"));
					error_test = false;
				}
			}
			if($("#reg_email").val().length < 14){
				error_open($("#reg_email_error"));
				error_test = false;
			}
			else{
				test = /(@innopolis\.ru)$/;
				result = registration_val_test(test, $("#reg_email").val());
				if(result == true)
					error_close($("#reg_email_error"));
				else{
					error_open($("#reg_email_error"));
					error_test = false;
				}
			}
			if($("#reg_password").val().length < 8){
				error_open($("#reg_password_error"));
				error_test = false;
			}
			else{
				test = /^[a-zA-Z0-9\!\@\#\$\%\^\&\*\(\)\-\_\+\=\;\:\,\.\/\?\\\|\`\~\[\]\{\} ]{8,30}$/;
				result = registration_val_test(test, $("#reg_password").val());
				if(result == true)
					error_close($("#reg_password_error"));
				else{
					error_open($("#reg_password_error"));
					error_test = false;
				}
			}
			if($("#reg_password").val() != $("#reg_password20").val()){
				error_open($("#reg_password20_error"));
				error_test = false;
			}
			else
				error_close($("#reg_password20_error"));
			if(error_test)
				$.post("/registration", {name:$("#reg_name").val(), surname:$("#reg_surname").val(), email:$("#reg_email").val(), password:$("#reg_password").val()}, function(result){
					if(String(result) == "false")
						$("#reg_error").slideDown(0).css({"border-bottom":"2px solid #ff3b3b"}).animate({"opacity":"1", "padding-top":"8px", "padding-bottom":"8px", "height":"22px"}, 200);
					else
						enter(result);
				});
		}
		if($("#enter").text() == "Send an email"){
			$("#enter").css({"background-color":"none", "color":"white", "border-bottom":"2px solid white"}).text("Sign in").animate({"margin-top":"-=41px"}, 200);
			$("#enter_password").slideDown(0);
			$("#enter_inputs_box").animate({"height":"0px"}, 200).slideUp(0);
			$(".enter_inputs").css({"opacity":"0"});
			$("#reg").css({"margin-top":"+=2px"}).animate({"margin-top":"+=164px"}, 200);
		}
		if($("#enter").text() == "Continue"){
			$("#enter").css({"background-color":"none", "color":"white", "border-bottom":"2px solid white"}).text("Sign in").animate({"margin-top":"-=82px"}, 200);
			$("#enter_inputs_box").animate({"height":"0px"}, 200).slideUp(0);
			$(".enter_inputs").css({"opacity":"0"});
			$("#reg").css({"margin-top":"+=2px"}).animate({"margin-top":"+=123px"}, 200);
		}
		else if($(this).text() == "Sign up")
			$("#reg").animate({"margin-top":"+=205px"}, 200);
	});
	$(".main_buttons").mouseenter(function(){
		if($(this).text() == "Continue" || $(this).text() == "Send an email")
			$(this).css({"background-color":"#e7e7e7"});
		else
			$(this).css({"background-color":"rgba(180, 180, 180, 0.4)"});
	});
	$(".main_buttons").mouseout(function(){
		if($(this).text() == "Continue" || $(this).text() == "Send an email")
			$(this).css({"background-color":"#ffffff"});
		else
			$(this).css({"background-color":"none"});
	});
	$("#reg_name_error").mouseenter(function(){
		$("#error_more_name").slideDown(0).animate({"margin-left":"343px", "opacity":"1"}, 200);
	});
	$("#reg_name_error").mouseout(function(){
		$("#error_more_name").animate({"margin-left":"353px", "opacity":"0"}, 200).slideUp(0);
	});
	$("#reg_surname_error").mouseenter(function(){
		$("#error_more_surname").slideDown(0).animate({"margin-left":"343px", "opacity":"1"}, 200);
	});
	$("#reg_surname_error").mouseout(function(){
		$("#error_more_surname").animate({"margin-left":"353px", "opacity":"0"}, 200).slideUp(0);
	});
	$("#reg_email_error").mouseenter(function(){
		$("#error_more_email").slideDown(0).animate({"margin-left":"343px", "opacity":"1"}, 200);
	});
	$("#reg_email_error").mouseout(function(){
		$("#error_more_email").animate({"margin-left":"353px", "opacity":"0"}, 200).slideUp(0);
	});
	$("#reg_password_error").mouseenter(function(){
		$("#error_more_password").slideDown(0).animate({"margin-left":"343px", "opacity":"1"}, 200);
	});
	$("#reg_password_error").mouseout(function(){
		$("#error_more_password").animate({"margin-left":"353px", "opacity":"0"}, 200).slideUp(0);
	});
	$("#reg_password20_error").mouseenter(function(){
		$("#error_more_password20").slideDown(0).animate({"margin-left":"343px", "opacity":"1"}, 200);
	});
	$("#reg_password20_error").mouseout(function(){
		$("#error_more_password20").animate({"margin-left":"353px", "opacity":"0"}, 200).slideUp(0);
	});
	$("#enter_error").mouseenter(function(){
		if($(this).text() == "Forgot your password?")
			$(this).css({"text-decoration":"underline"});
	});
	$("#enter_error").mouseout(function(){
		$(this).css({"text-decoration":"none"});
	});
	$("#enter_error").click(function(){
		if($(this).text() == "Forgot your password?"){
			$("#enter_inputs_box").animate({"height":"41px"}, 200);
			$("#enter_password").delay(200).slideUp(0);
			$("#enter, #reg").animate({"margin-top":"-=41px"}, 200);
			$("#enter_error").animate({"padding-left":"327px", "width":"0px"}, 200).css({"cursor":"default", "text-decoration":"none"});
			$("#enter").animate({"padding-left":"335px", "width":"0px"}, 200);
			setTimeout(function(){
				$("#enter_error").text("Enter your email").animate({"padding-left":"8px", "width":"319px"}, 200);
				$("#enter").text("Send an email").animate({"padding-left":"0px", "width":"335px"}, 200);
			}, 200);
			$("#enter_error_back").slideDown(0).animate({"opacity":"1"}, 400);
		}
	});
	$("#enter_error_back").click(function(){
		$("#enter_inputs_box").animate({"height":"82px"}, 200);
		$("#enter_password").slideDown(0);
		$("#enter, #reg").animate({"margin-top":"+=41px"}, 200);
		$("#enter_error").animate({"padding-left":"327px", "width":"0px"}, 200).css({"cursor":"pointer"});
		$("#enter").animate({"padding-left":"335px", "width":"0px"}, 200);
		setTimeout(function(){
			$("#enter_error").text("Forgot your password?").animate({"padding-left":"8px", "width":"319px"}, 200);
			$("#enter").text("Continue").animate({"padding-left":"0px", "width":"335px"}, 200);
		}, 200);
		$("#enter_error_back").animate({"opacity":"0"}, 400).slideUp(0);
	});
});