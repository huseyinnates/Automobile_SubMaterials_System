import com.mongodb.client.AggregateIterable;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.logging.log4j.spi.LoggerContext;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.LoggerFactory;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author matth
 */
public class finder {
    public ArrayList<String> brands,models,years,piecenames,dealer;
    String uri = "mongodb+srv://admin:admin@atlascluster.xcy35.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";
    public String CarBrandName;
    MongoClient mongoClient; 
    MongoDatabase database;
    MongoCollection<Document> collection;
    public finder(){
        mongoClient = MongoClients.create(uri);
        database = mongoClient.getDatabase("AutomobileSubMaterialsSystem");
        collection = database.getCollection("AutomobileParts");
            
    }
    public void Apply(String willsearch,String carname,String modelname){
        
        CarBrandName=carname;
        //if equals to brand

        
            if("Brand".equals(willsearch)){
            brands=new ArrayList<String>();
  
            MongoCursor<String> c=collection.distinct(willsearch,String.class).iterator();
            
            while(c.hasNext()){
                brands.add(c.next());
            }
            for (int i = 0; i < brands.size(); i++) {
                System.out.println(brands.get(i));
                System.out.println(brands.size());
            }}
            
            //if equals to brand
            else if("Model".equals(willsearch)){
                models=new ArrayList<String>();
           
            Bson projectionFields = Projections.fields(
                    Projections.include("Model"),
                    Projections.excludeId());
            
            Document doc = collection.find(eq("Brand",CarBrandName))
                   .projection(projectionFields)
                    .sort(Sorts.descending("feature_type.rating"))
                    .first();
            MongoCursor cursor=collection.find(eq("Brand",CarBrandName))
                   .projection(projectionFields)
                    .sort(Sorts.descending("feature_type.rating"))
                    .cursor();
            
            Hashtable<String, String> htable = new Hashtable<>();
            Document temp=new Document();
            while(cursor.hasNext()){
                temp=(Document) cursor.next();
                htable.put((String)temp.get("Model"),"yes");
            }
              
            System.out.println("htable is:"+htable.toString());
            Enumeration enumeration = htable.keys();
            while(enumeration.hasMoreElements()){
                String stemp=(String)enumeration.nextElement();
                models.add(stemp);
                System.out.println(models.get(models.size()-1));
            }
            if (doc == null) {
                System.out.println("No results found.");
                
            } else {
                System.out.println(doc.toJson());
                System.out.println(doc.toString());
            }
        
        
            }
            //year
            else if("Year".equals(willsearch)){
                years=new ArrayList<String>();
           
            Bson projectionFields = Projections.fields(
                    Projections.include("Year"),
                    Projections.excludeId());
            
            Document doc = collection.find(eq("Model",CarBrandName))
                   .projection(projectionFields)
                    .sort(Sorts.descending("feature_type.rating"))
                    .first();
            MongoCursor cursor=collection.find(eq("Model",CarBrandName))
                   .projection(projectionFields)
                    .sort(Sorts.descending("feature_type.rating"))
                    .cursor();
            
            Hashtable<String, String> htable = new Hashtable<>();
            Document temp=new Document();
            while(cursor.hasNext()){
                temp=(Document) cursor.next();
                htable.put(temp.get("Year").toString(),"yes");
            }
              
            System.out.println("htable is:"+htable.toString());
            Enumeration enumeration = htable.keys();
            while(enumeration.hasMoreElements()){
                String stemp=(String)enumeration.nextElement();
                years.add(stemp);
                System.out.println(years.get(years.size()-1));
            }
            if (doc == null) {
                System.out.println("No results found.");
                
            } else {
                System.out.println(doc.toJson());
                System.out.println(doc.toString());
            }
        
        
    }
            
         else if("PieceName".equals(willsearch)){
             piecenames=new ArrayList<String>();
           
            Bson projectionFields = Projections.fields(
                    Projections.include("Model","Year","PieceName"),
                    Projections.excludeId());
            
            Document doc = collection.find(eq("Year",Integer.parseInt(CarBrandName)))
                   .projection(projectionFields)
                    .sort(Sorts.descending("feature_type.rating"))
                    .first();
            MongoCursor cursor=collection.find(eq("Year",Integer.parseInt(CarBrandName)))
                   .projection(projectionFields)
                    .sort(Sorts.descending("feature_type.rating"))
                    .cursor();
            
            Hashtable<String, String> htable = new Hashtable<>();  
            Document temp=new Document();
            while(cursor.hasNext()){
                temp=(Document) cursor.next();
                htable.put(temp.get("PieceName").toString(),temp.get("Model").toString());
           
            }

            System.out.println("htable is: 2434"+htable.toString());
            
            Enumeration enumeration = htable.keys();
            while(enumeration.hasMoreElements()){
                String stemp=(String)enumeration.nextElement();
                String check=htable.get(stemp);
                if(check.equals(modelname)){
                    piecenames.add(stemp);
                    System.out.println(piecenames.get(piecenames.size()-1));
                }
               
            }
            if (doc == null) {
                System.out.println("No results found.");
                
            } else {
                System.out.println(doc.toJson());
                System.out.println(doc.toString());
            }
        
        
    }
            else if("Dealer".equals(willsearch)){
             dealer=new ArrayList<String>();
           
            Bson projectionFields = Projections.fields(
                    Projections.include("Model","Year","PieceName","Dealer"),
                    Projections.excludeId());
            
            Document doc = collection.find(eq("ID",carname))
                   .projection(projectionFields)
                    .sort(Sorts.descending("feature_type.rating"))
                    .first();
            MongoCursor cursor=collection.find(eq("ID",carname))
                   .projection(projectionFields)
                    .sort(Sorts.descending("feature_type.rating"))
                    .cursor();
            
            Hashtable<String, String> htable = new Hashtable<>();  
            Document temp=new Document();
            while(cursor.hasNext()){
                temp=(Document) cursor.next();
                    htable.put(temp.get("Dealer").toString(),"YES");
           
            }

            System.out.println("htable is: 2434"+htable.toString());
            
            Enumeration enumeration = htable.keys();
            while(enumeration.hasMoreElements()){
                String stemp=(String)enumeration.nextElement();  
                dealer.add(stemp);
                System.out.println(dealer.get(dealer.size()-1));
             
               
            }
            if (doc == null) {
                System.out.println("No results found.");
                
            } else {
                System.out.println(doc.toJson());
                System.out.println(doc.toString());
            }
        
        
    }
        
            } 
        
                   
}
           
     

