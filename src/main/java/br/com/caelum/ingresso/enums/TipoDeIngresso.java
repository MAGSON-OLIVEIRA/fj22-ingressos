package br.com.caelum.ingresso.enums;

import java.math.BigDecimal;

import br.com.caelum.ingresso.desconto.DescontoBanco;
import br.com.caelum.ingresso.desconto.DescontoEstudante;
import br.com.caelum.ingresso.desconto.SemDesconto;
import br.com.caelum.ingresso.model.interfaces.Desconto;

public enum TipoDeIngresso {
	
	INTEIRO(new SemDesconto()),
	ESTUDANTE(new DescontoEstudante()),
	BANCO(new DescontoBanco());
	
	private final Desconto desconto;
	
	TipoDeIngresso(Desconto desconto){
		this.desconto = desconto;
	}
	
	public BigDecimal aplicarDesconto(BigDecimal valor) {
		return desconto.calculaDesconto(valor);
	}
	
	public String getDescricao() {
		return desconto.getDescricao();
	}
	

}
