package br.com.injectionframework.domain;

import br.com.injectionframework.configurations.Inject;

@Inject (variableKey="var", variableValue="DEV")
public class ServicoArmazenamentoLocal implements IServicoArmazenamento {

	@Override
	public void armazenar() {
		System.out.println("############################################################################ ");
		System.out.println("############## Executando Servi�o de Armazenamento Local!!! ################");
		System.out.println("############################################################################\n ");
	}

}
