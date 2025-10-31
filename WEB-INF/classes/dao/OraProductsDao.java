package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tera.Product;
import exp.ResourceAccessException;

public class OraProductsDao implements ProductsDao {

	public void addProduct(Product p) {

		Connection cn = null;
		PreparedStatement st = null;

		try {
			// Driverインターフェイスを実装するクラスをロードする
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.jdbc.Driver");
			// Connectionインターフェイスを実装するクラスの
			// インスタンスを返す
			//cn = DriverManager.getConnection(
			//		"jdbc:oracle:thin:@localhost:1521:ip", "scott", "tiger");
            cn = DriverManager.getConnection(
					"jdbc:mysql://localhost/mydb", "info", "pro");
			System.out.println("DB接続完了");
			// 自動コミットをOFFにする
			cn.setAutoCommit(false);

			// insert文
			String sql = "insert into t_products(pid,name,price) "
					+ "values(?,?,?)";

			// PreparedStatementインターフェイスを実装するクラスの
			// インスタンスを取得する
			st = cn.prepareStatement(sql);

			// パラメータをセットする
			st.setString(1, p.getPid());
			st.setString(2, p.getName());
			st.setString(3, p.getPrice());

			// SQLの実行
			st.executeUpdate();

			// コミットする
			cn.commit();

			// Class.forName()で例外発生の場合
		} catch (ClassNotFoundException e) {
			// 独自例外にラップして送出する
			throw new ResourceAccessException(e.getMessage(), e);

			// getConnection,prepareStatement,executeQueryで例外発生の場合
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException発生");

			// ロールバックする
			try {
				System.out.println("ロールバック実行");
				cn.rollback();
			} catch (SQLException e2) {
				// 独自例外にラップして送出する
				throw new ResourceAccessException(e2.getMessage(), e2);
			}

			// 独自例外にラップして送出する
			throw new ResourceAccessException(e.getMessage(), e);

		} finally {
			// リソースの解放処理を行う
			try {
				if (st != null) {
					st.close();
				}
			} catch (SQLException e2) {
				// 独自例外にラップして送出する
				throw new ResourceAccessException(e2.getMessage(), e2);
				
			} finally {
				try {
					if (cn != null) {
						cn.close();
					}
				} catch (SQLException e3) {
					// 独自例外にラップして送出する
					throw new ResourceAccessException(e3.getMessage(), e3);
				}
			}
		}

	}

	public Product getProduct(String pid) {
		return null;
	}

	public List getAllProducts() {
		
		Connection cn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		ArrayList products=new ArrayList();

		try {
			// Driverインターフェイスを実装するクラスをロードする
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.jdbc.Driver");

			// Connectionインターフェイスを実装するクラスの
			// インスタンスを返す
			//cn = DriverManager.getConnection(
			//		"jdbc:oracle:thin:@localhost:1521:ip", "scott", "tiger");
            cn = DriverManager.getConnection(
					"jdbc:mysql://localhost/mydb", "info", "pro");
System.out.println("gapDB接続完了");


			// 自動コミットをOFFにする
			cn.setAutoCommit(false);

			// insert文
			String sql = "select pid,name,price from t_products";

			// PreparedStatementインターフェイスを実装するクラスの
			// インスタンスを取得する
			st = cn.prepareStatement(sql);

			//select文を送信し
			//ResultSetインターフェイスを実装したクラスの
			//インスタンスが返る
			rs = st.executeQuery();

			//カーソルを1行ずつスクロールし、データをフェッチする
			while (rs.next()) {
				
				Product p=new Product();
				
				p.setPid(rs.getString(1));
				p.setName(rs.getString(2));
				p.setPrice(rs.getString(3));
				System.out.println(p.getPid()+":"+p.getName()+":"+p.getPrice());	
				
				//コレクションに追加する
				products.add(p);
			}

			// コミットする
			cn.commit();

			// Class.forName()で例外発生の場合
		} catch (ClassNotFoundException e) {
			// 独自例外にラップして送出する
			throw new ResourceAccessException(e.getMessage(), e);

			// getConnection,prepareStatement,executeQueryで例外発生の場合
		} catch (SQLException e) {

			// ロールバックする
			try {
				cn.rollback();
			} catch (SQLException e2) {
				// 独自例外にラップして送出する
				throw new ResourceAccessException(e2.getMessage(), e2);
			}

			// 独自例外にラップして送出する
			throw new ResourceAccessException(e.getMessage(), e);

		} finally {
			// リソースの解放処理を行う
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
			} catch (SQLException e2) {
				// 独自例外にラップして送出する
				throw new ResourceAccessException(e2.getMessage(), e2);
				
			} finally {
				try {
					if (cn != null) {
						cn.close();
					}
				} catch (SQLException e3) {
					// 独自例外にラップして送出する
					throw new ResourceAccessException(e3.getMessage(), e3);
				}
			}
		}
		
		return products;
	}

}
