package exp;

//詳細レベルの例外
public class ResourceAccessException extends IntegrationException {
	public ResourceAccessException(String mess, Throwable cause) {
		super(mess, cause);
	}
}
