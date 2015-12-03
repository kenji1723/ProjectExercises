package jp.ac.kcg.projectexercises.twitter.client

import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import jp.ac.kcg.projectexercises.R
import jp.ac.kcg.projectexercises.activites.ApplicationActivity
import jp.ac.kcg.projectexercises.activites.DialogOwner
import jp.ac.kcg.projectexercises.imageview.SmartImageView

import kotlinx.android.synthetic.item_clientuser.view.*

/**
 */
final class ClientUserListAdapter(context: Context, resource: Int, objects: List<ClientUser>) : ArrayAdapter<ClientUser>(context, resource, objects) {

    private val layoutInflater: LayoutInflater
    private var isListDialogAdapter = false

    init {
        if (context !is ApplicationActivity) {
            throw IllegalStateException("contextがNumeriActivityを継承していません")
        }
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    constructor(context: Context, resource: Int, objects: List<ClientUser>, isListDialogAdapter: Boolean) : this(context, resource, objects) {
        this.isListDialogAdapter = isListDialogAdapter
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val clientUser = getItem(position)
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_clientuser, null)
        }

        view!!.user_name_text.text = clientUser.name

        view.screen_name_text.text = clientUser.screenName

        val biggerProfileImageUrl = clientUser.biggerProfileImageUrl
        view.icon_image.setImage(true, SmartImageView.ProgressType.LOAD_ICON, biggerProfileImageUrl)

        view.delete_button.isFocusable = false
        if (isListDialogAdapter) {
            view.delete_button.visibility = View.GONE
            return view
        }
        view.delete_button.setImageDrawable(context.resources.getDrawable(R.drawable.ic_delete_black_24dp))
        view.delete_button.setOnClickListener { v ->
            val alertDialog = AlertDialog.Builder(context)
                    .setMessage(context.resources.getString(R.string.dialog_message_confirmation_delete_user))
                    .setPositiveButton(context.resources.getString(R.string.yes)) { dialog, which ->
                        ClientUsers.instance.deleteClientUser(clientUser)
                    }.setNegativeButton(context.resources.getString(R.string.cancel), null)
                    .create()
            (context as DialogOwner).showDialog(alertDialog)
        }
        return view
    }
}
