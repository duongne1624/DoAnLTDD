package com.qlykhachsan.doannhom;

import static androidx.core.content.ContentProviderCompat.requireContext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.qlykhachsan.doannhom.Adapter.ItemDichVuAdapter;
import com.qlykhachsan.doannhom.DTO.Services;
import com.qlykhachsan.doannhom.Fragment.DichVuFragment;

import java.util.List;

public class ThemDichVuActivity extends AppCompatActivity {

    private Button btnThemDV, btnHuy;
    ItemDichVuAdapter itemDichVuAdapter;
    private EditText edtNameDV, edtDonGiaDv;
    private KhachSanDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_dich_vu);
        anhxa();

        dao = KhachSanDB.getInstance(this).getDAO();

        btnThemDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenDV = edtNameDV.getText().toString();
                int donGia = Integer.parseInt(edtDonGiaDv.getText().toString());
                if(tenDV.isEmpty() || edtDonGiaDv.getText().toString().isEmpty())
                {
                    CustomToast.makeText(ThemDichVuActivity.this, "Không được để trống thông tin", false).show();
                }
                else {
                    Services sv = new Services(tenDV, donGia);
                    dao.insertOfService(sv);
                    CustomToast.makeText(ThemDichVuActivity.this, "Thêm thông tin thành công", true).show();
                    finish();
                }

            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void anhxa() {
        btnThemDV = findViewById(R.id.btnThem);
        btnHuy = findViewById(R.id.btnHuy);
        edtNameDV = findViewById(R.id.edtTenDichVu);
        edtDonGiaDv = findViewById(R.id.edtDonGia);
    }
}