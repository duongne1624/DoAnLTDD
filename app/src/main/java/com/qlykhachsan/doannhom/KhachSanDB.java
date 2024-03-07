package com.qlykhachsan.doannhom;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.qlykhachsan.doannhom.DTO.Categories;
import com.qlykhachsan.doannhom.DTO.OrderDetail;
import com.qlykhachsan.doannhom.DTO.Orders;
import com.qlykhachsan.doannhom.DTO.People;
import com.qlykhachsan.doannhom.DTO.Rooms;
import com.qlykhachsan.doannhom.DTO.ServiceCategory;
import com.qlykhachsan.doannhom.DTO.ServiceOrder;
import com.qlykhachsan.doannhom.DTO.Services;

@TypeConverters({Converters.class})
@Database(entities = {Categories.class, OrderDetail.class, Orders.class,
        People.class, Services.class, ServiceCategory.class, Rooms.class, ServiceOrder.class}, version = 1)
public abstract class KhachSanDB extends RoomDatabase {
    private static final String KHACHSAN_NAME = "khachsanmtcl";
    private static KhachSanDB instance;

    public static KhachSanDB getInstance(Context context) {
        if (instance == null)
            instance = Room.databaseBuilder(context, KhachSanDB.class, KHACHSAN_NAME).allowMainThreadQueries().build();
        return instance;
    }

    public abstract KhachSanDAO getDAO();

}
