
$('.input').keypress(function(e) {
    if(e.which == 13) {
        jQuery(this).blur();
        jQuery('#submit').focus().click();
    }
});