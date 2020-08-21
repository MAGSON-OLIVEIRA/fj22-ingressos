package br.com.caelum.ingresso.controller;

import java.util.List;

import javax.naming.Binding;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.form.SessaoForm;
import br.com.caelum.ingresso.validation.GerenciadorDeSessao;

@Controller
public class SessaoController {
	
	@Autowired
	private SalaDao salaDao;
	
	@Autowired
	private FilmeDao filmeDao;
	
	@Autowired
	private SessaoDao sessaoDao;
	
	@GetMapping("/admin/sessao")
	public ModelAndView formulario(@RequestParam Integer salaId) {
		ModelAndView mav = new ModelAndView("sessao/sessao");
		mav.addObject("sala", salaDao.findOne(salaId));
		mav.addObject("filmes", filmeDao.findAll());
		mav.addObject("form", new SessaoForm());
		return mav;
	}
	
	@PostMapping("/admin/sessao")
	@Transactional
	public ModelAndView salvar(@Valid SessaoForm form, BindingResult result) {
		if(result.hasErrors()) return form(form.getSalaId(), form);
		Sessao sessao = form.toSessao(salaDao, filmeDao);
		
		if(extracted(sessao)) {
			sessaoDao.save(sessao);
			return  new ModelAndView("redirect:/admin/sala/"+form.getSalaId()+"/sessoes");
		}
		

		return form(form.getSalaId(), form);
	}

	private boolean extracted(Sessao sessao) {
		List<Sessao> asSessoes = sessaoDao.buscaSessoesDaSala(sessao.getSala());
		GerenciadorDeSessao gerenciar = new GerenciadorDeSessao(asSessoes);
		return gerenciar.cabe(sessao);
	}

	//@RequestParam("salaId") 
	private ModelAndView form(Integer salaId, SessaoForm form) {
		ModelAndView mav = new ModelAndView("sessao/sessao");
		mav.addObject("sala", salaDao.findOne(salaId));
		mav.addObject("filmes", filmeDao.findAll());
		mav.addObject("form", form);
		return mav;
	}
	

}
