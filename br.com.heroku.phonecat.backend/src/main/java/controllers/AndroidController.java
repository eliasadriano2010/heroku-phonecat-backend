package controllers;

import java.util.ArrayList;

import model.Android;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.extjs.ExtJSJson;

@Resource
public class AndroidController {

	// ---------------- attributes
	// ---------------------------------------------------------------

	//private GenericDAOObjectify<Android> genericDaoObjectify;
	private Result result;
	private Validator validator;

	// ---------------- Constructors
	// --------------------------------------------------------------

	// dependency injector will call this constructor and get all dependencies
	public AndroidController(Result result, Validator validator) {
		super();
		// Generic Dao could not be called using dependency injection because
		// objectify needs register class
		//this.genericDaoObjectify = new GenericDAOObjectify<Android>(Android.class);
		this.result = result;
		this.validator = validator;
	}

	// GET /android/list => retrieve a full list (in ExtJSJson JSON format)
	// containing all entities
	@Get
	public void list() {
		
		ArrayList<Android> androidList = new ArrayList<Android>();
		
		Android android1 = new Android(new Long(1), "os 1" , "ui 1");
		androidList.add(android1);
		Android android2 = new Android(new Long(2), "os 2" , "ui 2");
		androidList.add(android2);
		Android android3 = new Android(new Long(3), "os 3" , "ui 3");
		androidList.add(android3);
		Android android4 = new Android(new Long(4), "os 4" , "ui 4");
		androidList.add(android4);
		Android android5 = new Android(new Long(5), "os 5" , "ui 5");
		androidList.add(android5);
		 
		
		try {
			result.use(ExtJSJson.class).from(androidList).success().serialize();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// POST /discipline/saveOrUpdate => this method receives a POST request
	// containing data in JSON and persists related entity
	// accepted format:

	// var data = {
	// android : {
	// "id" : $scope.vm.id,
	// "os" : $scope.vm.os != undefined ? $scope.vm.os:'',
	// "ui" : $scope.vm.ui != undefined ? $scope.vm.ui : ''
	// }
	// }

//	@Post
//	@Consumes("application/json")
//	public void create(Android android) {
//		AjaxReturn ajaxReturn = new AjaxReturn();
//		try {
//			List<String> errors = validation(android);
//
//			if (errors.isEmpty()) {
//				genericDaoObjectify.insertEntity(android);
//				ajaxReturn.setSucess(errors.isEmpty());
//			} else {
//				ajaxReturn.setSucess(false);
//				ajaxReturn.addErrors(errors);
//			}
//		} catch (Exception ex) {
//			ajaxReturn.setSucess(false);
//			ajaxReturn.addErrors(genericDaoObjectify.getErrors());
//		}
//		result.use(ExtJSJson.class).from(ajaxReturn).serialize();
//	}

	// This private method receives an object performs validation and returns a
	// list containing errors message
//	private List<String> validation(Android android) {
//		List<String> erros = new ArrayList<String>();
//		return erros;
//	}

	// POST /discipline/saveOrUpdate => this method receives a POST request
	// containing data in JSON and persists related entity
	// accepted format:

	// var data = {
	// android : {
	// "id" : $scope.vm.id,
	// "os" : $scope.vm.os != undefined ? $scope.vm.os:'',
	// "ui" : $scope.vm.ui != undefined ? $scope.vm.ui : ''
	// }
	// }

//	@Post
//	@Consumes("application/json")
//	public void update(Android android) {
//		AjaxReturn ajaxReturn = new AjaxReturn();
//		try {
//			List<String> errors = validation(android);
//
//			if (errors.isEmpty()) {
//				genericDaoObjectify.insertEntity(android);
//				ajaxReturn.setSucess(errors.isEmpty());
//			} else {
//				ajaxReturn.setSucess(false);
//				ajaxReturn.addErrors(errors);
//			}
//		} catch (Exception ex) {
//			ajaxReturn.setSucess(false);
//			ajaxReturn.addErrors(genericDaoObjectify.getErrors());
//		}
//		result.use(ExtJSJson.class).from(ajaxReturn).serialize();
//	}

	// POST /android/delete => this method receives a JSON request containing
	// id as parameter, deleted related entity and
	// answers if the entitiy was deleted successfully.
	// accpted format:
	// var data = {
	// "id" : value
	// }
//	@Post
//	@Consumes("application/json")
//	public void delete(Long id) {
//		AjaxReturn ajaxReturn = new AjaxReturn();
//		try {
//			genericDaoObjectify.deleteEntity(id);
//			if (genericDaoObjectify.getErrors().isEmpty()) {
//				ajaxReturn.setSucess(true);
//				ajaxReturn.setAlert("Deleted sucessfully!");
//				result.use(ExtJSJson.class).from(ajaxReturn).serialize();
//			} else {
//				ajaxReturn.addErrors(genericDaoObjectify.getErrors());
//				result.use(ExtJSJson.class).from(ajaxReturn).serialize();
//			}
//		} catch (Exception ex) {
//			ajaxReturn.addError(ex.getMessage());
//			result.use(ExtJSJson.class).from(ajaxReturn).serialize();
//		}

	//}

}
