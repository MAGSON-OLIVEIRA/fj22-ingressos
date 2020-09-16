package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.desconto.DescontoBanco;
import br.com.caelum.ingresso.desconto.DescontoEstudante;
import br.com.caelum.ingresso.desconto.SemDesconto;
import br.com.caelum.ingresso.enums.TipoDeIngresso;

public class DescontoTest {
	
	private Sala	sala;
	private Filme	filme;
	private Sessao	sessao;
	private Lugar lugar;
	
	@Before
	public void carregar() {
		lugar = new Lugar("A", 1);
		sala = new Sala("Eldorado	-	IMAX", new BigDecimal("20.5"));
		filme = new Filme(1, "Rogue	One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12"));
		sessao = new Sessao(LocalTime.parse("10:00:00"), sala, filme);

	}
	
	
	@Test
	public void semDesconto() {
		Ingresso ingresso = new Ingresso( sessao, TipoDeIngresso.INTEIRO, lugar);
		BigDecimal preco = new BigDecimal("32.50");
		Assert.assertEquals(preco,	ingresso.getPreco());
	}
	
	@Test
	public void estudanteDesconto() {
		Ingresso ingresso = new Ingresso( sessao, TipoDeIngresso.ESTUDANTE, lugar);
		BigDecimal preco = new BigDecimal("16.25");
		Assert.assertEquals(preco,	ingresso.getPreco());
	}
	
	
	@Test
	public void bancoDesconto() {
		Ingresso ingresso = new Ingresso( sessao, TipoDeIngresso.BANCO, lugar);
		BigDecimal preco = new BigDecimal("22.75");
		Assert.assertEquals(preco,	ingresso.getPreco());
	}

}
