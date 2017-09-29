package by.bstu.fit.mystore20.shared_resourse;

import android.Manifest;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import by.bstu.fit.mystore20.entity.Item;

/**
 * Created by BNT on 9/25/2017.
 */

public class IOUtil {

    private static final String storeFile = "myStore.txt";

    public static ArrayList<Item> restoreItemsFromFile(AppCompatActivity activity) {
        String ret = "";
        ArrayList<Item> items = null;

        try {
            InputStream inputStream = activity.openFileInput(storeFile);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();

                 items = new Gson().fromJson(ret,new TypeToken<ArrayList<Item>>(){}.getType());
            }
        }
        catch (FileNotFoundException e) {
           e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return items;

    }

    public static void saveTodosToFile(AppCompatActivity activity, Item savedItem) {
        ArrayList<Item> itemsList =  restoreItemsFromFile(activity);
        if(itemsList == null){
            itemsList = new ArrayList<>();
        }

        itemsList.add(savedItem);

            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(activity.openFileOutput(storeFile, Context.MODE_PRIVATE));
                String savedList = new Gson().toJson(itemsList);
                outputStreamWriter.write(savedList);
                outputStreamWriter.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

}
