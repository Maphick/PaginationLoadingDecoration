package com.makashovadev.a23_4

import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.makashovadev.a23_4.adapter.ProductAdapter
import com.makashovadev.a23_4.databinding.ActivityMainBinding
import com.makashovadev.a23_4.decoration.PaginationLoadingDecoration
import com.makashovadev.a23_4.item.Ad
import com.makashovadev.a23_4.item.Countable
import com.makashovadev.a23_4.item.Item
import com.makashovadev.a23_4.item.Product

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var itemIndex: TextView
    private lateinit var add: Button
    private lateinit var remove: Button
    private lateinit var change: Button
    private lateinit var up: ImageView
    private lateinit var save: ImageView
    private lateinit var down: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Init()
    }


    fun Init() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        itemIndex = binding.itemIndex
        add = binding.add
        remove = binding.remove
        change = binding.change
        RecyclerViewInit()
        RecyclerViewInitAdapter()
        recyclerView.addItemDecoration(PaginationLoadingDecoration())
        RecyclerViewScroll()
        RecyclerViewSetScroollListener()

        /*add.setOnClickListener {
            val newList = arrayListOf<Product>()
            newList.addAll(adapter.data)
            newList.add(getIndex(), Product(getIndex(), R.drawable.ic_orange, "Orange", "Orange juice is widely used as a drink in restaurants and cafes."))
            updateData(newList)
            //adapter.data.add(getIndex(), Product(getIndex(), R.drawable.ic_orange, "Orange", "Orange juice is widely used as a drink in restaurants and cafes."))
            //adapter.notifyItemInserted(getIndex())
        }
        remove.setOnClickListener {
            val newList = arrayListOf<Product>()
            newList.addAll(adapter.data)
            newList.removeAt(getIndex())
            updateData(newList)
            //adapter.data.removeAt(getIndex())
            // adapter.notifyItemRemoved(getIndex())
        }
        change.setOnClickListener{
            val newList = arrayListOf<Product>()
            newList.addAll(adapter.data)
            newList[getIndex()] =  Product(getIndex(), R.drawable.ic_pear, "Pear", "Under favorable conditions, the pear reaches a large size-up to 5-25 meters in height and 5 meters in diameter of the crown.")
            updateData(newList)
            //adapter.data[getIndex()] =  Product(getIndex(), R.drawable.ic_pear, "Pear", "Under favorable conditions, the pear reaches a large size-up to 5-25 meters in height and 5 meters in diameter of the crown.")
            //adapter.notifyItemChanged(getIndex())
        }*/
    }

    fun RecyclerViewInit() {
        recyclerView = binding.recyclerView
        up = binding.up
        save = binding.save
        down = binding.down
        adapter = ProductAdapter()
        recyclerView.adapter = adapter
    }

    fun RecyclerViewInitAdapter() {
        adapter.items = arrayListOf(
            Product(
                0,
                R.drawable.ic_apple,
                "Apple",
                "Juicy Apple fruit, which is eaten fresh, serves as a raw material in cooking and for making drinks."
            ),
            Countable(
                1,
                R.drawable.ic_strawberry,
                "12",
                "Strawberry",
                "A perennial herbaceous plant 5-20 cm high, with a thick brown rhizome. \"Mustache\" is short. The stem is thin."
            ),
            Ad("Акция", "Скидка 30% на все яблоки"),
            Product(
                2,
                R.drawable.ic_banana,
                "Banana",
                "It is one of the oldest food crops, and for tropical countries it is the most important food plant and the main export item."
            ),
            Countable(
                3,
                R.drawable.ic_lemon,
                "4",
                "Lemon",
                "Lemons are eaten fresh, and are also used in the manufacture of confectionery and soft drinks, in the liquor and perfume industry."
            ),
            Product(
                4,
                R.drawable.ic_lemon,
                "Lemon",
                "Lemons are eaten fresh, and are also used in the manufacture of confectionery and soft drinks, in the liquor and perfume industry."
            ),
            Ad("Реклама", "Реклама от партнеров"),
            Product(
                5,
                R.drawable.ic_pear,
                "Pear",
                "Under favorable conditions, the pear reaches a large size-up to 5-25 meters in height and 5 meters in diameter of the crown."
            ),

            Product(
                6,
                R.drawable.ic_strawberry,
                "Strawberry",
                "A perennial herbaceous plant 5-20 cm high, with a thick brown rhizome. \"Mustache\" is short. The stem is thin."
            ),
            Product(
                7,
                R.drawable.ic_orange,
                "Orange",
                "Orange juice is widely used as a drink in restaurants and cafes."
            ),
            Product(
                0,
                R.drawable.ic_apple,
                "Apple",
                "Juicy Apple fruit, which is eaten fresh, serves as a raw material in cooking and for making drinks."
            ),

            Product(
                0,
                R.drawable.ic_apple,
                "Apple",
                "Juicy Apple fruit, which is eaten fresh, serves as a raw material in cooking and for making drinks."
            )
        )
        recyclerView.adapter = adapter
    }

    fun RecyclerViewScroll() {
        var savePositionFirst = 0
        var savePositionLast = 0

        fun savePosition() {
            savePositionFirst =
                (recyclerView.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
            savePositionLast =
                (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
        }

        fun scrollToStart() {
            recyclerView.scrollToPosition(0)
        }

        fun scrollToSaveStartPosition() {
            recyclerView.scrollToPosition(savePositionFirst)
        }

        fun scrollToSaveLastPosition() {
            recyclerView.scrollToPosition(savePositionLast)
        }

        fun scrollToEnd() {
            recyclerView.scrollToPosition(adapter.itemCount - 1)
        }

        // Иконка Up должна пролистывать список в самое начало, а если список уже вначале,
        // то возвращать к сохраненной позиции.
        up.setOnClickListener {
            if ((recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition() == 0) {
                scrollToSaveLastPosition()
            } else {
                scrollToStart()
            }
        }

        save.setOnClickListener {
            savePosition()
        }

        down.setOnClickListener {
            if ((recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() == adapter.itemCount - 1) {
                scrollToSaveStartPosition()
            } else {
                scrollToEnd()
            }
        }
    }

    fun RecyclerViewSetScroollListener() {
        var isLoading = false
        val scrollListener = object : RecyclerView.OnScrollListener() {
            @Override
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as RecyclerView.LayoutManager
                //смотрим сколько элементов на экране
                val visibleItemCount: Int = layoutManager.childCount
                //сколько всего элементов
                val totalItemCount: Int = layoutManager.itemCount

                //какая позиция первого элемента
                val firstVisibleItems =
                    (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                //проверяем, грузим мы что-то или нет
                if (!isLoading) {
                    if (visibleItemCount + firstVisibleItems >= totalItemCount) {
                        //ставим флаг, что мы попросили еще элементы
                        isLoading = true
                        //Вызывает загрузку данных в RecyclerView
                        downloadNewData()
                        isLoading = false
                    }
                }
            }
        }
        recyclerView.setOnScrollListener(scrollListener)
    }


    fun downloadNewData() {
        val addedList = arrayListOf(
            Ad("Акция", "Скидка на бананы 15%"),
            Product(
                1,
                R.drawable.ic_banana,
                "Banana",
                "It is one of the oldest food crops, and for tropical countries it is the most important food plant and the main export item."
            ),
            Product(
                2,
                R.drawable.ic_lemon,
                "Lemon",
                "Lemons are eaten fresh, and are also used in the manufacture of confectionery and soft drinks, in the liquor and perfume industry."
            )
        )

        val newData = arrayListOf(
            Ad("Акция", "Скидка на бананы 15%"),
            Product(
                1,
                R.drawable.ic_banana,
                "Banana",
                "It is one of the oldest food crops, and for tropical countries it is the most important food plant and the main export item."
            ),
            Product(
                2,
                R.drawable.ic_lemon,
                "Lemon",
                "Lemons are eaten fresh, and are also used in the manufacture of confectionery and soft drinks, in the liquor and perfume industry."
            ),
            Product(
                3,
                R.drawable.ic_pear,
                "Pear",
                "Under favorable conditions, the pear reaches a large size-up to 5-25 meters in height and 5 meters in diameter of the crown."
            ),
            Product(
                4,
                R.drawable.ic_strawberry,
                "Strawberry",
                "A perennial herbaceous plant 5-20 cm high, with a thick brown rhizome. \"Mustache\" is short. The stem is thin."
            ),
            Product(
                5,
                R.drawable.ic_orange,
                "Orange",
                "Orange juice is widely used as a drink in restaurants and cafes."
            ),
            Product(
                6,
                R.drawable.ic_banana,
                "Banana",
                "It is one of the oldest food crops, and for tropical countries it is the most important food plant and the main export item."
            ),
            Product(
                7,
                R.drawable.ic_banana,
                "Banana",
                "It is one of the oldest food crops, and for tropical countries it is the most important food plant and the main export item."
            ),
            Product(
                8,
                R.drawable.ic_banana,
                "Banana",
                "It is one of the oldest food crops, and for tropical countries it is the most important food plant and the main export item."
            ),
            Product(
                9,
                R.drawable.ic_banana,
                "Banana",
                "It is one of the oldest food crops, and for tropical countries it is the most important food plant and the main export item."
            ),
            Product(
                10,
                R.drawable.ic_banana,
                "Banana",
                "It is one of the oldest food crops, and for tropical countries it is the most important food plant and the main export item."
            ),
            Product(
                11,
                R.drawable.ic_lemon,
                "Lemon",
                "Lemons are eaten fresh, and are also used in the manufacture of confectionery and soft drinks, in the liquor and perfume industry."
            ),
            Product(
                12,
                R.drawable.ic_lemon,
                "Lemon",
                "Lemons are eaten fresh, and are also used in the manufacture of confectionery and soft drinks, in the liquor and perfume industry."
            ),
            Product(
                13,
                R.drawable.ic_lemon,
                "Lemon",
                "Lemons are eaten fresh, and are also used in the manufacture of confectionery and soft drinks, in the liquor and perfume industry."
            )
        )

        val newList = arrayListOf<Item>()
        adapter.items?.let { newList.addAll(it) }
        newList.addAll(addedList)
        updateData(newList)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: ArrayList<Item>) {
        val oldList: ArrayList<Item> = adapter.items as ArrayList<Item>
        val diff = ProductDiff(oldList, newList)
        val diffResult = DiffUtil.calculateDiff(diff)
        adapter.items = newList
        diffResult.dispatchUpdatesTo(adapter)
    }


}