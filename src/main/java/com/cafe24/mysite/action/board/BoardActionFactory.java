package com.cafe24.mysite.action.board;

import com.cafe24.web.mvc.Action;
import com.cafe24.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("add".equals(actionName)) {
			action = new AddAction();
		} else if("addform".equals(actionName)) {
			action = new AddFormAction();
		} else if("list".equals(actionName)) {
			action = new ListAction();
		} else if("update".equals(actionName)) {
			action = new UpdateAction();
		} else if("updateform".equals(actionName)) {
			action = new UpdateFormAction();
		} else if("view".equals(actionName)) {
			action = new ViewAction();
		} else {
			action = new ListAction();
		}
		
		return action;
	}

}
