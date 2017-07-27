$(document).ready(function (){
    $('#moreMetasBtn').click(cloneMetaSelect);
    $('#removeMetaSelect').click(removeMetaSelect);
    
    
    
});

function cloneMetaSelect(){
    $("#metabeingSelect").clone().insertAfter($("#metabeingSelect"))
            .removeAttr('id').attr('id', 'moreMeta').append("<a id='removeMetaSelect' ><div style='color:red'> remove</div></a>");
}

function removeMetaSelect(){
    $("#moreMeta").hide();
}