package com.mantismonitor.controller;

import java.util.HashMap;
import java.util.Map;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Resource
public class TesteController {

	private Result result;
	public TesteController(Result result) {
		this.result = result;
	}
	
	public void teste() {
		Map<String, String> m = new HashMap<String, String>();
		result.include("k1", "2");
		result.include("k2", "3");
		
		result.use(Results.json()).withoutRoot().from(result.included()).serialize();
	}
	
}
