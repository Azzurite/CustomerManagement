package name.azzurite.customermanagement.test;

import name.azzurite.customermanagement.test.rest.error.ErrorsTest;
import name.azzurite.customermanagement.test.runner.PackageSuite;
import name.azzurite.customermanagement.test.runner.SuitePackages;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(PackageSuite.class)
@SuitePackages(basePackage = "name.azzurite.customermanagement.test",
		packages = {"rest.service"})
@SuiteClasses(ErrorsTest.class)
public class UnitTests {
}
