package com.example.recyclerview_kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView // Import các View cần thiết
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast // Cần cho sự kiện click

class SongAdapter(
    private val context: Context,
    private val songs: List<SongModel>
) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    // hằng số tĩnh TAG, tương đương trong java: private static final String TAG = "SongAdapter"
    companion object {
        private const val TAG = "SongAdapter"
    }

    /* định nghĩa ViewHolder: là một design pattern được sử dụng trong android
    * để giúp cho việc hiển thị các item trong RecyclerView trở nên mượt mà và hiệu quả hơn.
    * Hoạt động như một bộ nhớ đệm (cache) cho các View của item
    * --------------------------------
    * ViewHolder thể hiện vai trò:
    * 1. Lưu trữ các View (holding views): thay vì phải gọi phương thức findViewById() mỗi khi cần
    * hiển thị một item trong danh sách (rất tốn tài nguyên nếu lặp lại nhiều lần một công việc y như nhau),
    * ViewHolder sẽ thực hiện việc này chỉ một lần duy nhất trong mỗi item view được tạo ra. Nó sẽ "giữ"
    * các tham chiếu đến các view con (như titleTextView, artistTextView,...) bên trong itemView.
    *
    * 2. Tái sử dụng (Recycling): Khi người dùng cuộn danh sách, các item view cũ bị cuộn ra khỏi màn hình sẽ không bị hủy đi.
    * Thay vào đó, RecyclerView sẽ giữ lại chúng và tái sử dụng cho các item mới sắp hiển thị trên màn hình.
    * ViewHolder chứ các view này sẽ được lấy ra, dữ liệu mới sẽ được gán vào (thông qua bind()), và hiển thị lại.
    * Quá trình này giúp giảm đáng kể số lượng view cần được tạo ra và tìm kiếm --> từ đó cải thiện hiệu suất, tiết kiệm bộ nhớ
    * và giúp ứng dụng chạy mượt mà hơn
    *
    * ==> Nói chung, ViewHolder là inner class bắt buộc phải khai báo khi dùng RecyclerView, với mục đích chính
    * là lưu trữ các tham chiếu đến view của một item và cho phép RecyclerView tái sử dụng cá view đó một cách hiệu quả.
    */
    inner class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // ánh xạ View bằng findViewById()
        val titleTextView: TextView = itemView.findViewById(R.id.tv_title)
        val artistTextView: TextView = itemView.findViewById(R.id.tv_artist)
        val codeTextView: TextView = itemView.findViewById(R.id.tv_code)
        val lyricTextView: TextView = itemView.findViewById(R.id.tv_lyric)

        /*
        * setOnClickListener đặt trong khối init vì setOnClickListener chỉ thực hiện một lần
        * trong khối init của SongViewHolder, nói cách khác, việc THIẾT LẬP LISTENER (tức là tạo ra đối tượng
        * OnClickListener và gán nó vào itemView) chỉ xảy ra một lần cho mỗi đối tượng ViewHolder
        *
        * Listener đó sẽ được kích hoạt/thực thi nhiều lần mỗi khi user click vào itemView tương ứng.
        * Khởi tạo (setOnClickListener) --> thực hiện một lần
        * Kich hoạt (OnClickListener) --> có thể thực hiện nhiều lần bởi user
        */
        init {
            itemView.setOnClickListener {
                // Lấy vị trí item hiện tại item hiện tại
                /*
                * bindingAdapterPosition là một thuộc tính của lớp RecyclerView.ViewHolder --> trả về vị trí của item
                * mà ViewHolder đó đang hiển thị, tính theo Adapter. Ví dụ, nếu ViewHolder đang hiển thị bài hát thứ 3 trong
                * danh sách songs, bindingAdapterPosition sẽ trả về 2 (vì index bắt đầu từ 0)
                *
                * dùng bindingAdapterPosition an toàn vì: (cung cấp sự đảm bảo tốt hơn về tính nhất quán của dữ liệu)
                *   1. Vị trí này luôn phản ánh đúng vị trí của dữ liệu trong Adapter tại thời điểm gần nhất mà ViewHolder được gắn (bind) dữ liệu.
                *   2. Xử lý các thay đổi động: Khi ta thêm, xóa, sắp xếp lại các item trong RecyclerView, vị trí tuyệt đối của một ViewHolder trên màn hình có thể thay đổi,
                * nhưng dữ liệu nó đang hiển thị vẫn giữ nguyên. "bindingAdapterPosition" sẽ luôn cập nhật đúng vị tr tương ugnứ trong danh sách dữ liệu của Adapter.
                *   3. Xử lý trường hợp ViewHolder không còn được "bind": Nếu ViewHolder đã bị gỡ bỏ khỏi RecyclerView (ví dụ, itemdđã bị xóa nhưng animation xóa
                * vẫn đang chạy), việc truy cập vị trí của nó có thể gây lỗi. Trong TH này, bindingAdapterPosition s trả về RecyclerView.NO_POSITION (-1),
                * giúp ta kiểm tra và tránh được các lỗi IndexOutOfBoundsException
                 */
                val position = bindingAdapterPosition

                /*
                *   RecyclerView.NO_POSITION là một hằng số (constant) trong lớp RecyclerView
                *   Trả về: -1
                *   Được dùng như một giá trị flag để chỉ ra rằng ViewHolder ko tương ứng với bất kỳ vị tr hợp lệ nào trong Adapter. Xảy ra trong 3 TH sau:
                *       i. ViewHolder vừa được tạo nhưng chưa được bind (gắn) với bất kì dữ liệu nào
                *       ii. item tương ứng với ViewHolder đó vừa bị xóa khỏi Adapter, nhưng ViewHolder vẫn chauw được tái sử dụng (recycle)
                *       iii. Dữ liệu của Adapter đang trong quá trình thay đổi và vị trí chưa được xác định
                *
                *  ==> Câu lệnh kiểm tra này RẤT QUAN TRỌNG
                */
                // Kiểm tra xem vị trí có hợp lệ hay không
                if (position != RecyclerView.NO_POSITION) {
                    // Lấy đối tượng SongModel tại vị trí hiện tại
                    // trực tiếp từ danh sách 'songs' của Adapter
                    val song = songs[position] // Nếu không có dòng if này, chương trình sẽ bị crash ở đây nếu rơi vào các TH kể trên.

                    // hiển thị Toast
                    Toast.makeText(
                        context, // xài context được truyền vào Adapter
                        song.mTitle,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        fun bind(song: SongModel) {
            titleTextView.text = song.mTitle
            artistTextView.text = song.mArtist
            codeTextView.text = song.mCode
            lyricTextView.text = song.mLyric
        }
    }

    /* Trong Adapter RecyclerView có 3 phương thức bắt buộc cần phải triển khai (implementation) từ abstract class bao gồm:
    * 1. onCreateViewHolder(parent, viewType)
    * 2. onBindViewHolder(holder, position)
    * 3. getItemCount()
    */

    // hàm này tạo view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.row_item_song,
            parent,
            false
        )

        return SongViewHolder(view)
    }

    // Hàm này dùng để gán dữ liệu
    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        // Lấy đối tượng SongModel tại vị trí hiện tại và gọi bind() của ViewHoldẻ
        val song = songs[position]
        holder.bind(song)
    }

    // Hàm này trả về tổng số item
    override fun getItemCount() = songs.size
}