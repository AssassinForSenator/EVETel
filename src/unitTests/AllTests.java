package unitTests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import api.KillBoard;

public class AllTests {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testGetKillMailsFromFile() {
		KillBoard.getKillMailsFromFile("exoapi.xml");
	}

}
