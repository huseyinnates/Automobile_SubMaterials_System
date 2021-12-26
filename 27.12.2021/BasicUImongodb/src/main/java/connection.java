import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;

public class connection {

    public static void main( String[] args ) {

        // Replace the uri string with your MongoDB deployment's connection string
        String uri = "mongodb+srv://admin:admin@atlascluster.xcy35.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";

        try (MongoClient mongoClient = MongoClients.create(uri)) {

            MongoDatabase database = mongoClient.getDatabase("car_pieces");
            MongoCollection<Document> collection = database.getCollection("car_features");
            MongoCollection<Document> collection1 = database.getCollection("extra_collection");
            database.createCollection("fruits");
            Document doctd=new Document();
            doctd.append("elma","kalem123");
            doctd.append("muz","12453");
            doctd.append("erik","5kg");
            database.getCollection("fruits").insertOne(doctd);
            System.out.println("başrıyla eklendi");
            Bson projectionFields = Projections.fields(
                    Projections.include("brand", "color","latdec"),
                    Projections.excludeId());

            Document doc = collection.find(eq("brand", "bmw"))
                    .projection(projectionFields)
                    .sort(Sorts.descending("feature_type.rating"))
                    .first();
            
             Bson projectionFields1 = Projections.fields(
                    Projections.include("music", "pieceName","latdec"),
                    Projections.excludeId());

            Document doc1 = collection1.find(eq("music", "hans"))
                    .projection(projectionFields1)
                    .sort(Sorts.descending("latdec.rating"))
                    .first();

            if (doc == null) {
                System.out.println("No results found.");
            } else {
                System.out.println(doc.toJson());
                System.out.println(doc.toString());
            }
             if (doc1 == null) {
                System.out.println("No results found.");
            } else {
                System.out.println(doc1.toJson());
                System.out.println(doc1.toString());
            }
        }
    }
}
