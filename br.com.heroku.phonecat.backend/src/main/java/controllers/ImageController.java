package controllers;

import java.util.List;

import model.Gadget;
import model.Image;
import util.AjaxReturn;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.extjs.ExtJSJson;
import dao.GenericDAOMongoDB;

@Resource
public class ImageController {

	// ---------------- attributes
	// ---------------------------------------------------------------
	private GenericDAOMongoDB<Gadget> genericDAOMongoDB;
	private Result result;
	private Validator validator;

	// ---------------- Constructors
	// --------------------------------------------------------------

	// dependency injector will call this constructor and get all dependencies
	public ImageController(Result result, Validator validator) {
		super();
		// Generic Dao could not be called using dependency injection because
		// objectify needs register class
		this.genericDAOMongoDB = new GenericDAOMongoDB<Gadget>(
				Gadget.class);
		this.result = result;
		this.validator = validator;
	}

	
	
	
	

}
