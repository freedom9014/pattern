package exp;

//カテゴリーレベルの例外
public class IntegrationException extends SystemException {
	public IntegrationException(String mess, Throwable cause) {
		super(mess, cause);
	}
}

