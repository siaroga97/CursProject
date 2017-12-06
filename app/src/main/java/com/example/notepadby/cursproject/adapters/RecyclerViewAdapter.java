package com.example.notepadby.cursproject.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.notepadby.cursproject.DetailView;
import com.example.notepadby.cursproject.MainActivity;
import com.example.notepadby.cursproject.R;
import com.example.notepadby.cursproject.entity.ListElement;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ListElement> elements;

    public RecyclerViewAdapter(List<ListElement> elements) {
        this.elements = elements;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_view, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final  ListElement element = elements.get(position);

        ItemViewHolder viewHolder =  (ItemViewHolder) holder;

        View itemView = viewHolder.itemView;
        TextView title = viewHolder.title;
        TextView date = viewHolder.date;

        title.setText(element.getTitle());
        date.setText(element.getDate().toString());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailView.class);
                MainActivity.currentList = elements;

                intent.putExtra("id", element.getId());

                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView date;

        private View itemView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

            title = itemView.findViewById(R.id.item_title);
            date = itemView.findViewById(R.id.item_date);
        }

        public void setOnClickListener(View.OnClickListener listener){
            itemView.setOnClickListener(listener);
        }
    }
}
