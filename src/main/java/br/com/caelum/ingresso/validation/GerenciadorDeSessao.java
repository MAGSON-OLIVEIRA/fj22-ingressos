package br.com.caelum.ingresso.validation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessao {
	
	
	private List<Sessao> sessoesDaSala;
	
	public GerenciadorDeSessao(List<Sessao> sessoesDaSala) {
		this.sessoesDaSala = sessoesDaSala;
	}
	
	public boolean cabe(Sessao sessaoNovoa) {
		if (terminaAmanha(sessaoNovoa)) {
			return false;
		}

//		for (Sessao sessao : sessoesDaSala) {
//			if (horarioIsConflitante(sessao, sessaoNovoa))
//				return false;
//		}
		return sessoesDaSala.stream().noneMatch(sessaoExisten -> horarioIsConflitante(sessaoExisten, sessaoNovoa));
		
		
	}
	
	
	private boolean horarioIsConflitante(Sessao sessao, Sessao sessaoNova) {
		LocalDateTime inicioSessaoExitente = getInicioSessaoComDiaDeHoje(sessao);
		LocalDateTime terminoSessaoExitente = getTerminoSessaoComDiaDeHoje(sessao);

		LocalDateTime inicioSessaoNova = getInicioSessaoComDiaDeHoje(sessaoNova);
		LocalDateTime terminoSessaoNova = getTerminoSessaoComDiaDeHoje(sessaoNova);

		boolean sessaoNovaTerminaAntesDaExistente = terminoSessaoNova.isBefore(inicioSessaoExitente);
		boolean sessaoNovaComecaDepoisDaExiste = terminoSessaoExitente.isBefore(inicioSessaoNova);

		if (sessaoNovaTerminaAntesDaExistente || sessaoNovaComecaDepoisDaExiste) {
			return false;
		}

		return true;
	}

	private LocalDateTime getInicioSessaoComDiaDeHoje(Sessao sessao) {
		LocalDate hoje = LocalDate.now();
		return sessao.getHorario().atDate(hoje);
	}
	
	
	
	private boolean terminaAmanha(Sessao sessao) {
		LocalDateTime terminoSessaoNova = getTerminoSessaoComDiaDeHoje(sessao);
		LocalDateTime ultimoSegundoDeHoje = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
		
		if(terminoSessaoNova.isAfter(ultimoSegundoDeHoje)) {
			return true;
		}
		
		return false;
	}

	private LocalDateTime getTerminoSessaoComDiaDeHoje(Sessao sessao) {
		LocalDateTime inicioSessaoNova = getInicioSessaoComDiaDeHoje(sessao);
		return inicioSessaoNova.plus(sessao.getFilme().getDuracao());
	}
	
	

}
