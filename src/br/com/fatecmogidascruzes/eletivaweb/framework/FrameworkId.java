package br.com.fatecmogidascruzes.eletivaweb.framework;

import br.com.fatecmogidascruzes.eletivaweb.armazenamento.IServicoArmazenamento;
import br.com.fatecmogidascruzes.eletivaweb.armazenamento.ServicoArmazenamentoLocal;
import br.com.fatecmogidascruzes.eletivaweb.armazenamento.ServicoArmazenamentoS3;
import br.com.fatecmogidascruzes.eletivaweb.configurations.AutoInject;

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