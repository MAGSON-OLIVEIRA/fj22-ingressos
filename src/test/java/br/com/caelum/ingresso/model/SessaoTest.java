package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.enums.TipoDeIngresso;

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
	
	@Test
	public void garanteQueLugarA1EstaOcupado() {
		Lugar a1 = new Lugar("A", 1);
		Lugar a2 = new Lugar("A", 2);
		Lugar a3 = new Lugar("A", 3);
		
		Filme rogueOne = new Filme(1, "Test", Duration.ofMinutes(120), "", new BigDecimal("12.0"));
		
		Sala salaTest1 = new Sala("SalaTes", new BigDecimal("8.5"));
		Sessao	sessao	=	new	Sessao(LocalTime.parse("10:00:00"),	salaTest1, rogueOne);
		
		Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.INTEIRO, a1);
		
		Set<Ingresso> ingressos = Stream.of(ingresso).collect(Collectors.toSet());
		
		sessao.setIngressos(ingressos);
		Assert.assertFalse(sessao.isDisponivel(a1));
		Assert.assertTrue(sessao.isDisponivel(a2));
		Assert.assertTrue(sessao.isDisponivel(a3));
		
		
	}

}
