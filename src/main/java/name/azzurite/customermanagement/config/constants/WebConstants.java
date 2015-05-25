package name.azzurite.customermanagement.config.constants;

public class WebConstants {

	public static final String MEDIA_TYPE_JSON = "application/json;charset=utf-8";

	public static final String[] APP_LOCATIONS = {
			"/login",
			"/customers",
			"/customers/*",
			"/customers/*/summary"
	};

}
