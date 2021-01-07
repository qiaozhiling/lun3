package com.qzl.lun3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Context context;
    Data data;

    public RecyclerAdapter(Context context, Data data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //头像设置 四个一循环
        int size=data.headPic.size();
        holder.headPic.setImageResource(data.headPic.get((data.groupName.size() - position - 1) % size));
        holder.groupName.setText(data.groupName.get(data.groupName.size() - position - 1));
        holder.creator.setText(data.creator.get(data.creator.size() - position - 1));

    }

    @Override
    public int getItemCount() {
        return data.groupName.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView headPic;
        TextView creator;
        TextView groupName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            headPic = itemView.findViewById(R.id.headPic);
            creator = itemView.findViewById(R.id.creator);
            groupName = itemView.findViewById(R.id.groupName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(v);
                    }
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private OnItemClickListener onItemClickListener;
}