package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

public class SessaoTest {
	
	@Test
	public void oPrecoSessaoDeveSerIgualPrecoDaSalaMaisOprecoDoFilme()
	{
		Sala sala = new Sala("Test - imax", new BigDecimal("22.5"));
		Filme filme = new Filme(1, "Tes filme", Duration.ofMinutes(120), "", new BigDecimal("12"));
		
		BigDecimal soma = sala.getPreco().add(filme.getPreco());
		Sessao	sessao	=	new	Sessao(LocalTime.parse("10:00:00"),	sala, filme);
		Assert.assertEquals(soma,	sessao.getPreco());
	}

}
