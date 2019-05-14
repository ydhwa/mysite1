package com.cafe24.web.mvc;

public abstract class ActionFactory {
	// factory method pattern
	// 어떤 action이 들어올 지 몰라 현재 구현 불가능하므로, 이를 상속하는 클래스에서 구현하도록 함
	public abstract Action getAction(String actionName);
}
