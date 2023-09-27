/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.exemplos;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dados-pessoais-thy")
public class DadosPessoaisThymeleafController {

	@Autowired
	private DadosPessoaisService service;

	@GetMapping
	public ModelAndView findAll() {
		List<DadosPessoais> resultados = service.findAll();
		
		ModelAndView mv = new ModelAndView("exemplo-thymeleaf");
		mv.addObject("resultados", resultados);
		return mv;
	}

	@GetMapping("/busca")
	public ModelAndView buscar(
			@RequestParam("termo") String termoBusca,
			@RequestParam(defaultValue = "1") int pagina,
			@RequestParam(defaultValue = "1") int quantidade
		) {
		List<DadosPessoais> resultados = service.buscar(termoBusca, pagina, quantidade);

		ModelAndView mv = new ModelAndView("exemplo-thymeleaf");
		mv.addObject("resultados", resultados);
		return mv;
	}

}
