package controllers;

import model.User;
import util.AjaxReturn;
import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.extjs.ExtJSJson;
import dao.GenericDAOMongoDB;


/*
 * This is a VRAPTOR REST Controller, It receives requests from view processes them. Each method in this class 
 * constitutes a REST service that can be requested. The annotation @Resouce and the name of the class itself  
 * "Something"Cotrollers tells the framework that this is a REST service. Annotation @GET tells the framework that
 * the method receives only REST GET request, the same happens with annotations @POST, @PUT, among others REST requests.
 * Annotation @Consumes("application/json") tells that the method consumes JSON AJAX requests.    
 */

@Resource
public class UserController {

	// ---------------- attributes
	// ---------------------------------------------------------------

	private GenericDAOMongoDB<User> genericDAOMongoDB;
	private Result result;
	private Validator validator;

	// ---------------- Constructors
	// --------------------------------------------------------------
	// dependency injector will call this constructor and get all dependencies
	public UserController(Result result, Validator validator) {
		super();
		this.genericDAOMongoDB = new GenericDAOMongoDB<User>(User.class);
		this.result = result;
		this.validator = validator;
	}

	// GET /user/list => retrieve a full list (in ExtJSJson JSON format)
	// containing all entities
	@Get
	public void list() {
		try {
			result.use(ExtJSJson.class).from(genericDAOMongoDB.getEntities())
					.success().serialize();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// POST /user/authenticate => verify if an user exists in database
	// receives a request in json format and answers ok if the user exists, if
	// not, answers not logged.
	// Cause happens any exception, answers the cause.
	@Post
	@Consumes("application/json")
	public void login(User user) {

		try {

			User temp = genericDAOMongoDB.getEntityById(user.getEmail());

			if (temp.getPassword().equals(user.getPassword())) {
				result.use(ExtJSJson.class).from("ok").serialize();
			} else {
				result.use(ExtJSJson.class).from("not logged").serialize();
			}

		} catch (Exception ex) {
			result.use(ExtJSJson.class).from("not logged").serialize();
		}
	}

	// POST /user/saveOrUpdate
	// this method receives a POST request containing data in JSON and persists
	// related entity
	// accepted format
	// var data = {
	// user : {
	// "userName" : $scope.vm.username,
	// "plainTextPassword" : $scope.vm.password,
	// "email" : $scope.vm.email
	// }
	// }
	@Post
	@Consumes("application/json")
	public void saveOrUpdate(User user) {
		try {
			genericDAOMongoDB.insertEntity(user);
			result.use(ExtJSJson.class).from("ok").serialize();
		} catch (Exception ex) {
			result.use(ExtJSJson.class)
					.from("Was not possible add new user, try again latter!")
					.serialize();
		}
	}

	// POST /user/delete => this method receives a JSON request containing id
	// as parameter, deleted related entity and
	// answers if the entitiy was deleted successfully.
	// accpted format:
	// var data = {
	// "id" : value
	// }
	@Post
	@Consumes("application/json")
	public void delete(String id) {
		AjaxReturn ajaxReturn = new AjaxReturn();
		try {
			genericDAOMongoDB.deleteEntity(id);
			if (genericDAOMongoDB.getErrors().isEmpty()) {
				ajaxReturn.setSucess(true);
				ajaxReturn.setAlert("Deleted sucessfully!");
				result.use(ExtJSJson.class).from(ajaxReturn).serialize();
			} else {
				ajaxReturn.addErrors(genericDAOMongoDB.getErrors());
				result.use(ExtJSJson.class).from(ajaxReturn).serialize();
			}
		} catch (Exception ex) {
			ajaxReturn.addError(ex.getMessage());
			result.use(ExtJSJson.class).from(ajaxReturn).serialize();
		}
	}
	
	public void test(){
		
		User user = new User();
		user.setEmail("eliasadriano2010@gmail.com");
		user.setPassword("Brm5010225");
		
		genericDAOMongoDB.insertEntity(user);
		
		
		User temp = genericDAOMongoDB.getEntityById("eliasadriano2010@gmail.com");
		
	}
	
	
	
}
