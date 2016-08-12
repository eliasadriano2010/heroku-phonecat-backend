package controllers;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import model.Storage;
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
public class StorageController {

	// ---------------- attributes
	// ---------------------------------------------------------------

	private GenericDAOMongoDB<Storage> genericDAOMongoDB;
	private Result result;
	private Validator validator;

	// ---------------- Constructors
	// --------------------------------------------------------------

	// dependency injector will call this constructor and get all dependencies
	public StorageController(Result result, Validator validator) {
		super();
		// Generic Dao could not be called using dependency injection because
		// objectify needs register class
		this.genericDAOMongoDB = new GenericDAOMongoDB<Storage>(
				Storage.class);
		this.result = result;
		this.validator = validator;
	}

	// GET /storage/list => retrieve a full list (in ExtJSJson JSON format)
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

	// POST /discipline/saveOrUpdate => this method receives a POST request
	// containing data in JSON and persists related entity
	// accepted format:

	// var data = {
	// storage : {
	// "id" : $scope.vm.id,
	// "os" : $scope.vm.os != undefined ? $scope.vm.os:'',
	// "ui" : $scope.vm.ui != undefined ? $scope.vm.ui : ''
	// }
	// }

	@Post
	@Consumes("application/json")
	public void create(Storage storage) {
		AjaxReturn ajaxReturn = new AjaxReturn();
		try {
			List<String> errors = validation(storage);

			if (errors.isEmpty()) {
				if (storage.getId() == null) {
					storage.setId(ObjectId.get().toString());
					genericDAOMongoDB.insertEntity(storage);
				} else {
					genericDAOMongoDB.updateEntity(storage, storage.getId());
				}
			} else {
				ajaxReturn.setSucess(false);
				ajaxReturn.addErrors(errors);
			}
		} catch (Exception ex) {
			ajaxReturn.setSucess(false);
			ajaxReturn.addErrors(genericDAOMongoDB.getErrors());
		}
		result.use(ExtJSJson.class).from(ajaxReturn).serialize();
	}

	// This private method receives an object performs validation and returns a
	// list containing errors message
	private List<String> validation(Storage storage) {
		List<String> erros = new ArrayList<String>();
		return erros;
	}

	// POST /discipline/saveOrUpdate => this method receives a POST request
	// containing data in JSON and persists related entity
	// accepted format:

	// var data = {
	// storage : {
	// "id" : $scope.vm.id,
	// "os" : $scope.vm.os != undefined ? $scope.vm.os:'',
	// "ui" : $scope.vm.ui != undefined ? $scope.vm.ui : ''
	// }
	// }

	@Post
	@Consumes("application/json")
	public void update(Storage storage) {
		AjaxReturn ajaxReturn = new AjaxReturn();
		try {
			List<String> errors = validation(storage);

			if (errors.isEmpty()) {
				if (storage.getId() == null) {
					storage.setId(ObjectId.get().toString());
					genericDAOMongoDB.insertEntity(storage);
				} else {
					genericDAOMongoDB.updateEntity(storage, storage.getId());
				}
				ajaxReturn.setSucess(errors.isEmpty());
			} else {
				ajaxReturn.setSucess(false);
				ajaxReturn.addErrors(errors);
			}
		} catch (Exception ex) {
			ajaxReturn.setSucess(false);
			ajaxReturn.addErrors(genericDAOMongoDB.getErrors());
		}
		result.use(ExtJSJson.class).from(ajaxReturn).serialize();
	}

	// POST /storage/delete => this method receives a JSON request containing
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

}
