package jspCommunity;

public class App {
	public static String getSite() {
		return "JSP Community";
	}
	
	public static String getContextName() {
		return "jspCommunity";
	}
	
	
	public static String getMainUrl() {
		return "https://" + getSiteDomain() + ":" + getSitePort() + "/" + getContextName() + "/usr/home/main";
	}
	
	public static String getLoginUrl() {
		return "https://" + getSiteDomain() + ":" + getSitePort() + "/" + getContextName() + "/usr/member/login";
	}

	

	private static int getSitePort() {
		return 8080;
	}

	private static String getSiteDomain() {
		return "localhost";
	}
}
