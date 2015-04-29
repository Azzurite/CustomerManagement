package name.azzurite.customermanagement.test;

import name.azzurite.customermanagement.test.rest.error.ServiceErrorHandlerTest;
import name.azzurite.customermanagement.test.runner.PackageSuite;
import name.azzurite.customermanagement.test.runner.SuitePackages;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(PackageSuite.class)
@Suite.SuiteClasses({ServiceErrorHandlerTest.class})
@SuitePackages(basePackage = "name.azzurite.customermanagement.test",
		packages = {
				"rest.resource",
				"domain.repository"
		})
public class IntegrationTests {
}
