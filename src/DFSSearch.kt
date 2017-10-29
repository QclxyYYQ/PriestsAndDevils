import java.util.*

val s = Stack<State>()
fun search() {
    val actionList = LinkedList<Action>()
    val stateList = LinkedList<State>()
    stateList.add(initState)
    search(stateList.clone() as LinkedList<State>, actionList = actionList.clone() as LinkedList<Action>)
}

var countSolve = 0

private fun search(stateList: LinkedList<State>, actionList: LinkedList<Action>) {

    val state = stateList.last
//    println("开始 $state")

    for (action in Action.values()) {
        val s = state.copy()
        val a = actionList.clone() as LinkedList<Action>
        if (doAction(action, s)) {
//            println(action)
            if (!stateList.contains(s)) {
                a.addLast(action)
                stateList.addLast(s)
                if (checkFinish(s)) {
                    countSolve += 1
                    println("找到第 $countSolve 个解，步骤如下：")
                    a.forEach { println(it) }
                    return
                }
                search(stateList.clone() as LinkedList<State>, a)
            }
        }
    }
}