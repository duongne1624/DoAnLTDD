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

import com.qlykhachsan.doannhom.Adapter.ItemLoaiPhongAdapter;
import com.qlykhachsan.doannhom.DTO.Categories;
import com.qlykhachsan.doannhom.KhachSanDAO;
import com.qlykhachsan.doannhom.KhachSanDB;
import com.qlykhachsan.doannhom.R;

import java.util.List;

public class LoaiPhongFragment extends Fragment{
    private ItemLoaiPhongAdapter itemLoaiPhongAdapter;
    private KhachSanDAO dao;
    public static LoaiPhongFragment newInstance() {
        LoaiPhongFragment fragment = new LoaiPhongFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loai_phong, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dao = KhachSanDB.getInstance(requireContext()).getDAO();
        RecyclerView recyclerView = requireView().findViewById(R.id.fragLoaiPhong_rc);
        itemLoaiPhongAdapter = new ItemLoaiPhongAdapter();
        recyclerView.setAdapter(itemLoaiPhongAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Categories> listLoaiPhong = dao.getAllOfLoaiPhong();
        itemLoaiPhongAdapter.setData(listLoaiPhong);
    }

}