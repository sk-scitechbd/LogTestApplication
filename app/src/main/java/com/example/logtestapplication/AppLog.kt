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

sealed class ActivityAction(val action: String, val time: Long, var data: String?) {
    class InitAction : ActivityAction(IdleAction::class.java.simpleName, System.currentTimeMillis(), "init")
    class IdleAction : ActivityAction(IdleAction::class.java.simpleName, System.currentTimeMillis(), "idle")
    class EditAction : ActivityAction(EditAction::class.java.simpleName, System.currentTimeMillis(), "edited")
    class AddNewAction : ActivityAction(AddNewAction::class.java.simpleName, System.currentTimeMillis(), "added new")
    class DeleteAction : ActivityAction(DeleteAction::class.java.simpleName, System.currentTimeMillis(), "deleted")
    class SwapAction : ActivityAction(SwapAction::class.java.simpleName, System.currentTimeMillis(), "swapped")
    class DoneAction : ActivityAction(DoneAction::class.java.simpleName, System.currentTimeMillis(), "done")
    class SendAction : ActivityAction(SendAction::class.java.simpleName, System.currentTimeMillis(), "send")
    class ShareAction : ActivityAction(ShareAction::class.java.simpleName, System.currentTimeMillis(), "shared")
    class CloneAction : ActivityAction(CloneAction::class.java.simpleName, System.currentTimeMillis(), "cloned")
    class ColorPickAction : ActivityAction(ColorPickAction::class.java.simpleName, System.currentTimeMillis(), "myData")
}