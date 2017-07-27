


$(document).ready(function(){
    listStaticPages();
});




function listStaticPages(){
    
    $('#staticpage-list').empty();
    
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/FidgetBlog/getstaticpagelist',
        success: function(data,status){
            
            var i=0;
            
            $.each(data, function(index,staticPage){
                var id = staticPage.staticPageId;
                var staticPageTitle = staticPage.staticPageTitle;
                
                var staticPageList=$("#staticpage-list");
                var row = '<li id=\"staticpage-' + i + '" href = "${pageContext.request.contextPath}/static/' + id + '">';
         
                staticPageList.append(row);
                i++;
            });
            
       },
        error: function(){

       }
    });
}



