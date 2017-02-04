package dk.ninjabear.rssscanner.test;

import dk.ninjabear.rssscanner.storage.Storage;

public class StorageTest {
	public static void initStorage() {
		Storage.storeUrl("http://jp.dk/indland/?service=rssfeed");
		Storage.storeUrl("http://jp.dk/nyviden/?service=rssfeed");
		Storage.storeUrl("http://jp.dk/kultur/?service=rssfeed");

		Storage.storeKeyWord("vegan");
		Storage.storeKeyWord("vegansk");
		Storage.storeKeyWord("veganer");
		Storage.storeKeyWord("kødfri");
		Storage.storeKeyWord("anholdt");
	}
}
