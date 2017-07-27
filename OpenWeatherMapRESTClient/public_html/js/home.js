$(document).ready(function(){
    
   $('#get-weather-button').click(function(){
       var haveValidationErrors = checkAndDisplayValidationErrors($('#zipcode-form').find('input'));
       
       if(haveValidationErrors){
           $('#forecast').hide();
           clearWeatherData();
           return false;
       }
       
       
    clearWeatherData();
    makeAjaxCalls();
    $('#forecast').show();
 
   });
});
var lon;
var lat;
var iconText;

function checkAndDisplayValidationErrors(input){
     $('#errorMessages').empty(); 
     
     
     var errorMessages = [];
     
     input.each(function(){
         if(!this.validity.valid){
             var errorField = $('label[for='+ this.id + ']').text();
             errorMessages.push(errorField + ' ' + 'Please Enter a Five Digit Number');
         }
     });
     
     if(errorMessages.length > 0){
         $.each(errorMessages,function(index,message){
             $('#errorMessages').append($('<li>').attr({class: 'list-group-item list-group-item-danger'}).text(message));
         });
         return true;
     }
     else{
         return false;
     }
}
function clearWeatherData(){
    $('#city').empty();
    $('#weather-img').empty();
    $('#weather-data').empty();
    for(var i = 1; i < 6; i++){
        $('#date'+i).empty();
        $('#icon'+i).empty();
        $('#temp'+i).empty();
    }
    
    
}
function makeAjaxCalls(){
    
       //Month Names
       var monthNames = ["January", "February", "March", "April", "May",
       "June", "July", "August", "September", "October", "November", "December"];
   
       //Input Data
       var zipCode = 'zip=' + $('#zip-code').val();
       var unitType = '&units=' + $('#unit-type').val().toString().toLowerCase();
       var date = new Date();
       
       //Unit Swap
       var isImperial = (unitType === '&units=imperial');
       var f = 'F';
       var c = 'C';
       var tempUnit = f;
       if(isImperial){
            tempUnit = f;
        }else{
            tempUnit = c;
        }
       
       //Forecast GET
       $.ajax({
        type: 'GET',
        url: 'http://api.openweathermap.org/data/2.5/forecast/daily?' + zipCode + unitType + '&cnt=6&APPID=db0f0dc5f40cd455356f0e0fd6b564ca',
        
        success: function(weather){
            console.log(weather);
            console.log(weather.list[4]);
            for(var i = 1; i < 6; i++){
                var date = new Date((weather.list[i].dt)*1000) ;
                var currentDay = date.getDate() + ' ' + monthNames[date.getMonth()];
                var icon = weather.list[i].weather[0].icon;
                var iconText = weather.list[i].weather[0].main;
                var high = weather.list[i].temp.max;
                var low = weather.list[i].temp.min;
                
                $('#date'+i).append(currentDay);
                $('#icon'+i).append('<img src="http://openweathermap.org/img/w/'+ icon +'.png"/>');
                $('#icon'+i).append(iconText);
                $('#temp'+i).append('High: '+high+tempUnit+'<br>Low: '+low+tempUnit);
                
            }
        },
        error: function(){
            $('#errorMessages').append($('<li>').at
                    .attr({class: 'list-group-item list-group-item-danger'})
                    .text('Error calling web service. Please try again later.'));
        }
    });
    
    //Current Weather GET
     $.ajax({
        type: 'GET',
        url: 'http://api.openweathermap.org/data/2.5/weather?'+ zipCode + unitType + '&APPID=db0f0dc5f40cd455356f0e0fd6b564ca',
        
        success: function(weather){
            
            //Get Weather Data
            var city = weather.name ;
            var icon = weather.weather[0].icon;
            iconText = weather.weather[0].main;
            var temp = weather.main.temp;
            var humidity = weather.main.humidity;
            var wind = weather.wind.speed;
            
            //Get Lat, Lon and Description
            lon = weather.coord.lon;
            lat = weather.coord.lat;
            
            
            //Switch Units
            var isImperial = (unitType === '&units=imperial');
            var mph = ' miles/hour';
            var kmph = ' km/hour';
            var f = 'F';
            var c = 'C';
            var speed = mph;
            var tempUnit = f;
            if(isImperial){
                speed = mph;
                tempUnit = f;
            }else{
                speed = kmph;
                tempUnit = c;
            }
            
            //Append HTML w/ Weather Data
            $('#city').append('Current Conditions in ' + city);
            $('#weather-img').append('<img src="http://openweathermap.org/img/w/'+ icon +'.png"/>');
            $('#weather-img').append(iconText);
            $('#weather-data').append('Temperature: '+temp+ tempUnit+ '<br>Humidity: '
                    +humidity+'% <br>Wind: ' + wind + speed);
            
       getFlickrImage(lat, lon, icon, city);
            
        },
        error: function(){
            $('#errorMessages').append($('<li>').at
                    .attr({class: 'list-group-item list-group-item-danger'})
                    .text('Error calling web service. Please try again later.'));
            console.log('error1');
        }
                
            
        
        
    });
    
    
    
    
}

var counter = 1;
function getFlickrImage(lat, lon, icon, city){
    console.log('LAT: '+ lat + 'LON: ' + lon +'TEXT:'+ iconText);
    
    var brightMood = ',sunny,bright,sunshine,sun,blue';
    var darkMood = ',gloomy,cloudy,rain,rainy,dark,gray';
    var mood = null;
    
    if(icon === '01d.png' || icon === '02d.png' || icon === '03d.png' || icon === '04d.png'){
       mood = brightMood;
    }else{
        mood = darkMood;
    }
    
//Flickr API GET
            $.ajax({
        type: 'GET',
        url: 'https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=30fa3c1dea356157e2d401700f424378&lat='
                + lat + '&lon=' + lon + '&accuracy=1&tags=city,'+ city + mood+'&safe_search=1&sort=relevance&extras=url_l&format=json&nojsoncallback=1',

        success: function(flickr){
            var imagesFound = parseInt(flickr.photos.total);
            var image = Math.floor(Math.random() * (imagesFound - 1 +1) + 1 );
            console.log(image);
            //Set Img URL
            var imgURL = flickr.photos.photo[0].url_l ;
            console.log(imgURL);
            counter++;
            console.log(counter);
            if(counter === 3){
                counter = 1;
            }
            console.log(counter);
            //Append HTML w/ Flickr Image
            $('#bgImg'+counter.toString()).css('background-image', 'url('+imgURL+')');
            if(counter === 2){
                fadeOutOne();
            }else{
                fadeInOne();
            }
            
        },
        error: function(){
            $('#errorMessages').append($('<li>')
                    .attr({class: 'list-group-item list-group-item-danger'})
                    .text('Error calling web service. Please try again later.'));
            console.log('error2');
        }
    });
    
    }
    
    function fadeInOne(){
        setTimeout($('#bgImg1').fadeIn(500),1000);
        setTimeout($('#bgImg2').fadeOut(500),1000);
        
        
        
       // setTimeout(anim, 4000);
    }
    
    function fadeOutOne() {
        setTimeout($('#bgImg1').fadeOut(500),1000);
        setTimeout($('#bgImg2').fadeIn(500),1000);
    }
    








