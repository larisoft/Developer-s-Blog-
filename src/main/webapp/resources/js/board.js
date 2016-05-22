/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */  

$(document).ready(function(){
     
    $("#loading").toggle();  
  

});

function loadPage(page){
   $("#placeholder").slideUp(1000);
   $("#loading").slideDown(1000);
   
   $.ajax({url: page, async:true, error: failed(page), success: function(result){
          (console.log("returned" + result));
           $("#placeholder").html(result);
           $("#loading").slideUp(1000, function(){
               
           $("#placeholder").slideDown(1000);
           });
                  $("#placeholder").slideDown(1000);
   }});
   
   console.log("loading now");   
   return false;
}


function failed(p){  
  //  alert(p);
    
}

 

