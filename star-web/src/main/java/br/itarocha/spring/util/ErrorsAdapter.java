package br.itarocha.spring.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ErrorsAdapter {

	public static Validador resolve(BindingResult result){
		Validador v = new Validador();
		
		if (result.hasErrors()){
			for(FieldError f: result.getFieldErrors()) {
				v.adicionaErro(new CampoErro(f.getField(),f.getDefaultMessage()));
			}
		}
		return v;

	}
}
