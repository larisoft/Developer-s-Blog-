package com.larisoft.crackedbytes_.Shared.Model;

/*this class maintains a list of modules in this application
 *to add your own module, add the module like so
 *
 *MY_MODULE(4, "my_module");
 * 
 * 
 */
public enum Module { 
	BLOG (1, "blog"),
	BUG (2, "bug"),
	APP (3, "app"),
	UNKNOWN(0, "");
	
	
	private   String controller;
	private   int id;
	
	Module( int id, String controller) { 
		this.controller = controller;
		this.id = id;
	}
	
	//for creating this module when we know only the name;
	public void setController(String controller){
		this.controller= controller;
		
		if(controller.equals("blog")){
			this.id=1;
					
		}
		else if(controller.equals("bug")){
			this.id = 2;
		}
		
		else if(controller.equals("app")){
			this.id = 3;
		}
	}
	
   
	@Override
	public String toString(){
		
		return controller;
	}
	 
	public int toInt(){
		return id;
	}
	 
}
