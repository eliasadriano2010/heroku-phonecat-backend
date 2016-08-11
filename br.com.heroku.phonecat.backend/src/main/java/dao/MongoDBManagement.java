package dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Item;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class MongoDBManagement {

	public static boolean insertIntoCollection(
			final MongoCollection<Document> collection, final Object object) {
		// Use Jackson to convert Object to JSON String
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString;
		boolean status = true;

		try {
			jsonString = objectMapper.writeValueAsString(object);
			// Insert JSON into MongoDB
			System.out.println("Object:" + jsonString);
			Document doc = Document.parse(jsonString);
			collection.insertOne(doc);
		} catch (MongoWriteException mwe) {
			status = false;
		} catch (IOException e) {
			status = false;
		}
		return status;
	}// end insert

	// The MongoDB Upsert Operation is a special type of update which uses the
	// selection criteria for the update.
	// Should the document be found it will perform the update as expected,
	// however, if the document is not found, it will insert a new document into
	// the collection.
	// This makes your code much cleaner and easier to maintain.
	public static boolean saveOrUpdate(final MongoCollection<Document> collection, final Object object,
			String id) {
		boolean status = true;
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString;

		try {
			jsonString = objectMapper.writeValueAsString(object);
			// update/upsert using JSON into MongoDB
			System.out.println("Update Upset Object:" + jsonString);
			BasicDBObject query = new BasicDBObject();
			query.append("_id", id);
			BasicDBObject doc = BasicDBObject.parse(jsonString);
			Bson newDocument = new Document("$set", doc);
			UpdateResult result = collection.updateOne(query, newDocument,
					(new UpdateOptions()).upsert(true));
			System.out.println("Update Matched Count....: "
					+ result.getMatchedCount());
			System.out.println("Update Modified Count...: "
					+ result.getModifiedCount());
		} catch (IOException e) {
			status = false;
		}
		return status;
	}

	// ReplaceOne works by replacing the entire document with the current one
	// being passed into the method with the exception of the _id field. What it
	// means is that the document can have an entire different set of fields
	// associated with it, or in the case of our example, we will only have the
	// fields we set populated and all other fields will be left unset (set to
	// their default values of empty or zero).
	public static boolean replaceUpsertCollection(
			final MongoCollection<Document> collection, final Object object,
			ObjectId id) {

		boolean status = true;
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString;

		try {
			jsonString = objectMapper.writeValueAsString(object);
			// Insert JSON into MongoDB
			System.out.println("Update replace upser object:" + jsonString);
			BasicDBObject query = new BasicDBObject();
			query.append("_id", id);
			Document doc = Document.parse(jsonString);
			UpdateResult result = collection.replaceOne(query, doc,
					(new UpdateOptions()).upsert(true));
			System.out.println("Replace Matched Count....: "
					+ result.getMatchedCount());
			System.out.println("Replace Modified Count...: "
					+ result.getModifiedCount());
		} catch (MongoWriteException mwe) {
			status = false;
		} catch (IOException e) {
			status = false;
		}

		return status;
	}

	// works by performing the querying and update as one atomic operation in
	// MongoDB. Depending on the FindOneAndUpdateOptions() that are set. In my
	// example, I have turned on the upsert flag to true. The findOneAndUpdate
	// operation will update the first matching document in the collection that
	// matches the query filter.
	public static Document findAndUpdateCollection(
			final MongoCollection<Document> collection, final Object object,
			ObjectId id) {
		ObjectMapper objectMapper = new ObjectMapper();
		Document resultDocument = null;
		String jsonString;

		try {
			// findOneAndUpdate using JSON into MongoDB
			jsonString = objectMapper.writeValueAsString(object);
			System.out.println("findAndUpdateCollection:" + jsonString);
			BasicDBObject query = new BasicDBObject();
			query.append("_id", id);
			BasicDBObject doc = BasicDBObject.parse(jsonString);
			Bson newDocument = new Document("$set", doc);
			resultDocument = collection.findOneAndUpdate(query, newDocument,
					(new FindOneAndUpdateOptions()).upsert(true));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resultDocument;
	}

	public static Document findOneAndDeleteCollection(
			final MongoCollection<Document> collection, final ObjectId id) {

		Document resultDocument = null;

		// findOneAndDelete from MongoDB
		System.out.println("Using findOneAndDeleteCollection to delete ID: "
				+ id);
		BasicDBObject query = new BasicDBObject();
		query.append("_id", id);
		resultDocument = collection.findOneAndDelete(query);

		return resultDocument;
	}

	// *********************************** Consultas
	// *****************************//

	public static Document retrieveDocumentByID(
			final MongoCollection<Document> collection, final String id) {
		BasicDBObject query = new BasicDBObject();
		query.append("_id", id);

		for (Document doc : collection.find(query)) {
			return doc;
		}

		return null;

	}

	public static DeleteResult deleteOneFromCollection(
			final MongoCollection<Document> collection, final String id) {
		DeleteResult resultDocument = null;

		// findOneAndDelete from MongoDB
		System.out.println("Using deleteOneFromCollection to delete ID: " + id);
		BasicDBObject query = new BasicDBObject();
		query.append("_id", id);
		resultDocument = collection.deleteOne(query);

		return resultDocument;
	}

	public static List<String> retrieveAllAsString(
			final MongoCollection<Document> collection) {

		List<String> result = new ArrayList<String>();

		System.out.println("----[All Items in the" + collection.getNamespace()
				+ " Collection]----");
		for (Document doc : collection.find()) {
			System.out.println("object:" + doc.toJson() + "\n");
			result.add(doc.toJson());
		}
		return result;
	}

//	public static List<Document> retrieveAllDocuments(
//			final MongoCollection<Document> collection) {
//
//		List<Document> result = new ArrayList<Document>();
//
//		System.out.println("----[All Items in the" + collection.getNamespace()
//				+ " Collection]----");
//		for (Document doc : collection.find()) {
//			System.out.println("object:" + doc.toJson() + "\n");
//			result.add(doc);
//		}
//		return result;
//	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> retrieveAllDocuments(final MongoCollection<Document> collection, Class clazz) {

		List<T> result = new ArrayList<T>();

		for (Document document : collection.find()) {
			try {
				result.add((T) getPojo(document, clazz));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public static <T> T getPojo(Document document, Class<T> clazz)
			throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		T pojo = mapper.readValue(document.toJson(), clazz);

		return pojo;

	}

}
