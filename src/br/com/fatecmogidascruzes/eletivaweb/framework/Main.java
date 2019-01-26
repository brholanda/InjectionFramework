package br.com.fatecmogidascruzes.eletivaweb.framework;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static Map<String, Object> maptest = new HashMap<>();
	
	public static void main(String[] args) throws InstantiationException {
		TesteFramework teste = new TesteFramework ();
		teste.run();
		
	}
}
