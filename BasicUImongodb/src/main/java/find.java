import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;

public class find {

    public static void main( String[] args ) {

        // Replace the uri string with your MongoDB deployment's connection string
        String uri = "mongodb+srv://admin:admin@atlascluster.xcy35.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";

        try (MongoClient mongoClient = MongoClients.create(uri)) {

            MongoDatabase database = mongoClient.getDatabase("ostim");
            MongoCollection<Document> collection = database.getCollection("Brands");
           
            Bson projectionFields = Projections.fields(
                    Projections.include("brand", "model","year"),
                    Projections.excludeId());

            Document doc = collection.find(eq("brand", "TOYOTA"))
                    .projection(projectionFields)
                    .sort(Sorts.descending("feature_type.rating"))
                    .first();

            if (doc == null) {
                System.out.println("No results found.");
            } else {
                System.out.println(doc.toJson());
                System.out.println(doc.toString());
            }
        }
    }
}
