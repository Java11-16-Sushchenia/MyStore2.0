package by.bstu.fit.mystore20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import by.bstu.fit.mystore20.entity.Item;
import by.bstu.fit.mystore20.shared_resourse.Shared;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText;
    private Item buildItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildItem = getIntent().getSerializableExtra(Shared.ITEM_OBJECT) == null
                ? new Item()
                :(Item) getIntent().getSerializableExtra(Shared.ITEM_OBJECT);

        nameEditText = (EditText) findViewById(R.id.itemNameEditText);
        nameEditText.setText(buildItem.getName() == null
                ? ""
                : buildItem.getName());

    }

    public void onGoToNextActivityClick(View view){

        buildItem.setName(nameEditText.getText().toString());
        Intent nextActivity = new Intent(this, InputPriceActivity.class);
        nextActivity.putExtra(Shared.ITEM_OBJECT,buildItem);
        startActivity(nextActivity);
    }
}
