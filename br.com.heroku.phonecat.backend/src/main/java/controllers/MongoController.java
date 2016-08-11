package controllers;

import java.io.IOException;

import model.Item;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;

import br.com.caelum.vraptor.Resource;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

@Resource
public class MongoController {

	// testing methods following

	public void insertObject() {

		MongoClientURI uri = new MongoClientURI(
				"mongodb://heroku_n44skkbl:4rhd1j62j2625lrbjo8ds9e4bo@ds039195.mlab.com:39195/heroku_n44skkbl");
		MongoClient client = new MongoClient(uri);

		MongoDatabase database = client.getDatabase(uri.getDatabase());

		MongoCollection<Document> collection = database.getCollection("Item");

		final Item item1 = new Item();
		
		item1.setItemId("ITEM3");
		item1.setDescription("Mr. Coffee BVMC-PSTX91 Optimal Brew");
		item1.setManufacturer("Mr. Coffee");
		item1.setDepartment("kitchen");
		item1.setCategory("Coffee Machines");
		item1.setSubCategory("Thermal Carafe");
		item1.setListPrice(89.99);
		item1.setPrice(69.00);
		item1.setQuantity(3);

		boolean status = insertIntoCollection(collection, item1);
		System.out.println("Status of Insert: " + status);

	}

	public void updateObjectUpset() {

		MongoClientURI uri = new MongoClientURI(
				"mongodb://heroku_n44skkbl:4rhd1j62j2625lrbjo8ds9e4bo@ds039195.mlab.com:39195/heroku_n44skkbl");
		MongoClient client = new MongoClient(uri);

		MongoDatabase database = client.getDatabase(uri.getDatabase());

		MongoCollection<Document> collection = database.getCollection("Item");

		final Item item2 = new Item();
		
		item2.setItemId("B00DUHACEE");
		item2.setDescription("SterlingPro French Coffee Press"
				+ " -- 8 Cup/4 Mug (1 liter, 34 oz), Chrome");
		item2.setManufacturer("SterlingPro");
		item2.setDepartment("kitchen");
		item2.setCategory("Coffee Machines");
		item2.setSubCategory("French Press");
		item2.setListPrice(68.00);
		item2.setPrice(29.97);
		item2.setQuantity(8);

		boolean status = updateCollection(collection, item2, item2.getId());
		System.out.println("Status of Update: " + status);
		showDocumentByID(collection, item2.getId());

		item2.setPrice(31.99);
		status = updateCollection(collection, item2, item2.getId());
		System.out.println("Status of Update: " + status);
		showDocumentByID(collection, item2.getId());
	}

	public void updateObjectReplaceOneUpsert() {

		MongoClientURI uri = new MongoClientURI(
				"mongodb://heroku_n44skkbl:4rhd1j62j2625lrbjo8ds9e4bo@ds039195.mlab.com:39195/heroku_n44skkbl");
		MongoClient client = new MongoClient(uri);

		MongoDatabase database = client.getDatabase(uri.getDatabase());

		MongoCollection<Document> collection = database.getCollection("Item");

		final Item item3 = new Item();
		
		item3.setDescription("SterlingPro French Coffee Press");
		item3.setCategory("Coffee Machines");
		item3.setSubCategory("French Press");

		boolean status = replaceUpsertCollection(collection, item3,
				item3.getId());
		System.out.println("Status of Update: " + status);

		showDocumentByID(collection, item3.getId());

		item3.setPrice(31.99);
		status = replaceUpsertCollection(collection, item3, item3.getId());
		System.out.println("Status of Update: " + status);
		showDocumentByID(collection, item3.getId());

	}

	// findOneAndUpdate works by performing the querying and update as one
	// atomic operation in MongoDB. Depending on the FindOneAndUpdateOptions()
	// that are set. In my example, I have turned on the upsert flag to true.
	// The findOneAndUpdate operation will update the first matching document in
	// the collection that matches the query filter.
	public void findOneAndUpdate() {
		MongoClientURI uri = new MongoClientURI(
				"mongodb://heroku_n44skkbl:4rhd1j62j2625lrbjo8ds9e4bo@ds039195.mlab.com:39195/heroku_n44skkbl");
		MongoClient client = new MongoClient(uri);

		MongoDatabase database = client.getDatabase(uri.getDatabase());

		MongoCollection<Document> collection = database.getCollection("Item");

		final Item item4 = new Item();
		

		item4.setDescription("SterlingPro French Coffee Press"
				+ " -- 8 Cup/4 Mug (1 liter, 34 oz), Chrome");
		item4.setManufacturer("SterlingPro");
		item4.setDepartment("kitchen");
		item4.setCategory("Coffee Machines");
		item4.setSubCategory("French Press");
		item4.setListPrice(68.00);
		item4.setPrice(29.97);

		item4.setQuantity(12);

		Document updatedDoc = findAndUpdateCollection(collection, item4,
				item4.getId());
		System.out.println("Updated Document: " + updatedDoc);
		showDocumentByID(collection, item4.getId());

	}

	// methods that perfoms action following...

	public static boolean insertIntoCollection(
			final MongoCollection<Document> collection, final Object object) {
		// Use Jackson to convert Object to JSON String
		ObjectMapper mapper = new ObjectMapper();
		String jsonString;
		boolean status = true;

		try {
			jsonString = mapper.writeValueAsString(object);
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
	public static boolean updateCollection(
			final MongoCollection<Document> collection, final Object object,
			String id) {
		boolean status = true;
		ObjectMapper mapper = new ObjectMapper();
		String jsonString;

		try {
			jsonString = mapper.writeValueAsString(object);
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
			String id) {

		boolean status = true;
		ObjectMapper mapper = new ObjectMapper();
		String jsonString;

		try {
			jsonString = mapper.writeValueAsString(object);
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
			String id) {
		ObjectMapper mapper = new ObjectMapper();
		Document resultDocument = null;
		String jsonString;

		try {
			// findOneAndUpdate using JSON into MongoDB
			jsonString = mapper.writeValueAsString(object);
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

	public static void showDocumentByID(
			final MongoCollection<Document> collection, final ObjectId id) {
		BasicDBObject query = new BasicDBObject();
		query.append("_id", id);

		for (Document doc : collection.find(query)) {
			System.out.println(doc.toJson());
		}
	}
	
	public static DeleteResult deleteOneFromCollection(
		      final MongoCollection<Document> collection, final String id) {
		    DeleteResult resultDocument = null;
		 
		    // findOneAndDelete from MongoDB
		    System.out.println(
		        "Using deleteOneFromCollection to delete ID: " + id);
		    BasicDBObject query = new BasicDBObject();
		    query.append("_id", id);
		    resultDocument = collection.deleteOne(query);
		 
		    return resultDocument;
		  }
		 
		  public static void showDocumentByID(
		      final MongoCollection<Document> collection, final String id) {
		    BasicDBObject query = new BasicDBObject();
		    query.append("_id", id);
		 
		    for (Document doc : collection.find(query)) {
		      System.out.println(doc.toJson());
		    }
		  }
		 
		  public static void showAllDocuments(
		      final MongoCollection<Document> collection) {
		    System.out
		        .println("----[All Items in the Inventory Collection]----");
		    for (Document doc : collection.find()) {
		      System.out.println(doc.toJson());
		    }
		  }
		 
		  public static void showAllDocs(final DBCollection collection) {
		    DBCursor cursor = (DBCursor) collection.find().iterator();
		    try {
		      while (cursor.hasNext()) {
		        System.out.println(cursor.next().toString());
		      }
		    } finally {
		      cursor.close();
		    }
		  }
		  
		  

}
