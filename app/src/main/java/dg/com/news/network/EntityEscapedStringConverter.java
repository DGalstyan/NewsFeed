package dg.com.news.network;

import com.google.gson.Gson;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import retrofit.converter.ConversionException;
import retrofit.converter.GsonConverter;
import retrofit.mime.TypedInput;

/**
 * Created by dgalstya on 26.09.2017.
 */
public class EntityEscapedStringConverter extends GsonConverter {

    /**
     * Instantiates a new Entity escaped string converter.
     *
     * @param gson the gson
     */
    public EntityEscapedStringConverter(Gson gson) {
        super(gson);
    }


    @Override
    public Object fromBody (TypedInput body, Type type) throws ConversionException {
        try {
            String convertedString = fromStream(body.in());
            return convertedString;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * From stream string.
     *
     * @param in the in
     * @return the string
     * @throws IOException the io exception
     */
    public String fromStream (InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder out = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
            out.append(newLine);
        }
        return out.toString();
    }
}
