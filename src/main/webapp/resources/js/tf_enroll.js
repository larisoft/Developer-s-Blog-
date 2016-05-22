/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){
 
 
    load();
   

}); 


//set all clickables
function load(){ 
    
     $("#add_skill").click(function(){ 
         
      var  skill_hidden = $("#skill_section").attr("hidden");
           
      if(skill_hidden === undefined || skill_hidden == false){ 
            $("#skill_section").hide(2000);
            $("#skill_section").attr("hidden", "hidden");
        }
        else{
            
            $("#skill_section").show(2000); 
            $("#skill_section").removeAttr("hidden"); 
        } 
        return false;
    });
    
    
    
    $("#new_skill").click(function(){  
        var ski = $("#skills").val();
      
        var experience = $("#skills_experience").val();
      
        var proficiency = $("#skills_proficiency").val();
        
         log("gotten " + ski + experience + proficiency);
             
        if(isString(ski)===false){ 
            $("#skills_error").html("Please select skill value");
            return false;
        }
        else if (isString(experience)===false){ 
            $("#skills_error").html("Please specify experience");
            return false;
        }
        
        else if(isString(proficiency)===false){
            $("#skills_error").html("Please specifiy Proficiency");
            return false;
        }
       
       
        skills[skill_index] = new skill(ski, experience, proficiency);
        skill_index+=1;
        
        clear_values();
        update_skills_list();
        log("skill index "+JSON.stringify(skills));
         return false;
        
    });
}

function update_skills_list(){
    
    list = "";
    for(i = 0; i < skills.length; i++){
        list+=skills[i].name  + ", "; 
        
    }
    list+= " and General Computer Knowledge";
    $("#skills_list").val(list);
    $("#skills_entry").val(JSON.stringify(skills));
}

function clear_values(){
    
        var ski = $("#skills").val("");
      
        $("#skills_experience").val("");
      
        var proficiency = $("#skills_proficiency").val("");
    
        $("#skill_section").hide(200);
        $("skill_section").attr("hidden", "hidden");
    }


function log(message){
    console.log(message);
}


function isString(value){ 
    
    log("value is " + value);
  
    if(value===undefined){
        return false;
    } 
     
    if(value.length <1){
        
        return false
    }
   log("returning true");
   
   
    return true;
    }
    
//array of skills
var skills = [];

//no of skills in array so far
skill_index = 0;
function skill(name, experience, proficiency){
    this.name = name;
    this.experience = experience;
    this.proficiency = proficiency;
 }

    




