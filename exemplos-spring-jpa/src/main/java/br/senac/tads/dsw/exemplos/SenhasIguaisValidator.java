/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.exemplos;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 *
 * @author ftsuda
 */
public class SenhasIguaisValidator implements ConstraintValidator<SenhasIguais, DadosPessoais> {

	@Override
	public boolean isValid(DadosPessoais dados, ConstraintValidatorContext context) {
		if (dados.getSenha().equals(dados.getSenhaRepetida())) {
			return true;
		}
		return false;
	}

}
