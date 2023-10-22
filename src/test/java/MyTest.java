import org.junit.jupiter.api.BeforeAll;

class MyTest {

	static BaccaratDealer bd = new BaccaratDealer();

	@BeforeAll
	static void setup() {
		bd.generateDeck();
	}

	// @Test
	// void test() {
	// bd.print();
	// fail("Not yet implemented");
	// }

}
