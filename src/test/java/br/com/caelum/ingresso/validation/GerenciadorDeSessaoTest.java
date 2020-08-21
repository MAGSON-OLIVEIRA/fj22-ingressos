package br.com.caelum.ingresso.validation;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;


public class GerenciadorDeSessaoTest {
	
	private Filme rogueOne;
	private Sala sala3d;
	private Sessao sessaoDasDez;
	private Sessao sessaoDasTreze;
	private Sessao sessaoDasDezoito;
	
	@Before
	public void prepara() {
		this.rogueOne = new Filme(1,"test", Duration.ofMinutes(120),"",BigDecimal.ONE);
		this.sala3d = new Sala("3d",BigDecimal.ONE);
		this.sessaoDasDez = new Sessao(LocalTime.parse("10:00:00"), this.sala3d, this.rogueOne);
		this.sessaoDasTreze = new Sessao(LocalTime.parse("13:00:00"), this.sala3d, this.rogueOne);
		this.sessaoDasDezoito  = new Sessao(LocalTime.parse("18:00:00"), this.sala3d, this.rogueOne);
	}
	
	@Test
	public void garanteQueNaoDevePermitirSessaoHorario() {
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez);
		GerenciadorDeSessao g = new GerenciadorDeSessao(sessoes);
		Assert.assertFalse(g.cabe(sessaoDasDez));
	}
	
	@Test
	public void garanteQueNaoDevePermitirSessoesTerminandoDentroDoHorarioDeUmaSessaoJaExistente() {
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez);
		Sessao sessao = new Sessao(sessaoDasDez.getHorario().minusHours(1),	sala3d, rogueOne);
		GerenciadorDeSessao g = new GerenciadorDeSessao(sessoes);
		Assert.assertFalse(g.cabe(sessaoDasDez));
	}
	
	@Test
	public void garanteQueNaoDevePermitirSessoesIniciadoDentroDoHorarioDeUmaSessaoJaExistente() {
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez);
		//Sessao sessao = new Sessao(sessaoDasDez.getHorario().minusHours(1),	sala3d, rogueOne);
		GerenciadorDeSessao g = new GerenciadorDeSessao(sessoes);
		Assert.assertFalse(g.cabe(sessaoDasDez));
	}
	
	@Test
	public void garanteQueDevePermitirUmaInsercaoEntreDoisFilmes() {
		List<Sessao> sessoes = Arrays.asList(this.sessaoDasDez, this.sessaoDasDezoito);
		GerenciadorDeSessao g = new GerenciadorDeSessao(sessoes);
		Assert.assertTrue(g.cabe(sessaoDasTreze));
		
	}
	
	@Test
	public void garanteQueDeveNaoPermitirUmaSessaoQueTerminaNoProximoDia() {
		List<Sessao> sessoes = Collections.emptyList();
		GerenciadorDeSessao g = new GerenciadorDeSessao(sessoes);
		Sessao sessaoQueTerminaAmanha = new Sessao(LocalTime.parse("23:00:00"), this.sala3d, this.rogueOne);
		Assert.assertFalse(g.cabe(sessaoQueTerminaAmanha));
	}
	


}
