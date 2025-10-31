package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public abstract class AbstractDaoFactory {

	public static AbstractDaoFactory getFactory(){
		
		AbstractDaoFactory factory = null;
		Properties prop = new Properties();
		try {
			// クラスパスからdao.propertiesを読み込む
			java.io.InputStream in = AbstractDaoFactory.class.getClassLoader().getResourceAsStream("dao/dao.properties");
			if (in == null) {
				throw new FileNotFoundException("dao/dao.properties not found in classpath");
			}
			prop.load(in);

			// daoというキーに対応した文字列を取得します
			String name = prop.getProperty("dao");

			// 指定された名前のクラスに対応したClassクラスの
			// インスタンスを取得する（名前は完全限定名であること）
			Class c = Class.forName(name);

			// Classクラスのインスタンスを利用して
			// 対応するクラスのインスタンス化を行う
			factory = (AbstractDaoFactory) c.getDeclaredConstructor().newInstance();

		} catch (FileNotFoundException e) {
			// 実際には独自例外にラップしてスローする
			throw new RuntimeException(e.getMessage(), e);
		} catch (IOException e) {
			// 実際には独自例外にラップしてスローする
			throw new RuntimeException(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			// 実際には独自例外にラップしてスローする
			throw new RuntimeException(e.getMessage(), e);
		} catch (InstantiationException e) {
			// 実際には独自例外にラップしてスローする
			throw new RuntimeException(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			// 実際には独自例外にラップしてスローする
			throw new RuntimeException(e.getMessage(), e);
		} 
        catch (NoSuchMethodException e) {
            // 実際には独自例外にラップしてスローする
            throw new RuntimeException(e.getMessage(), e);
        } catch (java.lang.reflect.InvocationTargetException e) {
            // 実際には独自例外にラップしてスローする
            throw new RuntimeException(e.getMessage(), e);
        }

		return factory;

	}

	public abstract ProductsDao getProductsDao();

}
