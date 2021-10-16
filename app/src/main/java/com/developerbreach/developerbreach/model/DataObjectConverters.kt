package com.developerbreach.developerbreach.model

//data class ArticleNetwork(
//    val id: Int,
//    val articleId: Int,
//    val name: String,
//    val banner: String,
//    val postedDate: String,
//    val urlLink: String,
//    val excerpt: String
//) {
//    /**
//     * Allows the RecyclerView to determine which items have changed when the list of [Article]
//     * has been updated.
//     */
//    companion object DiffCallback : DiffUtil.ItemCallback<ArticleNetwork>() {
//
//        override fun areItemsTheSame(
//            oldItem: ArticleNetwork,
//            newItem: ArticleNetwork
//        ): Boolean {
//            return oldItem === newItem
//        }
//
//        override fun areContentsTheSame(
//            oldItem: ArticleNetwork,
//            newItem: ArticleNetwork
//        ): Boolean {
//            return oldItem.id == newItem.id
//        }
//    }
//}