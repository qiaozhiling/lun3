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

    Context context;//上下文？
    Data data;//数据

    public RecyclerAdapter(Context context, Data data) {
        this.context = context;
        this.data = data;
    }//实例RecyclerAdapter方法

    @NonNull
    @Override
    //onCreateViewHolder（）方法，负责承载每个子项的布局。它有两个参数，其中一个是 int viewType;
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_, parent, false));
    }

    @Override
    //onBindViewHolder()方法，负责将每个子项holder绑定数据
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //头像循环设置
        int size = data.headPic.size();
        holder.picId = data.headPic.get((data.groupName.size() - position - 1) % size);
        holder.headPic.setImageResource(data.headPic.get((data.groupName.size() - position - 1) % size));
        holder.groupName.setText(data.groupName.get(data.groupName.size() - position - 1));
        holder.creator.setText(data.creator.get(data.creator.size() - position - 1));

    }

    @Override
    public int getItemCount() {
        return data.groupName.size();
    }//item个数

    class ViewHolder extends RecyclerView.ViewHolder {

        int picId;
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
                        onItemClickListener.onItemClick(v, picId);
                    }
                }
            });

        }
    }

    ////////////////////////////////////////////////////////////////////
    public interface OnItemClickListener {
        void onItemClick(View v, int headPicID);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private OnItemClickListener onItemClickListener;
}