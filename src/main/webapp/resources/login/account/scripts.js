
jQuery(document).ready(function() {

    /*
        Background slideshow
    */
	var imgPath = $("#ctx").val();
    $.backstretch([
      imgPath+"/resources/login/img/backgrounds/1.jpg"
    , imgPath+"/resources/login/img/backgrounds/2.jpg"
    , imgPath+"/resources/login/img/backgrounds/3.jpg"
    , imgPath+"/resources/login/img/backgrounds/4.jpg"
    ], {duration: 3000, fade: 750});

});


