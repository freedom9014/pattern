package tera;

import dao.AbstractDaoFactory;
import dao.ProductsDao;

public class AddProductCommand extends AbstractCommand {
	
	public ResponseContext execute(ResponseContext resc){
		
		//RequestContextのインスタンスを取得する
		RequestContext reqc=getRequestContext();
		
		//RequestContextから入力パラメータを取得する
		String[] pids=reqc.getParameter("pid");
		String pid=pids[0];
		
		String[] names=reqc.getParameter("name");
		String name=names[0];
		
		String[] prices=reqc.getParameter("price");
		String price=prices[0];	
		
		//新しいProductクラスをインスタンス化する
		Product p=new Product();
		
		p.setPid(pid);
		p.setName(name);
		p.setPrice(price);
		
		//インテグレーションレイヤの処理を呼び出す
		AbstractDaoFactory factory=AbstractDaoFactory.getFactory();
		ProductsDao dao=factory.getProductsDao();
		dao.addProduct(p);
		
		//ResponseContextに転送先情報をセットする
		resc.setTarget("start");
		
		return resc;
	}
}
