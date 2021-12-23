import org.bson.Document;
import com.mongodb.client.gridfs.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class image  {

    public static void main( String[] args ) throws FileNotFoundException, IOException {
        String filePath = "C:/Users/matth/Desktop/mercedes_brand_logo.png";
        // Replace the uri string with your MongoDB deployment's connection string
        String uri = "mongodb+srv://admin:admin@atlascluster.xcy35.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("images");
        GridFSBucket gridFSBucket=GridFSBuckets.create(database);
        gridFSBucket.find().forEach(new Consumer<GridFSFile>() {
        @Override
         public void accept(final GridFSFile gridFSFile) {
        System.out.println(gridFSFile);
    }
});
        Bson query = Filters.eq("metadata.type", "zip archive");
        Bson sort = Sorts.ascending("filename");
        gridFSBucket.find(query)
                .sort(sort)
                .limit(5)
                .forEach(new Consumer<GridFSFile>() {
            @Override
            public void accept(final GridFSFile gridFSFile) {
                System.out.println(gridFSFile);
            }
        });
        
}}
