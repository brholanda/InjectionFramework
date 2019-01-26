package br.com.fatecmogidascruzes.eletivaweb.armazenamento;

import br.com.fatecmogidascruzes.eletivaweb.configurations.Inject;

@Inject (variableKey="var", variableValue="PROD")
public class ServicoArmazenamentoS3 implements IServicoArmazenamento {

	@Override
	public void armazenar() {
		System.out.println("############################################################################ ");
		System.out.println("################ Executando Serviço de Armazenamento S3!! ##################");
		System.out.println("############################################################################\n ");
	}

}
