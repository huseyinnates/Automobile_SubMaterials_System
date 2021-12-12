import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.util.ArrayList;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;

public class managedata {

    public static void main( String[] args ) {

        // Replace the uri string with your MongoDB deployment's connection string
        String uri = "mongodb+srv://admin:admin@atlascluster.xcy35.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            
            MongoDatabase ostim_database = mongoClient.getDatabase("ostim");
            ostim_database.createCollection("Brands");
               
            ArrayList<Document> doc=new ArrayList<Document>();
            Document doc_t=new Document();
            doc_t.append("brand","TOYOTA");
            doc_t.append("model","Avalon");
            doc_t.append("year",2016);
            doc.add(doc_t);
            Document doc_t1=new Document();
            doc_t1.append("brand","TOYOTA");
            doc_t1.append("model","Corolla");
            doc_t1.append("year",2021);
            doc.add(doc_t1);
            Document doc_t2=new Document();
            doc_t2.append("brand","TOYOTA");
            doc_t2.append("model","Yaris");
            doc_t2.append("year",2017);
            doc.add(doc_t2);
            
            Document doc_m=new Document();
            doc_m.append("brand","MERCEDES");
            doc_m.append("model","AMG");
            doc_m.append("year",2021);
            doc.add(doc_m);
            Document doc_m1=new Document();
            doc_m1.append("brand","MERCEDES");
            doc_m1.append("model","Maybach");
            doc_m1.append("year",2019);
            doc.add(doc_m1);
            Document doc_m2=new Document();
            doc_m2.append("brand","MERCEDES");
            doc_m2.append("model","EQS");
            doc_m2.append("year",2022);
            doc.add(doc_m2);
            
            ostim_database.getCollection("Brands").insertMany(doc);
            
            System.out.println("başarıyla eklendi");
            
    }
}
