package tera;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WebApplicationController implements ApplicationController {
	
	//RequestContextのファクトリメソッド
	public RequestContext getRequest(Object request) {

		RequestContext reqc=new WebRequestContext();
		
		//HttpServletRequestの実装クラスのインスタンスをセットする
		reqc.setRequest(request);
		
		return reqc;
	}

	//ResponseContextのファクトリメソッド、コア処理の選択と実行も行う
	public ResponseContext handleRequest(RequestContext req) {

		AbstractCommand command = CommandFactory.getCommand(req);
		command.init(req);//Commandを初期化する

		// コマンドを実行する
		// 戻り値としてResponseContextのインスタンスを得る
		// ここで具象ResponseContextのインスタンスを渡す
		ResponseContext resc = command.execute(new WebResponseContext());

		return resc;
	}

	//コア処理の結果の取得、Viewの選択と転送を行う
	public void handleResponse(RequestContext reqc, ResponseContext resc) {

		//Object型を返すためキャストする必要がある
		HttpServletRequest req = (HttpServletRequest) reqc.getRequest();
		HttpServletResponse res = (HttpServletResponse) resc.getResponse();

		//コア処理の実行結果を取得して登録を行う
		req.setAttribute("result",resc.getResult());

		RequestDispatcher rd = req.getRequestDispatcher(resc.getTarget());

		try {

			rd.forward(req, res);

		} catch (ServletException e) {
			//独自例外にラップしてthrowする
		} catch (IOException e) {
			//独自例外にラップしてthrowする
		}
	}
}
