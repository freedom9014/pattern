package tera;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public abstract class CommandFactory{
	public static AbstractCommand getCommand(RequestContext rc){
		
		AbstractCommand command = null;
		Properties prop = new Properties();
		
		try {
			// プロパティファイルを読み込む
			prop.load(new FileInputStream("/Users/yk/webapps/pattern/WEB-INF/classes/tera/commands.properties"));

			// パスに対応した文字列を取得します
			String name = prop.getProperty(rc.getCommandPath());
            System.out.println(name + new java.util.Date());
            //name = "tera.InputCommand";

			// 指定された名前のクラスに対応したClassクラスの
			// インスタンスを取得する（名前は完全限定名であること）
			Class c = Class.forName(name);

			// Classクラスのインスタンスを利用して
			// 対応するクラスのインスタンス化を行う
			command = (AbstractCommand) c.getDeclaredConstructor().newInstance();

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

		return command;

	}
}
