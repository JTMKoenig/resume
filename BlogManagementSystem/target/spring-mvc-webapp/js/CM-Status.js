$(document).ready(function () {
    
//    if($("#image-div[status=='0']")){
//        $('#image-div').addClass('alert alert-success');
//    }
//    if($("#image-div[status=='1']")){
//            $('#image-div').addClass('alert alert-warning');
//    }
//    if($("#image-div[status=='2']")){
//            $('#image-div').addClass('alert alert-danger');
//        }
//    
//
//    
//    if($('#title-div').attr('status','0') ){
//        $('#title-div').addClass('alert alert-success');
//    }
//    if($('#title-div').attr('status','1') ){
//            $('#title-div').addClass('alert alert-warning');
//        }
//    if($('#title-div').attr('status','2') ){
//            $('#title-div').addClass('alert alert-danger');
//        }
//    
//
//    
//    if($('#username-div').attr('status','0')){
//        $('#username-div').addClass('alert alert-success');
//    }
//    if($('#username-div').attr('status','1')){
//        $('#username-div').addClass('alert alert-warning');
//    }
//    if($('#username-div').attr('status','2')){
//        $('#username-div').addClass('alert alert-danger');
//    }
//    
//
//    
//    if($('#start-div').attr('status','0')){
//        $('#start-div').addClass('alert alert-success');
//    }
//    if($('#start-div').attr('status','1')){
//        $('#start-div').addClass('alert alert-warning');
//        }
//    if($('#start-div').attr('status','2')){
//        $('#start-div').addClass('alert alert-danger');
//        }
//    
//
//    
//    if($('#end-div').attr('status','0')){
//        $('#end-div').addClass('alert alert-success');
//    }
//    if($('#end-div').attr('status','1')){
//        $('#end-div').addClass('alert alert-warning');
//    }
//    if($('#end-div').attr('status','2')){
//        $('#end-div').addClass('alert alert-danger');
//    }
//
//    
//    if($('#body-div').attr('status','0')){
//        $('#body-div').addClass('alert alert-success');
//    }
//    if($('#body-div').attr('status','1')){
//            $('#body-div').addClass('alert alert-warning');
//    }
//    if($('#body-div').attr('status','2')){
//            $('#body-div').addClass('alert alert-danger');
//        }
//    


    var elem = $('div');


    $(elem).each(function(){
        if($(this).attr('status')=='0'){
            $(this).addClass('alert alert-success');
        }
     
        if($(this).attr('status')=='1'){
            $(this).addClass('alert alert-warning');
        }
        if($(this).attr('status')=='2'){
            $(this).addClass('alert alert-danger');
        }

     });

    

});
    

    

    



  
    
    
     
    
    
