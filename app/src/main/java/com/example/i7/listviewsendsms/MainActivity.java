package com.example.i7.listviewsendsms;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class PtoductRecord { //создаем структурку вместо встроенного класс в адапторе
    public String name;
    public String quantity;
    public String price;
}

public class MainActivity extends AppCompatActivity {

    // для добавления в список - необходимо реализовать адаптор с имплементацией BaseAdapter
    private class ProductAdapter extends BaseAdapter {


        private List<PtoductRecord> recordList = new ArrayList<>();


        @Override
        public int getCount() {
            return recordList.size(); // количество элементов
        }

        @Override
        public Object getItem(int position) {
            return recordList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(android.R.layout.simple_list_item_2, parent, false);

            TextView nameTextView = (TextView) view.findViewById(android.R.id.text1);
            TextView quantityPriceTextView = (TextView) view.findViewById(android.R.id.text2);

            PtoductRecord record = recordList.get(position);
            nameTextView.setText(record.name);
            quantityPriceTextView.setText(record.quantity + " x " + record.price);


            return view;

        }

        public void add(PtoductRecord record) {

            recordList.add(record);   // вгоняем данные name,quantity,price
            notifyDataSetChanged();  // после занисения, перерисовываем список


        }

    }

    private ProductAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // анимация hint вверх для EditText
        TextInputLayout tilName = (TextInputLayout) findViewById(R.id.nameEditTextHintUp);
        EditText etName = (EditText) tilName.findViewById(R.id.nameEditText);
        tilName.setHint(getString(R.string.product_name));

        TextInputLayout tilQuan = (TextInputLayout) findViewById(R.id.quantityEditTextHintUp);
        EditText etQuan = (EditText) tilQuan.findViewById(R.id.quantityEditText);
        tilQuan.setHint(getString(R.string.product_quantity));

        TextInputLayout tilPrice = (TextInputLayout) findViewById(R.id.priceEditTextHintUp);
        EditText etPrice = (EditText) tilPrice.findViewById(R.id.priceEditText);
        tilPrice.setHint(getString(R.string.product_price));



        FloatingActionButton sendButton = (FloatingActionButton) findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        adapter = new ProductAdapter();                                         // передаем adapter в наш productListView
        ((ListView) findViewById(R.id.productListView)).setAdapter(adapter);

    }

    private void onAdd() {                          // по нажатию добавить (addButton) - метод onAdd получает заполненные EditText

        PtoductRecord record = new PtoductRecord();
        record.name = getViewText(R.id.nameEditText);
        record.quantity = getViewText(R.id.quantityEditText);
        record.price = getViewText(R.id.priceEditText);

        String name = getViewText(R.id.nameEditText);
        String quantity = getViewText(R.id.quantityEditText);
        String price = getViewText(R.id.priceEditText);

        adapter.add(record);
    }

    private String getViewText(int id) {
        return ((EditText) findViewById(id)).getText().toString(); // находим элемент по id и получаем у него текст
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
