package by.bstu.fit.mystore20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import by.bstu.fit.mystore20.entity.Item;
import by.bstu.fit.mystore20.shared_resourse.Shared;

public class InputPriceActivity extends AppCompatActivity {

    private EditText priceEditText;
    private TextView itemNameTextView;
    private Item buildItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_price);

        buildItem = (Item)getIntent().getSerializableExtra(Shared.ITEM_OBJECT);
        priceEditText = (EditText) findViewById(R.id.priceEditText);

        itemNameTextView = (TextView) findViewById(R.id.itemNameTextView);

        itemNameTextView.setText(buildItem.getName() == null
                ? ""
                : buildItem.getName());
        priceEditText.setText(buildItem.getName());

        priceEditText.setText(Float.toString(buildItem.getPrice()));

    }

    public void onGoToPreviousActivityClick(View view){
        try{
            buildItem.setPrice(Float.parseFloat(priceEditText.getText().toString()));
        } catch (NumberFormatException e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), priceEditText.getText() + " cant parse", Toast.LENGTH_SHORT).show();
            return ;
        }

        Intent previousActivity = new Intent(this, MainActivity.class);
        previousActivity.putExtra(Shared.ITEM_OBJECT,buildItem);
        startActivity(previousActivity);
    }

    public void onGoToNextActivityClick(View view){
        try{
            buildItem.setPrice(Float.parseFloat(priceEditText.getText().toString()));
        } catch (NumberFormatException e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), priceEditText.getText() + " cant parce", Toast.LENGTH_SHORT).show();
            return ;
        }

        Intent nextActivity = new Intent(this, ImageInputActivity.class);
        nextActivity.putExtra(Shared.ITEM_OBJECT,buildItem);
        startActivity(nextActivity);
    }
}
