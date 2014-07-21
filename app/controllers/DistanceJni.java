package controllers;
public class DistanceJni {

	public native String execute(int argNum, String[] param);
	
	public String adapter(String[] param) {
		String ret = execute(param.length, param);
		return ret;
	}
	public void test() {
	
	}
}
