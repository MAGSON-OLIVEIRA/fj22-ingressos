package br.com.caelum.ingresso.desconto;

import java.math.BigDecimal;

import br.com.caelum.ingresso.model.interfaces.Desconto;

public class DescontoBanco  implements Desconto {

	@Override
	public BigDecimal calculaDesconto(BigDecimal preco) {
		return preco.subtract(trintaPorCentoSobre(preco));
	}
	
	private	BigDecimal	trintaPorCentoSobre(BigDecimal	precoOriginal) {
		return	precoOriginal.multiply(new	BigDecimal("0.3"));
	}

	@Override
	public String getDescricao() {
		return "Desconto Banco";
	}


}
