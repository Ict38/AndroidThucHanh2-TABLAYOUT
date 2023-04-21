package com.example.thuchanh2_bai4.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thuchanh2_bai4.R;
import com.example.thuchanh2_bai4.adapter.RecyclerViewAdapter;
import com.example.thuchanh2_bai4.database.SQLiteHelper;
import com.example.thuchanh2_bai4.model.Book;

import java.util.ArrayList;
import java.util.List;

public class FragmentSearch extends Fragment implements View.OnClickListener {

    private EditText input;
    private Button btnSearch, btnStatistic;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private SQLiteHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        adapter=new RecyclerViewAdapter(new ArrayList<>());
        db=new SQLiteHelper(getContext());
        List<Book> list=db.getAllBook();
        adapter.setList(list);
        LinearLayoutManager manager=new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        btnSearch.setOnClickListener(this);
        btnStatistic.setOnClickListener(this);
    }

    private void initView(View view) {
        input = view.findViewById(R.id.searchInput);
        btnSearch = view.findViewById(R.id.btnSearch);
        btnStatistic = view.findViewById(R.id.btnGetStatistic);
        recyclerView = view.findViewById(R.id.rvSearch);

    }

    @Override
    public void onClick(View view) {
        if (view==btnStatistic){
            List<Book> list=db.getStatistic();
            adapter.setList(list);
        }
        if (view==btnSearch){;
            List<Book> booksByPrice = db.findBooksByNameOrAuthor(input.getText().toString().trim());
            adapter.setList(booksByPrice);
        }
    }

}
