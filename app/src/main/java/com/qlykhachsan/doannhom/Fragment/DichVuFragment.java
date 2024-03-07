package com.qlykhachsan.doannhom.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qlykhachsan.doannhom.Adapter.ItemDichVuAdapter;
import com.qlykhachsan.doannhom.DTO.Services;
import com.qlykhachsan.doannhom.KhachSanDB;
import com.qlykhachsan.doannhom.R;

import java.util.ArrayList;
import java.util.List;

public class DichVuFragment extends Fragment {
    RecyclerView recyclerView;
    List<Services> list;
    ItemDichVuAdapter itemDichVuAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dich_vu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.fragDichVu_rc);
        itemDichVuAdapter = new ItemDichVuAdapter(requireContext(),list);
        list = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        list = KhachSanDB.getInstance(getContext()).getDAO().getAllService();
        itemDichVuAdapter.setData(list);
        recyclerView.setAdapter(itemDichVuAdapter);
    }
}