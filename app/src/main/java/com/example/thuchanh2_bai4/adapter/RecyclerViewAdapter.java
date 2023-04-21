package com.example.thuchanh2_bai4.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thuchanh2_bai4.R;
import com.example.thuchanh2_bai4.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class RecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerViewAdapter.BookViewHolder>{

    private List<Book> list;
    private ItemListener itemListener;

    public RecyclerViewAdapter(List<Book> list) {
        this.list = list;
    }

    public List<Book> getList() {
        return list;
    }

    public void setList(List<Book> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view,parent,false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book= list.get(position);
        holder.ten.setText(book.getTen());
        holder.tacgia.setText(book.getTacgia());
        if(book.getPhamvi().equals("hoc")) {
            holder.rd1.setChecked(true);
            holder.rd2.setChecked(false);
        }
        else{
            holder.rd2.setChecked(true);
            holder.rd1.setChecked(false);
        }
        String[] tmp = book.getDoituong().split(", ");
        for(String i : tmp){
            if(i.equals("CN")) holder.cb1.setChecked(true);
            if(i.equals("DT")) holder.cb2.setChecked(true);
            if(i.equals("VT")) holder.cb3.setChecked(true);
        }
        holder.ratingBar.setRating(book.getRating());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Book getItem(int position) {
        return list.get(position);
    }

    public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView ten, tacgia;
        RadioButton rd1 , rd2;
        CheckBox cb1, cb2, cb3;
        RatingBar ratingBar;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tvName);
            tacgia = itemView.findViewById(R.id.tvAuthor);
            rd1 = itemView.findViewById(R.id.hoc);
            rd2 = itemView.findViewById(R.id.tracuu);
            cb1 = itemView.findViewById(R.id.checkbox1);
            cb2 = itemView.findViewById(R.id.checkbox2);
            cb3 = itemView.findViewById(R.id.checkbox3);
            ratingBar = itemView.findViewById(R.id.rating);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemListener!=null){
                itemListener.onItemClick(view,getAdapterPosition());
            }
        }
    }
    public interface ItemListener{
        void onItemClick (View view,int position);
    }
}
