
$(document).ready(function () {
    
    if(+$('#imageFlag', 'value').val()=='0'){
        $('#image-div').addClass('alert alert-success');
    }
    if(+$('#imageFlag').val()=='1'){
            $('#image-div').addClass('alert alert-warning');
    }
    if(+$('#imageFlag').val()=='2'){
            $('#image-div').addClass('alert alert-danger');
        }
    

    
    if(+$('#titleFlag', 'value').val()=='0'){
        $('#title-div').addClass('alert alert-success');
    }
    if(+$('#titleFlag').val()=='1'){
            $('#title-div').addClass('alert alert-warning');
        }
    if(+$('#titleFlag').val()=='2'){
            $('#title-div').addClass('alert alert-danger');
        }
    

    
    if(+$('#authorFlag', 'value').val()=='0'){
        $('#username-div').addClass('alert alert-success');
    }
    if(+$('#authorFlag').val()=='1'){
        $('#username-div').addClass('alert alert-warning');
    }
    if(+$('#authorFlag').val()=='2'){
        $('#username-div').addClass('alert alert-danger');
    }
    

    
    if(+$('#startDateFlag', 'value').val()=='0'){
        $('#start-div').addClass('alert alert-success');
    }
    if(+$('#startDateFlag').val()=='1'){
            $('#start-div').addClass('alert alert-warning');
        }
    if(+$('#startDateFlag').val()=='2'){
            $('#start-div').addClass('alert alert-danger');
        }
    

    
    if(+$('#endDateFlag', 'value').val()=='0'){
        $('#end-div').addClass('alert alert-success');
    }
    if(+$('#endDateFlag').val()=='1'){
        $('#end-div').addClass('alert alert-warning');
    }
    if(+$('#endDateFlag').val()=='2'){
        $('#end-div').addClass('alert alert-danger');
    }
    

    
    if(+$('#bodyFlag', 'value').val()=='0'){
        $('#body-div').addClass('alert alert-success');
    }
    if(+$('#bodyFlag').val()=='1'){
            $('#body-div').addClass('alert alert-warning');
    }
    if(+$('#bodyFlag').val()=='2'){
            $('#body-div').addClass('alert alert-danger');
        }
    


    

});
    

    

    



  
    
    
     
    
    
