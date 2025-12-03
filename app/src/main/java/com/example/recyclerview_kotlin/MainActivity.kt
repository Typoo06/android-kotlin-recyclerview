package com.example.recyclerview_kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvSongs: RecyclerView
    private lateinit var mSongAdapter: SongAdapter

    /*
    *   List (danh sách bất biến - immutable):
    *       -> là một collection chỉ cho phép đọc (read-only)
    *       -> một khi đã tạo và gán giá trị, không thể thay đổi nội dung, tức là ko thể thêm, xóa, hay thay đổi phần tử.
    *       -> add(), remove(), clear() không tồn tại trong interface List
    *
    *   MutableList (danh sách khả biến - mutable):
    *       -> là một collection cho phép cả đọc và ghi
    */
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
        /*
        *   Đây là một thành pahàn cốt lỗi của RecyclerView. Nó chịu trách nhiệm đo lường và định vị các item view bên trong
        * RecyclerView (nói đơn giản là LayoutManager quyết định cách sắp xếp các item trên màn hình)
        *
        *   RecyclerView không tự mình làm việc này. Nó giao hoàn toàn nhiện vụ sắp xếp bố cục cho một LayoutManager
        * -> mang lại sự linh hoạt rất lớn, vì ta có thể thay đổi hoàn toàn cách hiển thị danh sách chỉ bằng cách thay đổi LayoutManager mà khôgn cần sửa đổi Adapter
        *
        *   Lấy ví dụ, tưởng tượng rằng ta có một caiái hộp (Chính là RecyclerView) và rất nhiều món đồ chơi (item view). Ta cần một người (LayoutManager)
        * để nói cho ta biết phải sắp xếp những món đồ chơi đó vào hộp như thế nào:
        *       + là xếp thành một hàng dọc?
        *       + xêp thành một hàng ngang?
        *       + xếp thành một lưới (grid)?
        *       + xếp so le nhau (staggered grid)?
        *  -> nếu không có người chỉ dẫn (LayoutManager), RecyclerView sẽ không bết phải làm gì với các item và sẽ không hiển thị bất cứ thứ gì.
        *   Có 3 loại LayoutManager chính:
        *       + LinearLayoutManager (.VERTICAL / .HORIZONTAL)
        *       + GridLayoutManager
        *       + StaggeredGridLayoutManager
        */
        val layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false // reverseLayout = false
        )
        rvSongs.layoutManager = layoutManager

        // có thể viết gọn hơn thành: rvSongs.layoutManager = LinearLayoutManager(this)
    }
}