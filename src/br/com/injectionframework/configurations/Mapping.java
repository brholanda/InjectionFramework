package br.com.injectionframework.configurations;

public class Mapping {
	private String path;
	private Class<?> clazz;
	
	public Mapping(String path, Class<?> clazz) {
		super();
		this.path = path;
		this.clazz = clazz;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public Class<?> getClazz() {
		return clazz;
	}
	
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}
}
