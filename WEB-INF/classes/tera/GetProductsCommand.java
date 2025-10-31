package tera;

import java.util.List;

import dao.AbstractDaoFactory;
import dao.ProductsDao;

public class GetProductsCommand extends AbstractCommand{

	public ResponseContext execute(ResponseContext resc){
		
		//インテグレーションレイヤの処理を呼び出す
		AbstractDaoFactory factory=AbstractDaoFactory.getFactory();
		ProductsDao dao=factory.getProductsDao();
		
		List products=dao.getAllProducts();
		
		//ResponseContextに結果をセットする
		resc.setResult(products);
		
		//ResponseContextに転送先情報をセットする
		resc.setTarget("view");
		
		return resc;
	}
}

