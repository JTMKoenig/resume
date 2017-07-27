$(document).ready(function () {
    
    if(+$('#titleFlag').val()===0){
        $('#title-div').addClass('alert alert-success');
    }
    if(+$('#titleFlag').val()===1){
            $('#title-div').addClass('alert alert-warning');
        }
    if(+$('#titleFlag').val()===2){
            $('#title-div').addClass('alert alert-danger');
        }
    
    $('#titleFlag').on('change', function(){
        $('#title-div').removeClass();
        $('#title-div').addClass('col-md-6');
        
        if(+$('#titleFlag').val()===0){
            $('#title-div').addClass('alert alert-success');
        }
        if(+$('#titleFlag').val()===1){
            $('#title-div').addClass('alert alert-warning');
        }
        if(+$('#titleFlag').val()===2){
            $('#title-div').addClass('alert alert-danger');
        }
    });
    
    if(+$('#authorFlag').val()===0){
        $('#username-div').addClass('alert alert-success');
    }
    if(+$('#authorFlag').val()===1){
        $('#username-div').addClass('alert alert-warning');
    }
    if(+$('#authorFlag').val()===2){
        $('#username-div').addClass('alert alert-danger');
    }
    
    $('#authorFlag').on('change', function(){
        $('#username-div').removeClass();
        $('#username-div').addClass('col-md-6');
        
        if(+$('#authorFlag').val()===0){
            $('#username-div').addClass('alert alert-success');
        }
        if(+$('#authorFlag').val()===1){
            $('#username-div').addClass('alert alert-warning');
        }
        if(+$('#authorFlag').val()===2){
            $('#username-div').addClass('alert alert-danger');
        }
    });
    
    if(+$('#startDateFlag').val()===0){
        $('#start-div').addClass('alert alert-success');
    }
    if(+$('#startDateFlag').val()===1){
            $('#start-div').addClass('alert alert-warning');
        }
    if(+$('#startDateFlag').val()===2){
            $('#start-div').addClass('alert alert-danger');
        }
    
    $('#startDateFlag').on('change', function(){
        $('#start-div').removeClass();
        $('#start-div').addClass('col-md-6');
        
        if(+$('#startDateFlag').val()===0){
            $('#start-div').addClass('alert alert-success');
    }
        if(+$('#startDateFlag').val()===1){
            $('#start-div').addClass('alert alert-warning');
        }
        if(+$('#startDateFlag').val()===2){
            $('#start-div').addClass('alert alert-danger');
        }
    });
    
    if(+$('#endDateFlag').val()===0){
        $('#end-div').addClass('alert alert-success');
    }
    if(+$('#endDateFlag').val()===1){
        $('#end-div').addClass('alert alert-warning');
    }
    if(+$('#endDateFlag').val()===2){
        $('#end-div').addClass('alert alert-danger');
    }
    
    $('#endDateFlag').on('change', function(){
        $('#end-div').removeClass();
        $('#end-div').addClass('col-md-6');
        
        if(+$('#endDateFlag').val()===0){
            $('#end-div').addClass('alert alert-success');
    }
        if(+$('#endDateFlag').val()===1){
        $('#end-div').addClass('alert alert-warning');
    }
        if(+$('#endDateFlag').val()===2){
        $('#end-div').addClass('alert alert-danger');
    }
    });
    
    if(+$('#bodyFlag').val()===0 && +$('#imageFlag').val()===0){
        $('#body-div').addClass('alert alert-success');
    }
    if(+$('#bodyFlag').val()===1 || +$('#imageFlag').val()===1){
            $('#body-div').addClass('alert alert-warning');
    }
    if(+$('#bodyFlag').val()===2 || +$('#imageFlag').val()===2){
            $('#body-div').addClass ('alert alert-danger');
        }
    
    $('#bodyFlag, #imageFlag').on('change', function(){
            $('#body-div').removeClass();
            $('#body-div').addClass('col-md-6');
        
            if(+$('#bodyFlag').val()===0 && +$('#imageFlag').val()===0){
            $('#body-div').addClass('alert alert-success');
    }
            if(+$('#bodyFlag').val()===1 || +$('#imageFlag').val()===1){
            $('#body-div').addClass('alert alert-warning');
    }
            if(+$('#bodyFlag').val()===2 || +$('#imageFlag').val()===2){
            $('#body-div').addClass('alert alert-danger');
    }
    
    
    });

    

});
    

    

    



  
    
    
     
    
    
