package com.qlykhachsan.doannhom;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.qlykhachsan.doannhom.DTO.Categories;
import com.qlykhachsan.doannhom.DTO.Rooms;
import com.qlykhachsan.doannhom.DTO.ServiceCategory;
import com.qlykhachsan.doannhom.DTO.Services;
import com.qlykhachsan.doannhom.Fragment.DichVuFragment;
import com.qlykhachsan.doannhom.Fragment.HoaDonFragment;
import com.qlykhachsan.doannhom.Fragment.KhachHangFragment;
import com.qlykhachsan.doannhom.Fragment.PhongFragment;
import com.qlykhachsan.doannhom.Fragment.TaiKhoanFragment;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private KhachSanDAO dao;
    private TextView tv_titleTb;
    private KhachSanSharedPreferences share;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        bottomNavigationView.setItemIconTintList(null);
        goiPhongFragment();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setLogo(R.drawable.home_25);
        addData();
        Click();
    }

    private void addData() {
        if (!share.getCheck2()) {
            share.setCheck2(true);
            dao.insertOfLoaiPhong(new Categories("Phòng Đơn",300,1));
            dao.insertOfLoaiPhong(new Categories("Phòng Đôi",500,2));
            dao.insertOfLoaiPhong(new Categories("Phòng Vip",800,4));

            //rooms
            //1: Phòng đơn 2: Phòng đôi 3: Phòng VIP
            dao.insertOfRooms(new Rooms("101", 1));
            dao.insertOfRooms(new Rooms("102", 1));
            dao.insertOfRooms(new Rooms("103", 1));
            dao.insertOfRooms(new Rooms("104", 2));
            dao.insertOfRooms(new Rooms("105", 2));
            dao.insertOfRooms(new Rooms("106", 3));
            dao.insertOfRooms(new Rooms("201", 1));
            dao.insertOfRooms(new Rooms("202", 1));
            dao.insertOfRooms(new Rooms("203", 1));
            dao.insertOfRooms(new Rooms("204", 2));
            dao.insertOfRooms(new Rooms("205", 2));
            dao.insertOfRooms(new Rooms("206", 3));
            dao.insertOfRooms(new Rooms("301", 1));
            dao.insertOfRooms(new Rooms("302", 1));
            dao.insertOfRooms(new Rooms("303", 1));
            dao.insertOfRooms(new Rooms("304", 2));
            dao.insertOfRooms(new Rooms("305", 2));
            dao.insertOfRooms(new Rooms("306", 3));
            dao.insertOfRooms(new Rooms("401", 1));
            dao.insertOfRooms(new Rooms("402", 1));
            dao.insertOfRooms(new Rooms("403", 1));
            dao.insertOfRooms(new Rooms("404", 2));
            dao.insertOfRooms(new Rooms("405", 2));
            dao.insertOfRooms(new Rooms("406", 3));
            dao.insertOfRooms(new Rooms("501", 1));
            dao.insertOfRooms(new Rooms("502", 1));
            dao.insertOfRooms(new Rooms("503", 1));
            dao.insertOfRooms(new Rooms("504", 2));
            dao.insertOfRooms(new Rooms("505", 2));
            dao.insertOfRooms(new Rooms("506", 3));

            //
            dao.insertOfServiceCategory(new ServiceCategory(1, 1));
            dao.insertOfServiceCategory(new ServiceCategory(1, 2));
            dao.insertOfServiceCategory(new ServiceCategory(1, 3));
            dao.insertOfServiceCategory(new ServiceCategory(1, 4));
            dao.insertOfServiceCategory(new ServiceCategory(1, 6));

            dao.insertOfServiceCategory(new ServiceCategory(2, 1));
            dao.insertOfServiceCategory(new ServiceCategory(2, 2));
            dao.insertOfServiceCategory(new ServiceCategory(2, 3));
            dao.insertOfServiceCategory(new ServiceCategory(2, 4));
            dao.insertOfServiceCategory(new ServiceCategory(2, 5));
            dao.insertOfServiceCategory(new ServiceCategory(2, 6));

            dao.insertOfServiceCategory(new ServiceCategory(3, 1));
            dao.insertOfServiceCategory(new ServiceCategory(3, 2));
            dao.insertOfServiceCategory(new ServiceCategory(3, 3));
            dao.insertOfServiceCategory(new ServiceCategory(3, 4));
            dao.insertOfServiceCategory(new ServiceCategory(3, 5));
            dao.insertOfServiceCategory(new ServiceCategory(3, 6));
            dao.insertOfServiceCategory(new ServiceCategory(3, 7));
            dao.insertOfServiceCategory(new ServiceCategory(3, 8));
            dao.insertOfServiceCategory(new ServiceCategory(3, 9));
            dao.insertOfServiceCategory(new ServiceCategory(3, 10));
            dao.insertOfServiceCategory(new ServiceCategory(3, 11));

            //Dịch vụ
            dao.insertOfService(new Services("Giặt đồ", 30));
            dao.insertOfService(new Services("Buffet sáng", 150));
            dao.insertOfService(new Services("Nước khoáng", 10));
            dao.insertOfService(new Services("Nước ngọt", 20));
            dao.insertOfService(new Services("Mỳ ăn liền", 10));
            dao.insertOfService(new Services("Cơm gà", 40));
            dao.insertOfService(new Services("Thuê xe (Xe máy)", 120));
            dao.insertOfService(new Services("Thuê xe (Xe oto)", 300));
        }
    }

    //sk gọi frm
    private void goiPhongFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new PhongFragment()).commit();
    }

    //sk click
    private void Click() {
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment fragment = null;
            String title = null;
            int id = item.getItemId();
            int logo = 0;
            if(id == R.id.menu_bottom1) {
                title = "Sơ Đồ Phòng";
                logo = R.drawable.home_25;
                fragment = new PhongFragment();
                loadFragment(fragment);
                return true;
            }
            else if(id == R.id.menu_bottom2) {
                title = "Khách Hàng";
                logo = R.drawable.customer_25;
                fragment = new KhachHangFragment();
                loadFragment(fragment);
                return true;
            }
            else if(id == R.id.menu_bottom3) {
                title = "Khách Hàng";
                logo = R.drawable.services_24;
                fragment = new DichVuFragment();
                loadFragment(fragment);
                return true;
            }
            else if(id == R.id.menu_bottom4) {
                title = "Hóa Đơn";
                logo = R.drawable.order_25;
                fragment = new HoaDonFragment();
                loadFragment(fragment);
                return true;
            }
            else if(id == R.id.menu_bottom5) {
                title = "Tài Khoản";
                logo = R.drawable.yourselt_25;
                fragment = new TaiKhoanFragment();
                loadFragment(fragment);
                return true;
            }
            else return false;
        });
    }

    private void anhXa() {
        bottomNavigationView = findViewById(R.id.bottomNavMenu);
        toolbar = findViewById(R.id.actiMain_tb);
        dao = KhachSanDB.getInstance(this).getDAO();
        share = new KhachSanSharedPreferences(this);
        tv_titleTb = findViewById(R.id.actiMain_tv_titleTb);
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }
}