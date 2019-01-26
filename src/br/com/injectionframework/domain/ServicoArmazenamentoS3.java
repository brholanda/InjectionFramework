package br.com.injectionframework.domain;

import br.com.injectionframework.configurations.Inject;

@Inject (variableKey="var", variableValue="PROD")
public class ServicoArmazenamentoS3 implements IServicoArmazenamento {

	@Override
	public void armazenar() {
		System.out.println("############################################################################ ");
		System.out.println("################ Executando Servi�o de Armazenamento S3!! ##################");
		System.out.println("############################################################################\n ");
	}

}
