package by.bstu.fit.mystore20.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import by.bstu.fit.mystore20.R;
import by.bstu.fit.mystore20.entity.Item;

import static by.bstu.fit.mystore20.ShowItemsActivity.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE;

public class ItemAdapter extends ArrayAdapter<Item> {
    private Context context;

    public ItemAdapter(Context context, ArrayList<Item> items) {
        super(context, 0, items);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Item item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }
        // Lookup view for data population
        TextView itemName = (TextView) convertView.findViewById(R.id.itemName);
        TextView itemPrice = (TextView) convertView.findViewById(R.id.itemPrice);
        ImageView itemPhoto = (ImageView) convertView.findViewById(R.id.itemPhoto);
        // Populate the data into the template view using the data object
        itemName.setText(item.getName());
        itemPrice.setText(Float.toString(item.getPrice()));

        Bitmap bitmap = null;


        try {

            Uri uri = Uri.parse(item.getPathToPhoto());
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(),uri);

            //bitmap = getBitmapFromUri(Uri.parse(item.getPathToPhoto()),context);
        } catch (IOException e) {
            e.printStackTrace();
        }
        itemPhoto.setImageBitmap(bitmap);
        // Return the completed view to render on screen
        return convertView;
    }

   /* public static Bitmap getThumbnail(Uri uri, Context context) throws FileNotFoundException, IOException{
        InputStream input = context.getContentResolver().openInputStream(uri);

        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither=true;//optional
        onlyBoundsOptions.inPreferredConfig=Bitmap.Config.ARGB_8888;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();

        if ((onlyBoundsOptions.outWidth == -1) || (onlyBoundsOptions.outHeight == -1)) {
            return null;
        }

        int originalSize = (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) ? onlyBoundsOptions.outHeight : onlyBoundsOptions.outWidth;

        double ratio = (originalSize > 1200) ? (originalSize / 700) : 1.0;

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
        bitmapOptions.inDither = true; //optional
        bitmapOptions.inPreferredConfig=Bitmap.Config.ARGB_8888;//
        input = context.getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();
        return bitmap;
    }*/
}