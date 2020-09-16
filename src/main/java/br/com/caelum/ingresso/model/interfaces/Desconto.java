package br.com.caelum.ingresso.model.interfaces;

import java.math.BigDecimal;

public interface Desconto {
	
	BigDecimal calculaDesconto(BigDecimal preco);
	String getDescricao();

}
