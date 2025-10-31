package tera;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FrontServlet extends jakarta.servlet.http.HttpServlet {
	//doGetは省略
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException { 
        doPost(req, res);
    }

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		// ApplicationControllerの実装クラスのインスタンスを取得する
		ApplicationController app = new WebApplicationController();

		// ファクトリメソッドを呼び出して
		// 具象クラスのインスタンスを取得する
		RequestContext reqc = app.getRequest(req);

		//ファクトリメソッドを呼び出す
		// RequestContextを渡してResponseContextを取得する
		ResponseContext resc = app.handleRequest(reqc);

		// ResponseContextに
		// HttpServletResponseインターフェイスを実装するクラスの
		// インスタンスを格納する
		resc.setResponse(res);

		// Viewの選択と転送処理を行う
		app.handleResponse(reqc, resc);
	}
}
