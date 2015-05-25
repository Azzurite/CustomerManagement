package name.azzurite.customermanagement.web;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Rewrites the given locations to the base path ("/")
 */
public class LocationRewriteFilter extends GenericFilterBean {

	public final List<Pattern> locationPatterns;


	/**
	 * Creates a filter that routes the given locations to the base path.
	 *
	 * @param locations a list of locations. '*' can be used to denote placeholders.
	 */
	public LocationRewriteFilter(String... locations) {
		this.locationPatterns = Arrays.stream(locations).map((location) -> {
			String locationRegex = location.replace("*", "[^/]*?");
			return Pattern.compile(locationRegex);
		}).collect(Collectors.toList());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if (httpRequest.getMethod().equals("GET") && locationMapped(httpRequest.getServletPath())) {
			httpRequest.getRequestDispatcher("/").forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	private boolean locationMapped(String location) {
		return locationPatterns.stream().anyMatch((pattern) -> {
			return pattern.matcher(location).matches();
		});
	}
}
