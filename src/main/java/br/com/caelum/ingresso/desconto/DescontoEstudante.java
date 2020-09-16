package br.com.caelum.ingresso.desconto;

import java.math.BigDecimal;

import br.com.caelum.ingresso.model.interfaces.Desconto;

public class DescontoEstudante implements Desconto  {

	@Override
	public BigDecimal calculaDesconto(BigDecimal preco) {
		return	preco.divide(new	BigDecimal("2.0"));

	}

	@Override
	public String getDescricao() {
		return "Desconto Estudante";
	}

}
