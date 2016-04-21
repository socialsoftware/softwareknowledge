package pt.ist.socialsoftware.softwareknowledge.jena;

public class JenaInterface {

	static private JenaInterface instance = null;

	static public JenaInterface getInstance() {
		if (instance == null) {
			instance = new JenaInterface();
		}
		return instance;
	}

	private void JenaInterface() {

	}

	public void addSource() {

	}

}
