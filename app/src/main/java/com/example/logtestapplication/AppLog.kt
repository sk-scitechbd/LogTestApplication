package com.example.logtestapplication

//arrayListOf(ActivityAction.InitAction().also { it.data = "init" })
data class AppLogModel(
    val userID: String = "",
    var actionsList: ArrayList<ActivityAction>,
)

/*sealed class ActivityLogModel(activityClass: String, actions: ArrayList<ActivityAction>) {
    val activity: String = activityClass
    var action: ArrayList<ActivityAction> = actions
}*/

sealed class ActivityAction(val action: String, val time: Long, data: Any?) {
    class InitAction(private val data: Any? = null) : ActivityAction(IdleAction::class.java.simpleName, System.currentTimeMillis(), data)
    class IdleAction(private val data: Any? = null) : ActivityAction(IdleAction::class.java.simpleName, System.currentTimeMillis(), data)
    class EditAction(private val data: Any? = null) : ActivityAction(EditAction::class.java.simpleName, System.currentTimeMillis(), data)
    class AddNewAction(private val data: Any? = null) : ActivityAction(AddNewAction::class.java.simpleName, System.currentTimeMillis(), data)
    class DeleteAction(private val data: Any? = null) : ActivityAction(DeleteAction::class.java.simpleName, System.currentTimeMillis(), data)
    class SwapAction(private val data: Any? = null) : ActivityAction(SwapAction::class.java.simpleName, System.currentTimeMillis(), data)
    class DoneAction(private val data: Any? = null) : ActivityAction(DoneAction::class.java.simpleName, System.currentTimeMillis(), data)
    class SendAction(private val data: Any? = null) : ActivityAction(SendAction::class.java.simpleName, System.currentTimeMillis(), data)
    class ShareAction(private val data: Any? = null) : ActivityAction(ShareAction::class.java.simpleName, System.currentTimeMillis(), data)
    class CloneAction(private val data: Any? = null) : ActivityAction(CloneAction::class.java.simpleName, System.currentTimeMillis(), data)
    class ColorPickAction(private val data: Any? = null) : ActivityAction(ColorPickAction::class.java.simpleName, System.currentTimeMillis(), data)
}

data class UserModel(val id: Long = System.currentTimeMillis(), var name: String = "a")