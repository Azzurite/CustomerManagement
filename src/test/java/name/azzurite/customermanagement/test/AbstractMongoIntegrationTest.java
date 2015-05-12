package name.azzurite.customermanagement.test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.Mongo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class AbstractMongoIntegrationTest extends AbstractIntegrationTest {

	@Inject
	private Mongo mongo;

	@Inject
	private MongoOperations ops;

	@Value("${spring.data.mongodb.database}")
	private String curDatabaseName;

	private static List<Object> databaseItemsForTest = new ArrayList<>();

	@AfterClass
	public static void clearItems() {
		databaseItemsForTest.clear();
	}

	@Before
	public void insertDatabaseItems() {
		clearDatabase();
		databaseItemsForTest.forEach(ops::insert);
	}

	protected static void dbAddForTests(Object obj) {
		databaseItemsForTest.add(obj);
	}

	@After
	public void clearDatabase() {
		DB db = mongo.getDB(curDatabaseName);
		for (String collection : db.getCollectionNames()) {
			if (collection.startsWith("system")) continue;

			db.getCollection(collection).remove(new BasicDBObject());
		}
	}

	protected static <T> void dbAddForTests(Iterable<T> items) {
		for (T item : items) {
			dbAddForTests(item);
		}
	}

	protected static <T> void dbAddForTests(T... items) {
		for (T item : items) {
			dbAddForTests(item);
		}
	}
}
