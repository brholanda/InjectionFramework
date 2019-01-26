package br.com.fatecmogidascruzes.eletivaweb.armazenamento;

import br.com.fatecmogidascruzes.eletivaweb.configurations.Inject;

@Inject (variableKey="var", variableValue="DEV")
public class ServicoArmazenamentoLocal implements IServicoArmazenamento {

	@Override
	public void armazenar() {
		System.out.println("############################################################################ ");
		System.out.println("############## Executando Serviço de Armazenamento Local!!! ################");
		System.out.println("############################################################################\n ");
	}

}
