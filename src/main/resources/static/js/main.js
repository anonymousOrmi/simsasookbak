(function ($) {
    "use strict";

    // Spinner
    var spinner = function () {
        setTimeout(function () {
            if ($('#spinner').length > 0) {
                $('#spinner').removeClass('show');
            }
        }, 1);
    };
    spinner(0);


    // Sticky Navbar
    $(window).scroll(function () {
        if ($(this).scrollTop() > 45) {
            $('.navbar').addClass('sticky-top shadow-sm');
        } else {
            $('.navbar').removeClass('sticky-top shadow-sm');
        }
    });


    // International Tour carousel
    $(".InternationalTour-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1000,
        center: false,
        dots: true,
        loop: true,
        margin: 25,
        nav : false,
        navText : [
            '<i class="bi bi-arrow-left"></i>',
            '<i class="bi bi-arrow-right"></i>'
        ],
        responsiveClass: true,
        responsive: {
            0:{
                items:1
            },
            768:{
                items:2
            },
            992:{
                items:2
            },
            1200:{
                items:3
            }
        }
    });


    // packages carousel
    $(".packages-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1000,
        center: false,
        dots: false,
        loop: true,
        margin: 25,
        nav : true,
        navText : [
            '<i class="bi bi-arrow-left"></i>',
            '<i class="bi bi-arrow-right"></i>'
        ],
        responsiveClass: true,
        responsive: {
            0:{
                items:1
            },
            768:{
                items:2
            },
            992:{
                items:2
            },
            1200:{
                items:3
            }
        }
    });


    // testimonial carousel
    $(".testimonial-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1000,
        center: true,
        dots: true,
        loop: true,
        margin: 25,
        nav : true,
        navText : [
            '<i class="bi bi-arrow-left"></i>',
            '<i class="bi bi-arrow-right"></i>'
        ],
        responsiveClass: true,
        responsive: {
            0:{
                items:1
            },
            768:{
                items:2
            },
            992:{
                items:2
            },
            1200:{
                items:3
            }
        }
    });

    
   // Back to top button
   $(window).scroll(function () {
    if ($(this).scrollTop() > 300) {
        $('.back-to-top').fadeIn('slow');
    } else {
        $('.back-to-top').fadeOut('slow');
    }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({scrollTop: 0}, 1500, 'easeInOutExpo');
        return false;
    }); 

    /* 유투브 데이터 조회 */
    $.ajax({
        url: '/api/youtube',
        method: 'GET',
        dataType : 'json',
        success: function (response) {
            let html = '';
            $.each(response, function (index, data) {
                console.log('data', data);
                let videoId = $(data).prop('videoId');
                html += '<div class="col-sm-6 col-md-6 col-lg-4 col-xl-3">' +
                    '<div class="ratio ratio-16x9">' +
                        `<iframe src="https://www.youtube.com/embed/${videoId}" 
                             title="YouTube video" allowfullscreen 
                             th:if="${videoId != null}"></iframe>` +
                    '</div></div>';
            });
            console.log(html);
            $('#youtube').html(html);
        },
        error: function (error) {
            console.error('Youtube Error : ', error);
        }
    });
})(jQuery);

