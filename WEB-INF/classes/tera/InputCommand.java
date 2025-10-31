package tera;

public class InputCommand extends AbstractCommand {
	public ResponseContext execute(ResponseContext resc){

		//転送先情報をセットする
		resc.setTarget("input");

		return resc;
	}
}
