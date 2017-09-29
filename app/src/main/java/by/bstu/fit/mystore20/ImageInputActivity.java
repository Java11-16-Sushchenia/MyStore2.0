package by.bstu.fit.mystore20;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import by.bstu.fit.mystore20.entity.Item;
import by.bstu.fit.mystore20.shared_resourse.Constants;
import by.bstu.fit.mystore20.shared_resourse.IOUtil;
import by.bstu.fit.mystore20.shared_resourse.Shared;

public class ImageInputActivity extends AppCompatActivity {

    private static final int READ_REQUEST_CODE = 42;
    private TextView itemNameTextView;
    private TextView itemPriceTextView;
    private Item buildItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_input);

        Intent intent = new Intent();

        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        startActivityForResult(Intent.createChooser(intent, "Select Picture"), READ_REQUEST_CODE);

        buildItem = (Item)getIntent().getSerializableExtra(Shared.ITEM_OBJECT);

        itemNameTextView = (TextView) findViewById(R.id.itemNameTextView);
        itemPriceTextView = (TextView) findViewById(R.id.itemPriceTextView);


        itemNameTextView.setText(buildItem.getName() == null
                ? ""
                : buildItem.getName());

        itemPriceTextView.setText(Float.toString(buildItem.getPrice()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == READ_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            buildItem.setPathToPhoto(uri.toString());
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onImageViewClick(View view){
        Intent intent = new Intent();


        intent.setType("image/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), READ_REQUEST_CODE);

    }

    public void onSaveButtonClick(View view){
        IOUtil.saveTodosToFile(this, buildItem);
        Intent allItems = new Intent(this, ShowItemsActivity.class);
        allItems.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        allItems.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        startActivity(allItems);
    }
}

