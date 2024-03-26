package com.qlykhachsan.doannhom;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.qlykhachsan.doannhom.Adapter.ItemService1Adapter;
import com.qlykhachsan.doannhom.DTO.OrderDetail;
import com.qlykhachsan.doannhom.DTO.Orders;
import com.qlykhachsan.doannhom.DTO.Rooms;
import com.qlykhachsan.doannhom.DTO.ServiceOrder;
import com.qlykhachsan.doannhom.DTO.Services;
import com.qlykhachsan.doannhom.Fragment.SoDoPhongFragment;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ChucNangSuaChuaActivity extends AppCompatActivity {

    TextView tvIDPhong, tvStatus;
    private SimpleDateFormat sdf;
    private Calendar calendar;
    private long time = 0;
    private Date d_checkIn,d_checkOut;
    private Rooms rooms;
    private int status;
    private String idRoom;
    private KhachSanDAO dao;
    private KhachSanSharedPreferences share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuc_nang_sua_chua);

        d_checkIn = new Date();
        d_checkOut = new Date();
        dao = KhachSanDB.getInstance(this).getDAO();
        share = new KhachSanSharedPreferences(this);
        anhxa();
        hanldeDataBundle();
        tvIDPhong.setText("Phòng: " + idRoom);
        tvStatus.setText("Trạng thái: " + status);
    }

    public void handleActionBtnQuayLai (View view) {
        finish();
    }

    public void handleActionBtnSave (View view) {
        rooms = dao.getObjOfRooms(idRoom);
        rooms.setStatus(2);
        try {
            time = sdf.parse(sdf.format(new Date(System.currentTimeMillis()))).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTimeInMillis(time);
        d_checkOut.setTime(time + 3600000 * 36 * 99);
        d_checkIn.setTime(time + 3600000 * 14);
        dao.UpdateRooms(rooms);
        int idOrder = 1;
        OrderDetail orderDetail = new OrderDetail(idRoom, idOrder, 0, d_checkIn, d_checkOut);
        dao.insertOfOrderDetail(orderDetail);
        CustomToast.makeText(this, "Căn phòng đã chỉnh thành trạng thái sửa chữa !", true).show();
        dao.reLoadEndOfOrders(idOrder);
        finish();
    }
    public void anhxa() {
        tvIDPhong = findViewById(R.id.tvIDPhong);
        tvStatus = findViewById(R.id.tvStatus);
    }

    private void hanldeDataBundle() {
        Bundle bundle = getIntent().getBundleExtra(SoDoPhongFragment.KEY_BUNDLE);
        idRoom = bundle.getString(SoDoPhongFragment.KEY_ROOM);
        status = bundle.getInt(SoDoPhongFragment.KEY_STATUS);
    }
}