package com.omkabel.diagnosa_penyakit.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.omkabel.diagnosa_penyakit.Model.Model_Sensor;
import com.omkabel.diagnosa_penyakit.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Adapter_History extends RecyclerView.Adapter<Adapter_History .MyViewHolder> {
    Context context;
    List<Model_Sensor> menu;
    public Adapter_History (Context context, List<Model_Sensor> data_menu) {
        this.context = context;
        this.menu= data_menu;
    }
    @Override
    public Adapter_History.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_history, parent, false);
        Adapter_History.MyViewHolder holder = new Adapter_History.MyViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(Adapter_History.MyViewHolder holder, final int position) {
        holder.Jam.setText(menu.get(position).getJam());
        holder.Jam.setVisibility(View.GONE);
        holder.Tanggal.setText(menu.get(position).getTanggal());
        holder.Id.setText(menu.get(position).getId());
        holder.Suhu.setText(menu.get(position).getValue()+" °C");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
//                Intent varIntent = new Intent(context, Dashboard.class);
//                varIntent.putExtra("SN", menu.get(position).getSn());
//                SharedPrefManager sharedPrefManager=new SharedPrefManager(context);
//                sharedPrefManager.saveSPString(SharedPrefManager.SP_Mac, menu.get(position).getSn());
////                varIntent.putExtra("NAMA", menu.get(position).getNama());
////                varIntent.putExtra("HARGA", menu.get(position).getHarga());
////                varIntent.putExtra("DESKRIPSI", menu.get(position).getDeskripsi());
////                varIntent.putExtra("GAMBAR_MENU", urlGambar);
////                varIntent.putExtra("GAMBAR", menu.get(position).getFoto());
//                context.startActivity(varIntent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return menu.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tanggal)
        TextView Tanggal;
        @BindView(R.id.jam)
        TextView Jam;
        @BindView(R.id.suhu)
        TextView Suhu;
        @BindView(R.id.id)
        TextView Id;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
