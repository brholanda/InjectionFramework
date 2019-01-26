package br.com.injectionframework.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ClassSearcher {
	
	public final static String dot = ".";
	public final static String slash = "/";
	
	public static Class<?>[] getClasses ( String packageName ) throws ClassNotFoundException, IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String packagePath = packageName.replace(dot, slash);
		Enumeration <URL> resources = classLoader.getResources( packagePath );
		
		List<File> folders = new ArrayList<>();
		
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			folders.add(new File(resource.getFile() ) );
		}
		
		ArrayList<Class<?>> classes = new ArrayList<>();
		for (File folder : folders) {
			classes.addAll(findClasses (folder, packageName ) );
		}
		
		return classes.toArray(new Class<?>[classes.size()]);
	}
	
	public static List<Class<?>> findClasses (File folder, String packageName ) throws ClassNotFoundException {
		
		List<Class<?>> classes = new ArrayList<>();
		if (!folder.exists())
			return classes;
		File [] files = folder.listFiles();
		
		for (File file : files) {
			if ( file.isDirectory() ) {
				classes.addAll( findClasses (file, packageName + '.' + file.getName() ) );
			} else if ( file.getName().endsWith("class") ) {
				System.out.println("####### Mapped Class -> " + packageName + "." + file.getName() );
				classes.add( Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
			}
		}
		
		return classes;
	}

}
