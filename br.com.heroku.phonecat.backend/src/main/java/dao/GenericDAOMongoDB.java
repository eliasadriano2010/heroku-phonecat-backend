package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.management.Query;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;

import br.com.caelum.vraptor.ioc.Component;

/**
 * Generic DAO for CRUD Operations
 *
 * @param <T>
 *            must be an Entity Objectify
 */
@Component
public class GenericDAOMongoDB<T> {

	private Class<T> typeParameterClass;
	private List<String> errors = new ArrayList<String>();
	private MongoDatabaseSettings mongoDatabaseSettings;

	ObjectMapper objectMapper;

	protected GenericDAOMongoDB() {
		mongoDatabaseSettings = new MongoDatabaseSettings();
		objectMapper = new ObjectMapper();
	}

	public GenericDAOMongoDB(Class<T> typeParameterClass) {
		this.typeParameterClass = typeParameterClass;
		mongoDatabaseSettings = new MongoDatabaseSettings();
		mongoDatabaseSettings.setCollection(this.typeParameterClass
				.getSimpleName());
	}

	/**
	 * return the entire list of Entity in json format
	 */
	public List<T> getEntities() {
		try {
			return MongoDBManagement.retrieveAllDocuments(
					mongoDatabaseSettings.getCollection(),
					this.typeParameterClass);
		} catch (Exception ex) {
			errors.add("An error has ocurred in method getEntities(): "
					+ ex.getCause());
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * return the entire list of Entity
	 */
	// public List<T> getEntities(Map<String, Object> keyValues) {
	// try {
	// final LoadType<T> type = ofy().load().type(this.typeParameterClass);
	//
	// Query<T> currentQuery = type;
	//
	// for (Map.Entry<String, Object> entry : keyValues.entrySet()) {
	// currentQuery = currentQuery.filter(entry.getKey(),
	// entry.getValue());
	// }
	// return currentQuery.list();
	// } catch (Exception ex) {
	// errors.add("An error has ocurred in method getEntities(Map<String, Object> keyValues): "
	// + ex.getCause());
	// ex.printStackTrace();
	// }
	// return null;
	// }

	/**
	 * return the entire list of Entity
	 */
	// public List<T> getEntitiesSortedByLimited(Map<String, Object> keyValues,
	// String sortParam, int limit) {
	// try {
	// final LoadType<T> type = ofy().load().type(this.typeParameterClass);
	//
	// Query<T> currentQuery = type;
	// for (Map.Entry<String, Object> entry : keyValues.entrySet()) {
	// currentQuery = currentQuery.filter(entry.getKey(),
	// entry.getValue());
	// }
	// // Sort
	// currentQuery = currentQuery.order(sortParam);
	// // Limit
	// currentQuery = currentQuery.limit(limit);
	//
	// return currentQuery.list();
	// } catch (Exception ex) {
	// errors.add("An error has ocurred in method getEntitiesSortedByLimited(Map<String, Object> keyValues, String sortParam, int limit): "
	// + ex.getCause());
	// ex.printStackTrace();
	// }
	// return null;
	//
	// }

	/**
	 * return the entity filtered by one criteria
	 */
	// public T getEntity(String column, String value) {
	// try {
	// return ofy().load().type(this.typeParameterClass)
	// .filter(column, value).first().now();
	// } catch (Exception ex) {
	// errors.add("An error has ocurred in method getEntity(String column, String value): "
	// + ex.getCause());
	// ex.printStackTrace();
	// }
	// return null;
	// }

	/**
	 * return list of entities filtered by one criteria
	 */
	// public List<T> getEntities(String column, String value) {
	// try {
	// List<T> listTemp = ofy().load().type(this.typeParameterClass)
	// .filter(column + " >=", value)
	// .filter(column + " <=", value + "\\ufffd").list();
	//
	// return listTemp;
	// } catch (Exception ex) {
	// errors.add("An error has ocurred in method getEntity(String column, String value): "
	// + ex.getCause());
	// ex.printStackTrace();
	// }
	// return null;
	// }

	// /**
	// * return the entity filtered by a list of criteria
	// */
	// public T getEntity(Map<String, Object> keyValues) {
	// try {
	// final LoadType<T> type = ofy()
	// .consistency(ReadPolicy.Consistency.STRONG).load()
	// .type(this.typeParameterClass);
	// Query<T> currentQuery = type;
	// for (Map.Entry<String, Object> entry : keyValues.entrySet()) {
	// currentQuery = currentQuery.filter(entry.getKey(),
	// entry.getValue());
	// }
	//
	// return currentQuery.first().now();
	// } catch (Exception ex) {
	// errors.add("An error has ocurred in method getEntity(Map<String, Object> keyValues): "
	// + ex.getCause());
	// ex.printStackTrace();
	// }
	// return null;
	// }

	/**
	 * return the entire list of Entity
	 */
	// public T getEntityById(String id) {
	// try {
	// return ofy().load().type(this.typeParameterClass).id(id).safe();
	// } catch (Exception ex) {
	// errors.add("An error has ocurred in method getEntityById(long id): "
	// + ex.getCause());
	//
	// ex.printStackTrace();
	// }
	//
	// return null;
	// }

	/**
	 * Insert one Entity Synchronous insert to get the ID
	 *
	 * @param object
	 *            entity to save
	 */
	public void insertEntity(T object) {
		try {
			MongoDBManagement.insertIntoCollection(
					mongoDatabaseSettings.getCollection(), object);
		} catch (Exception ex) {
			errors.add("An error has ocurred in method insertEntity(T object): "
					+ ex.getCause());
			ex.printStackTrace();
		}
	}

	/**
	 * Insert one Entity Synchronous insert to get the ID
	 *
	 * @param object
	 *            entity to save
	 */
	public void updateEntity(T object, String id) {
		try {
			MongoDBManagement.saveOrUpdate(
					mongoDatabaseSettings.getCollection(), object, id);
		} catch (Exception ex) {
			errors.add("An error has ocurred in method insertEntity(T object): "
					+ ex.getCause());
			ex.printStackTrace();
		}
	}

	/**
	 * Delete one entity
	 *
	 * @param id
	 *            the id Entity to delete
	 */
	public void deleteEntity(String id) {
		try {
				MongoDBManagement.deleteOneFromCollection(mongoDatabaseSettings.getCollection(), id);
		} catch (Exception ex) {
			errors.add("An error has ocurred in method insertEntities(List<T> objects): "
					+ ex.getCause());
			ex.printStackTrace();
		}

	}

	/**
	 * Delete one entity
	 *
	 * @param entities
	 *            the entities to delete
	 */
	// public void deleteEntities(List<T> entities) {
	// try {
	// ofy().delete().entities(entities);
	// } catch (Exception ex) {
	// errors.add("An error has ocurred in method insertEntities(List<T> objects): "
	// + ex.getCause());
	// ex.printStackTrace();
	// }
	//
	// }

	public Class<T> getTypeParameterClass() {
		return typeParameterClass;
	}

	public void setTypeParameterClass(Class<T> typeParameterClass) {
		this.typeParameterClass = typeParameterClass;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}