package com.qlykhachsan.doannhom.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.qlykhachsan.doannhom.Activiti_User.QuanLyActivity;
import com.qlykhachsan.doannhom.Activiti_User.ThongKeActivity;
import com.qlykhachsan.doannhom.Activiti_User.ThongTinNguoiDungActivity;
import com.qlykhachsan.doannhom.Activiti_User.UpdateThongTinActivity;
import com.qlykhachsan.doannhom.Adapter.ItemDichVuAdapter;
import com.qlykhachsan.doannhom.AddServiceActivity;
import com.qlykhachsan.doannhom.ChucNangDatPhongActivity;
import com.qlykhachsan.doannhom.CustomToast;
import com.qlykhachsan.doannhom.DTO.People;
import com.qlykhachsan.doannhom.DTO.Services;
import com.qlykhachsan.doannhom.DangNhapAcitivty;
import com.qlykhachsan.doannhom.HoaDonChiTietActivity;
import com.qlykhachsan.doannhom.KhachSanDAO;
import com.qlykhachsan.doannhom.KhachSanDB;
import com.qlykhachsan.doannhom.KhachSanSharedPreferences;
import com.qlykhachsan.doannhom.MainActivity;
import com.qlykhachsan.doannhom.ManHinhChaoActivity;
import com.qlykhachsan.doannhom.QuenMatKhauActivity;
import com.qlykhachsan.doannhom.R;
import com.qlykhachsan.doannhom.ThemDichVuActivity;

import java.util.ArrayList;
import java.util.List;

public class DichVuFragment extends Fragment {
    RecyclerView recyclerView;
    List<Services> list;
    ItemDichVuAdapter itemDichVuAdapter;
    Button btnAddService;
    private KhachSanSharedPreferences share;
    private boolean check;
    private KhachSanDAO dao;
    private People people;

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
        check = true;
        recyclerView = view.findViewById(R.id.fragDichVu_rc);
        itemDichVuAdapter = new ItemDichVuAdapter(requireContext(),list);
        btnAddService = view.findViewById(R.id.frmDichVu_AddService);
        list = new ArrayList<>();
        dao = KhachSanDB.getInstance(requireContext()).getDAO();
        share = new KhachSanSharedPreferences(requireContext());
        people = dao.checkLogin(share.getSDT2());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        list = KhachSanDB.getInstance(getContext()).getDAO().getAllService();
        itemDichVuAdapter.setData(list);
        recyclerView.setAdapter(itemDichVuAdapter);

        if(people.getStatus() != 2) {
            view.findViewById(R.id.frmDichVu_AddService).setVisibility(View.GONE);
            btnAddService.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(requireContext(), ThemDichVuActivity.class);
                    startActivity(i);
                    list = KhachSanDB.getInstance(getContext()).getDAO().getAllService();
                    itemDichVuAdapter.setData(list);
                    recyclerView.setAdapter(itemDichVuAdapter);
                }
            });
        }


    }
}