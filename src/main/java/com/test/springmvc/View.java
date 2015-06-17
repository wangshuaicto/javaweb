package com.test.springmvc;


public class View {
	//跳转路径
	private String url;
	private String dispatchAction = DispatchActionConstant.FORWARD;
	public View(String url)
	{
		this.url=url;
	}
	public View(String url,String name,Object value)
	{
		this.url=url;
		ViewData view = new ViewData();
		view.put(name, value);
	}
	public View(String url,String name,String dispathAction,Object value)
	{
		this.url=url;
		this.dispatchAction=dispathAction;
		ViewData view = new ViewData();
		view.put(name, value);
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDispatchAction() {
		return dispatchAction;
	}
	public void setDispatchAction(String dispatchAction) {
		this.dispatchAction = dispatchAction;
	}
}
