package andrey.elin.githubclient.ui.adapter

import andrey.elin.githubclient.R
import andrey.elin.githubclient.mvp.model.image.IImageLoader
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.view.*
import andrey.elin.githubclient.mvp.presenter.list.IUserListPresenter
import andrey.elin.githubclient.mvp.view.list.UserItemView
import android.widget.ImageView
import javax.inject.Inject

class UsersRVAdapter(val presenter : IUserListPresenter) : RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    @Inject
    lateinit var imageLoader : IImageLoader<ImageView>

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer, UserItemView {
        override var pos = -1

        override fun setLogin(text: String) = with(containerView) {
            tv_login.text = text
        }

        override fun loadAvatar(url: String) = with(containerView) {
            imageLoader.loadInto(url, iv_avatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        presenter.bindView(holder)
    }
}