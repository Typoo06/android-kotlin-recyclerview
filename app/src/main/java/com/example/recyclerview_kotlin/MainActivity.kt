package com.example.recyclerview_kotlin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvSongs: RecyclerView
    private lateinit var mSongAdapter: SongAdapter
    private lateinit var mSongs: MutableList<SongModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ánh xạ recyclerView
        rvSongs = findViewById(R.id.rv_songs)

        // khởi tạo đối tượng dữ liệu
        mSongs = ArrayList()

        mSongs.add(SongModel("60696", "NẾU EM CÒN TỒN TẠI", "Khi anh bắt đầu 1 tình yêu là lúc anh tự thấy", "Trịnh Đình Quang"))
        mSongs.add(SongModel("60781", "NGỐC", "Cô rất nhiều những câu chuyện Em đâu dễ dàng quên mình em biết", "Khắc Việt"))
        mSongs.add(SongModel("60658", "HÃY TIN ANH LẦN NỮA", "Đầu cho ta đã sai khi ở bên nhau cô yên yêu thương", "Thiên Dũng"))
        mSongs.add(SongModel("60610", "CHUỖI NGÀY VÔ EM", "Từ khi em bước ra đi cõi lòng anh ngập tràn bao đau", "Duy Cường"))
        mSongs.add(SongModel("60654", "KHI NGƯỜI MÌNH YÊU KHÓC", "Nước mắt em đang rơi trên những ngón tay nước mắt em", "Phạm Mạnh Quỳnh"))
        mSongs.add(SongModel("60685", "MÙ", "Anh mơ gặp em anh mơ được gần anh mơ được gần", "Trịnh Thăng Bình"))
        mSongs.add(SongModel("60752", "TÌNH YÊU CHẤP VÁ", "Muốn đi xa nơi yêu thương mình từng có Để không nghe", "Mr. Siro"))
        mSongs.add(SongModel("60645", "CHƠI NGAY MÙA TÀN", "Có ngay mùa em khuất xa nơi anh bỗng dưng đau", "Trung Đức"))
        mSongs.add(SongModel("60603", "CÂU NÓI EM CHƯA TRẢ LỜI", "Cần nói em 1 lời giải thích thật lòng dừng lặng im", "Yuki Huy Nam"))
        mSongs.add(SongModel("60720", "QUA ĐI LẶNG LẼ", "Đôi khi đến với nhau yêu thương chẳng được lâu nhưng khi", "Phạm Mạnh Quỳnh"))
        mSongs.add(SongModel("60656", "ANH LÀ ĐIỀU EM KHÔNG THỂ - REMIX", "Cần thêm bao lâu để em quên đi niềm đau cần thêm", "Thiện Ngôn"))

        // Khởi tạo adapter ->  truyền context và danh sách bài hát
        mSongAdapter = SongAdapter(this, mSongs)
        rvSongs.adapter = mSongAdapter

        // Thiết lập LayoutManager
        val layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false // reverseLayout = false
        )
        rvSongs.layoutManager = layoutManager

        // có thể viết gọn hơn thành: rvSongs.layoutManager = LinearLayoutManager(this)
    }
}