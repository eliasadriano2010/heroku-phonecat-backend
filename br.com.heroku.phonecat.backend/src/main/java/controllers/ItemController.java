package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Item;

import org.bson.types.ObjectId;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import util.AjaxReturn;
import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.extjs.ExtJSJson;
import dao.GenericDAOMongoDB;

@Resource
public class ItemController {

	// ---------------- attributes
	// ---------------------------------------------------------------

	private GenericDAOMongoDB<Item> genericDaoMongoDB;
	private Result result;
	private Validator validator;

	// ---------------- Constructors
	// --------------------------------------------------------------

	// dependency injector will call this constructor and get all dependencies
	public ItemController(Result result, Validator validator) {
		super();
		// Generic Dao could not be called using dependency injection because
		// objectify needs register class
		this.genericDaoMongoDB = new GenericDAOMongoDB<Item>(Item.class);
		this.result = result;
		this.validator = validator;
	}

	// GET /android/list => retrieve a full list (in ExtJSJson JSON format)
	// containing all entities
	@Get
	public void list() throws JsonParseException, JsonMappingException,
			IOException {
		try {
			result.use(ExtJSJson.class).from(genericDaoMongoDB.getEntities())
					.success().serialize();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void create() {
		final Item item1 = new Item();
		item1.setId(ObjectId.get().toString());
		item1.setItemId("ITEM2");
		item1.setDescription("Mr. Coffee BVMC-PSTX91 Optimal Brew");
		item1.setManufacturer("Mr. Coffee");
		item1.setDepartment("kitchen");
		item1.setCategory("Coffee Machines");
		item1.setSubCategory("Thermal Carafe");
		item1.setListPrice(89.99);
		item1.setPrice(69.00);
		item1.setQuantity(3);

		genericDaoMongoDB.insertEntity(item1);

	}

	public void update() {
		final Item item1 = new Item();
		item1.setId("57abe9a7a0d54a0ce6ad1876");
		item1.setItemId("ITEM3UPDATED");
		item1.setDescription("Creme Dental Colgate");
		item1.setManufacturer("Colgate");
		item1.setDepartment("DC");
		item1.setCategory("Cleaners");
		item1.setSubCategory("Using");
		item1.setListPrice(89.99);
		item1.setPrice(69.00);
		item1.setQuantity(3);

		genericDaoMongoDB.updateEntity(item1, item1.getId());

	}

	public void delete() {

		genericDaoMongoDB.deleteEntity("57abea4ea0d54a0ce826a9e1");

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

	@Post
	@Consumes("application/json")
	public void create(Item item) {
		AjaxReturn ajaxReturn = new AjaxReturn();
		try {
			List<String> errors = validation(item);

			if (errors.isEmpty()) {
				genericDaoMongoDB.insertEntity(item);
				ajaxReturn.setSucess(errors.isEmpty());
			} else {
				ajaxReturn.setSucess(false);
				ajaxReturn.addErrors(errors);
			}
		} catch (Exception ex) {
			ajaxReturn.setSucess(false);
			ajaxReturn.addErrors(genericDaoMongoDB.getErrors());
		}
		result.use(ExtJSJson.class).from(ajaxReturn).serialize();
	}

	// This private method receives an object performs validation and returns a
	// list containing errors message
	private List<String> validation(Item item) {
		List<String> erros = new ArrayList<String>();
		return erros;
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

	@Post
	@Consumes("application/json")
	public void update(Item item) {
		AjaxReturn ajaxReturn = new AjaxReturn();
		try {
			List<String> errors = validation(item);

			if (errors.isEmpty()) {
				genericDaoMongoDB.updateEntity(item, item.getId());
				ajaxReturn.setSucess(errors.isEmpty());
			} else {
				ajaxReturn.setSucess(false);
				ajaxReturn.addErrors(errors);
			}
		} catch (Exception ex) {
			ajaxReturn.setSucess(false);
			ajaxReturn.addErrors(genericDaoMongoDB.getErrors());
		}
		result.use(ExtJSJson.class).from(ajaxReturn).serialize();
	}

	// POST /android/delete => this method receives a JSON request containing
	// id as parameter, deleted related entity and
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
			genericDaoMongoDB.deleteEntity(id);
			if (genericDaoMongoDB.getErrors().isEmpty()) {
				ajaxReturn.setSucess(true);
				ajaxReturn.setAlert("Deleted sucessfully!");
				result.use(ExtJSJson.class).from(ajaxReturn).serialize();
			} else {
				ajaxReturn.addErrors(genericDaoMongoDB.getErrors());
				result.use(ExtJSJson.class).from(ajaxReturn).serialize();
			}
		} catch (Exception ex) {
			ajaxReturn.addError(ex.getMessage());
			result.use(ExtJSJson.class).from(ajaxReturn).serialize();
		}

	}

	// test a seguir

}
