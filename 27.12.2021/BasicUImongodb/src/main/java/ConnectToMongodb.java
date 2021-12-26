import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.util.ArrayList;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;

public class ConnectToMongodb {
    String uri = "mongodb+srv://admin:admin@atlascluster.xcy35.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";
    MongoDatabase database;
    MongoCollection<Document> collection;
    MongoClient mongoClient;
   
    public ConnectToMongodb() {
        mongoClient = MongoClients.create(uri);
        database = mongoClient.getDatabase("AutomobileSubMaterialsSystem");
        collection = database.getCollection("AutomobileParts");
           
    }
 
    public void AddData(String _Brand,String _Model,int _Year,String _EngineNo,String _PieceName,int _NumberOfPiece) {
 
            Document doctd=new Document();
            doctd.append("Brand",_Brand);
            doctd.append("Model",_Model);
            doctd.append("Year",_Year);
            doctd.append("PieceName",_PieceName);
            doctd.append("Dealer",_EngineNo);
            doctd.append("NumberOfPiece",_NumberOfPiece);
            String s=_Brand+_Model+_Year+_PieceName;
            //hashmap can handle this 
            doctd.append("ID",s);
            database.getCollection("AutomobileParts").insertOne(doctd);
            System.out.println("Added Successfully!");
       
    }
    String spub;
    public String FindData(String _Brand,String _Model,int _Year,String _EngineNo,String _PieceName) {
        spub=_Brand+_Model+_Year+_EngineNo+_PieceName;
       
            Bson projectionFields = Projections.fields(
                    Projections.include("Brand", "Model","Year","Dealer","PieceName","NumberOfPiece"),
                    Projections.excludeId());
            
            Document doc = collection.find(eq("ID",spub))
                   .projection(projectionFields)
                    .sort(Sorts.descending("feature_type.rating"))
                    .first();
            if (doc == null) {
                System.out.println("No results found.");
                 return " ";
            } else {
                System.out.println(doc.toJson());
                System.out.println(doc.toString());
                return doc.toString();
            }
        
        
    }
    
    public void UpdateData(int _NewNumberOfPiece) {

        
            Bson projectionFields = Projections.fields(
                    Projections.include("Brand", "Model","Year","Dealer","PieceName","NumberOfPiece"),
                    Projections.excludeId());
            
            Document doc = collection.find(eq("ID",spub))
                   .projection(projectionFields)
                    .sort(Sorts.descending("feature_type.rating"))
                    .first();
            
            Bson newdata=new Document("NumberOfPiece",_NewNumberOfPiece);
            Bson updatedata=new Document("$set",newdata);
            collection.updateOne(doc, updatedata);
            System.out.println("Updated");
                    
    }
    
    public void DeleteData() {
      
            collection.deleteOne(Filters.eq("ID",spub));
            System.out.println("Data deleted");
        }
        
    }
    

