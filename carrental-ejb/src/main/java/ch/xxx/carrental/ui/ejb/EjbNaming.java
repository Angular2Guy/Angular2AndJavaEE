package ch.xxx.carrental.ui.ejb;

public enum EjbNaming {
	JBOSS("org.jboss.as.naming.interfaces:org.jboss.ejb.client.naming");
	
	private final String key;
	
	EjbNaming(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}	
}
