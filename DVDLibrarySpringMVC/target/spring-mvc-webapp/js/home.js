$(document).ready(function (){
 $('#add-dvd-btn').click(showDvdForm);
 $('.cancel-btn').click(hideDvdForm);
 $('.edit-btn').click(showEditForm);
 $('#show-dvd-btn').click(showDvdDetails);
 $('#back-btn').click(hideDvdDetails);

   $(document).on('submit', '#update-form', function(e){
       //e.preventDefault();
       console.log(e);
             console.log('here'); 

   });
    
});

var ctx = $('ctx').val();

function showDvdForm(){
    $('#errorMessages').empty();
    $('#library-table-div').hide();
    $('.form-header').html('<h2>Add DVD</h2>');
    $('#dvd-add-form-div').show();
}

function hideDvdForm(){
    console.log("hide away");
    $('#errorMessages').empty();
    $('#library-table-div').show();
    $('#dvd-add-form-div').hide();
    $('#dvd-edit-form-div').hide();
    //Clear form input
    $("input[name='title']").val('');
    $("input[name='releaseDate']").val('');
    $("input[name='director']").val('');
    $("select[name='rating']").prop('selectedIndex',0);
    $("textarea[name='notes']").empty();
}

function showEditForm(){
    console.log("editMethod");
    $('#errorMessages').empty();
    var editBtn = this.getAttribute('data-id');
    console.log("edit: "+editBtn);
    
    $.ajax({
        type:'GET',
        url:'http://localhost:8080'+ctx+'/dvd/'+ editBtn,
        success: function(data, status){
 
            $("#dvd-id").val(data.id);
            $("input[name='title']").val(data.title);
            $("input[name='releaseDate']").val(data.releaseDate);
            $("input[name='director']").val(data.director);
            $("select[name='rating']").val(data.rating);
            $("textarea[name='notes']").html(data.notes);
            
            //Clear Empty Fields
            if(data.realeaseYear === 0){
                $('#year').val('');
            }
        },
       error: function(){
           console.log("get broken");
          $('#errorMessages').append($('<li>')
                    .attr({class: 'list-group-item list-group-item-danger'})
                    .text('Error calling web service. Please try again later.'));  
       } 
    });
    
    $('#library-table-div').hide();
    $('.form-header').html('<h2>Edit DVD</h2>');
    $('#dvd-edit-form-div').show();
}

function showDvdDetails(){
    $('#errorMessages').empty();
    var showBtn = this.getAttribute('data-id');
    
    $.ajax({
        type:'GET',
        url:'http://localhost:8080'+ctx+'/dvd/show/'+ showBtn,
        success: function(data, status){
            
            $('#details-title').html('<h2>Title: '+ data.title +'</h2>');
            $('#details-data').html('Release Date: ' + data.releaseDate +'<br/> Director: ' + data.director
                    + '<br />Rating: ' + data.rating + '<br />Notes: ' + data.notes);
    },
    error: function(){
           console.log("get broken");
          $('#errorMessages').append($('<li>')
                    .attr({class: 'list-group-item list-group-item-danger'})
                    .text('Error calling web service. Please try again later.'));  
       } 
     });
   
    $('#library-table-div').hide();
    $('#details-div').show();

    
    }

function hideDvdDetails(){
    $('#details-div').hide();
    $('#library-table-div').show();
}




