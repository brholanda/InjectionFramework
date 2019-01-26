package br.com.injectionframework.framework;

import br.com.injectionframework.configurations.AutoInject;
import br.com.injectionframework.domain.IServicoArmazenamento;
import br.com.injectionframework.domain.ServicoArmazenamentoLocal;
import br.com.injectionframework.domain.ServicoArmazenamentoS3;

public class FrameworkId {

	@AutoInject IServicoArmazenamento armazenamentoGenerico;
	@AutoInject ServicoArmazenamentoLocal armazenamentoLocal;
	@AutoInject ServicoArmazenamentoS3 armazenamentoS3;
	public void start () {

		armazenamentoGenerico.armazenar();
		armazenamentoLocal.armazenar();
		armazenamentoS3.armazenar();
	}
	
}