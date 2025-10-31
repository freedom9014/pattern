package tera;

import java.util.Map;
import jakarta.servlet.http.HttpServletRequest;

public class WebRequestContext implements RequestContext {
	private Map parameters;
	private HttpServletRequest request;
	public WebRequestContext() {	}

	public String getCommandPath() {

		//サーブレットパスを取得する
		String servletPath=request.getServletPath();

// servletPathがnullまたは長さ1未満なら空文字列を返す
//if (servletPath == null || servletPath.length() < 2) {
//    return "";
//}
//2文字目以降を取得する
        String commandPath = servletPath.substring(1);
        return commandPath;
	}

	public String[] getParameter(String key) {
		return (String[])parameters.get(key);
	}
	public Object getRequest() {
		return request;
	}
	public void setRequest(Object req) {

		//キャストする必要がある。
		request = (HttpServletRequest) req;

		parameters = request.getParameterMap();
	}
}
