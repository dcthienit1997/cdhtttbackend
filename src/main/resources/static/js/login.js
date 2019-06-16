$(document).ready(function () {
    $("#password").focusin(function () {
        $("#form-login").addClass("up");
    });
    $("#password").focusout(function () {
        $("#form-login").removeClass("up");
    });

// Panda Eye move
    $(document).on("mousemove", function (event) {
        var dw = $(document).width() / 15;
        var dh = $(document).height() / 15;
        var x = event.pageX / dw;
        var y = event.pageY / dh;
        $('.eye-ball').css({
            width: x,
            height: y
        });
    });

// validation


    // $('.btn').click(function () {
    //     console.log("clicked");
    //     $('form').addClass('wrong-entry');
    //     setTimeout(function () {
    //         $('form').removeClass('wrong-entry');
    //     }, 3000);
    // });
});