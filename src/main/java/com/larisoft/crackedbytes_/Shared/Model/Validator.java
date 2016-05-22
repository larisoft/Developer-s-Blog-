package com.larisoft.crackedbytes_.Shared.Model;

import java.util.List;

public interface Validator {
	
	boolean validate();
	
	List<String> getErrors();
}
