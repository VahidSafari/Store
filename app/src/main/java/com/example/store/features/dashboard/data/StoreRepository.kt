package com.example.store.features.dashboard.data

import com.example.store.core.api.NetworkHandler
import com.example.store.core.api.Result
import com.example.store.features.dashboard.ui.OffPrice
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class StoreRepository @Inject constructor(
    private val storeRemoteDataSource: StoreRemoteDataSource,
    private val storeLocalDataSource: StoreLocalDataSource,
    private val networkHandler: NetworkHandler
) {

    suspend fun populateDataBase() {
        val topSliderList = listOf(
            TopSliderEntity(
                1,
                "https://mootanroo.com/sites/default/files/users/user167479/slaild-farben-desktop.jpg"
            ),
            TopSliderEntity(
                2,
                "https://mootanroo.com/sites/default/files/users/user167479/slide-vichy-desktop.jpg"
            ),
            TopSliderEntity(
                3,
                "https://mootanroo.com/sites/default/files/users/user167479/slide-my-desktop.jpg"
            )
        )
        val categories = listOf(
            PiecesDto(
                CategoryEntity(
                    1,
                    "پیشنهادهای ویژه"
                ),
                listOf(
                    PieceEntity(
                        1,
                        "کفش عالی",
                        "http://www.pngall.com/wp-content/uploads/2016/03/Shoes-Free-Download-PNG.png",
                        40000,
                        20000,
                        12,
                        50000,
                        40000,
                        1
                    ),
                    PieceEntity(
                        2,
                        "کفش خوب",
                        "http://pluspng.com/img-png/shoes-png-sneaker-png-transparent-image-2500.png",
                        10000,
                        30000,
                        12,
                        60000,
                        45000,
                        1
                    ),
                    PieceEntity(
                        3,
                        "کفش بی نظیر",
                        "http://www.pngall.com/wp-content/uploads/2016/03/Shoes-Free-Download-PNG.png",
                        40000,
                        20000,
                        12,
                        50000,
                        40000,
                        1
                    )
                )
            ),
            PiecesDto(
                CategoryEntity(
                    2,
                    "پرفروش ها"
                ),
                listOf(
                    PieceEntity(
                        1,
                        "ویلا",
                        "http://pluspng.com/img-png/shirt-hd-png-dress-shirt-png-hd-png-image-480.png",
                        6500000,
                        10000000,
                        3,
                        500000,
                        400000,
                        2
                    ),
                    PieceEntity(
                        2,
                        "پنیر تازه",
                        "https://www.freepngimg.com/thumb/cheese/10-cheese-png-image-thumb.png",
                        99000,
                        120000,
                        12,
                        60000,
                        45000,
                        2
                    ),
                    PieceEntity(
                        3,
                        "کفش بی نظیر",
                        "http://pluspng.com/img-png/shoes-png-sneaker-png-transparent-image-2500.png",
                        21100,
                        24000,
                        12,
                        23000,
                        22000,
                        2
                    ),
                    PieceEntity(
                        4,
                        "marvelous shoes",
                        "https://demo.accesspressthemes.com/wordpress-plugins/wp-popup-banners-pro/wp-content/uploads/2017/04/shoes.png",
                        2000,
                        3000,
                        3,
                        2500,
                        2200,
                        2
                    ),
                    PieceEntity(
                        5,
                        "good shoes",
                        "http://pluspng.com/img-png/shoes-png-sneaker-png-transparent-image-2500.png",
                        40000,
                        50000,
                        10,
                        36000,
                        45000,
                        2
                    ),
                    PieceEntity(
                        6,
                        "shoes",
                        "http://www.pngall.com/wp-content/uploads/2016/03/Shoes-Free-Download-PNG.png",
                        53000,
                        68000,
                        20,
                        60000,
                        58000,
                        2
                    )
                )
            ),
            PiecesDto(
                CategoryEntity(
                    3,
                    "جدیدترین ها"
                ),
                listOf(
                    PieceEntity(
                        1,
                        "خانه ی بزرگ",
                        "https://www.housingpedia.com/wp-content/uploads/house-flip.png",
                        400000,
                        500000,
                        21,
                        32000,
                        30000,
                        3
                    ),
                    PieceEntity(
                        2,
                        "کفش مارک",
                        "https://demo.accesspressthemes.com/wordpress-plugins/wp-popup-banners-pro/wp-content/uploads/2017/04/shoes.png",
                        912000,
                        930000,
                        9,
                        100000,
                        80000,
                        3
                    ),
                    PieceEntity(
                        3,
                        " کفش بی نظیر",
                        "http://pluspng.com/img-png/shoes-png-sneaker-png-transparent-image-2500.png",
                        1200000,
                        1300000,
                        1,
                        130000,
                        125000,
                        3
                    ),
                    PieceEntity(
                        4,
                        "کفش ارزان",
                        "https://demo.accesspressthemes.com/wordpress-plugins/wp-popup-banners-pro/wp-content/uploads/2017/04/shoes.png",
                        2000,
                        3000,
                        3,
                        2500,
                        2200,
                        3
                    ),
                    PieceEntity(
                        5,
                        "کفش ورزشی",
                        "http://pluspng.com/img-png/shoes-png-sneaker-png-transparent-image-2500.png",
                        40000,
                        50000,
                        10,
                        36000,
                        45000,
                        3
                    )
                )
            )
        )
        storeLocalDataSource.insertTopSliderItems(topSliderList)
        storeLocalDataSource.insertCategories(categories)
    }


    suspend fun getStoreInfo(): Result<Pair<List<TopSliderEntity>, List<PiecesDto>>>? {

        var result: Result<Pair<List<TopSliderEntity>, List<PiecesDto>>>? = null
        if (networkHandler.hasNetworkConnection()) {
//            when (val response = storeRemoteDataSource.getStoreInformation()) {
//                is Result.Success -> {
//                    val topSliderItems = response.data.data.slider
//                    val bestSellerPieces = response.data.data.best_sellers
//                    val mostViewedPieces = response.data.data.most_viewed
//                    val specialOfferPieces = response.data.data.special_offers
//                    val newestPieces = response.data.data.newest
//                    storeLocalDataSource.insertTopSliderItems(
//                        topSliderItems.map { it.toTopSliderEntity() }
//                    )
//                    storeLocalDataSource.insertCategories(
//                        listOf(
//                            PiecesDto(CategoryEntity(1, "پیشنهاد ویژه"),
//                                specialOfferPieces.map { it.toPieceEntity(1) }
//                            ), PiecesDto(CategoryEntity(2, "پرفروش"),
//                                bestSellerPieces.map { it.toPieceEntity(2) }
//                            ), PiecesDto(CategoryEntity(3, "پر بازدید"),
//                                mostViewedPieces.map { it.toPieceEntity(3) }
//                            ), PiecesDto(CategoryEntity(4, "جدید"),
//                                newestPieces.map { it.toPieceEntity(4) }
//                            )
//                        )
//                    )
//
//                    val resultPair = Pair(
//                        storeLocalDataSource.getTopSliderItems(),
//                        storeLocalDataSource.getAllCategories()
//                    )
//                    result = Result.Success(resultPair)
//                }
//                is Result.Error -> {
            val topSliderItems = storeLocalDataSource.getTopSliderItems()
            val categories = storeLocalDataSource.getAllCategories()
            result = Result.Error(
//                        response.message
                "offline mode",
                Pair(
                    topSliderItems,
                    categories
                )
            )
//                }
//            }
        } else {
            result = Result.Error(
                "Network problem",
                Pair(
                    storeLocalDataSource.getTopSliderItems(),
                    storeLocalDataSource.getAllCategories()
                )
            )
        }
        return result
    }

    suspend fun search(searchPhrase: String) = storeLocalDataSource.search(searchPhrase)

}