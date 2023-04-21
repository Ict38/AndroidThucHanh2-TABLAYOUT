package com.example.thuchanh2_bai4.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thuchanh2_bai4.R;
import com.example.thuchanh2_bai4.UpdateAndDelete;
import com.example.thuchanh2_bai4.adapter.RecyclerViewAdapter;
import com.example.thuchanh2_bai4.database.SQLiteHelper;
import com.example.thuchanh2_bai4.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class FragmentList extends Fragment implements RecyclerViewAdapter.ItemListener {

    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;

    private SQLiteHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rvList);
        db=new SQLiteHelper(getContext());

        List<Book> list = db.getAllBook();
//        list.add(new Book(1, "CTDL", "Nguyen Manh Son","hoc","CN, VT", (float) 5));
//        list.add(new Book(2, "CTDL", "Nguyen Manh Son","hoc","CN, VT", (float) 5));
        adapter=new RecyclerViewAdapter(list);
        LinearLayoutManager manager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Book> list= db.getAllBook();
        adapter=new RecyclerViewAdapter(list);
        adapter.setList(list);
    }

    @Override
    public void onItemClick(View view, int position) {
        Book book= adapter.getItem(position);
        Intent intent=new Intent(getActivity(), UpdateAndDelete.class);
        intent.putExtra("book",book);
        startActivity(intent);
    }
}
