package br.com.caelum.ingresso.desconto;

import java.math.BigDecimal;

import br.com.caelum.ingresso.model.interfaces.Desconto;

public class SemDesconto implements Desconto  {

	@Override
	public BigDecimal calculaDesconto(BigDecimal preco) {
		return preco;
	}

	@Override
	public String getDescricao() {
		return "Normal";
	}

}
