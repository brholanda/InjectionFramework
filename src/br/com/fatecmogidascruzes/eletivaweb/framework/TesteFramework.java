package br.com.fatecmogidascruzes.eletivaweb.framework;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import br.com.fatecmogidascruzes.eletivaweb.configurations.AutoInject;
import br.com.fatecmogidascruzes.eletivaweb.configurations.Inject;
import br.com.fatecmogidascruzes.eletivaweb.util.ClassSearcher;

public class TesteFramework {

	private final Map<String, Object> mappedInjectResources = new HashMap<>();
	private final Map<String, Object> mappedAutoInjectResources = new HashMap<>();
	private final Map<String, Map<String, Object>> mappedInterfaces = new HashMap<>();

	private final String spliter = "\\.";
	private final String variableKey = "var";

	public TesteFramework() {
		System.setProperty(variableKey, "DEV");
		this.findResources();
	}

	public void run() {

		FrameworkId frameWorkId = (FrameworkId) mappedAutoInjectResources.get(FrameworkId.class.getName());
		frameWorkId.start();
	}

	private void findResources() {
		try {
			String[] parts = this.getClass().getPackage().getName().split(spliter);
			Class<?>[] classes = ClassSearcher.getClasses(parts[0]);
			getInjectClasses(classes);
			getAnnotadedClasses(classes);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	private void getInjectClasses(Class<?>[] classes) {
		Inject inject = null;
		try {
			for (Class<?> clazz : classes) {
				if (null != (inject = clazz.getDeclaredAnnotation(Inject.class))) {
					Object obj = null;
					obj = Class.forName(clazz.getName()).newInstance();
					Class<?>[] interfaces = clazz.getInterfaces();
					if (interfaces.length != 0) {
						for (Class<?> iFace : interfaces) {
							if (!mappedInterfaces.containsKey(iFace.getName())) {
								mappedInterfaces.put(iFace.getName(), new HashMap<String, Object>());
							}
							if (inject.variableValue().equals("")) {
								mappedInterfaces.get(iFace.getName()).put(clazz.getName(), obj);
							} else {
								mappedInterfaces.get(iFace.getName()).put(inject.variableValue(), obj);
							}
						}
					} 
					mappedInjectResources.put(clazz.getName(), obj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getAnnotadedClasses(Class<?>[] classes) {
		System.out.println("\n===================================================================================================\n");
		AutoInject autoInject = null;
		try {
			for (Class<?> clazz : classes) {

				Object objc = null;

				for (Field field : clazz.getDeclaredFields()) {
					if (null != (autoInject = (AutoInject) field.getDeclaredAnnotation(AutoInject.class))) {
						if (objc == null) {
							objc = Class.forName(clazz.getName()).newInstance();
						}
						if (field.getType().isInterface()) {
							if ( null != System.getProperty(variableKey) ) {
								field.set(objc, mappedInterfaces.get(field.getType().getName()).get(System.getProperty(variableKey)));
							} else if (!autoInject.qualifier().trim().equals("")) {
								field.set(objc, mappedInterfaces.get(field.getType().getName()).get(autoInject.qualifier()));
							} 
						} else {
							field.set(objc, mappedInjectResources.get(field.getType().getName()));
						}

						mappedAutoInjectResources.put(clazz.getName(), objc);
						System.out.println("####### Injected Field -> " + clazz.getSimpleName() + "." + field.getName());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		System.out.println("\n===================================================================================================\n");
	}

}
