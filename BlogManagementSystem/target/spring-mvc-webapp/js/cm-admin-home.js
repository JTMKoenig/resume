$(document).ready(function () {
$("tr[mycolor='green']").each(function(){
    
    $(this).addClass('alert alert-success');
    
});
$("tr[mycolor='red']").each(function(){
    
    $(this).addClass('alert alert-danger');
    
});
$("tr[mycolor='yellow']").each(function(){
    
    $(this).addClass('alert alert-warning');
    
});
$("tr[mycolor='grey']").each(function(){
    
    $(this).addClass('darkClass');
    $('.btn-danger',this).hide();
    $('.btn-success', this).show();  
});    
});
